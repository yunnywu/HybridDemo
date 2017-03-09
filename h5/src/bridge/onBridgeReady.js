export default () => {
	return new Promise((resolve) => {
    	let bridgeName = 'WebViewJavascriptBridge';
    	let readyEventName = 'WebViewJavascriptBridgeReady';
    
    	function onBridgeReady({bridge}) {
      		document.removeEventListener(readyEventName, onBridgeReady);
      		resolve(bridge);
    	}

    	if (typeof window[bridgeName] !== 'undefined') {
        	resolve(window[bridgeName]);
    	} else {
      		document.addEventListener(readyEventName, onBridgeReady);
    	}
  });
}