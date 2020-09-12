const getters = {
  userName: state => state.login.userName,
  token: state => state.login.token,
  flag: state => state.navmenu.flag
};
export default getters;
