package com.msr.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.common.constants.ResultCodeEnum;
import com.msr.common.exception.MSRException;
import com.msr.common.vo.R;
import com.msr.edu.entity.Teacher;
import com.msr.edu.query.TeacherQuery;
import com.msr.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
@Api(description="讲师管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    //读取教师信息
    //定义业务层的对象
    @Autowired
    private TeacherService teacherService ;

    /**
     * 所有讲师列表
     * @return
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("list")  //这边注释的""里可添加数字 已改变tomcat/网页查询的路径
    public R list (){
        List<Teacher> teacherList = teacherService.list(null);
        return R.ok().data("list",teacherList);
    }

    /**
     * 删除讲师函数
     */
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")  //路径传参
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
       teacherService.removeById(id);
       return R.ok();
    }

    /**
     * 分页教师列表
     * @param page
     * @param limit
     * @param teacherQuery
     * @return
     */
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(

            @ApiParam(name= "page" , value = "当前代码" , required = true)
        @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
        @PathVariable Long limit,

            @ApiParam (name = "teacherQuery" , value = "查询对象" , required = false)


            TeacherQuery teacherQuery){

             if(page <= 0 || limit <= 0){
            //throw new MSRException(21003, "参数不正确1");
            throw new MSRException(ResultCodeEnum.PARAM_ERROR);
                }

            Page<Teacher> pageParam = new Page<>(page,limit);

            teacherService.pageQuery(pageParam,teacherQuery);
            List<Teacher> records = pageParam.getRecords();
            long total = pageParam.getTotal();

            return R.ok().data("total" , total).data("rows" , records) ;
        }

    /**
     * 新增讲师
     * @param teacher
     * @return
     */
    @ApiOperation(value = "新增讲师")
     @PostMapping("save")
     public R save(
        @ApiParam(name = "teacher" , value = "讲师对象" , required = true)
        @RequestBody Teacher teacher ) {  //传递的是json对象  requestBody是将json对象转化为Java//  ～ ：～

        teacherService.save(teacher);
        return R.ok();
        }

    /**
     * 根据ID查询教师
     */
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("{id}")
    public R updateById(
        @ApiParam(name = "id" , value = "讲师ID" , required = true)
        @PathVariable String id ) {

        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    /**
     * 根据ID修改
     */
    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }



}

