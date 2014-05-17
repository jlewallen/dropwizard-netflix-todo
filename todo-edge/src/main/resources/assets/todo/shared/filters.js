'use strict';
define(['lib/angular/angular', 'lib/lodash'], function(angular, _) {
	var module = angular.module('nc.filters', []);

	module.filter('joinBy', function() {
		return function(input, delimiter) {
			return (input || []).join(delimiter || ', ');
		};
	});

	module.filter('startFrom', function() {
		return function(input, start) {
			if (input) {
				start = _.parseInt(start);
				return input.slice(start);
			}
			return input;
		};
	});

	module.filter('money', function() {
		return function(input) {
			if (input !== undefined && input != null) {
				return "$" + input.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			}
		};
	});

	return module;
});