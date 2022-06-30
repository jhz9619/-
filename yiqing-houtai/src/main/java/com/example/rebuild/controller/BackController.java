package com.example.rebuild.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.common.ServerResponse;
import com.example.rebuild.entity.Back;
import com.example.rebuild.entity.Record;
import com.example.rebuild.service.Impl.BackServiceImpl;
import com.example.rebuild.service.RecordService;
import com.example.rebuild.vo.BackVo;
import com.example.rebuild.vo.SearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/back")
public class BackController {

    @Autowired
    private BackServiceImpl service;

    @Autowired
    private RecordService recordService;

    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody Back record) {
        recordService.addRecord2(record, "修改后提交申请");
        return service.updateById(record) ? ServerResponse.ofSuccess("更新成功！") : ServerResponse.ofError("更新失败！");
    }

    @PostMapping("/modify2")
    public ServerResponse modify2(@RequestBody Back record) {
        recordService.applyRecord2(record);
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

    @GetMapping("/backs/{page}")
    public ServerResponse querys(@PathVariable("page") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit,
                                 @RequestParam(defaultValue = "admin") String role,
                                 @RequestParam(defaultValue = "") Integer userId,
                                 @RequestParam(defaultValue = "") String major) {

        Back back = new Back();
        back.setRemark("");
        back.setReason("");
        Page<BackVo> pages = new Page<>(page, limit);
        IPage<BackVo> iPage = service.select(pages, back, role, userId, major);

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

    @PostMapping({"/search"})
    public ServerResponse search(@RequestBody SearchRequest query,
                                 @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") Integer limit) {

        QueryWrapper<Back> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_time");
        wrapper.like(!StringUtils.isEmpty(query.getKeyword()), "reason", query.getKeyword())
                .eq("deleted",false);

        if("normal".equals(query.getRole())){
            wrapper.eq("userId", query.getUserId());
        }

        Page<Back> pages = new Page<>(page, limit);
        IPage<Back> iPage = service.page(pages, wrapper);

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

    @PostMapping({"/search2"})
    public ServerResponse search2(@RequestBody SearchRequest query,
                                  @RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit) {

        Back back = new Back();
        back.setRemark("");
        back.setReason(query.getKeyword());
        Page<BackVo> pages = new Page<>(page, limit);
        IPage<BackVo> iPage = service.select(pages, back, query.getRole(), query.getUserId(), query.getMajor());

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }


    @PostMapping("/add")
    public ServerResponse add(@RequestBody Back record) {

        boolean b = service.save(record);

        if (b) {
            recordService.addRecord2(record, "提交申请");
            return ServerResponse.ofSuccess("添加成功", record);
        }
        return ServerResponse.ofError("添加失败!");

    }

    /* 给前端用的 */
    @GetMapping({"/fontsearch/{username}"})
    public ServerResponse fontsearch(@PathVariable(value = "username",required = false) String username,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit) {

        QueryWrapper<Back> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.eq("deleted",false);
        Page<Back> pages = new Page<>(page, limit);
        IPage<Back> iPage = service.page(pages, wrapper);

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

    @GetMapping("/records")
    public ServerResponse records(@RequestParam Integer id) {

        QueryWrapper<Record> wrapper = new QueryWrapper<>();
        wrapper.eq("apply_id",id);
        return ServerResponse.ofSuccess(recordService.list(wrapper));

    }
}
