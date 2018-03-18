function MyWebPackPluginForOne() {
}

String.prototype.replaceAll = function (s1,s2){
  return this.replace(new RegExp(s1,"gm"),s2);
}

MyWebPackPluginForOne.prototype.apply = function (compiler) {
  compiler.plugin('compilation', function (compilation, options) {
    compilation.plugin('html-webpack-plugin-before-html-processing', function (htmlPluginData, callback) {
      console.log('---------------------------')
      console.log('replace all /static to ./static')
      fill(htmlPluginData.assets.css)
      fill(htmlPluginData.assets.js)
      htmlPluginData.html = htmlPluginData.html.replaceAll("/static", './static')
      console.log('---------------------------')

      callback(null, htmlPluginData);
    });
  });
};

function fill (array) {
  for (let i = 0; i < array.length; i++) {
    array[i] = '.' + array[i]
  }
}

module.exports = MyWebPackPluginForOne
