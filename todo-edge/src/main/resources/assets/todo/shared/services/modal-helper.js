'use strict';
define(['lib/angular/angular', 'shared/modules/shared-module', './busy-status'], function(angular, module) {
	module.factory("modalHelper", ['$modal', '$timeout', 'busy', function($modal, $timeout, busy) {
		return {
			open: function(options) {
				var modal = $modal.open(options);

				var showBusyAfter = $timeout(function() {
					var dialog = busy.showBusyDialog();

					modal.opened.then(function() {
						dialog.close();
					});

					modal.result.catch(function() {
						dialog.close();
					});
				}, 1000);

				modal.opened.then(function() {
					$timeout.cancel(showBusyAfter);
				});

				return modal;
			}
		};
	}]);
});