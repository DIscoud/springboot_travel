package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TTravelAdmin;
import com.lemon.entity.TTravelRoute;
import com.lemon.service.TTravelRouteService;
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
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/route")
@Slf4j
public class TTravelRouteController {

    @Autowired
    private TTravelRouteService tTravelRouteService;

    /**
     * 查询所有旅游线路信息
     * @return
     */
    @GetMapping("/getAll")
    public R<List<TTravelRoute>> getAll(){
        List<TTravelRoute> list = tTravelRouteService.list();
        log.info("查询全部旅游线路信息:"+list);
        return R.success("查询全部旅游线路信息成功！",list);
    }

    /**
     * 分页查询所有旅游线路信息 （带参数查询）
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String rutStart = (String)map.get("rutStart");//出发地
        String rutEnd = (String)map.get("rutEnd");//目的地
        String rutName = (String)map.get("rutName"); //线路名称
        Page<TTravelRoute> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelRoute> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(rutStart) && !"null".equals(rutStart)){
            lqw.like(TTravelRoute::getRutStart,rutStart);
        }
        if(StringUtils.isNotBlank(rutEnd) && !"null".equals(rutEnd)){
            lqw.like(TTravelRoute::getRutEnd,rutEnd);
        }
        if(StringUtils.isNotBlank(rutName) && !"null".equals(rutName)){
            lqw.like(TTravelRoute::getRutName,rutName);
        }
        IPage result = tTravelRouteService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     * 根据线路Id删除
     * @param rutId
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer rutId){
        return tTravelRouteService.removeById(rutId) ? Result.success() : Result.fail();
    }

    /**
     * 根据线路Id修改线路信息
     * @param tTravelRoute
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelRoute tTravelRoute){
        return tTravelRouteService.updateById(tTravelRoute) ? Result.success("修改成功！") : Result.fail();
    }

    /**
     * 新增线路
     * @param tTravelRoute
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelRoute tTravelRoute){
        return tTravelRouteService.save(tTravelRoute) ? Result.success("新增成功！") : Result.fail();
    }

}
