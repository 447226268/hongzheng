import Vue from "vue";
import Vuex from "vuex";
import login from "./modules/login";
import navmenu from "./modules/navmenu";
import getters from "./getters";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    login,
    navmenu
  },
  getters
});

export default store;