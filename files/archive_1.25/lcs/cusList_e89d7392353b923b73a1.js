webpackJsonp([20],{14:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),i=r.n(a),p=function(e){function t(){return n(this,t),o(this,e.apply(this,arguments))}return s(t,e),t.prototype.renderItems=function(){var e=this.props.items.map(this.props.itemRenderer),t=this.props.className;return this.props.itemsRenderer(e,{className:t})},t.prototype.render=function(){var e=this.props.items;return e&&0!==e.length?this.renderItems():null},t}(a.Component);p.displayName="List",p.propTypes={className:a.PropTypes.string,items:a.PropTypes.array.isRequired,itemRenderer:a.PropTypes.func,itemsRenderer:a.PropTypes.func},p.defaultProps={itemRenderer:function(e,t){return i.a.createElement("div",{key:t},e)},itemsRenderer:function(e,t){return i.a.createElement("div",t,e)}},t.a=p},147:function(e,t,r){"use strict";var n=r(0),o=r.n(n),s=r(3),a=r.n(s),i=function(e){var t=["icon"],r="";switch(e.type){case 1:t.push("icon-user"),r="暂无客户";break;case 2:t.push("icon-has-invest"),r="暂无已投资客户";break;case 3:t.push("icon-no-invest"),r="暂无未投资客户";break;case 4:t.push("icon-invest"),r="暂无投资记录";break;case 5:t.push("icon-redbag"),r="暂无红包记录"}return o.a.createElement("div",{className:"no-result"},o.a.createElement("div",{className:a()(t)}),o.a.createElement("p",{className:"txt"},r))};i.displayName="LcsNoResult",i.propTypes={type:n.PropTypes.number.isRequired},t.a=i},193:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),i=r.n(a),p=r(3),c=r.n(p),u=r(27),l=r.n(u);r.d(t,"a",function(){return h}),r.d(t,"b",function(){return m});var f,y=1,h=2,m=3,d=(f={},f[y]="全部",f[h]="已投资",f[m]="未投资",f),b=function(e){function t(r){n(this,t);var s=o(this,e.call(this,r));return s.toggle=function(){s.setState({opened:!s.state.opened})},s.changeType=l()(function(e){return function(t){t.preventDefault(),s.props.dataReport&&s.props.dataReport("筛选(按钮)"),s.props.changeType(e)}}),s.state={opened:!1},s}return s(t,e),t.prototype.renderTitle=function(){return i.a.createElement("div",{className:"tit"},i.a.createElement("span",null,d[this.props.type]||"全部"))},t.prototype.renderOptions=function(){var e=this,t=Object.keys(d).map(function(t){return t=parseInt(t,10),i.a.createElement("li",{key:t,className:c()({selected:e.props.type===t}),onClick:e.changeType(t)},d[t])});return i.a.createElement("ul",{className:"list"},t)},t.prototype.render=function(){return i.a.createElement("div",{className:c()({"tabs-cont":!0,current:this.state.opened}),onClick:this.toggle},this.renderTitle(),this.renderOptions())},t}(a.Component);b.displayName="LcsCusListFilter",b.propTypes={type:a.PropTypes.number.isRequired,changeType:a.PropTypes.func.isRequired,dataReport:a.PropTypes.func},t.c=b},29:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}function a(e){return e?(e.offsetTop||0)+a(e.offsetParent):0}var i=r(0),p=r.n(i),c=r(14),u=r(31),l=function(e){function t(){var r,s,i;n(this,t);for(var p=arguments.length,c=Array(p),u=0;u<p;u++)c[u]=arguments[u];return r=s=o(this,e.call.apply(e,[this].concat(c))),s.scrollListener=function(){var e=document.body.scrollTop||document.documentElement.scrollTop;a(s.el)+s.el.offsetHeight-e-window.innerHeight<250&&(s.detachScrollListener(),s.props.loadMore())},i=r,o(s,i)}return s(t,e),t.prototype.componentDidMount=function(){this.attachScrollListener()},t.prototype.componentDidUpdate=function(){this.attachScrollListener()},t.prototype.componentWillUnmount=function(){this.detachScrollListener()},t.prototype.attachScrollListener=function(){!this.props.hasMore||this.props.isFetching||this.props.error||(window.addEventListener("scroll",this.scrollListener),window.addEventListener("resize",this.scrollListener),this.scrollListener())},t.prototype.detachScrollListener=function(){window.removeEventListener("scroll",this.scrollListener),window.removeEventListener("resize",this.scrollListener)},t.prototype.render=function(){var e=this,t=this.props,r=t.className,n=t.items,o=t.itemRenderer,s=t.itemsRenderer,a=t.isFetching,i=t.hasMore,l=t.showEnd,f=t.error,y=t.loadMore,h={className:r,items:n,itemRenderer:o,itemsRenderer:s},m={isFetching:a,hasMore:i,showEnd:l,error:f,loadMore:y};return p.a.createElement("div",{ref:function(t){e.el=t}},p.a.createElement(c.a,h),p.a.createElement(u.a,m))},t}(i.Component);l.displayName="InfiniteList",l.propTypes={className:i.PropTypes.string,items:i.PropTypes.array.isRequired,itemRenderer:i.PropTypes.func,itemsRenderer:i.PropTypes.func,isFetching:i.PropTypes.bool,hasMore:i.PropTypes.bool,showEnd:i.PropTypes.bool,error:i.PropTypes.bool,loadMore:i.PropTypes.func.isRequired},l.defaultProps={showEnd:!0},t.a=l},291:function(e,t,r){"use strict";function n(e,t,r,n){var p;return p={},p[o.a]={types:[s,a,i],endpoint:"customerList",json:!0,params:{type:e,page:t},showLoading:n},p.filterType=e,p.page=t,p.refresh=r,p}var o=r(4);r.d(t,"b",function(){return s}),r.d(t,"c",function(){return a}),r.d(t,"d",function(){return i});var s="LCS_CUS_LIST_REQUEST",a="LCS_CUS_LIST_SUCCESS",i="LCS_CUS_LIST_FAILURE";t.a=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:1,t=arguments.length>1&&void 0!==arguments[1]&&arguments[1],r=!(arguments.length>2&&void 0!==arguments[2])||arguments[2];return e=parseInt(e,10)||1,function(o,s){var a=s().page,i=a.isFetching,p=a.totalPage,c=void 0===p?0:p,u=s().page.page,l=void 0===u?0:u;return t&&(l=0),i||!t&&l>0&&l>=c?null:o(n(e,l+1,t,r))}}},3:function(e,t,r){var n,o;!function(){"use strict";function r(){for(var e=[],t=0;t<arguments.length;t++){var n=arguments[t];if(n){var o=typeof n;if("string"===o||"number"===o)e.push(n);else if(Array.isArray(n))e.push(r.apply(null,n));else if("object"===o)for(var a in n)s.call(n,a)&&n[a]&&e.push(a)}}return e.join(" ")}var s={}.hasOwnProperty;"undefined"!=typeof e&&e.exports?e.exports=r:(n=[],o=function(){return r}.apply(t,n),!(void 0!==o&&(e.exports=o)))}()},31:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),i=r.n(a),p=r(32),c=(r.n(p),function(e){function t(){var r,s,a;n(this,t);for(var i=arguments.length,p=Array(i),c=0;c<i;c++)p[c]=arguments[c];return r=s=o(this,e.call.apply(e,[this].concat(p))),s.retry=function(){s.props.loadMore&&s.props.loadMore()},a=r,o(s,a)}return s(t,e),t.prototype.render=function(){return this.props.isFetching?i.a.createElement("div",{className:"base-loading"},i.a.createElement("i",{className:"icon-loading"}),i.a.createElement("span",{className:"txt"},"加载中")):this.props.showEnd&&!this.props.hasMore?i.a.createElement("div",{className:"base-loading"},i.a.createElement("span",{className:"txt"},"没有更多结果了")):this.props.error?i.a.createElement("div",{className:"base-loading"},i.a.createElement("span",{className:"txt",onClick:this.retry},"加载失败，点击重试")):null},t}(a.Component));c.displayName="LoadStatus",c.propTypes={isFetching:a.PropTypes.bool,showEnd:a.PropTypes.bool,hasMore:a.PropTypes.bool,error:a.PropTypes.bool,loadMore:a.PropTypes.func},t.a=c},32:function(e,t){},404:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=r(7),o=r(592),s=r(558),a=r.i(n.a)(o.a);a(s.a)},491:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),i=r.n(a),p=r(1),c=r.n(p),u=r(3),l=r.n(u),f=r(35),y=(r.n(f),function(e){function t(){var r,s,a;n(this,t);for(var i=arguments.length,p=Array(i),u=0;u<i;u++)p[u]=arguments[u];return r=s=o(this,e.call.apply(e,[this].concat(p))),s.goCusDetail=function(){s.props.dataReport&&s.props.dataReport("进入用户详情页"),c.a.ui.window.open(f.HYBRID_ROOT+"lcs/cusDetail.html",s.props.name,{uid:s.props.uid})},a=r,o(s,a)}return s(t,e),t.prototype.getSexStyle=function(){return l()({name:!0,female:1===this.props.sex,male:0===this.props.sex})},t.prototype.renderProfit=function(){return this.props.profit.indexOf("-")!==-1?i.a.createElement("div",{className:"amount green"},this.props.profit):i.a.createElement("div",{className:"amount red"},"+",this.props.profit)},t.prototype.payUserItem=function(){return i.a.createElement("div",{className:"user-box",onClick:this.goCusDetail},i.a.createElement("div",{className:"u-l"},i.a.createElement("div",{className:this.getSexStyle()},this.props.name),i.a.createElement("div",{className:"profit"},this.renderProfit(),i.a.createElement("div",{className:"tit"},"盈亏(元)"))),i.a.createElement("div",{className:"u-r"},i.a.createElement("div",{className:"assets"},i.a.createElement("div",{className:"amount"},this.props.investingAmt),i.a.createElement("div",{className:"tit"},"在投资产(元)"))))},t.prototype.renderStatus=function(){return 2===this.props.unPayType?i.a.createElement("div",{className:"status no-bind"},"未绑卡"):i.a.createElement("div",{className:"status no-pay"},"未支付")},t.prototype.noPayUserItem=function(){return i.a.createElement("div",{className:"user-box",onClick:this.goCusDetail},i.a.createElement("div",{className:"u-l"},i.a.createElement("div",{className:this.getSexStyle()},this.props.name),i.a.createElement("div",{className:"info"},"注册时间 ",this.props.regTime)),i.a.createElement("div",{className:"u-r"},this.renderStatus()))},t.prototype.render=function(){return this.props.unPayType?this.noPayUserItem():this.payUserItem()},t}(a.Component));y.displayName="LcsCusListItem",y.propTypes={name:a.PropTypes.string,sex:a.PropTypes.number,profit:a.PropTypes.string,investingAmt:a.PropTypes.string,regTime:a.PropTypes.string,unPayType:a.PropTypes.number,uid:a.PropTypes.number,dataReport:a.PropTypes.func},t.a=y},558:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}function a(e){var t=parseInt(e.query.type,10),r=e.page,n=r.isFetching,o=r.error,s=r.customerList,a=r.page,i=r.totalPage,p=r.filterType,c=void 0===p?t||1:p;return{isFetching:n,error:o,type:c,customerList:s,page:a,totalPage:i}}var i=r(0),p=r.n(i),c=r(1),u=r.n(c),l=r(5),f=r(291),y=r(193),h=r(491),m=r(147),d=r(29),b=r(649),v=(r.n(b),Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e}),g=function(e){function t(){var r,s,a;n(this,t);for(var i=arguments.length,c=Array(i),l=0;l<i;l++)c[l]=arguments[l];return r=s=o(this,e.call.apply(e,[this].concat(c))),s.changeType=function(e){s.props.loadCusList(e,!0).then(function(e){var t=e.retCode;0!==t&&u.a.ui.toast("网络异常,请下拉重试")})},s.loadMore=function(){s.props.loadCusList(s.props.type,!1,!1)},s.renderItem=function(e,t){return p.a.createElement(h.a,v({dataReport:s.dataReport},e,{key:t}))},s.renderItems=function(e,t){return p.a.createElement("div",t,e)},s.dataReport=function(e){u.a.stats("YJLcsModule",{"我的客户列表页面事件":e})},a=r,o(s,a)}return s(t,e),t.prototype.componentWillMount=function(){this.props.loadCusList(this.props.type)},t.prototype.componentDidMount=function(){var e=this;u.a.ui.window.openDropDownRefresh(function(){e.props.loadCusList(e.props.type,!0,!1)})},t.prototype.render=function(){var e=0===this.props.page||this.props.page<this.props.totalPage,t={className:"userlist-cont",items:this.props.customerList,isFetching:this.props.isFetching,itemRenderer:this.renderItem,itemsRenderer:this.renderItems,hasMore:e,error:this.props.error,loadMore:this.loadMore};return p.a.createElement("div",{className:"mod-myusers"},p.a.createElement(y.c,{type:this.props.type,changeType:this.changeType,dataReport:this.dataReport}),0!==this.props.customerList.length||e?p.a.createElement(d.a,t):p.a.createElement(m.a,{type:this.props.type}))},t}(i.Component);g.displayName="CfdsCusListPage",g.propTypes={type:i.PropTypes.number,customerList:i.PropTypes.array,page:i.PropTypes.number,totalPage:i.PropTypes.number,loadCusList:i.PropTypes.func,isFetching:i.PropTypes.bool,error:i.PropTypes.bool},t.a=r.i(l.a)(a,{loadCusList:f.a})(g)},592:function(e,t,r){"use strict";function n(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{isFetching:!1,customerList:[],totalPage:0,page:0,error:!1},t=arguments[1];switch(t.type){case i.b:return s()({},e,{isFetching:!0});case i.c:return s()({},e,{isFetching:!1,customerList:[].concat(t.refresh?[]:e.customerList,t.response.result.customerList),totalPage:t.response.result.totalPage,error:!1,page:t.page,filterType:t.filterType});case i.d:return s()({},e,{isFetching:!1,error:!0});default:return e}}var o=r(2),s=r.n(o),a=r(6),i=r(291);t.a=r.i(a.a)(n)},649:function(e,t){},976:function(e,t,r){e.exports=r(404)}},[976]);
//# sourceMappingURL=cusList_e89d7392353b923b73a1.js.map