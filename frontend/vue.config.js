// // vite.config.js
// import { defineConfig } from 'vite'
// import vue from '@vitejs/plugin-vue'

// // const { defineConfig } = require("@vue/cli-service");
// // module.exports = defineConfig({
// //   transpileDependencies: true,
// // });



// export default defineConfig({
//   plugins: [vue()],
//   server: {
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8085', // 后端服务的地址和端口
//         changeOrigin: true,
//         secure: false,
//         configure: (proxy, options) => {
//           proxy.on('error', (err, req, res) => {
//             console.error('Proxy error:', err);
//             res.writeHead(500, { 'Content-Type': 'application/json' });
//             res.end(JSON.stringify({ error: 'Proxy error' }));
//           });
//         }
//       }
//     }
//   }
// })