/*
 * @Time : 2022/10/15 23:12
 * @Author : hao
 * @File : Todos.java
 * @Software : IntelliJ IDEA
 */
package com.hao.todolistbackend.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todos {

    //使用MP的id生成策略
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private int done;

//    @TableLogic
//    private String deleted;
}
