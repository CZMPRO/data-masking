<template>
  <div class="log-page">
    <el-card class="log-card" shadow="hover">
      <template #header>
        <div class="flex justify-between items-center">
          <span class="log-title">日志管理</span>
          <div class="flex items-center">
            <el-avatar class="mr-2">Admin</el-avatar>
            <div>
              <p class="m-0">系统管理员</p>
              <p class="m-0 text-gray-500 text-sm">最后登录：2025/5/19 11:33:51</p>
            </div>
          </div>
        </div>
      </template>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="操作日志" name="operation">
          <div class="toolbar">
            <el-input
              v-model="searchOp"
              placeholder="搜索操作类型/用户/详情"
              clearable
              class="search-input"
            >
              <template #prefix>
                <el-icon><Search /></el-icon>
              </template>
            </el-input>
          </div>
          <el-table
            :data="pagedOperationLogs"
            stripe
            border
            style="width: 100%; margin-top: 16px"
            empty-text="暂无操作日志"
            @row-click="showOpDetail"
          >
            <el-table-column prop="log_id" label="日志ID" width="80" align="center" />
            <el-table-column prop="action_type" label="操作类型" align="center" />
            <el-table-column prop="username" label="操作用户" align="center" />
            <el-table-column prop="action_time" label="操作时间" width="180" align="center" />
            <el-table-column prop="action_detail" label="详情" show-overflow-tooltip align="center" />
            <el-table-column label="操作" width="80" align="center">
              <template #default="{ row }">
                <el-button type="primary" text @click.stop="showOpDetail(row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-bar">
            <el-pagination
              v-model:current-page="opCurrentPage"
              :page-size="pageSize"
              :total="filteredOperationLogs.length"
              layout="prev, pager, next"
              :pager-count="4"
              background
              style="margin-top: 16px; text-align: right;"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="审计日志" name="audit">
          <div class="toolbar">
            <el-input
              v-model="searchAudit"
              placeholder="搜索审计员/摘要/操作日志ID"
              clearable
              class="search-input"
            >
              <template #prefix>
                <el-icon><Search /></el-icon>
              </template>
            </el-input>
          </div>
          <el-table
            :data="pagedAuditLogs"
            stripe
            border
            style="width: 100%; margin-top: 16px"
            empty-text="暂无审计日志"
            @row-click="showAuditDetail"
          >
            <el-table-column prop="audit_id" label="审计ID" width="80" align="center" />
            <el-table-column prop="auditor_name" label="审计员" align="center" />
            <el-table-column prop="audit_time" label="审计时间" width="180" align="center" />
            <el-table-column prop="log_id" label="操作日志ID" align="center" />
            <el-table-column prop="report_summary" label="报告摘要" show-overflow-tooltip align="center" />
            <el-table-column label="操作" width="80" align="center">
              <template #default="{ row }">
                <el-button type="primary" text @click.stop="showAuditDetail(row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-bar">
            <el-pagination
              v-model:current-page="auditCurrentPage"
              :page-size="pageSize"
              :total="filteredAuditLogs.length"
              layout="prev, pager, next"
              :pager-count="4"
              background
              style="margin-top: 16px; text-align: right;"
            />
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <!-- 日志详情弹窗 -->
    <el-dialog v-model="showDetailDialog" title="日志详情" width="500px" :close-on-click-modal="false">
      <div v-if="detailType==='operation'">
        <p><b>日志ID：</b>{{ detailData.log_id }}</p>
        <p><b>操作类型：</b>{{ detailData.action_type }}</p>
        <p><b>操作用户：</b>{{ detailData.username }}</p>
        <p><b>操作时间：</b>{{ detailData.action_time }}</p>
        <p><b>详情：</b>{{ detailData.action_detail }}</p>
      </div>
      <div v-else>
        <p><b>审计ID：</b>{{ detailData.audit_id }}</p>
        <p><b>审计员：</b>{{ detailData.auditor_name }}</p>
        <p><b>审计时间：</b>{{ detailData.audit_time }}</p>
        <p><b>操作日志ID：</b>{{ detailData.log_id }}</p>
        <p><b>报告摘要：</b>{{ detailData.report_summary }}</p>
      </div>
      <template #footer>
        <el-button @click="showDetailDialog = false">关闭</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue'
