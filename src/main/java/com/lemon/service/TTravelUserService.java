package com.lemon.service;

import com.lemon.entity.TTravelUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2023-03-26
 */
public interface TTravelUserService extends IService<TTravelUser> {
    TTravelUser login(String username, String password);
}
