package com.example.rebuild.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.common.ServerResponse;
import com.example.rebuild.entity.Health;
import com.example.rebuild.service.HealthService;
import com.example.rebuild.vo.HealthVo;
import com.example.rebuild.vo.SearchHealthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService service;

    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody Health record) {
        return service.updateById(record) ? ServerResponse.ofSuccess("更新成功！") : ServerResponse.ofError("更新失败！");
    }

    @GetMapping("/delete/{id}")
    public ServerResponse delete(@PathVariable("id") Integer id) {
        return service.removeById(id) ? ServerResponse.ofSuccess("删除成功！") : ServerResponse.ofError("删除失败！");
    }

    @GetMapping("/{id}")
    public ServerResponse query(@PathVariable("id") Integer id) {
        return ServerResponse.ofSuccess(service.getById(id));
    }

    @GetMapping("/healths/{page}")
    public ServerResponse querys(@PathVariable("page") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit,
                                 @RequestParam(defaultValue = "admin") String role,
                                 @RequestParam(defaultValue = "") Integer userId,
                                 @RequestParam(defaultValue = "") String major) {

        Health health = new Health();
        Page<HealthVo> pages = new Page<>(page, limit);
        IPage<HealthVo> iPage = service.select(pages, health, role, userId, major );
        return ServerResponse.ofSuccess(iPage);

    }

    @PostMapping({"/search2"})
    public ServerResponse search2(@RequestBody SearchHealthRequest query,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit) {

        Health health = new Health();
        health.setCough(query.getCough());
        health.setFever(query.getFever());
        health.setTemperature(query.getTemperature());
        Page<HealthVo> pages = new Page<>(page, limit);
        IPage<HealthVo> iPage = service.select(pages, health, query.getRole(), query.getUserId(), query.getMajor());

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

    @PostMapping("/add")
    public ServerResponse add(@RequestBody Health record) {

        boolean b = service.save(record);
        if (b) {
            return ServerResponse.ofSuccess("添加成功", record);
        }
        return ServerResponse.ofError("添加失败!");

    }


    //给前端用的
    @GetMapping({"/fontsearch/{username}"})
    public ServerResponse fontsearch(@PathVariable(value = "username",required = false) String username,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit) {
        QueryWrapper<Health> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("deleted",false);
        Page<Health> pages = new Page<>(page, limit);
        IPage<Health> iPage = service.page(pages, wrapper);

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

}
