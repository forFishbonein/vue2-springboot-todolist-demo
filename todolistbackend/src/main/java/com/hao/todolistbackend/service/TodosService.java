package com.hao.todolistbackend.service;

import com.hao.todolistbackend.dao.pojo.Todos;
import com.hao.todolistbackend.dao.vo.TodosVo;

import java.util.List;

public interface TodosService {

    List<TodosVo> getAllTodos();

    void updateAllTodos(List<TodosVo> newTodos);
}
