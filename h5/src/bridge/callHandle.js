// CallHandle.js
import onBridgeReady from './onBridgeReady'

export default (handlerName, data = {}) => {
  return new Promise((resolve, reject) => {
    onBridgeReady().then((bridge) => {
      bridge.callHandler(handlerName, data, (response) => {
        console.log(response);
        let resPbj = JSON.parse(response);
        if(resPbj.retCode === 0) {
          resolve(JSON.parse(resPbj.retData));
        } else {
          reject(resPbj.retMessage);
        }
      });
    });
  });
};