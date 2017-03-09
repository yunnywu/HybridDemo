// CallHandle.js
import onBridgeReady from './onBridgeReady'

export default (handlerName, data = {}) => {
  return new Promise((resolve, reject) => {
    onBridgeReady().then((bridge) => {
      bridge.callHandler(handlerName, data, (response) => {
        if(response.retCode === 0) {
          resolve(response.retData);
        } else {
          reject(response);
        }
      });
    });
  });
};