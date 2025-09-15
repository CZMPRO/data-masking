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
        <el-table-column prop="policy_name" label="策略名称" align="center" />
        <el-table-column prop="target_field" label="目标字段" align="center" />
        <el-table-column prop="rule" label="脱敏规则" width="220" align="center" />
        <el-table-column prop="policy_desc" label="描述" align="center" />
        <el-table-column label="操作" width="80" align="center">
          <template #default="{ row }">
            <el-button type="danger" text @click="handleDelete(row.policy_id)">删除</el-button>
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
          <el-input v-model="addForm.policy_name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="目标字段">
          <el-input v-model="addForm.target_field" autocomplete="off" />
        </el-form-item>
        <el-form-item label="脱敏规则">
          <el-input v-model="addForm.rule" autocomplete="off" />
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="addForm.policy_desc" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAddDialog = false">取消</el-button>
        <el-button type="primary" @click="handleAddPolicy">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue'
import { Search, Plus } from '@element-plus/icons-vue'
import { ElMessageBox, ElMessage } from 'element-plus'

// 策略数据，字段与数据库一致
const policies = ref([
  {
    policy_id: 1,
    policy_name: '身份证号脱敏',
    target_field: 'id_number',
    rule: '保留前6位和后4位，中间用*替代',
    policy_desc: '针对身份证号的脱敏策略'
  },
  {
    policy_id: 2,
    policy_name: '手机号脱敏',
    target_field: 'phone_number',
    rule: '保留前3位和后4位，中间用*替代',
    policy_desc: '手机号脱敏'
  },
  {
    policy_id: 3,
    policy_name: '银行卡号脱敏',
    target_field: 'bank_card',
    rule: '保留前4位和后4位，中间用*替代',
    policy_desc: '银行卡号脱敏'
  },
  {
    policy_id: 4,
    policy_name: '邮箱脱敏',
    target_field: 'email',
    rule: '邮箱@前保留1位，后面用*替代',
    policy_desc: '邮箱脱敏'
  },
  {
    policy_id: 5,
    policy_name: '地址脱敏',
    target_field: 'address',
    rule: '详细地址用*替代',
    policy_desc: '地址脱敏'
  },
  {
    policy_id: 6,
    policy_name: '姓名脱敏',
    target_field: 'name',
    rule: '姓保留，名用*替代',
    policy_desc: '姓名脱敏'
  }
])

// 搜索功能
const searchText = ref('')
const filteredPolicies = computed(() =>
  policies.value.filter(p =>
    p.policy_name.toLowerCase().includes(searchText.value.toLowerCase())
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

// 新增策略
const showAddDialog = ref(false)
const addForm = ref({
  policy_name: '',
  target_field: '',
  rule: '',
  policy_desc: ''
})
function handleAddPolicy() {
  if (!addForm.value.policy_name || !addForm.value.target_field || !addForm.value.rule) {
    ElMessage.warning('请填写完整信息')
    return
  }
  const maxId = policies.value.length ? Math.max(...policies.value.map(p => p.policy_id)) : 0
  policies.value.push({
    policy_id: maxId + 1,
    policy_name: addForm.value.policy_name,
    target_field: addForm.value.target_field,
    rule: addForm.value.rule,
    policy_desc: addForm.value.policy_desc
  })
  ElMessage.success('新增策略成功')
  showAddDialog.value = false
  addForm.value.policy_name = ''
  addForm.value.target_field = ''
  addForm.value.rule = ''
  addForm.value.policy_desc = ''
}

// 删除策略
function handleDelete(policy_id) {
  ElMessageBox.confirm('确定要删除该策略吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    policies.value = policies.value.filter(p => p.policy_id !== policy_id)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

// 预留后端接口调用位置
// onMounted(async () => {
//   const res = await fetch('/api/policy/list')
//   policies.value = await res.json()
// })
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
.pagination-bar {
  margin-top: 8px;
}
</style>