const navmenu = {
  state: {
    flag: 'false'
  },
  mutations: {
    FLAG: (state, String) => {
      state.flag = String;
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