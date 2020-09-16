/*
 * @Author: ld
 * @Date: 2020-09-15 10:07:11
 * @LastEditors: ld
 * @LastEditTime: 2020-09-16 19:10:45
 * @Description: file content
 * @FilePath: \hongzheng\web\src\router\index.js
 */
import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [{
    path: "/",
    name: "Layout",
    redirect: 'home',
    component: () => import("../layout/index.vue"),
    children: [{
        path: 'home',
        name: 'Home',
        component: () => import("../views/home.vue"),
      },
      {
        path: 'studentinfo',
        name: 'Studentinfo',
        component: () => import("../views/student/info.vue"),
      },
      {
        path: 'studentinsert',
        name: 'Studentinsert',
        component: () => import("../views/student/insert.vue"),
      },
      {
        path: 'studentinsert/:id',
        name: 'Studentinsert',
        component: () => import("../views/student/insert.vue"),
      },
      {
        path: 'studentedit/:id',
        name: 'Studentedit',
        component: () => import("../views/student/edit.vue"),
      },
      {
        path: 'orderinfo',
        name: 'Orderinfo',
        component: () => import("../views/order/info.vue"),
      },
      {
        path: 'orderinsert',
        name: 'Orderinsert',
        component: () => import("../views/order/insert.vue"),
      },
      {
        path: 'orderedit/:id',
        name: 'Orderedit',
        component: () => import("../views/order/edit.vue"),
      },
      {
        path: 'memberinfo',
        name: 'Memberinfo',
        component: () => import("../views/member/info.vue"),
      },
      {
        path: 'placeinfo',
        name: 'Placeinfo',
        component: () => import("../views/place/info.vue"),
      },
      {
        path: 'manage',
        name: 'Manage',
        component: () => import("../views/manage/info.vue"),
      },

    ]

  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/login.vue"),
  }
];

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