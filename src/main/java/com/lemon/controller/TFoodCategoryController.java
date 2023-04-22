package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TFoodCategory;
import com.lemon.service.TFoodCategoryService;
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
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/foodCategory")
@Slf4j
public class TFoodCategoryController {
    @Autowired
    private TFoodCategoryService tFoodCategoryService;

    @GetMapping("/getAll")
    public R<List<TFoodCategory>> getAll(){
        List<TFoodCategory> list = tFoodCategoryService.list();
        log.info("查询全部美食分类信息:"+list);
        return R.success("查询全部美食分类信息成功！",list);
    }

    /**
     * 分页获取所有美食分类信息
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String categoryName = (String)map.get("categoryName");//省份名称
        Page<TFoodCategory> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TFoodCategory> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(categoryName) && !"null".equals(categoryName)){
            lqw.like(TFoodCategory::getCategoryName,categoryName);
        }
        IPage result = tFoodCategoryService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     * 根据美食分类Id删除
     * @param categoryId
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer categoryId){
        return tFoodCategoryService.removeById(categoryId) ? Result.success() : Result.fail();
    }

    /**
     * 新增美食分类信息
     * @param tFoodCategory
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TFoodCategory tFoodCategory){
        return tFoodCategoryService.save(tFoodCategory) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     * 修改美食分类信息
     * @param tFoodCategory
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TFoodCategory tFoodCategory){
        return tFoodCategoryService.updateById(tFoodCategory) ? Result.success("修改成功！") : Result.fail();
    }


}
