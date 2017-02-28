const path = require("path");
const webpack = require("webpack");

const ExtractTextPlugin = require("extract-text-webpack-plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const HtmlWebpackHarddiskPlugin = require('html-webpack-harddisk-plugin');
const ScriptExtHtmlWebpackPlugin = require("script-ext-html-webpack-plugin");

const extractSass = new ExtractTextPlugin({
    filename: "[name].[contenthash].css",
    disable: process.env.NODE_ENV !== "production",
});

const minify = {
    "removeComments": true,
    "removeCommentsFromCDATA": true,
    "removeCDATASectionsFromCDATA": true,
    "collapseWhitespace": true,
    "conservativeCollapse": true,
    "useShortDoctype": true,
    "keepClosingSlash": true,
    "minifyJS": true,
    "minifyCSS": true,
    "removeScriptTypeAttributes": true,
    "removeStyleTypeAttributes": true,
}

module.exports = {
    bail: true,
    entry: {
        pic: "./src/pic.ts",
        "new-pic": "./src/new-pic.ts",
    },
    output: {
        filename: "[name].js",
        publicPath: "assets",
        path: path.join(__dirname, "../hideapic-server/src/main/resources/assets"),
    },
    resolve: {
        extensions: [".scss", ".ts", ".js"]
    },
    devServer: {
        inline: true,
    },
    module: {
        loaders: [
            {
                test: /style\.scss$/,
                use: extractSass.extract({
                    use: ["css-loader?sourceMap=true", "postcss-loader", "sass-loader?sourceMap=true"],
                    fallback: "style-loader"
                }),
            },
            {
                test: /\.ts$/,
                use: ["ts-loader", "tslint-loader"]
            },
            {
                test: /\.(jpg|png)$/,
                loader: 'file-loader',
                options: {
                    name: '[path][name].[hash].[ext]',
                },
            },
        ],
    },
    plugins: [
        new HtmlWebpackPlugin({
            alwaysWriteToDisk: true,
            minify,
            template: "src/pic.html",
            filename: "../me/roitgrund/hideapic/pic.mustache",
            chunks: ["pic"],
        }),
        new HtmlWebpackPlugin({
            alwaysWriteToDisk: true,
            minify,
            template: "src/new-pic.html",
            filename: "../me/roitgrund/hideapic/new-pic.mustache",
            chunks: ["new-pic"],
        }),
        new HtmlWebpackHarddiskPlugin(),
        new ScriptExtHtmlWebpackPlugin({
            defaultAttribute: 'async'
        }),
        extractSass
    ],
};
