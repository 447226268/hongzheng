import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [{
  path: "/",
  name: "Layout",
  component: () => import("../layout/index.vue"),
  children: [{
      path: 'studentinfo',
      name: 'Studentinfo',
      component: () => import("../views/student/info.vue"),
    },
    {
      path: 'orderinfo',
      name: 'Orderinfo',
      component: () => import("../views/order/info.vue"),
    },
  ]
}, ];

const router = new VueRouter({
  mode: 'history',
  base: '/',
  routes,
  scrollBehavior(to, from, savedPosition) {
    console.log(to, from, savedPosition)
    return {
      x: 0,
      y: 0
    }
  }
});

export default router;