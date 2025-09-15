<template>
  <div class="user-page">
    <!-- 用户管理 -->
    <el-card class="user-card" shadow="hover">
      <template #header>
        <span class="user-title">用户管理</span>
      </template>
      <div class="toolbar">
        <el-input
          v-model="searchKey"
          placeholder="搜索用户名或邮箱"
          clearable
          size="default"
          class="search-input"
        />
        <el-button type="primary" @click="showAddDialog = true">新增用户</el-button>
      </div>
      <el-table
        :data="pagedUsers"
        stripe
        border
        style="width: 100%; margin-top: 16px"
        empty-text="暂无用户数据"
        height="auto"
      >
        <el-table-column prop="id" label="ID" width="80" align="center" />
        <el-table-column prop="username" label="用户名" align="center" />
        <el-table-column prop="email" label="邮箱" align="center" />
        <el-table-column prop="role" label="角色" align="center" />
        <el-table-column label="操作" width="120" align="center">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-bar">
        <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :total="filteredUsers.length"
          layout="prev, pager, next"
          :pager-count="4"
          background
          style="margin-top: 16px; text-align: right;"
        />
      </div>
    </el-card>

    <!-- 新增用户弹窗 -->
    <el-dialog v-model="showAddDialog" title="新增用户" width="400px" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="70px" class="add-form">
        <el-form-item label="用户名">
          <el-input v-model="addForm.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="addForm.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="addForm.role" placeholder="请选择角色">
            <el-option label="系统管理员" value="系统管理员" />
            <el-option label="数据安全管理员" value="数据安全管理员" />
            <el-option label="日志审核人员" value="日志审核人员" />
            <el-option label="数据使用人员" value="数据使用人员" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAddDialog = false">取消</el-button>
        <el-button type="primary" @click="handleAddUser">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'

// 1. 从 localStorage 读取用户数据
function getUsersFromStorage() {
  const data = localStorage.getItem('users')
  if (data) {
    try {
      return JSON.parse(data)
    } catch {
      return []
    }
  }
  return [
    { id: 1, username: 'admin', email: 'admin@example.com', role: '系统管理员' },
    { id: 2, username: 'security', email: 'security@example.com', role: '数据安全管理员' },
    { id: 3, username: 'auditor', email: 'auditor@example.com', role: '日志审核人员' },
    { id: 4, username: 'user1', email: 'user1@example.com', role: '数据使用人员' },
    { id: 5, username: 'user2', email: 'user2@example.com', role: '数据使用人员' }
  ]
}

const users = ref(getUsersFromStorage())

// 2. 监听 users 变化，自动保存到 localStorage
watch(users, (val) => {
  localStorage.setItem('users', JSON.stringify(val))
}, { deep: true })

const searchKey = ref('')

const filteredUsers = computed(() => {
  if (!searchKey.value.trim()) {
    return users.value
  }
  const key = searchKey.value.toLowerCase()
  return users.value.filter(user =>
    user.username.toLowerCase().includes(key) ||
    user.email.toLowerCase().includes(key)
  )
})

const handleDelete = (id) => {
  ElMessageBox.confirm('确定要删除该用户吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      users.value = users.value.filter(user => user.id !== id)
      ElMessage.success('删除成功')
    })
    .catch(() => {
      // 取消操作，不处理
    })
}

const showAddDialog = ref(false)
const addForm = ref({
  username: '',
  email: '',
  role: ''
})

function handleAddUser() {
  if (!addForm.value.username || !addForm.value.email || !addForm.value.role) {
    ElMessage.warning('请填写完整信息')
    return
  }
  // 简单邮箱校验
  const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailReg.test(addForm.value.email)) {
    ElMessage.warning('请输入正确的邮箱')
    return
  }
  // 新ID自增
  const maxId = users.value.length ? Math.max(...users.value.map(u => u.id)) : 0
  users.value.push({
    id: maxId + 1,
    username: addForm.value.username,
    email: addForm.value.email,
    role: addForm.value.role
  })
  ElMessage.success('新增用户成功')
  showAddDialog.value = false
  addForm.value.username = ''
  addForm.value.email = ''
  addForm.value.role = ''
}

const pageSize = 6
const currentPage = ref(1)

const pagedUsers = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredUsers.value.slice(start, start + pageSize)
})

// 当搜索关键字变化时，自动跳到第一页
watch(() => searchKey.value, () => {
  currentPage.value = 1
})
</script>

<style scoped>
.user-page {
  padding: 0 0 0 0;
  background: #f7f8fa;
  /* 不设置min-height，避免撑开main-content */
  height: 100%;
  box-sizing: border-box;
}
.user-card {
  border-radius: 12px;
  margin-bottom: 24px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.04);
  /* 让卡片高度自适应 */
}
.user-title {
  font-size: 20px;
  font-weight: bold;
  color: #333;
}
.toolbar {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-bottom: 8px;
}
.search-input {
  width: 220px;
  margin-right: 12px;
}
.add-form .el-form-item {
  margin-bottom: 18px;
}
.pagination-bar {
  margin-top: 8px;
}
</style>