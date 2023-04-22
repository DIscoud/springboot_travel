package com.lemon.service.impl;

import com.lemon.entity.TTravelUser;
import com.lemon.mapper.TTravelUserMapper;
import com.lemon.service.TTravelUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lemon
 * @since 2023-03-26
 */
@Service
public class TTravelUserServiceImpl extends ServiceImpl<TTravelUserMapper, TTravelUser> implements TTravelUserService {
    @Autowired
    TTravelUserMapper tTravelUserMapper;
    @Override
    public TTravelUser login(String username, String password) {
        return tTravelUserMapper.login(username,password);
    }
}
