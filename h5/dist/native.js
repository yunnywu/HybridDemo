(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define("native", [], factory);
	else if(typeof exports === 'object')
		exports["native"] = factory();
	else
		root["native"] = factory();
})(this, function() {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	var _toast = __webpack_require__(1);

	var _toast2 = _interopRequireDefault(_toast);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	module.exports = {
		toast: _toast2.default
	}; //index.js

/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	var _callHandle = __webpack_require__(2);

	var _callHandle2 = _interopRequireDefault(_callHandle);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	//msg = message
	//duration long or short
	exports.default = function (msg, duration) {
	  return (0, _callHandle2.default)('toast', { 'message': msg, 'duration': duration });
	}; //getDeviceInfo.js

/***/ },
/* 2 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	var _onBridgeReady = __webpack_require__(3);

	var _onBridgeReady2 = _interopRequireDefault(_onBridgeReady);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	exports.default = function (handlerName) {
	  var data = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};

	  return new Promise(function (resolve, reject) {
	    (0, _onBridgeReady2.default)().then(function (bridge) {
	      bridge.callHandler(handlerName, data, function (response) {
	        if (response.retCode === 0) {
	          resolve(response.retData);
	        } else {
	          reject(response);
	        }
	      });
	    });
	  });
	}; // CallHandle.js

/***/ },
/* 3 */
/***/ function(module, exports) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	  value: true
	});

	exports.default = function () {
	  return new Promise(function (resolve) {
	    var bridgeName = 'WebViewJavascriptBridge';
	    var readyEventName = 'WebViewJavascriptBridgeReady';

	    function onBridgeReady(_ref) {
	      var bridge = _ref.bridge;

	      document.removeEventListener(readyEventName, onBridgeReady);
	      resolve(bridge);
	    }

	    if (typeof window[bridgeName] !== 'undefined') {
	      resolve(window[bridgeName]);
	    } else {
	      document.addEventListener(readyEventName, onBridgeReady);
	    }
	  });
	};

/***/ }
/******/ ])
});
;