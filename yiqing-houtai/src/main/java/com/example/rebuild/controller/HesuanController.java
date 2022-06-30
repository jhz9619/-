package com.example.rebuild.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.common.ServerResponse;
import com.example.rebuild.entity.Hesuan;
import com.example.rebuild.service.Impl.HesuanServiceImpl;
import com.example.rebuild.vo.HesuanVo;
import com.example.rebuild.vo.SearchHealthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hesuan")
public class HesuanController {

    @Autowired
    private HesuanServiceImpl service;

    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody Hesuan record) {
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

    @GetMapping("/hesuans/{page}")
    public ServerResponse querys(@PathVariable("page") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit,
                                 @RequestParam(defaultValue = "admin") String role,
                                 @RequestParam(defaultValue = "") Integer userId,
                                 @RequestParam(defaultValue = "") String major) {

        Hesuan hesuan = new Hesuan();
        Page<HesuanVo> pages = new Page<>(page, limit);
        IPage<HesuanVo> iPage = service.select(pages, hesuan, role, userId, major );
        return ServerResponse.ofSuccess(iPage);

    }

    @PostMapping({"/search2"})
    public ServerResponse search2(@RequestBody SearchHealthRequest query,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit) {

        Hesuan hesuan = new Hesuan();
        Page<HesuanVo> pages = new Page<>(page, limit);
        IPage<HesuanVo> iPage = service.select(pages, hesuan, query.getRole(), query.getUserId(), query.getMajor());

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

    @PostMapping("/add")
    public ServerResponse add(@RequestBody Hesuan record) {

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
        QueryWrapper<Hesuan> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_time");
        wrapper.eq("deleted",false);
        Page<Hesuan> pages = new Page<>(page, limit);
        IPage<Hesuan> iPage = service.page(pages, wrapper);

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }
}