import { Search } from '@element-plus/icons-vue'

// 模拟用户数据
const userMap = {
  1: 'admin',
  2: 'security',
  3: 'auditor',
  4: 'user1'
}

// 操作日志数据（实际应从后端获取）
const operationLogs = ref([
  { log_id: 1, action_type: '创建用户', user_id: 1, action_time: '2025-05-19 10:00:00', action_detail: '创建了用户user1' },
  { log_id: 2, action_type: '上传敏感数据', user_id: 4, action_time: '2025-05-19 10:10:00', action_detail: '上传身份证号数据' },
  { log_id: 3, action_type: '执行脱敏任务', user_id: 2, action_time: '2025-05-19 10:20:00', action_detail: '对数据101执行身份证号脱敏' },
  { log_id: 4, action_type: '删除策略', user_id: 1, action_time: '2025-05-19 10:30:00', action_detail: '删除了策略“手机号脱敏”' },
  { log_id: 5, action_type: '审核日志', user_id: 3, action_time: '2025-05-19 10:40:00', action_detail: '审核了操作日志3' },
  { log_id: 6, action_type: '修改权限', user_id: 1, action_time: '2025-05-19 10:50:00', action_detail: '修改了user2的权限' }
])

// 审计日志数据（实际应从后端获取）
const auditLogs = ref([
  { audit_id: 1, auditor_id: 3, audit_time: '2025-05-19 11:00:00', report_summary: '操作日志3审核通过', log_id: 3 },
  { audit_id: 2, auditor_id: 3, audit_time: '2025-05-19 11:10:00', report_summary: '操作日志4审核通过', log_id: 4 }
])

// 操作日志搜索与分页
const searchOp = ref('')
const pageSize = 6
const opCurrentPage = ref(1)
const filteredOperationLogs = computed(() =>
  operationLogs.value
    .map(log => ({
      ...log,
      username: userMap[log.user_id] || `用户${log.user_id}`
    }))
    .filter(log =>
      log.action_type.includes(searchOp.value) ||
      log.username.includes(searchOp.value) ||
      (log.action_detail && log.action_detail.includes(searchOp.value))
    )
)
const pagedOperationLogs = computed(() => {
  const start = (opCurrentPage.value - 1) * pageSize
  return filteredOperationLogs.value.slice(start, start + pageSize)
})
watch(() => searchOp.value, () => { opCurrentPage.value = 1 })

// 审计日志搜索与分页
const searchAudit = ref('')
const auditCurrentPage = ref(1)
const filteredAuditLogs = computed(() =>
  auditLogs.value
    .map(log => ({
      ...log,
      auditor_name: userMap[log.auditor_id] || `用户${log.auditor_id}`
    }))
    .filter(log =>
      log.auditor_name.includes(searchAudit.value) ||
      (log.report_summary && log.report_summary.includes(searchAudit.value)) ||
      String(log.log_id).includes(searchAudit.value)
    )
)
const pagedAuditLogs = computed(() => {
  const start = (auditCurrentPage.value - 1) * pageSize
  return filteredAuditLogs.value.slice(start, start + pageSize)
})
watch(() => searchAudit.value, () => { auditCurrentPage.value = 1 })

// Tab切换
const activeTab = ref('operation')

// 日志详情弹窗
const showDetailDialog = ref(false)
const detailData = ref({})
const detailType = ref('operation')
function showOpDetail(row) {
  detailData.value = row
  detailType.value = 'operation'
  showDetailDialog.value = true
}
function showAuditDetail(row) {
  detailData.value = row
  detailType.value = 'audit'
  showDetailDialog.value = true
}
</script>

<style scoped>
.log-page {
  padding: 32px 24px;
  background: #f7f8fa;
  min-height: 100vh;
}
.log-card {
  border-radius: 12px;
  margin-bottom: 24px;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.04);
}
.log-title {
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
  width: 260px;
  margin-right: 12px;
}
.pagination-bar {
  margin-top: 8px;
}
</style>
