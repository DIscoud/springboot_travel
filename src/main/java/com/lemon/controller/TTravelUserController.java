package com.lemon.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TTravelAdmin;
import com.lemon.entity.TTravelUser;
import com.lemon.entity.TTravelUser;
import com.lemon.service.TTravelAdminService;
import com.lemon.service.TTravelUserService;
import com.lemon.service.TTravelUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2023-03-26
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class TTravelUserController {
    @Autowired
    private TTravelUserService tTravelUserService;
    @Autowired
    private TTravelAdminService tTravelAdminService;

    /**
     * 查询全部用户信息
     * @return
     */
    @GetMapping("/getAll")
    public R<List<TTravelUser>> getAll(){
        List<TTravelUser> list = tTravelUserService.list();
        log.info("查询全部用户信息:"+list);
        return R.success("查询全部用户信息成功！",list);
    }

    /**
     * 查询全部用户信息
     * @return
     */
    @ApiOperation(value = "Login",notes = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户账号"),
            @ApiImplicitParam(name = "password",value = "用户密码")
    })
    @RequestMapping(value = "/login",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public String Login(String username,String password ){
        System.out.println(username);
        TTravelUser tTravelUser = tTravelUserService.login(username,DigestUtils.md5DigestAsHex(password.getBytes()));
        String json = null;
        if (tTravelUser == null){
            TTravelAdmin tTravelAdmin = tTravelAdminService.login(username,DigestUtils.md5DigestAsHex(password.getBytes()));
            json = JSON.toJSONString(tTravelAdmin);
        }else {
            json = JSON.toJSONString(tTravelUser);
        }
        return json;
    }

    @ApiOperation(value = "register",notes = "登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "useracc",value = "用户账号"),
            @ApiImplicitParam(name = "password",value = "用户密码"),
            @ApiImplicitParam(name = "username",value = "用户名称")
    })
    @RequestMapping(value = "/register",produces = { "text/html;charset=UTF-8;", "application/json;charset=UTF-8;" })
    public int register(String username,String password,String useracc){
        System.out.println(useracc+","+password+","+useracc);
        int num = tTravelUserService.register(username,DigestUtils.md5DigestAsHex(password.getBytes()),useracc);
        System.out.println(num);
        return 1;
    }


    /**
     * 分页查询全部用户信息（可带条件 账号、性别、账号状态）
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String username = (String)map.get("userUsername");
        String userSex = (String)map.get("userSex");
        String userStatus = (String)map.get("userStatus");
        Page<TTravelUser> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelUser> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(username) && !"null".equals(username)){
            lqw.like(TTravelUser::getUserUsername,username);
        }
        if(StringUtils.isNotBlank(userSex) && !"null".equals(userSex)){
            lqw.like(TTravelUser::getUserSex,userSex);
        }
        if(StringUtils.isNotBlank(userStatus) && !"null".equals(userStatus)){
            lqw.like(TTravelUser::getUserStatus,userStatus);
        }
        IPage result = tTravelUserService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     *新增用户信息
     * @param tTravelUser
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelUser tTravelUser){
        //1、将用户新增提交的密码进行MD5加密
        tTravelUser.setUserPassword(DigestUtils.md5DigestAsHex(tTravelUser.getUserPassword().getBytes()));
        return tTravelUserService.save(tTravelUser) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     *根据id修改用户
     * @param tTravelUser
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelUser tTravelUser){
        //根据id获取要修改的用户
        TTravelUser selectUser = tTravelUserService.getById(tTravelUser.getUserUid());
        //密码没改过则直接设置为原密码
        if(selectUser.getUserPassword().equals(tTravelUser.getUserPassword())){
            tTravelUser.setUserPassword(selectUser.getUserPassword());
        }else{//改过密码则重新设置密码
            tTravelUser.setUserPassword(DigestUtils.md5DigestAsHex(tTravelUser.getUserPassword().getBytes()));
        }
        return tTravelUserService.updateById(tTravelUser) ? Result.success("修改成功！") : Result.fail();
    }


    /**
     * 根据id删除用户信息
     * @param userUid
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer userUid){
        return tTravelUserService.removeById(userUid) ? Result.success() : Result.fail();
    }


}
