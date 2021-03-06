package com.msr.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msr.edu.entity.Teacher;
import com.msr.edu.mapper.TeacherMapper;
import com.msr.edu.query.TeacherQuery;
import com.msr.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author tom
 * @since 2020-06-05
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public void pageQuery(Page<Teacher> pageParam, TeacherQuery teacherQuery) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("sort");

        if(teacherQuery == null){
            baseMapper.selectPage(pageParam,queryWrapper);
            return ;
        }

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        if (!org.springframework.util.StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }

        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }

        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);

        }

        baseMapper.selectPage(pageParam,queryWrapper);
    }
}
