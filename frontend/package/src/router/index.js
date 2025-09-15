import { createRouter, createWebHistory } from "vue-router";

const routes = [
  {
    path: "/",
    name: "login",
    component: () => import("../views/LoginView.vue"),
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/RegisterView.vue')
  },
  {
    path: '/',
    component: () => import('../layouts/MainLayout.vue'),
    children: [
      {
        path: 'dashboard',
        name: 'dashboard',
        component: () => import('../views/DashboardView.vue')
      },
      {
        path: 'user',
        name: 'user',
        component: () => import('../views/UserView.vue')
      },
      {
        path: 'policy',
        name: 'policy',
        component: () => import('../views/PolicyView.vue')
      },
      {
        path: 'task',
        name: 'task',
        component: () => import('../views/TaskView.vue')
      },
      {
        path: 'data',
        name: 'data',
        component: () => import('../views/DataView.vue')
      },
      {
        path: 'log',
        name: 'log',
        component: () => import('../views/LogView.vue')
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
