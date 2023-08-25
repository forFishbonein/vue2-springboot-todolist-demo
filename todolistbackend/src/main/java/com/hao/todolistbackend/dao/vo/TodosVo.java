/*
 * @Time : 2022/10/17 14:08
 * @Author : hao
 * @File : TodosVo.java
 * @Software : IntelliJ IDEA
 */
package com.hao.todolistbackend.dao.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodosVo {

    private Integer id;

    private String title;

    private boolean done;

}
