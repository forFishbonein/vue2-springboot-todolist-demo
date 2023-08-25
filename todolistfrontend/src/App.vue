<template>

  <div id="root">
    <div class="todo-container">
      <div class="uploads">
        <!-- action="http://fishbones.com.cn:9000/file/upload" -->
        <el-upload
                list-type="picture-card"
                ref="upload"
                action="http://fishbones.com.cn:9000/file/upload"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                :on-success="success"
                :on-error="error"
                multiple
                :limit="1"
                :on-exceed="handleExceed"
                :file-list="this.fileList">
            <i class="el-icon-plus"></i>
      </el-upload>
      </div>
      <div class="warning">
          <p>图片右上角出现绿色钩才代表上传成功！</p>
          <p>请不要上传多张图片，请不要多次上传，一人一天一次！</p>
          <p>注意：上传成功和失败都会有消息提示！</p>
        </div>
      <div class="todo-wrap">
        <MyHeader @addTodo="addTodo"/>
        <List
            :todos="todos"
        />
        <MyFooter
            :todos="todos"
            @checkAllTodo="checkAllTodo"
            @clearAllDoneTodo="clearAllDoneTodo"
        />
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import MyHeader from "@/components/MyHeader";
import List from "@/components/List";
import MyFooter from '@/components/MyFooter';
import pubsub from "pubsub-js";

export default {
  name: "App",
  components:{
    List,
    MyFooter,
    MyHeader
  },
  data() {
    return {
      // todos: [
      //   {id: '001', title: '吃饭', done: false},
      //   {id: '002', title: "睡觉", done: true},
      //   {id: '003', title: '打代码', done: false}
      // ]
      todos: [],
      // 从本地获取：
      // todos:JSON.parse(localStorage.getItem('todos')) || []
      dialogVisible: false,
      fileList: [],
    }
  },
  methods:{
    getTodos(){
      axios.get('http://fishbones.com.cn:9000/getAll').then(
					response => {
						this.todos = response.data.data
					},
					error => {
						console.log('请求失败了', error.message)
					}
		   )
    },
    //添加的todo
    addTodo(todo){
      // console.log('我是app组件，我收到了数据');
      this.todos.unshift(todo);
    },
    checkTodo(id){
      const todo = this.todos.find(todo => todo.id === id);
      todo.done = !todo.done;
    },
    deleteTodo(_, id){
      this.todos = this.todos.filter(todo => todo.id !== id);
    },
    checkAllTodo(done){
      this.todos.forEach(todo => todo.done = done);
    },
    clearAllDoneTodo(){
      this.todos = this.todos.filter(todo => !todo.done)
    },
    updateTodo(id, title){
      this.todos.forEach(todo => {
         if(todo.id === id) todo.title = title;
      })
    },
    handleRemove(file, fileList) {
        console.log(file, fileList);
    },
    handlePreview(file) {
        console.log(file);
    },
    handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
    },
    success(){
      this.$message.success("上传成功！")
    },
    error(){
      this.$message.warning("上传失败！")
    }
  },
  watch:{
    // 深度监视
    todos:{
      deep: true, // 深度监视当我监视数组中的对象的某个属性的变化它也会产生反应
      handler(newValue) {
        // console.log(newValue)
        // 本地存储存的是key和value都是字符串
        // 数据存放在本地存储中
        // localStorage.setItem("todos", JSON.stringify(newValue))
        // 从后端获取数据：
        axios.post('http://fishbones.com.cn:9000/updateAll', newValue).then(
					response => {
						// this.todos = response.data.data //不要再重新返回了，否则又会触发watch，造成死循环
            // console.log(newValue)
					},
					error => {
						console.log('请求失败了', error.message)
					}
        )
      }
    },
  },
  //已挂在绑定事件总线
  mounted() {
    this.getTodos();
    this.$bus.$on('checkTodo', this.checkTodo);
    this.$bus.$on('updateTodo', this.updateTodo);
    this.pubId = pubsub.subscribe('deleteTodo', this.deleteTodo);
  },
  //被卸载注意解绑
  beforeMount() {
    this.$bus.$off('checkTodo');
    this.$bus.$off('updateTodo');
    pubsub.unsubscribe(this.pubId); //取消订阅的方式与取消定时器的方式是类似的，记住
  }
}
</script>

<style>
/*base*/
body {
  background: #fff;
}

.btn {
  display: inline-block;
  padding: 4px 12px;
  margin-bottom: 0;
  font-size: 14px;
  line-height: 20px;
  text-align: center;
  vertical-align: middle;
  cursor: pointer;
  box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
  border-radius: 4px;
}

.btn-danger {
  color: #fff;
  background-color: #da4f49;
  border: 1px solid #bd362f;
}

.btn-danger:hover {
  color: #fff;
  background-color: #bd362f;
}

.btn-edit{
  color: #fff;
  background-color: skyblue;
  border: 1px solid rgb(103, 159, 180);
  margin-right: 5px;
}

.btn:focus {
  outline: none;
}

.todo-container {
  width: 600px;
  margin: 0 auto;
}
.todo-container .todo-wrap {
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
}
.uploads{
  display: flex;
  justify-content: center;
  margin-bottom: 30px;
}
.warning{
  display: flex;
  flex-direction: column;
  color: #bd362f;
  margin-bottom: 30px;
}
</style>


