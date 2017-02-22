webpackJsonp([32],{281:function(e,t,n){"use strict";function r(e,t){var n;return n={},n[a.a]={types:[o,i,s],endpoint:"exchange",json:!0,params:{exchangePid:e},showLoading:t},n}var a=n(4);n.d(t,"b",function(){return o}),n.d(t,"c",function(){return i}),n.d(t,"d",function(){return s});var o="INTEGRALCENTER_EXCHANGE_REQUEST",i="INTEGRALCENTER_EXCHANGE_SUCCESS",s="INTEGRALCENTER_EXCHANGE_FAILURE";t.a=function(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return function(n,a){var o=a().page.isExchangeFetching;return o?null:n(r(e,t))}}},282:function(e,t,n){"use strict";function r(e,t){var n;return n={},n[a.a]={types:[o,i,s],endpoint:"exchangeProductDetail",json:!0,params:{exchangePid:e},showLoading:t},n}var a=n(4);n.d(t,"b",function(){return o}),n.d(t,"c",function(){return i}),n.d(t,"d",function(){return s});var o="INTEGRALCENTER_DETAIL_REQUEST",i="INTEGRALCENTER_DETAIL_SUCCESS",s="INTEGRALCENTER_DETAIL_FAILURE";t.a=function(e){var t=!(arguments.length>1&&void 0!==arguments[1])||arguments[1];return function(n,a){var o=a().page.isFetching;return o?null:n(r(e,t))}}},395:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var r=n(7),a=n(585),o=n(549),i=n.i(r.a)(a.a);i(o.a)},482:function(e,t,n){"use strict";function r(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function a(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}var i=n(0),s=n.n(i),c=n(1),p=n.n(c),l=n(640),u=(n.n(l),function(e){function t(){r(this,t);var n=a(this,e.call(this));return n.goExchangeRecord=function(e){e.preventDefault(),p.a.ui.window.open("g_point_record"),n.closeConfirm()},n.handleExchange=function(e){e.preventDefault(),n.props.isLogin?n.props.stock>0&&n.props.userPoint>=n.props.point&&n.setState({confirmType:0,isShowConfirm:!0}):p.a.ui.goLogin()},n.confirmExchange=function(e){e.preventDefault(),n.props.exchange(n.props.exchangePid).then(function(e){var t=e.retCode,r=e.retMsg;0===t?n.setState({confirmType:1}):t===-1?(p.a.ui.alert("提示","网络不给力，请稍后再试~","知道了"),n.closeConfirm()):(p.a.ui.alert("提示",r,"知道了"),n.closeConfirm()),n.props.loadDetail(n.props.exchangePid,!1)})},n.closeConfirm=function(){n.setState({isShowConfirm:!1})},n.renderConfirm=function(){var e=null;return 0===n.state.confirmType&&(e=s.a.createElement("div",{className:"confirm-layer"},s.a.createElement("p",null,"是否确认以",n.props.point,"积分兑换",n.props.title,"？"),s.a.createElement("div",{className:"button-cont"},s.a.createElement("a",{className:"cfm-btn btn-grey",onClick:n.closeConfirm},"关闭"),s.a.createElement("a",{className:"cfm-btn",onClick:n.confirmExchange},"确认")))),1===n.state.confirmType&&(e=s.a.createElement("div",{className:"confirm-layer"},s.a.createElement("h4",null,"兑换成功"),s.a.createElement("ul",null,s.a.createElement("li",null,n.props.exchangeResult.prompt),n.props.exchangeResult.info&&n.props.exchangeResult.info.map(function(e,t){return s.a.createElement("li",{key:t},e.k,"：",e.v)})),s.a.createElement("div",{className:"button-cont"},s.a.createElement("a",{className:"cfm-btn btn-grey",onClick:n.closeConfirm},"关闭"),s.a.createElement("a",{className:"cfm-btn",onClick:n.goExchangeRecord},"查看详情")))),s.a.createElement("div",null,s.a.createElement("div",{className:"mask"}),e)},n.appleTip=function(){var e=null;return p.a.ua.os.iOS&&(e=s.a.createElement("div",{className:"tips"},"本活动由财富派提供，和苹果公司（Apple Inc）无关")),e},n.state={confirmType:0,isShowConfirm:!1},n}return o(t,e),t.prototype.render=function(){var e="立即兑换";return this.props.stock<=0&&(e="已兑完"),this.props.isLogin&&this.props.userPoint<this.props.point&&(e="积分不足"),s.a.createElement("div",null,s.a.createElement("div",{className:"mod mod-detail"},s.a.createElement("div",{className:"banner-cont"},s.a.createElement("img",{src:this.props.imgUrl})),s.a.createElement("div",{className:"desc-cont"},s.a.createElement("h1",null,this.props.title),s.a.createElement("h2",null,"商品描述"),s.a.createElement("span",{dangerouslySetInnerHTML:{__html:this.props.description}}),s.a.createElement("h2",null,"兑换条件"),s.a.createElement("span",{dangerouslySetInnerHTML:{__html:this.props.rule}}),s.a.createElement("h2",null,"兑换流程"),s.a.createElement("span",{dangerouslySetInnerHTML:{__html:this.props.process}})),this.appleTip(),1===this.props.state?s.a.createElement("div",{className:"fixed-cont"},s.a.createElement("div",{className:"price"},"积分：",s.a.createElement("span",null,this.props.point)),s.a.createElement("div",{className:"amount"},"剩余数量：",s.a.createElement("span",null,this.props.stock)),s.a.createElement("a",{href:"#",className:this.props.stock<=0||this.props.isLogin&&this.props.userPoint<this.props.point?"action-btn disabled":"action-btn",onClick:this.handleExchange},e)):null),this.state.isShowConfirm?this.renderConfirm():null)},t}(i.Component));u.displayName="IntegralcenterDetail",u.propTypes={isLogin:i.PropTypes.bool,imgUrl:i.PropTypes.string,title:i.PropTypes.string,subTitle:i.PropTypes.string,description:i.PropTypes.string,rule:i.PropTypes.string,process:i.PropTypes.string,point:i.PropTypes.number,stock:i.PropTypes.number,userPoint:i.PropTypes.number,exchangePid:i.PropTypes.any,exchangeResult:i.PropTypes.object,state:i.PropTypes.number,exchange:i.PropTypes.func,loadDetail:i.PropTypes.func},t.a=u},549:function(e,t,n){"use strict";function r(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function a(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function o(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}function i(e){var t=parseInt(e.query.exchangePid,10),n=e.page,r=e.user.isLogin;return{page:n,exchangePid:t,isLogin:r}}var s=n(0),c=n.n(s),p=n(1),l=n.n(p),u=n(5),h=n(282),f=n(61),m=n(281),g=n(482),d=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var n=arguments[t];for(var r in n)Object.prototype.hasOwnProperty.call(n,r)&&(e[r]=n[r])}return e},E=function(e){function t(){return r(this,t),a(this,e.apply(this,arguments))}return o(t,e),t.prototype.componentWillMount=function(){this.props.loadDetail(this.props.exchangePid)},t.prototype.componentDidMount=function(){var e=this;l.a.ui.window.openDropDownRefresh(function(){e.props.loadUser(!0),e.props.loadDetail(e.props.exchangePid,!1)}),l.a.lifeStage.viewWillAppear(function(){e.props.loadUser(),e.props.loadDetail(e.props.exchangePid,!1)})},t.prototype.render=function(){var e={exchangePid:this.props.exchangePid,exchange:this.props.exchange,exchangeResult:this.props.page.exchangeResult,loadDetail:this.props.loadDetail,isLogin:this.props.isLogin};return c.a.createElement(g.a,d({},this.props.page.data,e))},t}(s.Component);E.displayName="IntegralcenterDetailPage",E.propTypes={loadDetail:s.PropTypes.func,loadUser:s.PropTypes.func,exchange:s.PropTypes.func,exchangePid:s.PropTypes.any,exchangeResult:s.PropTypes.object,page:s.PropTypes.object,isLogin:s.PropTypes.bool},t.a=n.i(u.a)(i,{loadDetail:h.a,exchange:m.a,loadUser:f.d})(E)},585:function(e,t,n){"use strict";function r(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{isFetching:!1,data:{},isExchageFetching:!1,exchangeResult:{}},t=arguments[1];switch(t.type){case s.b:return i()({},e,{isFetching:!0});case s.c:return i()({},e,{isFetching:!1,data:t.response.result});case s.d:return i()({},e,{isFetching:!1});case c.b:return i()({},e,{isExchageFetching:!0});case c.c:return i()({},e,{isExchageFetching:!1,exchangeResult:t.response.result});case c.d:return i()({},e,{isExchageFetching:!1});default:return e}}var a=n(6),o=n(2),i=n.n(o),s=n(282),c=n(281);t.a=n.i(a.a)(r)},640:function(e,t){},967:function(e,t,n){e.exports=n(395)}},[967]);
//# sourceMappingURL=detail_7c3d9db133560eabd5a3.js.map