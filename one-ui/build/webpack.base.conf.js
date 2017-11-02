var path = require('path')
var utils = require('./utils')
var config = require('../config')
var vueLoaderConfig = require('./vue-loader.conf')

function resolve (dir) {
  return path.join(__dirname, '..', dir)
}

module.exports = {
  // 获取多入口, 注意这个路径， 至于为什么是 ./src仍然需要了解，我觉得应该是 ../src
  cache: true,
  entry: utils.getEntries('./src/module/**/*.js'),
  output: {
    path: config.build.assetsRoot,
    filename: '[name].js',
    publicPath: process.env.NODE_ENV === 'production'
      ? config.build.assetsPublicPath
      : config.dev.assetsPublicPath
  },
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src'),
      'components': resolve('src/common/components'),
      'mixins': resolve('src/common/mixins'),
      'utils': resolve('src/common/utils'),

      'element-ui': resolve('node_modules/element-ui'),
      'moment': 'moment/min/moment.min.js',
      'vuex': 'vuex/dist/vuex.min.js',
      'vue-resource': 'vue-resource/dist/vue-resource.min.js',
      'vue-router': 'vue-router/dist/vue-router.min.js'
    }
  },
  externals: {
    jquery: 'window.$'
  },
  module: {
    noParse:[/vue\.runtime\.min/, /vue-router\.min/, /vue-resource\.min/, /vuex\.min/, /moment\.min/],
    rules: [
      {
        test: /\.(js|vue)$/,
        loader: 'eslint-loader',
        enforce: 'pre',
        include: [resolve('src'), resolve('test')],
        options: {
          formatter: require('eslint-friendly-formatter')
        }
      },
      {
        test: /\.vue$/,
        loader: 'vue-loader',
        options: vueLoaderConfig
      },
      {
        test: /\.js$/,
        loader:[ 'happypack/loader?id=js'],
        include: [resolve('src')],
        exclude: [path.resolve('../../node_modules')]
      },
      {
        test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('img/[name].[hash:7].[ext]')
        }
      },
      {
        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
        loader: 'url-loader',
        options: {
          limit: 10000,
          name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
        }
      }
    ]
  }
}
