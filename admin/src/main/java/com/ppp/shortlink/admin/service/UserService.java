package com.ppp.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppp.shortlink.admin.dao.entity.UserDO;
import com.ppp.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.ppp.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {
    /**
     * 根据用户名返回用户信息
     * @param username
     * @return 用户信息
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    Boolean hasUsername(String username);

    /**
     * 用户注册
     * @param userRegisterReqDTO
     */
    void register(UserRegisterReqDTO userRegisterReqDTO);
}
