<template>
  <div class="container">
    <el-card class="data-card" shadow="hover">
      <template #header>
        <div class="flex justify-between items-center">
          <span class="data-title">我的权限与数据</span>
          <div class="flex items-center">
            <el-avatar class="mr-2">Admin</el-avatar>
            <div>
              <p class="m-0">当前用户：{{ currentUser }}</p>
              <p class="m-0 text-gray-500 text-sm">角色：{{ userRole }}</p>
            </div>
          </div>
        </div>
      </template>

      <div class="permissions-table">
        <h3>我的权限</h3>
        <el-table :data="userPermissions" style="width: 100%" border>
          <el-table-column prop="permissionId" label="权限ID" width="120" />
          <el-table-column prop="permissionName" label="权限名称" />
          <el-table-column prop="description" label="描述" />
        </el-table>
      </div>

      <div class="sensitive-data-table">
        <h3>敏感数据列表</h3>
        <el-table :data="pagedData" stripe border style="width: 100%;">
          <el-table-column prop="data_id" label="ID" width="70" align="center" />
          <el-table-column prop="name" label="姓名" align="center" />
          <el-table-column prop="gender" label="性别" width="70" align="center">
            <template #default="scope">
              <span v-if="permissions.has('VIEW_GENDER')">{{ scope.row.gender }}</span>
              <span v-else>{{ maskGender(scope.row.gender) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="id_number" label="身份证号" align="center">
            <template #default="scope">
              <span v-if="permissions.has('VIEW_ID_CARD')">{{ scope.row.id_number }}</span>
              <span v-else>{{ maskIdCard(scope.row.id_number) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="phone_number" label="手机号" align="center">
            <template #default="scope">
              <span v-if="permissions.has('VIEW_PHONE_NUMBER')">{{ scope.row.phone_number }}</span>
              <span v-else>{{ maskPhoneNumber(scope.row.phone_number) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="email" label="邮箱" align="center">
            <template #default="scope">
              <span v-if="permissions.has('VIEW_EMAIL')">{{ scope.row.email }}</span>
              <span v-else>{{ maskEmail(scope.row.email) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="bank_card" label="银行卡号" align="center">
            <template #default="scope">
              <span v-if="permissions.has('VIEW_BANK_CARD')">{{ scope.row.bank_card }}</span>
              <span v-else>{{ maskBankCard(scope.row.bank_card) }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="upload_time" label="上传时间" width="160" align="center" />
          <el-table-column prop="uploader_name" label="上传人" width="110" align="center" />
        </el-table>

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
      </div>
    </el-card>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref, computed, onMounted } from 'vue';
import { ElMessage } from 'element-plus';

// 用户信息和权限
const currentUser = ref('admin');
const userRole = ref('管理员');
const userPermissions = ref([
  { permissionId: 'VIEW_ID_CARD', permissionName: '查看身份证号', description: '可以查看完整的身份证号' },
  { permissionId: 'VIEW_PHONE_NUMBER', permissionName: '查看手机号', description: '可以查看完整的手机号' },
  { permissionId: 'VIEW_EMAIL', permissionName: '查看邮箱', description: '可以查看完整的邮箱地址' },
  { permissionId: 'VIEW_BANK_CARD', permissionName: '查看银行卡号', description: '可以查看完整的银行卡号' },
]);

// 权限集合
const permissions = ref(new Set(['VIEW_ID_CARD', 'VIEW_PHONE_NUMBER', 'VIEW_EMAIL']));

// 敏感数据
const dataList = ref([]);

// 配置 Axios 默认请求头
axios.defaults.headers.common['X-Username'] = 'user1'; // 动态设置当前登录用户名

// 获取数据方法（调用后端 API）
async function fetchData() {
  try {
    const response = await axios.get('http://localhost:8085/api/data-page/list');
    console.log('Sensitive Data:', response.data);

    // 确保后端返回的是 JSON 格式
    if (!response.data || typeof response.data !== 'object') {
      throw new Error('Response is not in JSON format');
    }

    dataList.value = response.data;
  } catch (error) {
    console.error('Error fetching data:', error);
    ElMessage.error('加载数据失败: ' + (error.response?.data?.message || error.message));
  }
}

// 脱敏规则
function maskIdCard(idCard) {
  if (!idCard || idCard.length < 2) return idCard;
  return idCard.charAt(0) + '*'.repeat(idCard.length - 2) + idCard.charAt(idCard.length - 1);
}

function maskPhoneNumber(phoneNumber) {
  if (!phoneNumber || phoneNumber.length < 8) return phoneNumber;
  return phoneNumber.substring(0, 3) + '*'.repeat(phoneNumber.length - 7) + phoneNumber.substring(phoneNumber.length - 4);
}

function maskEmail(email) {
  if (!email) return email;
  const atIndex = email.indexOf('@');
  if (atIndex === -1) return email;
  return email.substring(0, 1) + '*'.repeat(email.length - atIndex) + email.substring(atIndex);
}

function maskBankCard(bankCard) {
  if (!bankCard || bankCard.length < 4) return bankCard;
  return '*'.repeat(bankCard.length - 4) + bankCard.substring(bankCard.length - 4);
}

function maskGender(gender) {
  if (!gender || gender.length < 1) return gender;
  return gender.substring(0, 1) + '某';
}

// 分页功能
const searchText = ref('');
const pageSize = 10;
const currentPage = ref(1);
const filteredData = computed(() =>
  dataList.value.filter((d) =>
    d.name.includes(searchText.value) ||
    d.id_number.includes(searchText.value) ||
    d.phone_number.includes(searchText.value) ||
    d.email.includes(searchText.value)
  )
);
const pagedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  return filteredData.value.slice(start, start + pageSize);
});

// 组件挂载后初始化数据
onMounted(async () => {
  await fetchData();
});
</script>

<style scoped>
.container {
  padding: 20px;
}
.data-card {
  margin-bottom: 20px;
}
.data-title {
  font-size: 20px;
  font-weight: bold;
}
.permissions-table,
.sensitive-data-table {
  margin-bottom: 20px;
}
h3 {
  margin-bottom: 10px;
  color: #333;
}
.pagination-bar {
  margin-top: 10px;
}
</style>