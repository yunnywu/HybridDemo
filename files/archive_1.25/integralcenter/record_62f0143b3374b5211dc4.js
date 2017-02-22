webpackJsonp([21],{14:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),s=r.n(a),c=function(e){function t(){return n(this,t),o(this,e.apply(this,arguments))}return i(t,e),t.prototype.renderItems=function(){var e=this.props.items.map(this.props.itemRenderer),t=this.props.className;return this.props.itemsRenderer(e,{className:t})},t.prototype.render=function(){var e=this.props.items;return e&&0!==e.length?this.renderItems():null},t}(a.Component);c.displayName="List",c.propTypes={className:a.PropTypes.string,items:a.PropTypes.array.isRequired,itemRenderer:a.PropTypes.func,itemsRenderer:a.PropTypes.func},c.defaultProps={itemRenderer:function(e,t){return s.a.createElement("div",{key:t},e)},itemsRenderer:function(e,t){return s.a.createElement("div",t,e)}},t.a=c},283:function(e,t,r){"use strict";function n(e,t,r){var n;return n={},n[o.a]={types:[i,a,s],endpoint:"exchangeRecordList",json:!0,params:{reqType:0,page:e},showLoading:r},n.refresh=t,n.page=e,n}var o=r(4);r.d(t,"b",function(){return i}),r.d(t,"c",function(){return a}),r.d(t,"d",function(){return s});var i="INTEGRALCENTER_EXCHANGERECORD_REQUEST",a="INTEGRALCENTER_EXCHANGERECORD_SUCCESS",s="INTEGRALCENTER_EXCHANGERECORD_FAILURE";t.a=function(){var e=arguments.length>0&&void 0!==arguments[0]&&arguments[0],t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return function(r,o){var i=o().page,a=i.isExchangeFetching,s=i.exchangeTotalPages,c=void 0===s?0:s,p=o().page.exchangeCurrentPage,l=void 0===p?0:p;return e&&(l=0),(a||l>=c)&&!e?null:r(n(l+1,e,t))}}},286:function(e,t,r){"use strict";function n(e,t,r){var n;return n={},n[o.a]={types:[i,a,s],endpoint:"exchangeRecordList",json:!0,params:{reqType:1,page:e},showLoading:r},n.refresh=t,n.page=e,n}var o=r(4);r.d(t,"b",function(){return i}),r.d(t,"c",function(){return a}),r.d(t,"d",function(){return s});var i="INTEGRALCENTER_PRIZERECORD_REQUEST",a="INTEGRALCENTER_PRIZERECORD_SUCCESS",s="INTEGRALCENTER_PRIZERECORD_FAILURE";t.a=function(){var e=arguments.length>0&&void 0!==arguments[0]&&arguments[0],t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return function(r,o){var i=o().page,a=i.isPrizFetching,s=i.prizeTotalPages,c=void 0===s?0:s,p=o().page.prizeCurrentPage,l=void 0===p?0:p;return e&&(l=0),(a||l>=c)&&!e?null:r(n(l+1,e,t))}}},29:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}function a(e){return e?(e.offsetTop||0)+a(e.offsetParent):0}var s=r(0),c=r.n(s),p=r(14),l=r(31),u=function(e){function t(){var r,i,s;n(this,t);for(var c=arguments.length,p=Array(c),l=0;l<c;l++)p[l]=arguments[l];return r=i=o(this,e.call.apply(e,[this].concat(p))),i.scrollListener=function(){var e=document.body.scrollTop||document.documentElement.scrollTop;a(i.el)+i.el.offsetHeight-e-window.innerHeight<250&&(i.detachScrollListener(),i.props.loadMore())},s=r,o(i,s)}return i(t,e),t.prototype.componentDidMount=function(){this.attachScrollListener()},t.prototype.componentDidUpdate=function(){this.attachScrollListener()},t.prototype.componentWillUnmount=function(){this.detachScrollListener()},t.prototype.attachScrollListener=function(){!this.props.hasMore||this.props.isFetching||this.props.error||(window.addEventListener("scroll",this.scrollListener),window.addEventListener("resize",this.scrollListener),this.scrollListener())},t.prototype.detachScrollListener=function(){window.removeEventListener("scroll",this.scrollListener),window.removeEventListener("resize",this.scrollListener)},t.prototype.render=function(){var e=this,t=this.props,r=t.className,n=t.items,o=t.itemRenderer,i=t.itemsRenderer,a=t.isFetching,s=t.hasMore,u=t.showEnd,h=t.error,f=t.loadMore,d={className:r,items:n,itemRenderer:o,itemsRenderer:i},y={isFetching:a,hasMore:s,showEnd:u,error:h,loadMore:f};return c.a.createElement("div",{ref:function(t){e.el=t}},c.a.createElement(p.a,d),c.a.createElement(l.a,y))},t}(s.Component);u.displayName="InfiniteList",u.propTypes={className:s.PropTypes.string,items:s.PropTypes.array.isRequired,itemRenderer:s.PropTypes.func,itemsRenderer:s.PropTypes.func,isFetching:s.PropTypes.bool,hasMore:s.PropTypes.bool,showEnd:s.PropTypes.bool,error:s.PropTypes.bool,loadMore:s.PropTypes.func.isRequired},u.defaultProps={showEnd:!0},t.a=u},31:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),s=r.n(a),c=r(32),p=(r.n(c),function(e){function t(){var r,i,a;n(this,t);for(var s=arguments.length,c=Array(s),p=0;p<s;p++)c[p]=arguments[p];return r=i=o(this,e.call.apply(e,[this].concat(c))),i.retry=function(){i.props.loadMore&&i.props.loadMore()},a=r,o(i,a)}return i(t,e),t.prototype.render=function(){return this.props.isFetching?s.a.createElement("div",{className:"base-loading"},s.a.createElement("i",{className:"icon-loading"}),s.a.createElement("span",{className:"txt"},"加载中")):this.props.showEnd&&!this.props.hasMore?s.a.createElement("div",{className:"base-loading"},s.a.createElement("span",{className:"txt"},"没有更多结果了")):this.props.error?s.a.createElement("div",{className:"base-loading"},s.a.createElement("span",{className:"txt",onClick:this.retry},"加载失败，点击重试")):null},t}(a.Component));p.displayName="LoadStatus",p.propTypes={isFetching:a.PropTypes.bool,showEnd:a.PropTypes.bool,hasMore:a.PropTypes.bool,error:a.PropTypes.bool,loadMore:a.PropTypes.func},t.a=p},32:function(e,t){},399:function(e,t,r){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=r(7),o=r(589),i=r(553),a=r.i(n.a)(o.a);a(i.a)},487:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),s=r.n(a),c=r(644),p=(r.n(c),r(488)),l=r(29),u=r(27),h=r.n(u),f=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},d=function(e){function t(){var r,i,a;n(this,t);for(var c=arguments.length,l=Array(c),u=0;u<c;u++)l[u]=arguments[u];return r=i=o(this,e.call.apply(e,[this].concat(l))),i.switchTab=h()(function(e){return function(){i.props.handleSwitch(e)}}),i.renderRecordItem=function(e,t){return s.a.createElement(p.a,f({},e,{key:t}))},i.renderRecordItems=function(e,t){return s.a.createElement("div",t,e)},a=r,o(i,a)}return i(t,e),t.prototype.render=function(){var e=this,t={className:"prize-list",items:this.props.exchangeList,itemRenderer:this.renderRecordItem,itemsRenderer:this.renderRecordItems,isFetching:this.props.isExchangeFetching,hasMore:this.props.exchangeCurrentPage<this.props.exchangeTotalPages,error:this.props.exchangeError,loadMore:function(){e.props.loadExchangeRecord(!1,!1)}},r={className:"prize-list",items:this.props.prizeList,itemRenderer:this.renderRecordItem,itemsRenderer:this.renderRecordItems,isFetching:this.props.isPrzieFetching,hasMore:this.props.prizeCurrentPage<this.props.prizeTotalPages,error:this.props.prizeError,loadMore:function(){e.props.loadPrizeRecord(!1,!1)}},n=s.a.createElement("div",{className:"prize-list"},s.a.createElement("div",{className:"no-data"},s.a.createElement("div",{className:"icon"}),s.a.createElement("p",null,"暂无记录")));return s.a.createElement("div",{className:"mod mod-record"},s.a.createElement("div",{className:"tab-cont"},s.a.createElement("ul",null,s.a.createElement("li",{className:this.props.reqType?"":"current",onClick:this.switchTab(0)},s.a.createElement("a",{href:"#"},"兑换记录")),s.a.createElement("li",{className:this.props.reqType?"current":"",onClick:this.switchTab(1)},s.a.createElement("a",{href:"#"},"中奖记录")))),this.props.reqType?0===this.props.prizeList.length?n:s.a.createElement(l.a,r):0===this.props.exchangeList.length?n:s.a.createElement(l.a,t))},t}(a.Component);d.displayName="IntegralcenterRecord",d.propTypes={isExchangeFetching:a.PropTypes.bool,exchangeCurrentPage:a.PropTypes.number,exchangeTotalPages:a.PropTypes.number,exchangeList:a.PropTypes.array,exchangeError:a.PropTypes.bool,isPrzieFetching:a.PropTypes.bool,prizeCurrentPage:a.PropTypes.number,prizeTotalPages:a.PropTypes.number,prizeList:a.PropTypes.array,prizeError:a.PropTypes.bool,loadExchangeRecord:a.PropTypes.func,loadPrizeRecord:a.PropTypes.func,handleSwitch:a.PropTypes.func,reqType:a.PropTypes.number},d.defaultProps={exchangeList:[],prizeList:[]},t.a=d},488:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var a=r(0),s=r.n(a),c=r(27),p=r.n(c),l=r(1),u=r.n(l),h=function(e){function t(){var r,i,a;n(this,t);for(var s=arguments.length,c=Array(s),l=0;l<s;l++)c[l]=arguments[l];return r=i=o(this,e.call.apply(e,[this].concat(c))),i.goProduct=p()(function(e){return function(){e>0&&u.a.ui.window.open("g_point_detail","商品详情",{exchangePid:e})}}),i.gofund=function(e){e.stopPropagation(),e.preventDefault(),u.a.navigator.fixList()},a=r,o(i,a)}return i(t,e),t.prototype.render=function(){var e=0===this.props.type?"list-item":this.props.exchangePid<0?"list-item no-arrow":"list-item  arrow";return s.a.createElement("div",{className:e,onClick:this.goProduct(this.props.exchangePid),style:this.props.exchangePid<0&&0!==this.props.type?{pointerEvents:"none"}:{}},s.a.createElement("div",{className:"item-bg"},s.a.createElement("img",{src:this.props.imgUrl})),s.a.createElement("div",{className:"item-name"},this.props.title),s.a.createElement("div",{className:"item-info"},this.props.itemInfo.map(function(e,t){return s.a.createElement("p",{key:t},s.a.createElement("i",null,e.k,"："),s.a.createElement("span",null,e.v))})),0===this.props.type?s.a.createElement("a",{href:"#",className:"action-btn",onClick:this.gofund},"立即使用"):null)},t}(a.Component);h.displayName="IntegralcenterRecord",h.propTypes={type:a.PropTypes.number,exchangePid:a.PropTypes.number,title:a.PropTypes.string,imgUrl:a.PropTypes.string,itemInfo:a.PropTypes.arrayOf(a.PropTypes.shape({k:a.PropTypes.string,v:a.PropTypes.string}))},t.a=h},553:function(e,t,r){"use strict";function n(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}function a(e){var t=e.page;return t}var s=r(0),c=r.n(s),p=r(1),l=r.n(p),u=r(5),h=r(283),f=r(286),d=r(487),y=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},m=function(e){function t(){n(this,t);var r=o(this,e.call(this));return r.handleSwitch=function(e){r.setState({reqType:e})},r.state={reqType:0},r}return i(t,e),t.prototype.componentWillMount=function(){this.props.loadExchangeRecord(!0,!1),this.props.loadPrizeRecord(!0,!1)},t.prototype.componentDidMount=function(){var e=this;l.a.ui.window.openDropDownRefresh(function(){0===e.state.reqType?e.props.loadExchangeRecord(!0,!1):e.props.loadPrizeRecord(!0,!1)}),l.a.ui.header.setRightBtn({text:"说明",onClick:function(){l.a.ui.window.open("g_point_rule")}}),l.a.ui.header.setLeftBtn({type:"back",onClick:function(){l.a.ui.popView.goTo(1)}})},t.prototype.render=function(){return c.a.createElement(d.a,y({},this.props,{reqType:this.state.reqType,handleSwitch:this.handleSwitch}))},t}(s.Component);m.displayName="IntegralcenterRecordPage",m.propTypes={loadExchangeRecord:s.PropTypes.func,loadPrizeRecord:s.PropTypes.func},t.a=r.i(u.a)(a,{loadExchangeRecord:h.a,loadPrizeRecord:f.a})(m)},589:function(e,t,r){"use strict";function n(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{isExchangeFetching:!1,exchangeCurrentPage:0,exchangeTotalPages:0,exchangeList:[],exchangeError:!1,isPrzieFetching:!1,prizeCurrentPage:0,prizeTotalPages:0,prizeList:[],prizeError:!1},t=arguments[1];switch(t.type){case s.b:return a()({},e,{isExchangeFetching:!0});case s.c:return a()({},e,{isExchangeFetching:!1,exchangeCurrentPage:t.page,exchangeTotalPages:t.response.result.totalPages,exchangeList:[].concat(t.refresh?[]:e.exchangeList,t.response.result.virtualRecordList)});case s.d:return a()({},e,{isExchangeFetching:!1,exchangeError:!0});case c.b:return a()({},e,{isPrzieFetching:!0});case c.c:return a()({},e,{isPrzieFetching:!1,prizeCurrentPage:t.page,prizeTotalPages:t.response.result.totalPages,prizeList:[].concat(t.refresh?[]:e.prizeList,t.response.result.virtualRecordList)});case c.d:return a()({},e,{isPrzieFetching:!1,prizeListError:!0});default:return e}}var o=r(6),i=r(2),a=r.n(i),s=r(283),c=r(286);t.a=r.i(o.a)(n)},644:function(e,t){},971:function(e,t,r){e.exports=r(399)}},[971]);
//# sourceMappingURL=record_62f0143b3374b5211dc4.js.map