<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="com.gps.util.Util,com.gps.servlet.MKgpsServlet,com.gps.bean.LoginInfo"
%>
<%
String mapImagePath = basePath;
if( login.getMapType()==LoginInfo.MAPABC ){
	mapImagePath = request.getScheme()+ "://116.52.2.210:" + request.getServerPort() + request.getContextPath() + "/";;
%>
	<%--
	//map abc test key
	<script src="http://app.mapabc.com/apis?&t=flashmap&v=2.2&key=f03346eb3a99be025979045e8fa1a281c5159611991d381a5492ce64662856281c00983d7cb0402b"></script>
	//http://www.kmmkgps.com:8080/ key
	--%>
	<script src="http://app.mapabc.com/apis?&t=flashmap&v=2.2&key=e8eb35afcc4c53bd5a251b4f4eec1f7142ad4efd42ee781bbfc9fcb590cb9e9a61d0b2fa6580678a"></script>
	<script type="text/javascript">
	function GBrowserIsCompatible(){
		return true;
	}
	function GUnload(){
	}
	function createCommonMap( mapDivID, option ){
		var mapoption = null;
		if( option ){
			mapoption = option;
		} else {
			var mapoption = new MMapOptions();
		    mapoption.toolbar=DEFAULT; //设置工具条
		    mapoption.toolbarPos=new MPoint(0,0);
		    mapoption.overviewMap =DEFAULT; //设置鹰眼
		    mapoption.returnCoordType=COORD_TYPE_OFFSET;
		    mapoption.isCongruence=true;
		    mapoption.hasDefaultMenu=true;
		}
	    return new MMap( mapDivID, mapoption );
	}
	function createCommonLatLng(lat,lng){
		return new MLngLat(lng, lat);
	}
	function createCommonMarker( LatLng, option, markerID ){
		var markerOption = null;
		if( option ){
			markerOption = option;
		} else {
			markerOption = new MMarkerOptions();
			markerOption.canShowTip = true;
			markerOption.isDraggable = false;
			markerOption.imageAlign=5;//设置图片锚点相对于图片的位置
			//markerOption.isDimorphic=true;//可选项，是否具有二态，默认为false即没有二态
			//markerOption.dimorphicColor=0x046788;
		}
		var marker = new MMarker( LatLng, markerOption );
		if(markerID)
			marker.id = markerID;
		else
			marker.id = "m"+Math.random();
		return marker;
	}
	</script> 
<%} else { %>
	<%--
	//google test key
	<script src="http://ditu.google.cn/maps?file=api&amp;v=2&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
	            type="text/javascript"></script>
	//http://coderrr.cn key
	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xTSmvGA0XCj36UHVVZ8iHCdzg4lexRN2Dl7iNTpsvYL1AS9L78Gia781Q" type="text/javascript"></script>
	
	<script src="http://ditu.google.cn/maps?file=api&amp;v=2&amp;key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xS2a6rLo0hCjJ1du1lHIflzOvlgCxT6Ft00YNQfpmf5rzuwyfEyLw7mAg"
	            type="text/javascript"></script>
	//http://www.kmmkgps.com:8080/ key
	
	//Google Global API
	<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA&hl=zh-CN" type="text/javascript"></script>
	
	//Google China API
	<script src="http://ditu.google.cn/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA&hl=zh-CN" type="text/javascript"></script>
	--%>
	<% if( login.getMapType()==LoginInfo.GOOGLE_MAP_CN ){ %>
		<script src="http://ditu.google.cn/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA&hl=zh-CN" type="text/javascript"></script>
		<script src="http://www.google.cn/uds/api?file=uds.js&v=1.s&key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA" type="text/javascript"></script>
	<% } else { %>
		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA&hl=zh-CN" type="text/javascript"></script>
		<script src="http://www.google.cn/uds/api?file=uds.js&v=1.s&key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA" type="text/javascript"></script>
	<% } %>
	<script type="text/javascript">
	var CN_OFFSET_LAT = <%=login.getMapType()==LoginInfo.GOOGLE_MAP_CN ? MKgpsServlet.mkgpsConf.getProperty("googleMap.cnOffsetLat") : 0%>;
	var CN_OFFSET_LON = <%=login.getMapType()==LoginInfo.GOOGLE_MAP_CN ? MKgpsServlet.mkgpsConf.getProperty("googleMap.cnOffsetLon") : 0%>;

	var START_ICON = "<%=mapImagePath %>images/google_icon/start.png";
	var STOP_ICON = "<%=mapImagePath %>images/google_icon/stop.png";
	var RUNNING_ICON = "<%=mapImagePath %>images/google_icon/running.png";
	var OFFLINE_ICON = "<%=mapImagePath %>images/google_icon/offline.png";
	var ALERT_ICON = "<%=mapImagePath %>images/google_icon/alert.png";

	function addVehicleMarker(mapObj, vs) {
		var html = "</b><br>车牌号: <b>" + vs.licensPadNumber + 
			"</b><br>自编号: <b>" + vs.internalNumber + 
			"</b><br>纬度: <b>" + vs.currentLat + 
			"</b><br>经度: <b>" + vs.currentLong + 
			"</b><br>行驶状态: <b>" + vs.isRunning + 
			"</b><br>在线状态: <b>" + vs.isOnline + 
			"</b><br>求救状态: <b>" + vs.isAskHelp + 
			"</b><br>限制区域报警: <b>" + vs.limitAreaAlarm + 
			"</b><br>超速报警: <b>" + vs.overSpeed + 
			"</b><br>疲劳驾驶: <b>" + vs.tireDrive + 
			"</b><br>当前速度: <b>" + vs.currentSpeed + 
			"</b><br>更新时间: <b>" + vs.lastUpdate;
		
			var marker = new DivImageMarker( new GLatLng( Number(vs.currentLat)+CN_OFFSET_LAT,Number(vs.currentLong)+CN_OFFSET_LON ), vs.licensPadNumber ,"<%=mapImagePath%>" + vs.alertIcon );
		    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
		    	mapObj.setCenter(latlng);
				marker.imgMarker_.openInfoWindowHtml(html);
			});
		    mapObj.addOverlay(marker);
		    return marker;
	}
	
	function createCommonMap( mapDivID, option ){
		if(option) {
			return new GMap2( document.getElementById(mapDivID), option );
		} else {
			var mapObj = new GMap2( document.getElementById(mapDivID) );
			mapObj.addControl(new GMapTypeControl());
		    mapObj.addControl(new GLargeMapControl());
		    mapObj.addControl(new MeasureDistanceControl());
		    mapObj.addControl(new MapSearcherControl());
		    mapObj.addControl(new LimitAreaControl());
		  	return mapObj;
		}
	}
	function createCommonLatLng(lat,lng){
		return new GLatLng(lat, lng);
	}
	function createCommonMarker( LatLng, option, markerID ){
		if(markerOption)
			return new GMarker( latlng, option );
		else
			return new GMarker( latlng );
	}

	function setCenterByLatLngs(mapObj, lat1, lng1, lat2, lng2){
		mapObj.setCenter(
				new GLatLng(
						(Number(lat1) + Number(lat2))/2,
						(Number(lng1) + Number(lng2))/2
						),
				mapObj.getBoundsZoomLevel(
						new GLatLngBounds(
								new GLatLng(Math.min(lat1,lat2), Math.min(lng1,lng2)),
								new GLatLng(Math.max(lat1,lat2), Math.max(lng1,lng2))
								)
						)
				);
	}
	
	function setCenterByMarkers(mapObj, markers){
		var minLat = <%=Util.MAX_LAT%>;
		var minLng = <%=Util.MAX_LON%>;
		var maxLat = <%=Util.MIN_LAT%>;
		var maxLng = <%=Util.MIN_LON%>;
		for ( var i = 0; i < markers.length; i++) {
			var m = markers[i];
			var lat = m.getLatLng().lat();
			var lng = m.getLatLng().lng();
			
			if( lat < minLat )
				minLat = lat;
			if( lat > maxLat )
				maxLat = lat;
			
			if( lng < minLng )
				minLng = lng;
			if( lng > maxLng )
				maxLng = lng;
		}
		setCenterByLatLngs(mapObj, maxLat, maxLng, minLat, minLng);
	}

	//controls
	function MeasureDistanceControl() {}
	MeasureDistanceControl.prototype = new GControl();
	MeasureDistanceControl.prototype.measureDistanceLine = null;
	MeasureDistanceControl.prototype.measureDistancePoints = null;
	MeasureDistanceControl.prototype.initialize = function(mapObj) {
    	var container = document.createElement("div");
    	var $controlDiv = $(container).width(160).append("<div>描点测距 / 油耗估算</div>").children();
    	$controlDiv.addClass("mapLabel").addClass("mapLink");
    	
        GEvent.addDomListener($controlDiv[0], "click", function() {
        	var mdc = this;
        	if( this.measureDistanceLine ){
        		GEvent.clearListeners(mapObj, "click");
        		mapObj.removeOverlay(this.measureDistanceLine);
        		for(var i=0; i<this.measureDistancePoints.length; i++){
        			mapObj.removeOverlay( this.measureDistancePoints[i] );
        		}
        		this.measureDistanceLine = null;
        		this.measureDistancePoints = null;
        		$controlDiv.html("描点测距 / 油耗估算").nextAll().remove();
        	} else {
        		this.measureDistancePoints = new Array();
        		this.measureDistanceLine = new GPolyline(new Array(), "#000066", 2);
      	    	mapObj.addOverlay(this.measureDistanceLine);
          	    GEvent.addListener(mapObj, "click", function(overlay, latlng) {
      				if(!overlay){
      					mdc.measureDistanceLine.insertVertex(0, latlng);
      					if(mdc.measureDistancePoints.length){
      						if( isNaN( $controlDiv.nextAll("input:first").val() ) )
      	              			$controlDiv.nextAll("span:last").html("0 升");
      	              		else
      							$controlDiv.nextAll("span:last").html( Math.round( $controlDiv.nextAll("input:first").val()*mdc.measureDistanceLine.getLength() /100000) + " 升");
      						mdc.measureDistancePoints.push(
    	      	      			new DivMarker(
    	  	      					latlng, 
    	  	      					Math.round( mdc.measureDistanceLine.getLength()/1000 ) + "公里"
    	  	      	      		)
        	  	      	    );
          				} else {
          					mdc.measureDistancePoints.push(
    	      	      			new DivMarker(
    	  	      					latlng,
    	  	      					"起点"
    	  	      	      		)
        	  	      	    );
              			}
      					mapObj.addOverlay( mdc.measureDistancePoints[mdc.measureDistancePoints.length-1] );
      				}
      		    });
          		$controlDiv.html("停止描点").after("<span style='color:#0000cc;background:white;' >百公里耗油量：</span><input type='text' maxlength=10 /><span style='color:#0000cc;background:white;' >升</span><br><span style='color:#0000cc;background:white;' >总&nbsp;&nbsp;耗&nbsp;&nbsp;油&nbsp;&nbsp;量&nbsp;&nbsp;：</span><span style='color:#0000cc;background:white;' >0 升</span>");
          		$controlDiv.nextAll("input:first").width(50).keyup( function() {
              		if( isNaN($(this).val()) )
              			$controlDiv.nextAll("span:last").html("0 升");
              		else
              			$controlDiv.nextAll("span:last").html(Math.round($(this).val()*mdc.measureDistanceLine.getLength()/100000) + " 升");
          		}); 
        	}
        });
        mapObj.getContainer().appendChild(container);
        return container;
    }
	MeasureDistanceControl.prototype.getDefaultPosition = function() {
		return new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(100, 7));
    }

	function MapSearcherControl() {}
	MapSearcherControl.prototype = new GControl();
	MapSearcherControl.prototype.localSearch = null;
	MapSearcherControl.prototype.searchResultPoints = null;
	MapSearcherControl.prototype.searchResultVehicles = null;
	MapSearcherControl.prototype.oldOverLays = null;
	MapSearcherControl.prototype.addoverlayListener = null;
	MapSearcherControl.prototype.removeoverlayListener = null;
	MapSearcherControl.prototype.initialize = function(mapObj) {
		var msc = this;
		this.mapObj = mapObj;
		var container = document.createElement("div");
    	var $controlDiv = $(container).width(80).append("<div>地图查询</div>").children();
    	$controlDiv.addClass("mapLabel").addClass("mapLink")
//    		.append("<img src='<%=mapImagePath%>images/google_icon/map_center.gif' width='16' height='16' style='positon:absolute' />").children("img:first")
//    		.css("top",mapObj.getSize().height/2-8)
//    		.css("left",mapObj.getSize().width/2-8)
    	;
    	this.oldOverLays = new Array();
    	this.addoverlayListener = GEvent.addListener(mapObj, "addoverlay", function(overlay) {
    		if( overlay instanceof GOverlay )
				msc.oldOverLays.push(overlay);
    	});
    	this.removeoverlayListener = GEvent.addListener(mapObj, "removeoverlay", function(overlay) {
    		if( overlay instanceof GOverlay ){
    			for(var i=0;i<msc.oldOverLays.length;i++){
    				if( msc.oldOverLays[i] == overlay ){
    					msc.oldOverLays.splice(i,1);
    					break;
    				}
        		}
    		}
    	});
    	
        GEvent.addDomListener($controlDiv[0], "click", function() {
        	if( msc.searchResultPoints ){
        		if(msc.oldOverLays && msc.oldOverLays.length>0){
        			for(var i=0;i<msc.oldOverLays.length;i++){
        				msc.oldOverLays[i].show();
        			}
        		}
        		msc.addoverlayListener = GEvent.addListener(mapObj, "addoverlay", function(overlay) {
        			if( overlay instanceof GOverlay )
        				msc.oldOverLays.push(overlay);
            	});
        		msc.removeoverlayListener = GEvent.addListener(mapObj, "removeoverlay", function(overlay) {
            		if( overlay instanceof GOverlay ){
            			for(var i=0;i<msc.oldOverLays.length;i++){
            				if( msc.oldOverLays[i] == overlay ){
            					msc.oldOverLays.splice(i,1);
            					break;
            				}
                		}
            		}
            	});
    	    	
        		for(var i=0; i<msc.searchResultPoints.length; i++){
        			mapObj.removeOverlay( msc.searchResultPoints[i] );
        		}
        		msc.searchResultPoints = null;
        		for(var i=0; i<msc.searchResultVehicles.length; i++){
        			mapObj.removeOverlay( msc.searchResultVehicles[i] );
        		}
        		msc.searchResultVehicles = null;
        		$controlDiv.html("地图查询").nextAll().hide();
        	} else {
        		if(msc.oldOverLays && msc.oldOverLays.length>0){
        			for(var i=0;i<msc.oldOverLays.length;i++){
        				msc.oldOverLays[i].hide();
        			}
        		}
        		GEvent.removeListener(msc.addoverlayListener);
        		GEvent.removeListener(msc.removeoverlayListener);
                		
        		msc.searchResultPoints = new Array();
        		msc.searchResultVehicles = new Array();
          		if( msc.localSearch == null ){
          			$controlDiv.html("停止查询").after("<div id='nameDiv' style='width:150px;'/><div id='searcherCanvas' style='width:150px;'/>");
          			
          			msc.$resultDiv = $controlDiv.nextAll("div:last");
          			
          			msc.localSearch = new google.search.LocalSearch();
          			msc.localSearch.setResultSetSize( google.search.Search.LARGE_RESULTSET );
          			msc.localSearch.setSearchCompleteCallback(msc, MapSearcherControl.prototype.OnLocalSearchComplete);
	
          			msc.searchForm = new google.search.SearchForm(false, $controlDiv.nextAll("div:first")[0]);
          			msc.searchForm.setOnSubmitCallback(msc, MapSearcherControl.prototype.OnSubmitCallback);
          			msc.searchForm.input.focus();
          		} else {
          			$controlDiv.html("停止查询").nextAll().show();
          		}
        	}
        });
        mapObj.getContainer().appendChild(container);
        return container;	    
	}
	MapSearcherControl.prototype.getDefaultPosition = function() {
		return new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(280, 7));
	}
	MapSearcherControl.prototype.OnLocalSearchComplete = function() {
		if ( this.localSearch.results==null || this.localSearch.results.length<1 ){
			this.$resultDiv.html("没有找到匹配结果，请修改关键字重新查询！");
			this.mapObj.setZoom(this.oldZoom);
			return;
		}
		for ( var i = 0; i < this.localSearch.results.length; i++) {
			var result = this.localSearch.results[i];
			if (result.GsearchResultClass == GlocalSearch.RESULT_CLASS) {
				var lat = parseFloat(result.lat);
				var lng = parseFloat(result.lng);
				this.searchResultPoints.push(this.creatLinkAndMarker( lat, lng, result ));
			}
		}
		setCenterByMarkers(this.mapObj, this.searchResultPoints);
	}
	MapSearcherControl.prototype.OnSubmitCallback = function(searchForm) {
		if( searchForm.input.value.length<1 )
            return false;
		
		this.$resultDiv.html("");
		this.mapObj.closeInfoWindow();
		for ( var i = 0; i < this.searchResultPoints.length; i++) {
			this.mapObj.removeOverlay(this.searchResultPoints[i]);
		}
		this.searchResultPoints = new Array();
		for ( var i = 0; i < this.searchResultVehicles.length; i++) {
			this.mapObj.removeOverlay(this.searchResultVehicles[i]);
		}
		this.searchResultVehicles = new Array();
		
		this.oldZoom = this.mapObj.getZoom();
		this.mapObj.setZoom(20);
		this.localSearch.setCenterPoint(this.mapObj);
		this.localSearch.execute(searchForm.input.value);
		return false;
	}
	MapSearcherControl.prototype.searchText = function(text) {
		if( text && text.length>0 ){
			this.searchForm.input.value = text;
			this.localSearch.execute(text);
			//this.searchForm.execute(text);
		}
	}
	MapSearcherControl.prototype.creatLinkAndMarker = function(lat, lng, result) {
		var msc = this;
		var marker = new DivImageMarker( new GLatLng(lat,lng), result.title );
	    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
	    	var $info = $(result.html);
	    	$info.find("div:has(a):gt(0)").remove();
	    	var content = "<div style='text-align:center'>查询范围：<input type=‘text’ id=‘queryRadius’ style='width:40px;' maxlength='3' value='10' />公里 ";
	    	content += "<br />";
	    	content += "<input type='button' value='查询附近车辆' /></div>";
	    	$info.append(content).find(":button").click(function(){
	    			$.blockUI({
	    				message : "<label style='height:100px;'>查询中请稍候...</label>"
	    			});
	    			if(msc.searchResultVehicles){
	    				for ( var i = 0; i < msc.searchResultVehicles.length; i++) {
	    					msc.mapObj.removeOverlay(msc.searchResultVehicles[i]);
		            	}
	    			}
	    			msc.searchResultVehicles = new Array();
	            	
	            	var queryRadius = $(this).prev().val();
	            	if( queryRadius==null || queryRadius.length==0 )
	            		queryRadius  = 10;
	            	$.ajax({
	            		url: "mkgps.do",
	            		data: {
	            			action: "VechicleSearchByLatLngAjaxAction",
	            			lat: lat,
	            			lng: lng,
	            			queryRadius: queryRadius
	            		},
	            		dataType: "json",
	            		cache: false,
	            		success: function(json) {
	            			if( json.vehicles ){
	            				var minLat = mapObj.getBounds().getSouthWest().lat();
	            				var minLng = mapObj.getBounds().getSouthWest().lng();
	            				var maxLat = mapObj.getBounds().getNorthEast().lat();
	            				var maxLng = mapObj.getBounds().getNorthEast().lng();
	            				for( var i=0; i<json.vehicles.length; i++ ){
	    	        				var v = json.vehicles[i];
	    	        				msc.searchResultVehicles.push( msc.creatVehicle(v) );

	            					if( v.lat < minLat )
	            						minLat = v.lat;
	            					if( v.lat > maxLat )
	            						maxLat = v.lat;
	            					
	            					if( v.lng < minLng )
	            						minLng = v.lng;
	            					if( v.lng > maxLng )
	            						maxLng = v.lng;
	            				}
	            				setCenterByLatLngs(msc.mapObj, maxLat, maxLng, minLat, minLng);
	            		    }
	            			$.unblockUI();
	            		},
	            		error: function() {
	            			$.blockUI({
	    						message : "<label>查询车辆失败!</label>"
	    					});
	    					setTimeout('$.unblockUI()', 1000);
	            		}
	            	});
		    	});
	    	marker.imgMarker_.openInfoWindowHtml($info[0]);
		});
	    this.mapObj.addOverlay(marker);
	    return marker;
	}
	MapSearcherControl.prototype.creatVehicle = function(vehicle) {
		if(this.mapObj){
			var html = "</b><br>车牌号: <b>" + vehicle.licensPadNumber + 
				"</b><br>经度: <b>" + vehicle.lng +
				"</b><br>纬度: <b>" + vehicle.lat;
			var marker = new DivImageMarker( new GLatLng(Number(vehicle.lat)+CN_OFFSET_LAT,Number(vehicle.lng)+CN_OFFSET_LON), vehicle.licensPadNumber, RUNNING_ICON );
		    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
				marker.imgMarker_.openInfoWindowHtml(html);
			});
		    this.mapObj.addOverlay(marker);
		    return marker;
		}
	}

	function LimitAreaControl() {}
	LimitAreaControl.prototype = new GControl();
	LimitAreaControl.prototype.isShow = false;
	LimitAreaControl.prototype.limitAreas = null;
	LimitAreaControl.prototype.initialize = function(mapObj) {
    	var container = document.createElement("div");
    	var $controlDiv = $(container).width(80).append("<div>限制区域</div>").children();
    	$controlDiv.addClass("mapLabel").addClass("mapLink");
    	var lac = this;
    	
        GEvent.addDomListener($controlDiv[0], "click", function() {
        	if(lac.isShow){
            	$controlDiv.nextAll().hide();
            	var $checkBoxs = $controlDiv.nextAll("span").children("input");
                for(var i=0;i<$checkBoxs.length;i++){
              		lac.limitAreas[$checkBoxs[i].id].polygon.hide();
                }
            } else {
            	if( lac.limitAreas==null ){
                	$.blockUI({
        				message : "<label style='height:100px;'>查询中请稍候...</label>"
        			});
                	$.ajax({
                		url: "mkgps.do",
                		data: {
                			action: "LimitAreaSearchAjaxAction"
                		},
                		dataType: "json",
                		cache: false,
                		async: false,
                		success: function(json) {
                			lac.limitAreas = json ? json : {};
                			for(var p in lac.limitAreas){
                				var limitArea = lac.limitAreas[p];
                				var points = new Array();
                				for(var j=0;j<limitArea.points.length;j++){
                					var p = limitArea.points[j];
                					if( p.lat && p.lng && p.lat>0 && p.lng>0 )
                						points.push(new GLatLng( Number(p.lat)+CN_OFFSET_LAT,Number(p.lng)+CN_OFFSET_LON ));
                				}
                				limitArea.polygon = new GPolygon(points, "#3355ff", 3, 0.8, "#335599", 0.3);
                				mapObj.addOverlay(limitArea.polygon);

                				$controlDiv.after(
               						"<span style='color:#0000cc;background:white;white-space:nowrap' >" 
               						+ "<input type='checkBox' id='"
               						+ limitArea.id 
               						+ "' />"
                                   	+ limitArea.name 
                                   	+ "</span><br>");
                          		$controlDiv.next("span:first").children("input").click( function() {
                              		if( this.checked )
                              			lac.limitAreas[this.id].polygon.show();
                              		else
                              			lac.limitAreas[this.id].polygon.hide();
                          		}); 
                			}
                			$.unblockUI();
                		},
                		error: function() {
                			$.blockUI({
        						message : "<label>查询限制区域失败!</label>"
        					});
        					setTimeout('$.unblockUI()', 1000);
                		}
                	});
                }
            	$controlDiv.nextAll().show();
            	var $checkBoxs = $controlDiv.nextAll("span").children("input");
                for(var i=0;i<$checkBoxs.length;i++){
                    var cb = $checkBoxs[i];
                    if( cb.checked )
                    	lac.limitAreas[cb.id].polygon.show();
              		else
              			lac.limitAreas[cb.id].polygon.hide();
                }
            }
            lac.isShow = !lac.isShow;
        });
        mapObj.getContainer().appendChild(container);
        return container;
    }
	LimitAreaControl.prototype.getDefaultPosition = function() {
		return new GControlPosition(G_ANCHOR_TOP_LEFT, new GSize(380, 7));
    }
    
	// markers
	function DivMarker( latlng, text ) {
		this.latlng_ = latlng;
		this.text_ = text;
	}
    DivMarker.prototype = new GOverlay();
    DivMarker.prototype.initialize = function(mapObj) {
    	var div = document.createElement("div");
    	$(div).addClass("mapLabel");
    	var p = mapObj.fromLatLngToDivPixel(this.latlng_);
    	$(div).html(this.text_).css("position","absolute").css("left",(p.x)).css("top",(p.y));
    	mapObj.getPane(G_MAP_MAP_PANE).appendChild(div);
        this.mapObj_ = mapObj;
        this.div_ = div;
    }
    DivMarker.prototype.remove = function() {
    	this.div_.parentNode.removeChild(this.div_);
    }
    DivMarker.prototype.copy = function() {
    	return new DivMarker(this.latlng_);
    }
    DivMarker.prototype.redraw = function(force) {
    	if (!force)
			return;
    	var p = this.mapObj_.fromLatLngToDivPixel(this.latlng_);
    	$(this.div_).html(this.text_).css("position","absolute").css("left",(p.x)).css("top",(p.y));
    }
    DivMarker.prototype.show = function() {
    	$(this.div_).show();
    }
    DivMarker.prototype.hide = function() {
    	$(this.div_).hide();
    }

    function DivImageMarker( latlng, text, image ) {
		this.latlng_ = latlng;
		this.text_ = "";
		if(text)
			this.text_ = text;
		if(image){
			this.image_ = image;
			var myIcon = new GIcon(G_DEFAULT_ICON,image);
			myIcon.shadow = null;
			myIcon.iconSize  = new GSize(28, 28);
			myIcon.iconAnchor = new GPoint(20, 28);
			this.imgMarker_ = new GMarker(this.latlng_, { icon: myIcon });
		} else {
			this.imgMarker_ = new GMarker(this.latlng_);
		}
	}
    DivImageMarker.prototype = new GOverlay();
    DivImageMarker.prototype.initialize = function(mapObj) {
    	mapObj.addOverlay(this.imgMarker_);
    	
    	var div = document.createElement("div");
        this.mapObj_ = mapObj;
        this.div_ = div;

        $(div).addClass("mapLabel");
    	var p = mapObj.fromLatLngToDivPixel(this.latlng_);
    	$(div).html(this.text_).css("position","absolute").css("left",p.x-this.imgMarker_.getIcon().iconSize.width-8).css("top",(p.y-this.imgMarker_.getIcon().iconSize.height-$(this.div_).height()-3));
    	mapObj.getPane(G_MAP_MAP_PANE).appendChild(div);
    }
    DivImageMarker.prototype.remove = function() {
    	mapObj.removeOverlay(this.imgMarker_);
    	this.div_.parentNode.removeChild(this.div_);
    }
    DivImageMarker.prototype.copy = function() {
    	return new DivImageMarker(this.latlng_,this.text_,this.image_);
    }
    DivImageMarker.prototype.redraw = function(force) {
    	this.imgMarker_.redraw(force);
    	if (!force)
			return;
    	var p = this.mapObj_.fromLatLngToDivPixel(this.latlng_);
    	$(this.div_).html(this.text_).css("position","absolute").css("left",p.x-this.imgMarker_.getIcon().iconSize.width-8).css("top",(p.y-this.imgMarker_.getIcon().iconSize.height-$(this.div_).height()-3));
    }
    DivImageMarker.prototype.getLatLng = function() {
    	return this.imgMarker_.getLatLng();
    }
    DivImageMarker.prototype.show = function() {
    	this.imgMarker_.show();
    	$(this.div_).show();
    }
    DivImageMarker.prototype.hide = function() {
    	this.imgMarker_.hide();
    	$(this.div_).hide();
    }
	</script>
<%}%>
