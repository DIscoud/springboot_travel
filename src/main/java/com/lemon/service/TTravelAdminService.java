package com.lemon.service;

import com.lemon.entity.TTravelAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lemon
 * @since 2023-02-11
 */
public interface TTravelAdminService extends IService<TTravelAdmin> {
    TTravelAdmin login(String username, String md5DigestAsHex);
}
