function buildContextMenu(obj) {
	var $obj = $(obj);
	if ($obj.attr("cmenu")) {
		var cmenu = $obj.attr("cmenu");
		$obj.addClass("cmVoice {cMenu:'contextMenuModal'}");
		$(document).buildContextualMenu( {
			menuWidth : 200,
			overflow : 2,
			menuSelector : ".menuContainer",
			iconPath : "images/menu/ico/",
			hasImages : true,
			fadeInTime : 100,
			fadeOutTime : 100,
			adjustLeft : 0,
			adjustTop : 0,
			opacity : .99,
			closeOnMouseOut : false,
			onContextualMenu : function(o,e) {				
				var modalStr = eval(cmenu+"(o,e)");
				$("#contextMenuModal").html(modalStr);
			},
			shadow : false
		});
	}
}

function treeContextMenu(obj, event) {
	var srcE = event.srcElement||event.target;
	var div = '';
	var subDiv = '';
	if(srcE.className.indexOf("TreeRow")==-1){
		div+='<a action="expandAll(true)">展开所有</a>';
		div+='<a action="expandAll(false)">收缩所有</a>';
	} else {
		 var checkedList = tree.getAllChecked();
		 var uncheckedList = tree.getAllUnchecked();
		 var partiallyList = tree.getAllPartiallyChecked();
		
		div+='<a disabled>'+srcE.innerHTML+'</a>';
		div+='<a rel="separator"></a>';
		
		if(currentId!=null){
			if(currentId.indexOf("v_")==0){
				if(role==1 || role==11){
					if(uncheckedList.indexOf(currentId)!=-1)
						div+='<a img="trace.gif" class="{action: \'startTrack()\'}">启用本车监控</a>';
					if(checkedList.indexOf(currentId)!=-1)
						div+='<a img="stop-trace.gif" class="{action: \'stopTrack()\'}">停止本车监控</a>';
					div+='<a img="frequence.gif" class="{action: \'setFrequence()\'}">设定本车采样频率</a>';
				}
				
				div+='<a img="hprio_tsk.gif" class="{menu:\'sub_menu\'}">增改限制区域</a>';
				//$("#tree-div").block({
				//	message : "<label>查询限制区域中！</label>"
				//});
				$.ajax({
					url : "mkgps.do",
					dataType : "json",
					data : {
						action : "LimitAreaSearchByVehicleAjaxAction",
						id: currentId
					},
					cache : false,
					async: false,
					success : function(json){
						if(json){
							for(var p in json){
		        				var limitArea = json[p];
		        				if(limitArea.opType){
//		        					public final static short RULE_OP_OBEY = 1;
//		        					public final static short RULE_OP_DISOBEY = 2;
		        					if( limitArea.opType==1 ){
		        						subDiv+='<a img="enabled_co(1).gif" class="{action: \'delLimitArea(' + limitArea.id + ')\'}">进入' + limitArea.name + '报警</a>';
		        						subDiv+='<a class="{action: \'addLimitArea(' + limitArea.id + ', false)\'}">离开' + limitArea.name + '报警</a>';
		        					} else {
		        						subDiv+='<a class="{action: \'addLimitArea(' + limitArea.id + ', true)\'}">进入' + limitArea.name + '报警</a>';
		        						subDiv+='<a img="enabled_co(1).gif" class="{action: \'delLimitArea(' + limitArea.id + ')\'}">离开' + limitArea.name + '报警</a>';
		        					}
		        				} else {
		        					subDiv+='<a class="{action: \'addLimitArea(' + limitArea.id + ', true)\'}">进入' + limitArea.name + '报警</a>';
		        					subDiv+='<a class="{action: \'addLimitArea(' + limitArea.id + ', false)\'}">离开' + limitArea.name + '报警</a>';
		        				}
							}
						}
					},
					error : function(xml, status, e){
						//$("#tree-div").block({
						//	message : "<label>查询限制区域失败！</label>"
						//});
					},
					complete : function(data){
						//setTimeout(function(){
						//	$("#tree-div").unblock();
						//}, 500);
					}
				});
			} else if(currentId.indexOf("o_")==0){
				if(role==1 || role==2){
					if(uncheckedList.indexOf(currentId)!=-1 || partiallyList.indexOf(currentId)!=-1)
						div+='<a img="trace.gif" class="{action: \'startTrack()\'}">启用单位所有车辆监控</a>';
					if(checkedList.indexOf(currentId)!=-1 || partiallyList.indexOf(currentId)!=-1)
						div+='<a img="stop-trace.gif" class="{action: \'stopTrack()\'}">停止单位所有车辆监控</a>';
					div+='<a img="frequence.gif" class="{action: \'setFrequence()\'}">批量设定单位车辆采样频率</a>';
				}
			} else if(currentId.indexOf("u_")==0){
				if(role==1 || role==11){
					if(uncheckedList.indexOf(currentId)!=-1 || partiallyList.indexOf(currentId)!=-1)
						div+='<a img="trace.gif" class="{action: \'startTrack()\'}">启用车主所有车辆监控</a>';
					if(checkedList.indexOf(currentId)!=-1 || partiallyList.indexOf(currentId)!=-1)
						div+='<a img="stop-trace.gif" class="{action: \'stopTrack()\'}">停止车主所有车辆监控</a>';
					div+='<a img="frequence.gif" class="{action: \'setFrequence()\'}">批量设定车主车辆采样频率</a>';
				}
			}
		}
		div+='<a img="delete.gif" class="{action: \'alert(1)\'}">删除</a>';
	}
	$("#sub_menu").html(subDiv);
	return div;
}

