<template>
  <div class="register-page">
    <div class="container">
      <h1>数据脱敏治理中心</h1>

      <el-form ref="formRef" :model="form" :rules="rules" label-position="top">
        <el-form-item prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>

        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>

        <el-form-item prop="password">
          <el-input v-model="form.password" type="password" show-password placeholder="设置密码" />
        </el-form-item>

        <!-- 角色选择（可选，默认数据使用人员） -->
        <!--
        <el-form-item prop="role_id">
          <el-select v-model="form.role_id" placeholder="请选择角色">
            <el-option label="系统管理员" :value="1" />
            <el-option label="数据安全管理人员" :value="2" />
            <el-option label="日志审核人员" :value="3" />
            <el-option label="数据使用人员" :value="4" />
          </el-select>
        </el-form-item>
        -->

        <el-button class="submit-btn" type="primary" @click="onSubmit">
          立即注册
        </el-button>
      </el-form>

      <div class="footer">
        <router-link to="/">已有账号？去登录</router-link>
        <div class="lang-select">🌐 简体中文</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const formRef = ref()

// 字段与数据库一致
const form = reactive({
  email: '',
  username: '',
  password: '',
  // role_id: 4 // 可选，默认数据使用人员
})

const rules = {
  email: [
    { required: true, message: '请输入邮箱', trigger: 'blur' },
    { type: 'email', message: '邮箱格式不正确', trigger: ['blur', 'change'] },
    { max: 20, message: '邮箱不能超过20字符', trigger: 'blur' }
  ],
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名2-20字符', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码6-20字符', trigger: 'blur' }
  ]
  // role_id: [
  //   { required: true, message: '请选择角色', trigger: 'change' }
  // ]
}

const onSubmit = () => {
  formRef.value.validate(async (valid) => {
    if (valid) {
      // 预留后端注册接口调用
      // try {
      //   await apiRegister({
      //     email: form.email,
      //     username: form.username,
      //     password: form.password,
      //     role_id: form.role_id || 4
      //   })
      //   ElMessage.success('注册成功！')
      //   router.push('/login')
      // } catch (e) {
      //   ElMessage.error(e.message || '注册失败')
      //   return
      // }

      // 模拟注册逻辑
      ElMessage.success('注册成功！')
      router.push('/login')
    } else {
      ElMessage.error('请完善注册信息')
    }
  })
}
</script>

<style scoped>
.register-page {
  height: 100vh;
  background: url('@/assets/background_libai.png') no-repeat center center fixed;
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;
}

.container {
  width: 400px;
  background: rgba(255, 255, 255, 0.95);
  padding: 30px;
  border-radius: 15px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  text-align: center;
}

h1 {
  font-size: 32px;
  margin-bottom: 20px;
  color: #333;
}

.submit-btn {
  width: 100%;
  background-color: #ff77a9;
  border: none;
  font-size: 16px;
  margin-top: 20px;
}

.footer {
  margin-top: 20px;
  font-size: 13px;
  color: #666;
  display: flex;
  justify-content: space-between;
}

.footer a {
  color: #409eff;
  text-decoration: none;
}

.lang-select {
  font-size: 13px;
  color: #999;
}

@media (max-width: 460px) {
  .container {
    width: 90%;
    margin: 50px auto;
  }
}
</style>
