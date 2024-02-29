package com.ppp.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ppp.shortlink.admin.common.convention.result.Result;
import com.ppp.shortlink.admin.common.convention.result.Results;
import com.ppp.shortlink.admin.dto.resp.UserActualRespDTO;
import com.ppp.shortlink.admin.dto.resp.UserRespDTO;
import com.ppp.shortlink.admin.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/api/shortlink/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable String username){
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查询用户无脱敏信息
     * @param username
     * @return
     */
    @GetMapping("/api/shortlink/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("/api/shortlink/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam String username){
        return Results.success(userService.hasUsername(username));
    }
}
