 /*eslint-disable*/
import Vue from "vue";
import App from "./App.vue";
import vuetify from "./plugins/vuetify";
import Managing from "./components";
import router from './router';
import ExcelExportButton from "./components/base-ui/export-btn.vue";
import Keycloak from 'keycloak-js';
Vue.config.productionTip = false;
Vue.component("excel-export-button", ExcelExportButton);
Vue.prototype.$Vue = Vue;

const axios = require("axios").default;
require('./style.css');

// backend host url
axios.backend = null; //"http://localhost:8088";

// axios.backendUrl = new URL(axios.backend);
axios.fixUrl = function(original){

  if(!axios.backend && original.indexOf("/")==0) return original;

  var url = null;

  try{
    url = new URL(original);
  }catch(e){
    url = new URL(axios.backend + original);
  }

  if(!axios.backend) return url.pathname;

  url.hostname = axios.backendUrl.hostname;
  url.port = axios.backendUrl.port;

  return url.href;
}

const templateFiles = require.context("./components", true);
Vue.prototype.$ManagerLists = [];
templateFiles.keys().forEach(function(tempFiles) {
  if (!tempFiles.includes("Manager.vue") && tempFiles.includes("vue")) {
    Vue.prototype.$ManagerLists.push(
      tempFiles.replace("./", "").replace(".vue", "")
    );
  }
});
Vue.use(Managing);
const pluralCaseList = []

pluralCaseList.push( {plural: "companies", pascal: "Company"} )
pluralCaseList.push( {plural: "products", pascal: "Product"} )

pluralCaseList.push( {plural: "inventories", pascal: "Inventory"} )

pluralCaseList.push( {plural: "salesOrders", pascal: "SalesOrder"} )


Vue.prototype.$ManagerLists.forEach(function(item, idx) {
  pluralCaseList.forEach(function(tmp) {
    if(item.toLowerCase() == tmp.pascal.toLowerCase()) {
      var obj = {
        name: item,
        plural: tmp.plural
      }
      Vue.prototype.$ManagerLists[idx] = obj
    }
  })
})

let initOptions = {
  url: `http://localhost:9090/`,
  realm: `master`,
  clientId: `cliend-name`,
  onLoad: `login-required`,
};


let keycloak = new Keycloak(initOptions);

init();

function init() {
  keycloak.init({
    onLoad: initOptions.onLoad,
  }).then(auth => {
    const ONE_MINUTE = 60000;
  
    if (!auth) {
      window.location.reload();
    } else {
      console.info(`Auth ok`);
    }

    Vue.prototype.$OAuth = keycloak
  
    new Vue({
      vuetify,
      router,
      render: h => h(App, {
        props: {
          OAuth: keycloak,
        },
      }),
    }).$mount("#app");
  
    window.setTimeout(refreshToken.bind(null, keycloak), ONE_MINUTE);
  }).catch(() => {
    
    new Vue({
      vuetify,
      router,
      render: h => h(App)
    }).$mount("#app");

    console.error(`Auth Fail`);
  })
}

function refreshToken() {
  keycloak.updateToken(70).then(refreshed => {
    if (refreshed) {
      successRefresh(refreshed);
    } else {
      warnRefresh();
    }
  }).error(errorRefresh);
}

function successRefresh(refreshed) {
  console.debug(`Token refreshed ${refreshed}`);
}

function warnRefresh() {
  console.warn(`Token not refreshed, valid for 
  ${Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000)} seconds`);
}

function errorRefresh() {
  console.error('Failed to refresh token');
}
