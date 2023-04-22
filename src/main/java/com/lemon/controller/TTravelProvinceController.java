package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TTravelProvince;
import com.lemon.entity.TTravelRoute;
import com.lemon.service.TTravelProvinceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lemon
 * @since 2023-03-21
 */
@RestController
@RequestMapping("/province")
@Slf4j
public class TTravelProvinceController {

    @Autowired
    private TTravelProvinceService tTravelProvinceService;

    @GetMapping("/getAll")
    public R<List<TTravelProvince>> getAll(){
        List<TTravelProvince> list = tTravelProvinceService.list();
        log.info("查询全部省份信息:"+list);
        return R.success("查询全部省份信息成功！",list);
    }

    /**
     * 分页获取所有省份信息
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String provinceName = (String)map.get("provinceName");//省份名称
        Page<TTravelProvince> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelProvince> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(provinceName) && !"null".equals(provinceName)){
            lqw.like(TTravelProvince::getProvinceName,provinceName);
        }
        IPage result = tTravelProvinceService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     * 根据省份Id删除
     * @param provinceId
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer provinceId){
        return tTravelProvinceService.removeById(provinceId) ? Result.success() : Result.fail();
    }

    /**
     * 新增省份字典
     * @param tTravelProvince
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelProvince tTravelProvince){
        return tTravelProvinceService.save(tTravelProvince) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     * 修改省份字典
     * @param tTravelProvince
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelProvince tTravelProvince){
        return tTravelProvinceService.updateById(tTravelProvince) ? Result.success("修改成功！") : Result.fail();
    }


}
