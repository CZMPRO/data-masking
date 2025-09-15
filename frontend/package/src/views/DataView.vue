<template>
  <div class="data-page">
    <el-card class="data-card" shadow="hover">
      <template #header>
        <div class="flex justify-between items-center">
          <span class="data-title">敏感数据管理</span>
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
          placeholder="搜索姓名/身份证号/手机号/邮箱"
          clearable
          class="search-input"
        >
          <template #prefix>
            <el-icon><Search /></el-icon>
          </template>
        </el-input>
        <el-button type="primary" @click="showAddDialog = true">
          <el-icon class="mr-1"><Plus /></el-icon>
          上传数据
        </el-button>
        <el-button type="success" @click="showBatchDialog = true" style="margin-left: 12px;">
          <el-icon class="mr-1"><UploadFilled /></el-icon>
          批量上传
        </el-button>
        <el-button type="warning" @click="handleMaskAll" style="margin-left: 12px;">
          <el-icon class="mr-1"><Document /></el-icon>
          一键脱敏
        </el-button>
      </div>
      <!-- 支持横向和纵向滚动 -->
      <div class="table-scroll-wrapper">
        <el-table
          :data="pagedData"
          stripe
          border
          style="min-width: 1100px; width: 100%;"
          empty-text="暂无数据"
        >
          <el-table-column prop="data_id" label="ID" width="70" align="center" />
          <el-table-column prop="name" label="姓名" align="center" />
          <el-table-column prop="gender" label="性别" width="70" align="center" />
          <el-table-column prop="id_number" label="身份证号" align="center" />
          <el-table-column prop="phone_number" label="手机号" align="center" />
          <el-table-column prop="email" label="邮箱" align="center" />
          <el-table-column prop="bank_card" label="银行卡号" align="center" />
          <el-table-column prop="upload_time" label="上传时间" width="160" align="center" />
          <el-table-column prop="uploader_name" label="上传人" width="110" align="center" />
          <el-table-column label="操作" width="120" align="center">
            <template #default="{ row }">
              <el-button type="primary" text @click="showDetail(row)">详情</el-button>
              <el-button type="danger" text @click="handleDelete(row.data_id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination-bar">
        <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :total="filteredData.length"
          layout="prev, pager, next"
          :pager-count="7"
          background
          style="margin-top: 16px; text-align: right;"
        />
      </div>
    </el-card>

    <!-- 上传数据弹窗 -->
    <el-dialog v-model="showAddDialog" title="上传敏感数据" width="500px" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="90px" class="add-form">
        <el-form-item label="姓名">
          <el-input v-model="addForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="addForm.gender" placeholder="请选择性别">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input v-model="addForm.id_number" autocomplete="off" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="addForm.phone_number" autocomplete="off" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="addForm.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="银行卡号">
          <el-input v-model="addForm.bank_card" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="showAddDialog = false">取消</el-button>
        <el-button type="primary" @click="handleAddData">确定</el-button>
      </template>
    </el-dialog>

    <!-- 数据详情弹窗 -->
    <el-dialog v-model="showDetailDialog" title="数据详情" width="500px" :close-on-click-modal="false">
      <div v-if="detailData">
        <p><b>ID：</b>{{ detailData.data_id }}</p>
        <p><b>姓名：</b>{{ detailData.name }}</p>
        <p><b>性别：</b>{{ detailData.gender }}</p>
        <p><b>身份证号：</b>{{ detailData.id_number }}</p>
        <p><b>手机号：</b>{{ detailData.phone_number }}</p>
        <p><b>邮箱：</b>{{ detailData.email }}</p>
        <p><b>银行卡号：</b>{{ detailData.bank_card }}</p>
        <p><b>上传时间：</b>{{ detailData.upload_time }}</p>
        <p><b>上传人：</b>{{ detailData.uploader_name }}</p>
      </div>
      <template #footer>
        <el-button @click="showDetailDialog = false">关闭</el-button>
      </template>
    </el-dialog>

    <!-- 批量上传数据弹窗 -->
    <el-dialog v-model="showBatchDialog" title="批量上传敏感数据" width="500px" :close-on-click-modal="false">
      <el-upload
        class="upload-demo"
        drag
        action=""
        :auto-upload="false"
        :show-file-list="true"
        :before-upload="beforeBatchUpload"
        :on-change="handleBatchFileChange"
        accept=".xlsx,.xls,.csv"
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或 <em>点击上传</em></div>
        <template #tip>
          <div class="el-upload__tip">仅支持Excel或CSV文件，字段需与模板一致</div>
        </template>
      </el-upload>
      <template #footer>
        <el-button @click="showBatchDialog = false">取消</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { Search, Plus, Document } from '@element-plus/icons-vue'
