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
</script>


            