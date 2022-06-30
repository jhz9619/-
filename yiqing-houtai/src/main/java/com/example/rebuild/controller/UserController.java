package com.example.rebuild.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.common.ServerResponse;
import com.example.rebuild.common.SysConstant;
import com.example.rebuild.common.SysLog;
import com.example.rebuild.entity.User;
import com.example.rebuild.service.Impl.TokenService;
import com.example.rebuild.service.UserService;
import com.example.rebuild.vo.PasswordVO;
import com.example.rebuild.vo.UserLoginRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    @SysLog(value= SysConstant.LOGIN)
    public ServerResponse login(@RequestBody UserLoginRequest adminLoginRequest) throws Exception{

        Map<String, Object> map = new HashMap();
        User user = userService.login(adminLoginRequest.getUsername(), adminLoginRequest.getPassword());

        if (user != null){
            String token = tokenService.getToken(user);
            map.put("user", user);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        return ServerResponse.ofError("用户名或密码错误!");

    }

    @GetMapping("/info")
    public ServerResponse info(HttpServletRequest request) {

        Map<String, Object> map = new HashMap();
        String token = request.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        User user = userService.info(Integer.valueOf(userId));

        if (user != null){
            map.put("userinfo", user);
            return ServerResponse.ofSuccess(map);
        }
        return ServerResponse.ofError("查询失败!");

    }

    //管理员更新个人资料
    @PostMapping("/modify")
    public ServerResponse modify(@RequestBody User user) {
        if(user.getDeleted()!=null && user.getDeleted() == 1)
            return userService.removeById(user) ? ServerResponse.ofSuccess("更新成功！") : ServerResponse.ofError("更新失败！");
        else
            return userService.updateById(user) ? ServerResponse.ofSuccess("更新成功！") : ServerResponse.ofError("更新失败！");
    }

    //根据ID查询管理员信息
    @GetMapping("/{id}")
    public ServerResponse queryUser(@PathVariable("id") Integer id) {
        return ServerResponse.ofSuccess(userService.getById(id));
    }

    @GetMapping("/users/{page}")
    public ServerResponse queryUsers(@PathVariable("page") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit) {

        Page<User> pages = new Page<>(page, limit);
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("deleted",false).eq("user_type",true);
        IPage<User> iPage = userService.page(pages, wrapper);
        return ServerResponse.ofSuccess(iPage);

    }

    @GetMapping({"/search/{keyword}","/search/"})
    public ServerResponse searchUser(@PathVariable(value = "keyword",required = false) String keyword,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.like(!StringUtils.isEmpty(keyword), "realname", keyword).eq("deleted",false);
        Page<User> pages = new Page<>(page, limit);
        IPage<User> iPage = userService.page(pages, wrapper);

        if (page != null) {
            return ServerResponse.ofSuccess(iPage);
        }
        return ServerResponse.ofError("查询不到数据!");

    }

    @PostMapping("/resetpass/{id}")
    public ServerResponse resetpass(@PathVariable Integer id) {

        User t = new User();
        t.setId(id);
        t.setPassword("123456");
        boolean b = userService.saveOrUpdate(t);

        if(b) {
            return ServerResponse.ofSuccess("成功！");
        }
        return ServerResponse.ofError("失败！");

    }

    //修改密码
    @PostMapping("/password")
    @SysLog(value= SysConstant.MODIFY_PASSWORD)
    public ServerResponse updatePass(@RequestBody PasswordVO passwordVO) {

        QueryWrapper<User> wrapper = new QueryWrapper();
        wrapper.eq("id", passwordVO.getId());
        wrapper.eq("password", passwordVO.getOldPass());
        User user = userService.getOne(wrapper);

        if (user == null) {
            return ServerResponse.ofError("旧密码错误");
        }

        if (!passwordVO.getNewPass().equals(passwordVO.getRePass())){
            return ServerResponse.ofError("两次密码不一致");
        }

        // 否则进入修改密码流程
        user.setPassword(passwordVO.getNewPass());
        boolean b = userService.updateById(user);
        return ServerResponse.ofSuccess("密码修改成功");

    }

    @PostMapping("/add")
    public ServerResponse addUser(@RequestBody User user) {

        user.setPassword("123456");
        user.setUserType(1);
        user.setRemark("China");
        boolean b = userService.save(user);

        if (b) {
            return ServerResponse.ofSuccess("添加成功", user);
        }
        return ServerResponse.ofError("添加失败!");

    }

    @PostMapping("/register")
    public ServerResponse register(@RequestBody User user) {

        user.setUserId(user.getUsername());
        user.setUserType(1);
        user.setRemark("China");
        boolean b = userService.save(user);

        if (b) {
            return ServerResponse.ofSuccess("添加成功", user);
        }
        return ServerResponse.ofError("添加失败!");

    }

}
