//getDeviceInfo.js
import callHandle from '../bridge/callHandle';

//msg = message
//duration long or short
export default (number) => {
  return callHandle('call', {'number':number});
};