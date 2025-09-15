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
        <el-table-column prop="role_name" label="角色" align="center" />
        <el-table-column label="操作" width="150" align="center">
          <template #default="{ row }">
            <el-button type="danger" size="small" @click="handleDelete(Number(row.id))">
              删除
            </el-button>
            <el-button type="primary" size="small" @click="handleEditUser(row)">
              编辑
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
          <el-select v-model="addForm.role_id" placeholder="请选择角色">
            <el-option label="系统管理员" value="1" />
            <el-option label="数据安全管理员" value="2" />
            <el-option label="日志审核人员" value="3" />
            <el-option label="数据使用人员" value="4" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAddDialog = false">取消</el-button>
        <el-button type="primary" @click="handleAddUser">确定</el-button>
      </template>
    </el-dialog>

    <!-- 编辑用户弹窗 -->
    <el-dialog v-model="showEditDialog" title="编辑用户" width="400px" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="70px" class="edit-form">
        <el-form-item label="用户名">
          <el-input v-model="editForm.username" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editForm.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="editForm.role_id" placeholder="请选择角色">
            <el-option label="系统管理员" value="1" />
            <el-option label="数据安全管理员" value="2" />
            <el-option label="日志审核人员" value="3" />
            <el-option label="数据使用人员" value="4" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showEditDialog = false">取消</el-button>
        <el-button type="primary" @click="handleUpdateUser">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue'
import { ElMessageBox, ElMessage } from 'element-plus'

// 从数据库获取用户数据的函数
const fetchUsersFromDatabase = async () => {
  try {
    const response = await fetch('http://localhost:8085/api/user-page/users')
    if (!response.ok) {
      throw new Error('Failed to fetch users from database')
    }
    const data = await response.json()

    console.log('Fetched users:', data); // 添加调试信息
    return data.map(user => ({
      ...user,
      id: Number(user.userId), // 确保 id 是数字
      role_name: getRoleName(user.roleId) // 将 role_id 转换为 role_name
    }));
  } catch (error) {
    console.error('Error fetching users:', error)
    ElMessage.error('获取用户数据失败')
    return []
  }
}

// 获取角色名称
const getRoleName = (roleId) => {
  switch (roleId) {
    case 1:
      return '系统管理员'
    case 2:
      return '数据安全管理员'
    case 3:
      return '日志审核人员'
    case 4:
      return '数据使用人员'
    default:
      return '未知角色'
  }
}

// 在组件挂载时获取用户数据
const users = ref([])
onMounted(async () => {
  users.value = await fetchUsersFromDatabase()
})

// 搜索和筛选用户
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

// 删除用户
const handleDelete = (id) => {
  console.log('Deleting user with ID:', id); // 添加调试信息
  if (typeof id !== 'number' || isNaN(id)) {
    console.error('Invalid user ID:', id);
    ElMessage.error('无效的用户ID');
    return;
  }

  ElMessageBox.confirm('确定要删除该用户吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async () => {
      try {
        const response = await fetch(`http://localhost:8085/api/user-page/users/${id}`, { method: 'DELETE' });
        if (!response.ok) {
          throw new Error('Failed to delete user');
        }
        users.value = users.value.filter(user => user.id !== id);
        ElMessage.success('删除成功');
      } catch (error) {
        console.error('Error deleting user:', error);
        ElMessage.error('删除用户失败');
      }
    })
    .catch(() => {
      // 取消操作，不处理
    });
};

// 添加用户
const showAddDialog = ref(false)
const addForm = ref({
  username: '',
  email: '',
  role_id: ''
})

const handleAddUser = async () => {
  if (!addForm.value.username || !addForm.value.email || !addForm.value.role_id) {
    ElMessage.warning('请填写完整信息')
    return
  }
  const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailReg.test(addForm.value.email)) {
    ElMessage.warning('请输入正确的邮箱')
    return
  }

  try {
    const response = await fetch('http://localhost:8085/api/user-page/users', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(addForm.value)
    })
    if (!response.ok) {
      throw new Error('Failed to add user')
    }
    const newUser = await response.json()
    users.value.push(newUser)
    ElMessage.success('新增用户成功')
    showAddDialog.value = false
    addForm.value.username = ''
    addForm.value.email = ''
    addForm.value.role_id = ''
  } catch (error) {
    console.error('Error adding user:', error)
    ElMessage.error('新增用户失败')
  }
}

// 编辑用户
const showEditDialog = ref(false)
const userToEdit = ref(null)
const editForm = ref({
  id: null,
  username: '',
  email: '',
  role_id: ''
})

const handleEditUser = (user) => {
  userToEdit.value = { ...user }
  editForm.value = { ...user }
  showEditDialog.value = true
}

const handleUpdateUser = async () => {
  if (!editForm.value.username || !editForm.value.email || !editForm.value.role_id) {
    ElMessage.warning('请填写完整信息')
    return
  }
  const emailReg = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailReg.test(editForm.value.email)) {
    ElMessage.warning('请输入正确的邮箱')
    return
  }
  try {
    const response = await fetch(`http://localhost:8085/api/user-page/users/${editForm.value.id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(editForm.value)
    })
    if (!response.ok) {
      throw new Error('Failed to update user')
    }
    const updatedUser = await response.json()
    const index = users.value.findIndex(user => user.id === updatedUser.id)
    if (index !== -1) {
      users.value[index] = updatedUser
    }
    ElMessage.success('更新用户成功')
    showEditDialog.value = false
  } catch (error) {
    console.error('Error updating user:', error)
    ElMessage.error('更新用户失败')
  }
}

// 分页
const pageSize = 6
const currentPage = ref(1)
const pagedUsers = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredUsers.value.slice(start, start + pageSize)
})

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
.edit-form .el-form-item {
  margin-bottom: 18px;
}
.pagination-bar {
  margin-top: 8px;
}
</style>



