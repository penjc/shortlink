package com.ppp.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ppp.shortlink.admin.common.convention.result.Result;
import com.ppp.shortlink.admin.common.convention.result.Results;
import com.ppp.shortlink.admin.dto.req.UserLoginReqDTO;
import com.ppp.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.ppp.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.ppp.shortlink.admin.dto.resp.UserActualRespDTO;
import com.ppp.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.ppp.shortlink.admin.dto.resp.UserRespDTO;
import com.ppp.shortlink.admin.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/api/short-link/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable String username){
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查询用户无脱敏信息
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable String username){
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     * @param username
     * @return
     */
    @GetMapping("/api/short-link/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam String username){
        return Results.success(userService.hasUsername(username));
    }

    /**
     * 用户注册
     * @param userRegisterReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/user/")
    public Result<Void> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO){
        userService.register(userRegisterReqDTO);
        return Results.success();
    }

    /**
     * 根据用户名修改用户信息
     * @param userUpdateReqDTO
     * @return
     */
    @PutMapping("/api/short-link/v1/user/")
    public Result<Void> update(@RequestBody UserUpdateReqDTO userUpdateReqDTO){
        userService.update(userUpdateReqDTO);
        return Results.success();
    }

    /**
     * 用户登录
     * @param userLoginReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO userLoginReqDTO){
        UserLoginRespDTO userLoginRespDTO = userService.login(userLoginReqDTO);
        return Results.success(userLoginRespDTO);
    }

    /**
     * 检查用户是否登录
     * @param token
     * @return
     */
    @GetMapping("/api/short-link/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token){
        Boolean result = userService.checkLogin(username, token);
        return Results.success(result);
    }

    /**
     * 用户退出登录
     * @param username
     * @param token
     * @return
     */
    @DeleteMapping("/api/short-link/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token){
        userService.logout(username, token);
        return Results.success();
    }
}
