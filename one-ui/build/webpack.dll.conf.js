var path = require('path')
var utils = require('./utils')
var webpack = require('webpack')
var dllPath = path.join(__dirname, '../static/dll/')

module.exports = {
  entry: {
    vendor: ['lodash.debounce', 'moment', 'photoswipe', 'vee-validate', 'vue', 'vue-resource', 'vue-router', 'vuex']
  },
  output: {
    path: dllPath,
    filename: '[name].dll.js',
    library: '[name]_library'
  },
  plugins: [
    // uglifjs压缩
    new webpack.optimize.UglifyJsPlugin({
      compress: {
        warnings: false
      }
    }),
    // 描述依赖对应关系的json文件
    new webpack.DllPlugin({
      path: path.join(dllPath, '[name]-mainfest.json'),
      name: '[name]_library',
      context: __dirname // 执行的上下文环境，对之后DllReferencePlugin有用
    })
  ]
}
