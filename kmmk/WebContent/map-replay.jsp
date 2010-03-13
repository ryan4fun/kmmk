<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<script type="text/javascript">
	var _oldLine = null;
	var _replayLine = null;
	var _replayLinePoints = null;
	var _replayInterval = null;
	var _endMarker = null;
	var _oldEndMarkerLatLng = null;
	var _stopMarkers = null;
	function ReplayControl() {}
	ReplayControl.prototype = new GControl();
	ReplayControl.prototype.initialize = function(mapObj) {
		var container = document.createElement("div");
    	$(container).addClass("mapLabel").addClass("mapLink").html("轨迹回放");
        GEvent.addDomListener(container, "click", function() {
       		if( _replayLine ){
       			clearInterval(_replayInterval);
       			_oldLine.show();
       			for(var i=0;i<_stopMarkers.length;i++){
       				_stopMarkers[i].show();
       			}
       			mapObj.removeOverlay(_replayLine);
           		_replayLine = null;
           		$(container).html("轨迹回放");
           		_endMarker.setLatLng(_oldEndMarkerLatLng);
           	} else {
           		_oldLine.hide();
           		_endMarker.setLatLng(_replayLinePoints[0]);
           		for(var i=0;i<_stopMarkers.length;i++){
           			_stopMarkers[i].hide();
       			}
           		_replayLine = new GPolyline(new Array(), "#00ff00", 6);
       	    	mapObj.addOverlay(_replayLine);
       	    	$(container).html("停止回放");
       	    	_replayLine.insertVertex(0, _replayLinePoints[0]);
       	    	_replayInterval = setInterval( function(){
       	    		if( _replayLinePoints && _replayLinePoints.length>_replayLine.getVertexCount() ){
	       	 			var latlng = _replayLinePoints[_replayLine.getVertexCount()];
	       	 			_replayLine.insertVertex(0, latlng);
	       	 			_endMarker.setLatLng(latlng);
	       	 			for(var i=0;i<_stopMarkers.length;i++){
	       	 				if( _stopMarkers[i].getLatLng().equals(latlng) )
	       	 					_stopMarkers[i].show();
	       	 			}
	       	 		} else {
	       	 			clearInterval(_replayInterval);
	       	 			_oldLine.show();
	       	 			for(var i=0;i<_stopMarkers.length;i++){
	       	 				_stopMarkers[i].show();
	       	 			}
	       	 			mapObj.removeOverlay(_replayLine);
	       	     		_replayLine = null;
	       	     		$(container).html("轨迹回放");
	       	     		_endMarker.setLatLng(_oldEndMarkerLatLng);
	       	 		}}, 1000 );
        	}
        });
        mapObj.getContainer().appendChild(container);
        return container;
    }
	ReplayControl.prototype.getDefaultPosition = function() {
      return new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(480, 7));
    }
	ReplayControl.prototype.initReplay = function( mapObj, replayLinePoints, oldLine, endMarker, stopMarkers ) {
		if(mapObj){
			if( replayLinePoints && replayLinePoints.length>0 && oldLine && endMarker ){
				mapObj.addOverlay(oldLine);
				_replayLinePoints = replayLinePoints;
				_oldLine = oldLine;
				_endMarker = endMarker;
				if( _endMarker )
					_oldEndMarkerLatLng = _endMarker.getLatLng();
				_stopMarkers = stopMarkers ? stopMarkers : new Array();
			} else {
				mapObj.removeControl(this);
			}
		}
	}

	function CheckPointControl() {}
	CheckPointControl.prototype = new GControl();
	CheckPointControl.prototype.isShow = false;
	CheckPointControl.prototype.segments = null;
	CheckPointControl.prototype.initialize = function(mapObj) {
    	var container = document.createElement("div");
    	var $controlDiv = $(container).width(80).append("<div>路线检查点</div>").children().addClass("mapLabel").addClass("mapLink");
    	var cpc = this;
    	
        GEvent.addDomListener($controlDiv[0], "click", function() {
        	var $checkBoxs = $controlDiv.nextAll("span").children("input");
        	if(cpc.isShow){
            	$controlDiv.nextAll().hide();
                for(var i=0;i<$checkBoxs.length;i++){
              		cpc.segments[$checkBoxs[i].id].line.hide();
                }
            } else {
            	if( cpc.segments==null ){
                	$.blockUI({
        				message : "<label style='height:100px;'>查询中请稍候...</label>"
        			});
                	$.ajax({
                		url: "mkgps.do",
                		data: {
                			action: "SegmentSearchAjaxAction"
                		},
                		dataType: "json",
                		cache: false,
                		async: false,
                		success: function(json) {
                			cpc.segments = json ? json : {};
                			for(var p in cpc.segments){
                				var segment = cpc.segments[p];
                				var points = new Array();
                				for(var j=0;j<segment.points.length;j++){
                					var p = segment.points[j];
                					if( p.lat && p.lng && p.lat>0 && p.lng>0 )
                						points.push(new GLatLng( Number(p.lat)+CN_OFFSET_LAT,Number(p.lng)+CN_OFFSET_LON ));
                				}
                				segment.line = new GPolyline(points, "#00ff00", 6);
                				mapObj.addOverlay(segment.line);

                				$controlDiv.after(
               						"<span style='color:#0000cc;background:white;white-space:nowrap' >" 
               						+ "<input type='button' id='"
               						+ segment.id 
               						+ "' value='检查' />"
                                   	+ segment.name 
                                   	+ "</span><br>");
                          		$controlDiv.next("span:first").children("input").click( function() {
                          			cpc.doCheck(cpc.segments[this.id]);
                          		}); 
                			}
                			$.unblockUI();
                		},
                		error: function() {
                			$.blockUI({
        						message : "<label>查询路线失败!</label>"
        					});
        					setTimeout('$.unblockUI()', 1000);
                		}
                	});
                }
            	$controlDiv.nextAll().show();
            	for(var i=0;i<$checkBoxs.length;i++){
              		cpc.segments[$checkBoxs[i].id].line.show();
                }
            }
            cpc.isShow = !cpc.isShow;
        });
        mapObj.getContainer().appendChild(container);
        return container;
    }
	CheckPointControl.prototype.getDefaultPosition = function() {
      return new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(560, 7));
    }
	CheckPointControl.prototype.doCheck = function(segment) {
		//public final static byte SEGMENT_DETAIL_TYPE_ROAD_POINT = 1;
		//public final static byte SEGMENT_DETAIL_TYPE_CHECK_POINT = 2;
		alert( this.$startDate.val() + this.$endDate.val() + this.$vehicleId.val() + segment.id );
	}
	CheckPointControl.prototype.initCheckPoint = function( $vehicleId, $startDate, $endDate ) {
		this.$vehicleId = $vehicleId;
		this.$startDate = $startDate;
		this.$endDate = $endDate;
	}
</script>


            