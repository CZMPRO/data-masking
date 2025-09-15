<template>
  <div class="task-page">
    <el-card class="task-card" shadow="hover">
      <template #header>
        <div class="flex justify-between items-center">
          <span class="task-title">脱敏任务管理</span>
          <div class="flex items-center">
            <el-avatar class="mr-2">Admin</el-avatar>
            <div>
              <p class="m-0">系统管理员</p>
              <p class="m-0 text-gray-500 text-sm">最后登录：2025/5/19 11:33:51</p>
            </div>
          </div>
        </div>
      </template>
      <div class="toolbar">
        <el-input v-model="searchText" placeholder="搜索任务ID/状态/策略" clearable class="search-input">
          <template #prefix>
            <el-icon>
              <Search />
            </el-icon>
          </template>
        </el-input>
        <el-button type="primary" @click="showAddDialog = true">
          <el-icon class="mr-1">
            <Plus />
          </el-icon>
          新建任务
        </el-button>
      </div>
      <el-table :data="pagedTasks" stripe border style="width: 100%; margin-top: 16px" empty-text="暂无任务">
        <el-table-column prop="task_id" label="任务ID" width="80" align="center" />
        <el-table-column prop="task_status" label="状态" width="100" align="center">
          <template #default="{ row }">
            <el-tag :type="statusTagType(row.task_status)">{{ row.task_status }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="execute_time" label="执行时间" width="180" align="center" />
        <el-table-column prop="policy_name" label="策略名称" align="center" />
        <el-table-column prop="data_id" label="数据ID" width="90" align="center" />
        <el-table-column label="操作" width="80" align="center">
          <template #default="{ row }">
            <el-button type="danger" text @click="handleDelete(row.task_id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-bar">
        <el-pagination v-model:current-page="currentPage" :page-size="pageSize" :total="filteredTasks.length"
          layout="prev, pager, next" :pager-count="3" background style="margin-top: 16px; text-align: right;" />
      </div>
    </el-card>

    <!-- 新建任务弹窗 -->
    <el-dialog v-model="showAddDialog" title="新建脱敏任务" width="420px" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="90px" class="add-form">
        <el-form-item label="策略">
          <el-select v-model="addForm.policy_id" placeholder="请选择策略">
            <el-option v-for="p in policyOptions" :key="p.policy_id" :label="p.policy_name" :value="p.policy_id" />
          </el-select>
        </el-form-item>
        <el-form-item label="数据ID">
          <el-input v-model="addForm.data_id" autocomplete="off" />
        </el-form-item>
        <el-form-item label="执行时间">
          <el-date-picker v-model="addForm.execute_time" type="datetime" placeholder="选择执行时间" style="width: 100%;" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAddDialog = false">取消</el-button>
        <el-button type="primary" @click="handleAddTask">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { Search, Plus } from '@element-plus/icons-vue'
import { ElMessageBox, ElMessage } from 'element-plus'

// 示例策略选项（实际应从数据库获取）
const policyOptions = [
  { policy_id: 1, policy_name: '身份证号脱敏' },
  { policy_id: 2, policy_name: '手机号脱敏' },
  { policy_id: 3, policy_name: '银行卡号脱敏' }
]

// 脱敏任务数据
const tasks = ref([
  {
    task_id: 1,
    task_status: '待处理',
    execute_time: '2025-05-19 14:00:00',
    policy_id: 1,
    policy_name: '身份证号脱敏',
    data_id: 101
  },
  {
    task_id: 2,
    task_status: '完成',
    execute_time: '2025-05-18 10:30:00',
    policy_id: 2,
    policy_name: '手机号脱敏',
    data_id: 102
  }
  // ...更多任务
])

// 搜索
const searchText = ref('')
const filteredTasks = computed(() =>
  tasks.value.filter(t =>
    String(t.task_id).includes(searchText.value) ||
    t.task_status.includes(searchText.value) ||
    (t.policy_name && t.policy_name.includes(searchText.value))
  )
)

// 分页
const pageSize = 5
const currentPage = ref(1)
const pagedTasks = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredTasks.value.slice(start, start + pageSize)
})
watch(() => searchText.value, () => {
  currentPage.value = 1
})

// 新建任务
const showAddDialog = ref(false)
const addForm = ref({
  policy_id: '',
  data_id: '',
  execute_time: ''
})
function handleAddTask() {
  if (!addForm.value.policy_id || !addForm.value.data_id || !addForm.value.execute_time) {
    ElMessage.warning('请填写完整信息')
    return
  }
  const maxId = tasks.value.length ? Math.max(...tasks.value.map(t => t.task_id)) : 0
  const policy = policyOptions.find(p => p.policy_id === addForm.value.policy_id)
  tasks.value.push({
    task_id: maxId + 1,
    task_status: '待处理',
    execute_time: addForm.value.execute_time,
    policy_id: addForm.value.policy_id,
    policy_name: policy ? policy.policy_name : '',
    data_id: addForm.value.data_id
  })
  ElMessage.success('新建任务成功')
  showAddDialog.value = false
  addForm.value.policy_id = ''
  addForm.value.data_id = ''
  addForm.value.execute_time = ''
}

// 删除任务
function handleDelete(id) {
  ElMessageBox.confirm('确定要删除该任务吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    tasks.value = tasks.value.filter(t => t.task_id !== id)
    ElMessage.success('删除成功')
  }).catch(() => { })
}

// 状态标签颜色
function statusTagType(status) {
  if (status === '完成') return 'success'
  if (status === '失败') return 'danger'
  return 'warning'
}
</script>

<style scoped>
.task-page {
  padding: 32px 24px;
  background: #f7f8fa;
  min-height: 100vh;
}

.task-card {
  border-radius: 12px;
  margin-bottom: 24px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.04);
}

.task-title {
  font-size: 20px;
  font-weight: bold;
  color: #333;
}

.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.search-input {
  width: 260px;
  margin-right: 12px;
}

.add-form .el-form-item {
  margin-bottom: 18px;
}

.pagination-bar {
  margin-top: 8px;
}
</style>