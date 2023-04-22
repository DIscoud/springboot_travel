package com.lemon.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.common.QueryPageParam;
import com.lemon.common.R;
import com.lemon.common.Result;
import com.lemon.entity.TTravelHotel;
import com.lemon.entity.TTravelScenic;
import com.lemon.service.TTravelScenicService;
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
@RequestMapping("/scenic")
@Slf4j
public class TTravelScenicController {

    @Autowired
    private TTravelScenicService tTravelScenicService;

    @GetMapping("/getAll")
    public R<List<TTravelScenic>> getAll(){
        List<TTravelScenic> list = tTravelScenicService.list();
        log.info("查询全部景点信息:"+list);
        return R.success("查询全部景点信息成功！",list);
    }

    /**
     * 分页查询景点信息（带参数）
     * @param query
     * @return
     */
    @PostMapping("/page")
    public Result page(@RequestBody QueryPageParam query){
        HashMap map = query.getParam();
        String snicName = (String)map.get("snicName");//景点名称
        String snicProvince = (String)map.get("snicProvince");//景点所属省份
        Page<TTravelScenic> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelScenic> lqw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(snicName) && !"null".equals(snicName)){
            lqw.like(TTravelScenic::getSnicName,snicName);
        }
        if(StringUtils.isNotBlank(snicProvince) && !"null".equals(snicProvince)){
            lqw.like(TTravelScenic::getSnicProvince,snicProvince);
        }
        IPage result = tTravelScenicService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }

    /**
     * 根据Id删除景点信息
     * @param snicId
     * @return
     */
    @GetMapping("/del")
    public Result del(Integer snicId){
        return tTravelScenicService.removeById(snicId) ? Result.success() : Result.fail();
    }

    /**
     * 新增景点信息
     * @param tTravelScenic
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody TTravelScenic tTravelScenic){
        return tTravelScenicService.save(tTravelScenic) ? Result.success("新增成功！") : Result.fail();
    }

    /**
     * 修改景点信息
     * @param tTravelScenic
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody TTravelScenic tTravelScenic){
        return tTravelScenicService.updateById(tTravelScenic) ? Result.success("修改成功！") : Result.fail();
    }

    /**
     * 分页获取热门景点
     * @param query
     * @return
     */
    @PostMapping("/pageHot")
    public Result pageHot(@RequestBody QueryPageParam query){
        Page<TTravelScenic> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<TTravelScenic> lqw = new LambdaQueryWrapper<>();
        lqw.eq(TTravelScenic::getSnicHot,1);
        IPage result = tTravelScenicService.page(page, lqw);
        System.out.println(result.getTotal());
        return Result.success(result.getRecords(),result.getTotal());
    }
}
