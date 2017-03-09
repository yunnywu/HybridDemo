//getDeviceInfo.js
import callHandle from '../bridge/callHandle';

export default (msg) => {
  return callHandle('toast',msg);
};