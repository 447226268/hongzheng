const login = {
  state: {
    userName: localStorage.getItem("userName") || "",
    token: localStorage.getItem("token") || ""
  },
  mutations: {
    TOKEN: (state, String) => {
      state.token = String;
      localStorage.setItem("token", state.token)
    },
    USERNAME: (state, String) => {
      state.userName = String;
      localStorage.setItem("userName", state.userName)
    }
  },
  actions: {
    token({
      commit
    }, String) {
      commit("TOKEN", String);
    },
    userName({
      commit
    }, String) {
      commit("USERNAME", String);
    },
    logout({
      commit
    }) {
      commit('USERNAME', '')
      commit('TOKEN', '')
      localStorage.removeItem('token')
      localStorage.removeItem('userName')
    }
  }
};

export default login;