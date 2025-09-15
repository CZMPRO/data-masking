<template>
  <div class="dashboard">
    <!-- 左侧菜单栏 -->
    <div class="side-bar">
      <el-menu class="side-menu" :default-active="activeMenu" @select="handleMenuSelect" background-color="#1f2d3d"
        text-color="#fff" active-text-color="#ffd04b" :router="false" unique-opened>
        <el-menu-item index="dashboard">
          <el-icon>
            <Odometer />
          </el-icon>
          <span>仪表盘</span>
        </el-menu-item>
        <el-menu-item index="user">
          <el-icon>
            <User />
          </el-icon>
          <span>用户管理</span>
        </el-menu-item>
        <el-menu-item index="policy">
          <el-icon>
            <Operation />
          </el-icon>
          <span>策略管理</span>
        </el-menu-item>
        <el-menu-item index="task">
          <el-icon>
            <List />
          </el-icon>
          <span>任务管理</span>
        </el-menu-item>
        <el-menu-item index="data">
          <el-icon>
            <DataAnalysis />
          </el-icon>
          <span>数据管理</span>
        </el-menu-item>
        <el-menu-item index="log">
          <el-icon>
            <Document />
          </el-icon>
          <span>日志查看</span>
        </el-menu-item>
      </el-menu>
      <div class="logout-container">
        <el-button type="danger" icon="el-icon-switch-button" round @click="handleLogout" style="width: 100%;">
          退出登录
        </el-button>
      </div>
    </div>
    <!-- 右侧内容区 -->
    <div class="main-content">
      <router-view />
    </div>
  </div>
</template>

<script setup>
import { useRouter, useRoute } from 'vue-router'
import {
  Odometer,
  User,
  Operation,
  List,
  DataAnalysis,
  Document
} from '@element-plus/icons-vue'

const router = useRouter()
const route = useRoute()
const activeMenu = route.name

function handleMenuSelect(index) {
  router.push({ name: index })
}
function handleLogout() {
  router.push({ name: 'login' })
}
</script>

<style scoped>
/* 可复用 DashboardView.vue 的样式 */
.dashboard {
  display: flex;
  min-height: 100vh;
  background: #f9f9fb;
}

.side-bar {
  width: 220px;
  background-color: #1f2d3d;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  height: 100vh;
}

.side-menu {
  flex: 1;
  border-right: none;
  background-color: transparent;
  color: #fff;
  overflow-y: auto;
}

.logout-container {
  padding: 15px 20px;
  background-color: #162233;
  border-top: 1px solid #2a3a4d;
}

.main-content {
  flex: 1;
  padding: 20px;
  display: flex;
  flex-direction: column;
  /* overflow: hidden; */
  /* 新添加 */
  overflow: auto;
  height: 100vh;
  box-sizing: border-box;
}
</style>