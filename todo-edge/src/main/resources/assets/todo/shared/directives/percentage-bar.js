'use strict';
define(["shared/modules/shared-module"], function(module) {
	module.directive("percentageBar", function() {
		return {
			scope : {},
			transclude : true,
			template : "<span style='width: {{percentage}}%;' ng-transclude></span>",
			link : function($scope, el, attrs) {
				$scope.percentage = $scope.$eval(attrs.percentageBar) * 100;
				el.addClass('meter');
			}
		};
	});
});
