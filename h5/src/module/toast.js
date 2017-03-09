//getDeviceInfo.js
import callHandle from '../bridge/callHandle';

//msg = message
//duration long or short
export default (msg, duration) => {
  return callHandle('toast', {'message':msg, 'duration':duration});
};