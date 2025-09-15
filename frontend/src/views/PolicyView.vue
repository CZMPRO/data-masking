<template>
  <div class="policy-page">
    <el-card class="policy-card" shadow="hover">
      <template #header>
        <div class="flex justify-between items-center">
          <span class="policy-title">策略管理</span>
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
        <el-input
          v-model="searchText"
          placeholder="搜索策略..."
          clearable
          class="search-input"
        >
          <template #prefix>
            <el-icon><Search /></el-icon>
          </template>
        </el-input>
        <el-button type="primary" @click="showAddDialog = true">
          <el-icon class="mr-1"><Plus /></el-icon>
          添加策略
        </el-button>
      </div>
      <el-table
        :data="pagedPolicies"
        stripe
        border
        style="width: 100%; margin-top: 16px"
        empty-text="暂无策略"
      >
        <el-table-column prop="policy_id" label="ID" width="80" align="center" />
        <el-table-column prop="policyName" label="策略名称" align="center" />
        <el-table-column prop="targetField" label="目标字段" align="center" />
        <el-table-column prop="rule" label="脱敏规则" width="220" align="center" />
        <el-table-column prop="policyDesc" label="描述" align="center" />
        <el-table-column label="操作" width="150" align="center">
          <template #default="{ row }">
            <el-button type="danger" text @click="handleDelete(row.policy_id)">删除</el-button>
            <el-button type="primary" text @click="handleEditPolicy(row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination-bar">
        <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :total="filteredPolicies.length"
          layout="prev, pager, next"
          :pager-count="3"
          background
          style="margin-top: 16px; text-align: right;"
        />
      </div>
    </el-card>

    <!-- 新增策略弹窗 -->
    <el-dialog v-model="showAddDialog" title="新增策略" width="420px" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" class="add-form">
        <el-form-item label="策略名称">
          <el-input v-model="addForm.policyName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="目标字段">
          <el-input v-model="addForm.targetField" autocomplete="off" />
        </el-form-item>
        <el-form-item label="脱敏规则">
          <el-input v-model="addForm.rule" autocomplete="off" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="addForm.policyDesc" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAddDialog = false">取消</el-button>
        <el-button type="primary" @click="handleAddPolicy">确定</el-button>
      </template>
    </el-dialog>

    <!-- 编辑策略弹窗 -->
    <el-dialog v-model="showEditDialog" title="编辑策略" width="420px" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" class="edit-form">
        <el-form-item label="策略名称">
          <el-input v-model="editForm.policyName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="目标字段">
          <el-input v-model="editForm.targetField" autocomplete="off" />
        </el-form-item>
        <el-form-item label="脱敏规则">
          <el-input v-model="editForm.rule" autocomplete="off" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="editForm.policyDesc" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showEditDialog = false">取消</el-button>
        <el-button type="primary" @click="handleUpdatePolicy">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue'
import { Search, Plus } from '@element-plus/icons-vue'
import { ElMessageBox, ElMessage } from 'element-plus'

// 策略数据，字段与数据库一致
const policies = ref([])

// 搜索功能
const searchText = ref('')
const filteredPolicies = computed(() =>
  policies.value.filter(p =>
    p.policyName?.toLowerCase().includes(searchText.value.toLowerCase())
  )
)

// 分页
const pageSize = 5
const currentPage = ref(1)
const pagedPolicies = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredPolicies.value.slice(start, start + pageSize)
})
watch(() => searchText.value, () => {
  currentPage.value = 1
})

// 获取策略列表
async function fetchPolicies() {
  try {
    const response = await fetch('http://localhost:8085/api/policy/list')
    if (!response.ok) {
      throw new Error('Failed to fetch policies')
    }
    policies.value = await response.json()

    console.log('Fetched policies:', policies.value); // 添加调试信息
    policies.value = policies.value.map(policy => ({
      ...policy,
      policy_id: Number(policy.policyId), // 确保 id 是数字
    }));
  } catch (error) {
    console.error('Error fetching policies:', error)
    ElMessage.error('获取策略数据失败')
  }
}

