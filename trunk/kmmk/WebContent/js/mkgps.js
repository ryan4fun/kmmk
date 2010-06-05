[].indexOf || (Array.prototype.indexOf = function(v){
	for(var i = this.length; i-- && this[i] !== v;);
		return i;
});


/*
window.onerror = function(e){	
	alert("页面错误：\r"+e+"\r请手动刷新页面！");
	if(window.event && window.event.retureValue){
		window.event.cancelDefault = true;
	}
	if(e && e.preventDefault){
		e.preventDefault();		
	}
	if (e && e.stopPropagation) {	
		e.stopPropagation();
	}
	return true;
}
*/
function openWindow(url, width, height, isCompact) {
	var iWidth = width ? width : 640;
	var iHeight = height ? height : 480;
	var iTop = (window.screen.height - iHeight) / 2;
	var iLeft = (window.screen.width - iWidth) / 2;
	var status = isCompact?
			"status=yes,scrollbars=no,toolbar=no,location=no,direction=no,resizable=yes,":
			"status=yes,scrollbars=yes,toolbar=yes,location=yes,direction=no,resizable=yes,";
	status+="width="+ iWidth+",height="+iHeight+",top="+iTop+",left="+iLeft;
	window.open(url,"",	status);
}

function href(url){
	window.location.href=url;
}

var divId;
function delSingleRec(actionName, recID) {
	var m;
	if (recID != null && recID != "") {
		m = '<p align="center">';
		m += '<input type="button" style="width:100px;" value="确定" onclick="$(divId).unblock();">';
		m += '</p>';
		divId = "#p_" + recID;
		$(divId).block( {
			message : "删除中..."
		});
		$.ajax( {
			url : "mkgps.do",
			data : {
				action : actionName,
				recID : recID
			},
			cache : false,
			success : function() {
				$(divId).block( {
					message : "<label>删除成功</label>"
				});
				setTimeout("$(divId).parent().remove()", 2000);
			},
			error : function(xml, status, e) {
				m = '<label>删除失败' + e + '</label>' + m;
				$(divId).block( {
					message : m
				});
			}
		});
	} else {
		m = '<label>无法删除该记录！</label>' + m;
		$(divId).block( {
			message : m
		});
	}
}

function buildQ(o) {
	var s = "";
	if (o && o.tagName) {
		if (o.tagName == "input" || o.tagName == "select") {
			s += o.name + ":" + o.value + ",";
		} else {
			var is = o.getElementsByTagName("input");
			for ( var i = 0; i < is.length; i++) {
				if (is[i].type
						&& (is[i].type == "submit" || is[i].type == "button" || is[i].type == "reset"))
					continue;
				if ((is[i].type == "radio" && is[i].checked == true)
						|| is[i].type != "radio")
					s += is[i].name + ":" + is[i].value + ",";
			}
			var ss = o.getElementsByTagName("select");
			for ( var i = 0; i < ss.length; i++) {
				s += ss[i].name + ":" + ss[i].value + ",";
			}
			var tas = o.getElementsByTagName("textarea");
			for ( var i = 0; i < tas.length; i++) {
				s += tas[i].name + ":" + tas[i].value + ",";
			}
		}
	}
	if (s.length > 0)
		s = s.substr(0, s.length - 1);
	return s;
}

Date.prototype.addDay = function(day) {
	var val = this.valueOf();
	val += day * 24 * 60 * 60 * 1000;
	return new Date(val);
}

Array.prototype.clear = function() {
	this.length = 0;
}
Array.prototype.insertAt = function(index, obj) {
	this.splice(index, 0, obj);
}
Array.prototype.removeAt = function(index) {
	this.splice(index, 1);
}
Array.prototype.remove = function(obj) {
	var index = this.indexOf(obj);
	if (index >= 0) {
		this.removeAt(index);
	}
}
if(!Array.prototype.indexOf){
	Array.prototype.indexOf = function(v){
		for(var i = this.length; i-- && this[i] !== v;);
		return i;
	}
}
function setCookie(name,value){
	if (document.cookie) {
		var exp  = new Date();
	    exp.setTime(exp.getTime() + 365*24*60*60*1000);
	    document.cookie = "mkgps_" + name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
}
function getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )"+"mkgps_" + name+"=([^;]*)(;|$)"));
    return (arr != null) ? unescape(arr[2]) : null;
}
function delCookie(name){
    var cval=getCookie(name);
    if(cval!=null){
    	var exp = new Date();
        exp.setTime(exp.getTime() - 1);
        document.cookie= "mkgps_" + name + "="+cval+";expires="+exp.toGMTString();
    }
}