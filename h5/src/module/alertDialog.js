//getDeviceInfo.js
import callHandle from '../bridge/callHandle';

//msg = message
//duration long or short
export default (title, message, callbackOk,callbackkCancel) => {
  return callHandle('alertDialog', {'title':title, 'message':message}).then(function(response){
  		console.log('alertDialog' + response);
  		if(response.result && response.result.toLowerCase() === "ok"){
			if(callbackOk){
				callbackOk();
			}
  		}else if(response.result && response.result.toLowerCase() === "cancel"){
  			if(callbackkCancel){
  				callbackkCancel();
  			}
  		}
  }).catch(function(error){
  		console.log('error ' + error);
  });
};