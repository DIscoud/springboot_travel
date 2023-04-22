package com.lemon.mapper;

import com.lemon.entity.TTravelUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2023-03-26
 */
@Mapper
public interface TTravelUserMapper extends BaseMapper<TTravelUser> {

    TTravelUser login(@Param("username") String username,@Param("userpwd") String password);
}