import { ElMessageBox, ElMessage } from 'element-plus'
import * as XLSX from 'xlsx'

// 模拟用户映射
const userMap = {
  1: 'admin',
  2: 'security',
  3: 'auditor',
  4: 'user1'
}

// 敏感数据（字段与数据库一致，实际应从后端获取）
const dataList = ref([
  {
    data_id: 1,
    id_number: '110101199001011234',
    name: '张三',
    gender: '男',
    phone_number: '13800000001',
    email: 'zhangsan@example.com',
    bank_card: '6222021001112345678',
    upload_time: '2025-05-19 10:00:00',
    uploader_id: 1
  },
  {
    data_id: 2,
    id_number: '110101199202022345',
    name: '李四',
    gender: '女',
    phone_number: '13900000002',
    email: 'lisi@example.com',
    bank_card: '6222021002223456789',
    upload_time: '2025-05-19 11:00:00',
    uploader_id: 4
  }
  // ...更多数据
])

// 搜索与分页
const searchText = ref('')
const pageSize = 15
const currentPage = ref(1)
const filteredData = computed(() =>
  dataList.value
    .map(d => ({
      ...d,
      uploader_name: userMap[d.uploader_id] || `用户${d.uploader_id}`
    }))
    .filter(d =>
      d.name.includes(searchText.value) ||
      d.id_number.includes(searchText.value) ||
      d.phone_number.includes(searchText.value) ||
      d.email.includes(searchText.value)
    )
)
const pagedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  return filteredData.value.slice(start, start + pageSize)
})
watch(() => searchText.value, () => { currentPage.value = 1 })

// 上传数据弹窗
const showAddDialog = ref(false)
const addForm = ref({
  name: '',
  gender: '',
  id_number: '',
  phone_number: '',
  email: '',
  bank_card: ''
})
function handleAddData() {
  if (!addForm.value.name || !addForm.value.id_number) {
    ElMessage.warning('请填写姓名和身份证号')
    return
  }
  const maxId = dataList.value.length ? Math.max(...dataList.value.map(d => d.data_id)) : 0
  dataList.value.push({
    data_id: maxId + 1,
    name: addForm.value.name,
    gender: addForm.value.gender,
    id_number: addForm.value.id_number,
    phone_number: addForm.value.phone_number,
    email: addForm.value.email,
    bank_card: addForm.value.bank_card,
    upload_time: new Date().toISOString().slice(0, 19).replace('T', ' '),
    uploader_id: 1 // 实际应取当前登录用户ID
  })
  ElMessage.success('上传成功')
  showAddDialog.value = false
  addForm.value.name = ''
  addForm.value.gender = ''
  addForm.value.id_number = ''
  addForm.value.phone_number = ''
  addForm.value.email = ''
  addForm.value.bank_card = ''
}

