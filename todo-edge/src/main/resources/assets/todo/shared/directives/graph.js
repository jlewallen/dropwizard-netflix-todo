'use strict';
define(["shared/modules/shared-module", "text!./graph.html", "lib/jqplot/excanvas.min", "lib/jqplot/jquery.jqplot.min", "lib/jqplot/plugins/jqplot.dateAxisRenderer.min"], function(module, template) {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
	}

	function guid() {
		return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
	}

	module.directive("graph", ["$http", function($http) {
		return {
			template : template,
			link : function($scope, el, attrs) {
				var model = [];
				$scope.busy = true;
				$scope.id = "graph-" + guid();
				$http.get(attrs.graph + "?_format=json").success(function(model) {
					var samples = model.samples;
					if (samples.length > 1) {
						$.jqplot($scope.id, [ samples ], {
							title : 'Weekly Balance',
							axes : {
								xaxis : {
									renderer : $.jqplot.DateAxisRenderer,
									tickRenderer : $.jqplot.CanvasAxisTickRenderer,
									tickOptions : {
										formatString : '%b %#d, %y',
										angle : -45
									},
									min : samples[0][0],
									max : samples[samples.length - 1][0]
								}
							},
							series : [
								{
									lineWidth : 1
								}
							]
						});
						$scope.busy = false;
					}
				});
			}
		};
	}]);
});
