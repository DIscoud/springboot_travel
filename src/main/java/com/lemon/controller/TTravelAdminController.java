package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TTravelAdmin;
import com.lemon.service.TTravelAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2023-02-11
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class TTravelAdminController {

    @Autowired
    private TTravelAdminService tTravelAdminService;

    /**
     * 查询全部管理员信息
     * @return
     */
    @GetMapping("/getAll")
    public R<List<TTravelAdmin>> getAll(){
        List<TTravelAdmin> list = tTravelAdminService.list();
        log.info("查询全部管理员信息:"+list);
        return R.success("查询全部管理员信息成功！",list);
    }

    /**
     * 分页查询全部管理员信息（可带条件 账号、性别、账号状态）
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String username = (String)map.get("admUsername");
        String admSex = (String)map.get("admSex");
        String admStatus = (String)map.get("admStatus");
        Page<TTravelAdmin> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelAdmin> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(username) && !"null".equals(username)){
            lqw.like(TTravelAdmin::getAdmUsername,username);
        }
        if(StringUtils.isNotBlank(admSex) && !"null".equals(admSex)){
            lqw.like(TTravelAdmin::getAdmSex,admSex);
        }
        if(StringUtils.isNotBlank(admStatus) && !"null".equals(admStatus)){
            lqw.like(TTravelAdmin::getAdmStatus,admStatus);
        }
        IPage result = tTravelAdminService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     *新增管理员信息
     * @param tTravelAdmin
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelAdmin tTravelAdmin){
        //1、将用户新增提交的密码进行MD5加密
        tTravelAdmin.setAdmPassword(DigestUtils.md5DigestAsHex(tTravelAdmin.getAdmPassword().getBytes()));
        return tTravelAdminService.save(tTravelAdmin) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     *根据id修改管理员
     * @param tTravelAdmin
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelAdmin tTravelAdmin){
        //根据id获取要修改的管理员
        TTravelAdmin selectAdmin = tTravelAdminService.getById(tTravelAdmin.getAdmAid());
        //密码没改过则直接设置为原密码
        if(selectAdmin.getAdmPassword().equals(tTravelAdmin.getAdmPassword())){
            tTravelAdmin.setAdmPassword(selectAdmin.getAdmPassword());
        }else{//改过密码则重新设置密码
            tTravelAdmin.setAdmPassword(DigestUtils.md5DigestAsHex(tTravelAdmin.getAdmPassword().getBytes()));
        }
        return tTravelAdminService.updateById(tTravelAdmin) ? Result.success("修改成功！") : Result.fail();
    }


    /**
     * 根据id删除管理员信息
     * @param admAid
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer admAid){
        return tTravelAdminService.removeById(admAid) ? Result.success() : Result.fail();
    }

}
