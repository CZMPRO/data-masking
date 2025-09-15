<template>
    <!-- 只保留主内容区 -->
    <div>
        <!-- 个人信息 -->
        <el-card class="user-info-card" shadow="hover" style="margin-bottom: 20px;">
            <el-row>
                <el-col :span="12">
                    <div class="info-label">👑 当前角色：</div>
                    <div class="info-value">系统管理员</div>
                </el-col>
                <el-col :span="12">
                    <div class="info-label">🕐 最后登录：</div>
                    <div class="info-value">2025/5/19 11:35:25</div>
                </el-col>
            </el-row>
        </el-card>
        <!-- 统计卡片 -->
        <el-row :gutter="20" class="stats-row" style="margin-bottom: 20px;">
            <el-col :span="6">
                <el-card class="stat-card">
                    <div class="stat-title">总用户数</div>
                    <div class="stat-value">128</div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="stat-card">
                    <div class="stat-title">脱敏策略数</div>
                    <div class="stat-value">24</div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="stat-card">
                    <div class="stat-title">待处理任务</div>
                    <div class="stat-value">7</div>
                </el-card>
            </el-col>
            <el-col :span="6">
                <el-card class="stat-card">
                    <div class="stat-title">今日操作日志</div>
                    <div class="stat-value">32</div>
                </el-card>
            </el-col>
        </el-row>
        <!-- 最近任务 和 操作日志 并排 -->
        <el-row :gutter="20" style="height: calc(100vh - 370px);">
            <el-col :span="12" class="section-col">
                <el-card class="recent-section" style="height: 100%; overflow-y: auto;">
                    <template #header>
                        <div class="section-title">最近任务</div>
                    </template>
                    <el-timeline>
                        <el-timeline-item v-for="(task, index) in recentTasks" :key="index" :timestamp="task.time">
                            {{ task.name }}
                        </el-timeline-item>
                    </el-timeline>
                </el-card>
            </el-col>
            <el-col :span="12" class="section-col">
                <el-card class="recent-section" style="height: 100%; overflow-y: auto;">
                    <template #header>
                        <div class="section-title">最近操作日志</div>
                    </template>
                    <el-table :data="operationLogs" size="small" style="width: 100%">
                        <el-table-column prop="action" label="操作" />
                        <el-table-column prop="operator" label="操作人" width="120" />
                        <el-table-column prop="time" label="时间" width="180" />
                    </el-table>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const recentTasks = ref([
    { name: '身份证号脱敏任务', time: '2023-05-19 10:30' },
    { name: '银行卡号脱敏任务', time: '2023-05-19 09:15' },
    { name: '手机号脱敏任务', time: '2023-05-19 08:45' },
    { name: '邮箱地址脱敏任务', time: '2023-05-18 16:20' },
]);

const operationLogs = ref([
    { action: '创建新策略', operator: 'admin', time: '2023-05-19 11:20' },
    { action: '上传敏感数据', operator: 'user1', time: '2023-05-19 10:05' },
    { action: '修改用户权限', operator: 'admin', time: '2023-05-19 09:30' },
    { action: '审核操作日志', operator: 'auditor', time: '2023-05-18 17:15' },
]);
</script>

<style scoped>
.user-info-card {
    font-size: 14px;
    color: #333;
}

.info-label {
    font-weight: 600;
    margin-bottom: 5px;
    color: #606266;
}

.info-value {
    font-size: 16px;
    font-weight: bold;
    color: #409eff;
}

.stat-card {
    text-align: center;
    padding: 20px 0;
}

.stat-title {
    color: #909399;
    margin-bottom: 10px;
    font-weight: 500;
}

.stat-value {
    font-size: 28px;
    font-weight: bold;
    color: #303133;
}

.section-title {
    font-size: 18px;
    font-weight: 700;
    color: #303133;
}

.section-col {
    display: flex;
    flex-direction: column;
}

.recent-section {
    background: #fff;
    flex: 1;
}

.el-timeline {
    padding-left: 10px;
}
</style>
