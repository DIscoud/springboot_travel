package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TFoodCategory;
import com.lemon.entity.TTravelFood;
import com.lemon.entity.TTravelScenic;
import com.lemon.service.TTravelFoodService;
import com.lemon.service.TTravelScenicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/food")
@Slf4j
public class TTravelFoodController {
    @Autowired
    private TTravelFoodService tTravelFoodService;

    @GetMapping("/getAll")
    public R<List<TTravelFood>> getAll(){
        List<TTravelFood> list = tTravelFoodService.list();
        log.info("查询全部美食信息:"+list);
        return R.success("查询全部美食信息成功！",list);
    }

    /**
     * 分页查询美食信息（带参数）
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String foodName = (String)map.get("foodName");//美食名称
        String foodCategory = (String)map.get("foodCategory");//美食分类
        String snicName = (String)map.get("snicName");//附近景
        Page<TTravelFood> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelFood> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(snicName) && !"null".equals(snicName)){
            lqw.like(TTravelFood::getSnicName,snicName);
        }
        if(StringUtils.isNotBlank(foodName) && !"null".equals(foodName)){
            lqw.like(TTravelFood::getFoodName,foodName);
        }
        if(StringUtils.isNotBlank(foodCategory) && !"null".equals(foodCategory)){
            lqw.like(TTravelFood::getFoodCategory,foodCategory);
        }
        IPage result = tTravelFoodService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     * 根据美食Id删除美食信息
     * @param foodId
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer foodId){
        return tTravelFoodService.removeById(foodId) ? Result.success() : Result.fail();
    }

    /**
     * 新增美食信息
     * @param tTravelFood
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelFood tTravelFood){
        tTravelFood.setRutTime(LocalDateTime.now());
        return tTravelFoodService.save(tTravelFood) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     * 修改美食信息
     * @param tTravelFood
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelFood tTravelFood){
        return tTravelFoodService.updateById(tTravelFood) ? Result.success("修改成功！") : Result.fail();
    }

    /**
     * 分页获取热门美食
     * @param query
     * @return
     */
    @PostMapping("/pageHot")
    public Result pageHot(@RequestBody QueryPageParam query){
        Page<TTravelFood> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelFood> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TTravelFood::getFoodHot,1);
        IPage result = tTravelFoodService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }
}