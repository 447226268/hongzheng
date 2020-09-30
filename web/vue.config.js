const path = require('path')

function resolve(dir) {
  return path.join(__dirname, dir)
}

module.exports = {
  publicPath: '/',
  css: {
    sourceMap: false
  },
  productionSourceMap: false,
  chainWebpack: config => {
    config.resolve.alias
      .set('@', resolve('src'))
      .set('@assets', resolve('src/assets'))
      .set('@components', resolve('src/components'))
      .set('@utils', resolve('src/utils'))
      .set('@views', resolve('src/views'))
      .set('@api', resolve('src/api'))
  },
  devServer: {
    disableHostCheck: true,
    // proxy: {
    //   '/front': {
    //     target: process.env.VUE_APP_BASE_API,
    //     changeOrigin: true,
    //     ws: true,
    //     pathRewrite: {
    //       '^/front': ''
    //     }
    //   }
    // }
  }
}