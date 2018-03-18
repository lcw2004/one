const path = require('path')
const utils = require('./utils')
const webpack = require('webpack')
const dllPath = path.join(__dirname, '../static/dll/')
const CleanWebpackPlugin = require('clean-webpack-plugin')

const cleanOptions = {
  root: path.resolve(__dirname, '../'),
  exclude:  ['vendor-mainfest.json'],
  verbose: true,
  dry: false
}

module.exports = {
  entry: {
    vendor: ['lodash.debounce', 'moment', 'photoswipe', 'vue', 'vue-router', 'vuex', 'axios', 'vee-validate', 'kindeditor', 'url-template', 'babel-polyfill']
  },
  output: {
    path: dllPath,
    filename: '[name].[hash].dll.js',
    library: '[name]_library'
  },
  plugins: [
    // 清除dll文件，重新生成
    new CleanWebpackPlugin([dllPath], cleanOptions),
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
