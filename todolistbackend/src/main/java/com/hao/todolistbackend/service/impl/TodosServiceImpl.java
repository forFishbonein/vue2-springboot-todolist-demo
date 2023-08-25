/*
 * @Time : 2022/10/15 23:39
 * @Author : hao
 * @File : TodosServiceImpl.java
 * @Software : IntelliJ IDEA
 */
package com.hao.todolistbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hao.todolistbackend.dao.mapper.TodosMapper;
import com.hao.todolistbackend.dao.pojo.Todos;
import com.hao.todolistbackend.dao.vo.TodosVo;
import com.hao.todolistbackend.service.TodosService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodosServiceImpl implements TodosService {

    @Autowired
    private TodosMapper todosMapper;

    @Override
    public List<TodosVo> getAllTodos() {

        List<Todos> todos = todosMapper.selectList(null);
        List<TodosVo> todosVo = new ArrayList<>();
        for (Todos todo : todos) {
            TodosVo todoVo = new TodosVo();
            BeanUtils.copyProperties(todo, todoVo);
            if(todo.getDone()==0){
                todoVo.setDone(false);
            }else{
                todoVo.setDone(true);
            }
            todosVo.add(todoVo);
        }
        return todosVo;
    }

    @Override
    public void updateAllTodos(List<TodosVo> newTodos) {

        if (newTodos!=null && !newTodos.isEmpty()) {
            ArrayList<Integer> array = new ArrayList<Integer>();
            //        List<Todos> todos = new ArrayList<>();
            for (TodosVo newTodo : newTodos) {
                Todos todo = new Todos();
                BeanUtils.copyProperties(newTodo, todo);
                if (!newTodo.isDone()) {
                    todo.setDone(0);
                } else {
                    todo.setDone(1);
                }
                //            todos.add(todo);
                //根据id更新
                int update = todosMapper.updateById(todo);
                if (update == 0) { //如果没更新就插入
                    int insert = todosMapper.insert(todo);
                }
                //收集所有的id
                array.add(todo.getId());
            }
            if(array!=null && !array.isEmpty()) {
                //把不涉及插入和更新的进行删除
                LambdaQueryWrapper<Todos> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.notIn(Todos::getId, array);
                todosMapper.delete(queryWrapper);
            }
        } else {
            todosMapper.delete(null);
        }
    }
}
