const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  runtimeCompiler: true, //令其在运行时可以编译，这样路由才能生效
  // publicPath: process.env.NODE_ENV === "production" ? "/todolist/" : "/",
  // outputDir: "todolist",
  publicPath: "/",
});
