var utils = require('./utils')
var webpack = require('webpack')
var config = require('../config')
var merge = require('webpack-merge')
var baseWebpackConfig = require('./webpack.base.conf')
var HtmlWebpackPlugin = require('html-webpack-plugin')
var FriendlyErrorsPlugin = require('friendly-errors-webpack-plugin')
var HappyPackPlugin = require('happypack');

// add hot-reload related code to entry chunks
Object.keys(baseWebpackConfig.entry).forEach(function (name) {
  baseWebpackConfig.entry[name] = ['./build/dev-client'].concat(baseWebpackConfig.entry[name])
})

module.exports = merge(baseWebpackConfig, {
  module: {
    rules: utils.styleLoaders({ sourceMap: config.dev.cssSourceMap })
  },
  // cheap-module-eval-source-map is faster for development
  devtool: '#cheap-module-eval-source-map',
  plugins: [
    new webpack.DllReferencePlugin({
      name: 'vendor_library',
      context: __dirname,
      manifest: require('../static/dll/vendor-mainfest.json')
    }),
    new HappyPackPlugin({
      id: 'js',
      loaders: [{
        loader: 'babel-loader?cacheDirectory=true'
      }],
      threads: 4
    }),
    new HappyPackPlugin({
      id: 'css',
      threads: 4,
      loaders: [
        'vue-loader',
        'css-loader',
        'less-loader',
      ],
    }),
    new webpack.DefinePlugin({
      'process.env': config.dev.env
    }),
    // https://github.com/glenjamin/webpack-hot-middleware#installation--usage
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoEmitOnErrorsPlugin(),
    new FriendlyErrorsPlugin(),
    ...utils.getHtmlWebpackPlugins()
  ]
})
