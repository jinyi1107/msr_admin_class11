package com.msr.edu.controller;


import com.msr.edu.entity.Teacher;
import com.msr.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author tom
 * @since 2020-06-05
 *
 * @RestController ::::::::命令返回一个json格式
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    //读取教师信息
    //定义业务层的对象
    @Autowired
    private TeacherService teacherService ;

    @GetMapping("")  //这边注释的""里可添加数字 已改变tomcat/网页查询的路径
    public List<Teacher> list (){
        return teacherService.list(null);
    }
}

