package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.Rr;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.query.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2021-02-22
 */
@Api(description = "讲师管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;



    @ApiOperation(value = "所有讲师列表")
    @GetMapping("list")
    public Rr list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return Rr.ok().data("list",list);
    }

    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("{id}")
    public Rr removeById(
            @ApiParam(name = "id", value = "讲师id",required = true)
            @PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        return Rr.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "当前页码",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "limit",value = "每页显示数",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "teacherQuery",value = "查询条件实体",required = true,paramType = "path")
    }
    )
    @PostMapping("pageList/{page}/{limit}")
    public Rr pageList(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestBody TeacherQuery teacherQuery){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  Rr.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("新增讲师")
    @ApiImplicitParams({
             @ApiImplicitParam(name = "eduTeacher",value = "讲师实体",required = true,paramType = "path")
    }
    )
    @PostMapping("save")
    public Rr save(
            @RequestBody EduTeacher eduTeacher){
        eduTeacherService.save(eduTeacher);
        return Rr.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public Rr getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);
        return Rr.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("update")
    public Rr updateById(
            @ApiParam(name = "eduTeacher", value = "讲师", required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return Rr.ok();
        } else {
            return Rr.error();
        }

    }
}

