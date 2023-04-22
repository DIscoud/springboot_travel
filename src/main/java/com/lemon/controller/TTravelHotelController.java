package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TTravelHotel;
import com.lemon.entity.TTravelProvince;
import com.lemon.service.TTravelHotelService;
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
 * @since 2023-03-25
 */
@RestController
@RequestMapping("/hotel")
@Slf4j
public class TTravelHotelController {

    @Autowired
    private TTravelHotelService tTravelHotelService;

    @GetMapping("/getAll")
    public R<List<TTravelHotel>> getAll(){
        List<TTravelHotel> list = tTravelHotelService.list();
        log.info("查询全部酒店信息:"+list);
        return R.success("查询全部酒店信息成功！",list);
    }

    /**
     * 分页查询酒店信息（带参数）
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String htlName = (String)map.get("htlName");//酒店名称
        String snicName = (String)map.get("snicName");//附近景点名称
        Page<TTravelHotel> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelHotel> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(htlName) && !"null".equals(htlName)){
            lqw.like(TTravelHotel::getHtlName,htlName);
        }
        if(StringUtils.isNotBlank(snicName) && !"null".equals(snicName)){
            lqw.like(TTravelHotel::getSnicName,snicName);
        }
        IPage result = tTravelHotelService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     * 根据id删除酒店信息
     * @param htlId
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer htlId){
        return tTravelHotelService.removeById(htlId) ? Result.success() : Result.fail();
    }

    /**
     * 新增酒店信息
     * @param travelHotel
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelHotel travelHotel){
        return tTravelHotelService.save(travelHotel) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     * 修改酒店信息
     * @param travelHotel
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelHotel travelHotel){
        return tTravelHotelService.updateById(travelHotel) ? Result.success("修改成功！") : Result.fail();
    }

}