// 删除数据
function handleDelete(data_id) {
  ElMessageBox.confirm('确定要删除该数据吗？', '删除确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    dataList.value = dataList.value.filter(d => d.data_id !== data_id)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

// 详情弹窗
const showDetailDialog = ref(false)
const detailData = ref(null)
function showDetail(row) {
  detailData.value = row
  showDetailDialog.value = true
}

// 脱敏规则（可从后端获取，这里前端内置常见规则）
const maskRules = {
  id_number: v => v ? v.replace(/^(.{6}).*(.{4})$/, '$1********$2') : '',
  phone_number: v => v ? v.replace(/^(\d{3})\d*(\d{4})$/, '$1****$2') : '',
  email: v => v ? v.replace(/^(.).+(@.+)$/, '$1****$2') : '',
  bank_card: v => v ? v.replace(/^(.{4})\d+(.{4})$/, '$1****$2') : '',
  name: v => v ? v[0] + '*'.repeat(v.length - 1) : v
}

// 一键脱敏
function handleMaskAll() {
  ElMessageBox.confirm('确定要对所有数据进行一键脱敏吗？该操作不可恢复！', '脱敏确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    dataList.value = dataList.value.map(item => ({
      ...item,
      id_number: maskRules.id_number(item.id_number),
      phone_number: maskRules.phone_number(item.phone_number),
      email: maskRules.email(item.email),
      bank_card: maskRules.bank_card(item.bank_card),
      name: maskRules.name(item.name)
    }))
    ElMessage.success('一键脱敏成功，数据已全部脱敏')
  }).catch(() => {})
}

// 批量上传数据弹窗
const showBatchDialog = ref(false)
const batchFile = ref(null)

function beforeBatchUpload(file) {
  batchFile.value = file
  return false // 阻止自动上传
}

function handleBatchFileChange(fileObj) {
  const file = fileObj.raw
  const reader = new FileReader()
  reader.onload = (e) => {
    const data = new Uint8Array(e.target.result)
    const workbook = XLSX.read(data, { type: 'array' })
    const sheetName = workbook.SheetNames[0]
    const worksheet = workbook.Sheets[sheetName]
    const jsonData = XLSX.utils.sheet_to_json(worksheet)
    // 字段映射与校验
    const validData = jsonData.map((row, idx) => ({
      data_id: Date.now() + idx,
      name: row['姓名'] || '',
      gender: row['性别'] || '',
      id_number: row['身份证号'] || '',
      phone_number: row['手机号'] || '',
      email: row['邮箱'] || '',
      bank_card: row['银行卡号'] || '',
      upload_time: new Date().toISOString().slice(0, 19).replace('T', ' '),
      uploader_id: 1 // 实际应取当前登录用户ID
    })).filter(row => row.name && row.id_number)
    if (validData.length) {
      dataList.value.push(...validData)
      ElMessage.success(`成功导入${validData.length}条数据`)
      showBatchDialog.value = false
    } else {
      ElMessage.warning('未检测到有效数据')
    }
  }
  reader.readAsArrayBuffer(file)
}

// 预留后端接口调用位置
// onMounted(async () => {
//   const res = await fetch('/api/data/list')
//   dataList.value = await res.json()
// })
</script>

<style scoped>
.data-page {
  padding: 32px 24px;
  background: #f7f8fa;
  min-height: 100vh;
}
.data-card {
  border-radius: 12px;
  margin-bottom: 24px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.04);
}
.data-title {
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
.table-scroll-wrapper {
  max-height: 480px;
  overflow-x: auto;
  overflow-y: auto;
  /* 让表格区域出现自定义滚动条 */
}
.table-scroll-wrapper::-webkit-scrollbar {
  height: 14px;
  width: 14px;
  background: #f1f1f1;
  border-radius: 8px;
}
.table-scroll-wrapper::-webkit-scrollbar-thumb {
  background: #bdbdbd;
  border-radius: 8px;
  border: 3px solid #f1f1f1;
}
.table-scroll-wrapper::-webkit-scrollbar-thumb:hover {
  background: #909399;
}
.table-scroll-wrapper::-webkit-scrollbar-corner {
  background: #f1f1f1;
}
.add-form .el-form-item {
  margin-bottom: 18px;
}
.pagination-bar {
  margin-top: 8px;
}
</style>
