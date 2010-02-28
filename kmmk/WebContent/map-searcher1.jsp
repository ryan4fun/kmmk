	<script src="http://www.google.cn/uds/api?file=uds.js&v=1.s&key=ABQIAAAA-8-oGaONg1QfuJrk5m7I5xQorsySYhUbhDW2XK52qse7ScM4shS8yW66UftJun-0rl96hQic_ybHlA"
	      type="text/javascript"></script>
	<script type="text/javascript">
    function MapSearcher( myMap, searchDiv, searchString ) {
		this.myMap = myMap;
		this.markerList = new Array();
		this.searchDiv =  searchDiv;
		
		// Create a search control
		var searchControl = new GSearchControl();
		
		// Add in a full set of searchers
		var localSearch = new GlocalSearch();
		var options = new GsearcherOptions();
		options.setExpandMode(GSearchControl.EXPAND_MODE_OPEN);
		searchControl.addSearcher(localSearch, options);
		
		// Set the Local Search center point
		localSearch.setCenterPoint(this.myMap);
		
		// tell the searcher to draw itself and tell it where to attach
		var drawOptions = new google.search.DrawOptions(); 
		drawOptions.setSearchFormRoot(this.searchDiv); 
		searchControl.draw(searchDiv, drawOptions);
		
		// tell the search control to call be on start/stop
		searchControl.setSearchCompleteCallback(this, MapSearcher.prototype.OnSearchComplete);
		searchControl.setSearchStartingCallback(this, MapSearcher.prototype.OnSearchStarting);
		searchControl.setOnKeepCallback(this, MapSearcher.prototype.OnKeep, "view on map");

		// execute an inital search
		if(searchString)
			searchControl.execute(searchString);
    }

    MapSearcher.prototype.OnSearchComplete = function(sc, searcher) {
      // if we have local search results, put them on the map
      if ( searcher.results && searcher.results.length > 0) {
        for (var i = 0; i < searcher.results.length; i++) {
          var result = searcher.results[i];

		  $(this.searchDiv).append("<div>" + result.title + "</div>"); 
          // if this is a local search result, then proceed...
          if (result.GsearchResultClass == GlocalSearch.RESULT_CLASS ) {
            var markerObject = new Object();
            markerObject.result = result;
            markerObject.latLng = new GLatLng(parseFloat(result.lat), parseFloat(result.lng));
            markerObject.gmarker = new GMarker(markerObject.latLng);
            var clickHandler = method_closure(this, MapSearcher.prototype.OnMarkerClick, [markerObject]);
            GEvent.bind(markerObject.gmarker, "click", this, clickHandler);
            this.markerList.push(markerObject);
            this.myMap.addOverlay(markerObject.gmarker);
            result.__markerObject__ = markerObject;
          }
        }
        this.OnMarkerClick(this.markerList[0]);
      }
    }

    MapSearcher.prototype.OnSearchStarting = function(sc, searcher, query) {
      // close the info window and clear markers
      this.myMap.closeInfoWindow();
      for (var i=0; i < this.markerList.length; i++) {
        var markerObject = this.markerList[i];
        this.myMap.removeOverlay(markerObject.gmarker);
      }
      this.markerList = new Array();
    }

    MapSearcher.prototype.OnKeep = function(result) {
      if (result.__markerObject__) {
        markerObject = result.__markerObject__;
        this.OnMarkerClick(markerObject);
      }
    }

    MapSearcher.prototype.OnMarkerClick = function(markerObject) {
      this.myMap.closeInfoWindow();
      var htmlNode = markerObject.result.html.cloneNode(true);
      markerObject.gmarker.openInfoWindow(htmlNode);
    }


    function method_closure(object, method, opt_argArray) {
      return function() {
        return method.apply(object, opt_argArray);
      }
    }
	</script>


            