// 在组件挂载时获取策略数据
onMounted(async () => {
  await fetchPolicies()
})

// 新增策略
const showAddDialog = ref(false)
const addForm = ref({
  policyName: '',
  targetField: '',
  rule: '',
  policyDesc: ''
})

const handleAddPolicy = async () => {
  if (!addForm.value.policyName || !addForm.value.targetField || !addForm.value.rule) {
    ElMessage.warning('请填写完整信息')
    return
  }

  try {
    const response = await fetch('http://localhost:8085/api/policy/add', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(addForm.value)
    })
    if (!response.ok) {
      const errorData = await response.json(); // 获取详细的错误信息
      throw new Error(`Failed to add policy: ${errorData.message || ''}`);
    }
    const newPolicy = await response.json()
    policies.value.push({ ...newPolicy, policy_id: Number(newPolicy.policyId) }) // 确保 id 是数字
    ElMessage.success('新增策略成功')
    showAddDialog.value = false
    addForm.value.policyName = ''
    addForm.value.targetField = ''
    addForm.value.rule = ''
    addForm.value.policyDesc = ''
  } catch (error) {
    console.error('Error adding policy:', error)
    ElMessage.error(error.message || '新增策略失败')
  }
}

// 删除策略
const handleDelete = (policy_id) => {
  if (typeof policy_id !== 'number') {
    ElMessage.error('无效的策略ID')
    return
  }

  ElMessageBox.confirm('确定要删除该策略吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      const response = await fetch(`http://localhost:8085/api/policy/${policy_id}`, { method: 'DELETE' });
      if (!response.ok) {
        const errorData = await response.json(); // 获取详细的错误信息
        throw new Error(`Failed to delete policy: ${errorData.message || ''}`);
      }
      policies.value = policies.value.filter(p => p.policy_id !== policy_id);
      ElMessage.success('删除成功');
    } catch (error) {
      console.error('Error deleting policy:', error);
      ElMessage.error(error.message || '删除策略失败');
    }
  }).catch(() => {})
}

// 编辑策略
const showEditDialog = ref(false)
const editForm = ref({
  policy_id: null,
  policyName: '',
  targetField: '',
  rule: '',
  policyDesc: ''
})

const handleEditPolicy = (policy) => {
  if (typeof policy.policy_id !== 'number') {
    ElMessage.error('无效的策略ID')
    return
  }

  editForm.value = { ...policy }
  showEditDialog.value = true
}

const handleUpdatePolicy = async () => {
  if (!editForm.value.policyName || !editForm.value.targetField || !editForm.value.rule) {
    ElMessage.warning('请填写完整信息')
    return
  }

  if (typeof editForm.value.policy_id !== 'number') {
    ElMessage.error('无效的策略ID')
    return
  }

  try {
    const response = await fetch(`http://localhost:8085/api/policy/${editForm.value.policy_id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(editForm.value)
    })
    if (!response.ok) {
      const errorData = await response.json(); // 获取详细的错误信息
      throw new Error(`Failed to update policy: ${errorData.message || ''}`);
    }
    const updatedPolicy = await response.json()
    const index = policies.value.findIndex(p => p.policy_id === updatedPolicy.policy_id)
    if (index !== -1) {
      policies.value[index] = { ...updatedPolicy, policy_id: Number(updatedPolicy.policyId) } // 确保 id 是数字
    }
    ElMessage.success('更新策略成功')
    showEditDialog.value = false
  } catch (error) {
    console.error('Error updating policy:', error)
    ElMessage.error(error.message || '更新策略失败')
  }
}
</script>

<style scoped>
.policy-page {
  padding: 32px 24px;
  background: #f7f8fa;
  min-height: 100vh;
}
.policy-card {
  border-radius: 12px;
  margin-bottom: 24px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.04);
}
.policy-title {
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
.edit-form .el-form-item {
  margin-bottom: 18px;
}
.pagination-bar {
  margin-top: 8px;
}
</style>



