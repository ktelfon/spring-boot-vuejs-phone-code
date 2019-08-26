// vue.config.js
module.exports = {
  // proxy all webpack dev-server requests starting with /api
  // to our Spring Boot backend (localhost:8088) using http-proxy-middleware
  // see https://cli.vuejs.org/config/#devserver-proxy
  // devServer: {
  //   proxy: {
  //     '/api': {
  //       target: 'http://127.0.0.1:8098', // this configuration needs to correspond to the Spring Boot backends' application.properties server.port
  //       ws: true,
  //       changeOrigin: true,
  //       pathRewrite: { '^/api': '' },
  //     }
  //   }
  // },

  devServer: {
    host: '127.0.0.1',          //local ip
    port: 8080,
    historyApiFallback: true,
    inline: true,
    proxy: {
      '/api/*': {
        target: 'http://127.0.0.1:8098',
        pathRewrite: { '^/api': '' },
        secure: false,
        changeOrigin: true
      }
    }
  },
  // Change build paths to make them Maven compatible
  // see https://cli.vuejs.org/config/
  outputDir: 'target/dist',
  assetsDir: 'static'
};
