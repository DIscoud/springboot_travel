package com.lemon.service.impl;

import com.lemon.entity.TTravelAdmin;
import com.lemon.mapper.TTravelAdminMapper;
import com.lemon.service.TTravelAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2023-02-11
 */
@Service
public class TTravelAdminServiceImpl extends ServiceImpl<TTravelAdminMapper, TTravelAdmin> implements TTravelAdminService {
    @Autowired
    TTravelAdminMapper travelAdminMapper;
    @Override
    public TTravelAdmin login(String username, String md5DigestAsHex) {
        return travelAdminMapper.login(username,md5DigestAsHex);
    }
}
