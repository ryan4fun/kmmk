<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="http://www.google.cn/uds/api?file=uds.js&v=1.s&key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA"
	type="text/javascript"></script>
<script type="text/javascript">
	function MapSearcher(myMap, searchInput, resultDiv, searchCallBack, markerCallBack) {
		this.myMap = myMap;
		this.markerList = new Array();
		this.searchInput = searchInput;
		this.resultDiv = resultDiv;
		this.searchCallBack = searchCallBack;
		this.markerCallBack = markerCallBack;
		
		this.localSearch = new google.search.LocalSearch();
		this.localSearch.setCenterPoint(this.myMap);
		this.localSearch.setResultSetSize( google.search.Search.LARGE_RESULTSET );
		this.localSearch.setSearchCompleteCallback(this, MapSearcher.prototype.OnLocalSearchComplete);

		this.searchForm = new google.search.SearchForm(false, searchInput);
		this.searchForm.setOnSubmitCallback(this, MapSearcher.prototype.CaptureForm);
		this.searchForm.input.focus();
	}

	MapSearcher.prototype.OnLocalSearchComplete = function() {
		if ( this.localSearch.results==null || this.localSearch.results.length<1 ){
			this.resultDiv.innerHTML = "没有找到匹配结果，请修改关键字重新查询！";
			return;
		}
		this.resultDiv.innerHTML = "";
		this.myMap.closeInfoWindow();
		for ( var i = 0; i < this.markerList.length; i++) {
			this.myMap.removeOverlay(this.markerList[i]);
		}
		this.markerList = new Array();
		
		for ( var i = 0; i < this.localSearch.results.length; i++) {
			var result = this.localSearch.results[i];
			if (result.GsearchResultClass == GlocalSearch.RESULT_CLASS) {
				var lat = parseFloat(result.lat);
				var lng = parseFloat(result.lng);
				this.markerList.push(this.creatLinkAndMarker( lat, lng, result, $(this.resultDiv) ));
			}
		}
		setCenterByMarkers(this.myMap, this.markerList);
	}

	MapSearcher.prototype.CaptureForm = function(searchForm) {
		if(this.searchCallBack)
			this.searchCallBack();
		if (searchForm.input.value){
			this.myMap.setZoom(20);
			this.localSearch.setCenterPoint(this.myMap);
			this.localSearch.execute(searchForm.input.value);
		}
		return false;
	}
	
	MapSearcher.prototype.searchText = function(text) {
		if( text && text.length>0 ){
			this.searchForm.input.value = text;
			this.localSearch.execute(text);
			//this.searchForm.execute(text);
		}
	}
	
	MapSearcher.prototype.creatLinkAndMarker = function(lat, lng, result,$resultDiv) {
		var marker = new DivImageMarker( new GLatLng(lat,lng), result.title );
		var cb = this.markerCallBack;
	    GEvent.addListener(marker.imgMarker_, "click", function(latlng) {
			marker.imgMarker_.openInfoWindowHtml(result.html);
			if(cb)
				cb(lat, lng);
		});
	    this.myMap.addOverlay(marker);
	    $resultDiv.append("<a style='color:blue;' >" + result.title + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").children("a:last").click(function(){
	    	marker.imgMarker_.openInfoWindowHtml(result.html);
	    	if(cb)
	    		cb(lat, lng);
		});
	    return marker;
	}
</script>