function startTrack(){
	changeTrackingState(currentId, true);
}

function stopTrack(){
	changeTrackingState(currentId, false);
}

function setFrequence(){
	var text = tree.getItemText(currentId);
	jPrompt("请输入新的采样频率（秒）", "60", text+" - 设定采样频率", function(value){
		if(!value) return;
		$.ajax( {
			url : "mkgps.do",
			data : {
				action : "SetFrequenceAjax",
				id : currentId,
				value : value
			},
			cache : false,
			success : function() {
				$("#tree-div").block( {
					message : "<label>设定成功</label>"
				});
			},
			error : function(xml, status, e) {
				$("#tree-div").block( {
					message : "<label style='color:red;'>设定失败" + e + "</label>"
				});
			},
			complete : function(data){
				setTimeout(function(){
					$("#tree-div").unblock();
				}, 2000);
			}
		});
	});
}

function addLimitArea( limitAreaId, isObey ){
	$.ajax({
		url : "mkgps.do",
		dataType : "json",
		data : {
			action : "AddLimitAreaAjax",
			id: currentId,
			limitAreaId: limitAreaId,
			isObey: isObey
		},
		cache : false,
		success : function(json){
			//$("#tree-div").block({
			//	message : "<label>添加限制区域成功！</label>"
			//});
		},
		error : function(xml, status, e){
			$("#tree-div").block({
				message : "<label style='color:red;'>添加限制区域失败！</label>"
			});
		},
		complete : function(data){
			setTimeout(function(){
				$("#tree-div").unblock();
			}, 2000);
		}
	});
}

function delLimitArea( limitAreaId ){
	$.ajax({
		url : "mkgps.do",
		dataType : "json",
		data : {
			action : "DelLimitAreaAjax",
			id: currentId,
			limitAreaId: limitAreaId
		},
		cache : false,
		success : function(json){
			$("#tree-div").block({
				message : "<label>取消限制区域成功！</label>"
			});
		},
		error : function(xml, status, e){
			$("#tree-div").block({
				message : "<label style='color:red;'>取消限制区域失败！</label>"
			});
		},
		complete : function(data){
			setTimeout(function(){
				$("#tree-div").unblock();
			}, 2000);
		}
	});
}
