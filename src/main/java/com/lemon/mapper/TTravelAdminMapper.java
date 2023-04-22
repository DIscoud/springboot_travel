package com.lemon.mapper;

import com.lemon.entity.TTravelAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lemon
 * @since 2023-02-11
 */
@Mapper
public interface TTravelAdminMapper extends BaseMapper<TTravelAdmin> {
    TTravelAdmin login(@Param("username") String username,@Param("pwd") String md5DigestAsHex);
}
