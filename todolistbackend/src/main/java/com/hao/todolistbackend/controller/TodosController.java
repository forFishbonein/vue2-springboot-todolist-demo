/*
 * @Time : 2022/10/15 23:53
 * @Author : hao
 * @File : TodosController.java
 * @Software : IntelliJ IDEA
 */
package com.hao.todolistbackend.controller;

import com.hao.todolistbackend.dao.pojo.Todos;
import com.hao.todolistbackend.dao.vo.Result;
import com.hao.todolistbackend.dao.vo.TodosVo;
import com.hao.todolistbackend.service.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodosController {

    @Autowired
    private TodosService todosService;

    @GetMapping("getAll")
    public Result getAllTodos(){

        List<TodosVo> allTodos = todosService.getAllTodos();
        return Result.success(allTodos);
    }

    @PostMapping("updateAll")
    public Result updateAllTodos(@RequestBody List<TodosVo> allNewTodos){

        todosService.updateAllTodos(allNewTodos);
//        List<Todos> allTodos = todosService.getAllTodos();
        return Result.success(null);
    }

}
