<template>
  <div class="login-page">
    <div class="container">
      <h1>数据脱敏治理中心</h1>

      <el-form ref="formRef" :model="form" :rules="rules" label-width="0">
        <el-form-item prop="loginInput">
          <el-input
            v-model="form.loginInput"
            placeholder="请输入邮箱或用户名"
            clearable
            size="large"
            autocomplete="username"
          />
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="form.password"
            placeholder="请输入密码"
            show-password
            size="large"
            type="password"
            autocomplete="current-password"
            style="margin-top: 12px"
          />
        </el-form-item>
        <el-button
          type="primary"
          class="submit-btn"
          size="large"
          @click="onLogin"
        >
          登录
        </el-button>
      </el-form>

      <div class="footer">
        <router-link to="/register">没有账号？去注册</router-link>
        <div class="lang-select">🌐 简体中文</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'

const router = useRouter()
const formRef = ref()

// 定义 loginMessage
const loginMessage = ref('')

// 表单数据，字段与数据库一致
const form = ref({
  loginInput: '', // 可为邮箱或用户名
  password: ''
})


// 邮箱校验
function validateEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  return re.test(email.toLowerCase())
}

// 登录处理
async function onLogin() {
  formRef.value.validate(async (valid) => {
    if (!valid) return;

    try {
      const response = await fetch('http://localhost:8085/api/home-page/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: form.value.loginInput,
          password: form.value.password
        })
      });

      if (!response.ok) {
        throw new Error('net error');
      }

      const data = await response.json();
      console.log('login response:', data);
      console.log('login responseobj:', data.message);

      if (data.message === 'Login Success') {
        loginMessage.value = 'Login Success';
        console.log('Login OK');
        // 这里可以添加登录成功后的跳转逻辑

        router.push('/dashboard');
      } else {
        loginMessage.value = 'Login Failed';
      }
    } catch (error) {
      console.error(error);
      loginMessage.value = 'error';
      ElMessage.error('faled');
    }
  });
}


</script>

<style scoped>
.login-page {
  background: url('@/assets/background_libai.png') no-repeat center center fixed;
  background-size: cover;
  height: 100vh;
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
  margin-top: 20px;
  background-color: #ff77a9;
  border: none;
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
  color: #999;
}

@media (max-width: 460px) {
  .container {
    width: 90%;
    margin: 50px auto;
  }
}
</style>
