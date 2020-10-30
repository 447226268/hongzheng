const navmenu = {
  state: {
    flag: localStorage.getItem("flag") || "false",
  },
  mutations: {
    FLAG: (state, String) => {
      state.flag = String;
      localStorage.setItem("flag", state.flag)
    },
  },
  actions: {
    flag({
      commit
    }, String) {
      commit("FLAG", String);
    },
  }
};

export default navmenu;