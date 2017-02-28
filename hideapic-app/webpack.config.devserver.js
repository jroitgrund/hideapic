const config = Object.assign({}, require("./webpack.config.js"));

config.output.publicPath = "http://localhost:8000/assets";
config.devServer = {
    port: 8000,
};
config.devtool = "inline-sourcemap";

module.exports = config;