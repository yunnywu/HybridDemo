var path = require('path');
module.exports = {
  entry: "./src/index.js",
  output: {
      path:  path.resolve(__dirname, 'dist'),
      filename: "native.js",
      library: 'native',
      libraryTarget: 'umd',
      umdNamedDefine: true
  },
  module: {
     loaders: [
      {test: /\.js$/, exclude: /node_modules/, loader: 'babel'},
      {test: /\.json$/, loader: 'json'}
    ]
  },

}