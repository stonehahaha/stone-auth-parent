package com.stone.system.controller;

import com.stone.common.result.R;
import com.stone.common.result.ResultCodeEnum;
import com.stone.common.utils.JwtHelper;
import com.stone.common.utils.MD5;
import com.stone.model.system.SysUser;
import com.stone.model.vo.LoginVo;
import com.stone.system.exception.StoneException;
import com.stone.system.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stonestart
 * @create 2022/10/28 - 23:51
 */
@Api(tags = "后台登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo) {
        SysUser sysUser = sysUserService.getByUsername(loginVo.getUsername());
        if(null == sysUser) {
            throw new StoneException(ResultCodeEnum.ACCOUNT_ERROR);
        }
        if(!MD5.encrypt(loginVo.getPassword()).equals(sysUser.getPassword())) {
            throw new StoneException(ResultCodeEnum.PASSWORD_ERROR);
        }
        if(sysUser.getStatus().intValue() == 0) {
            throw new StoneException(ResultCodeEnum.ACCOUNT_STOP);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtHelper.createToken(sysUser.getId(), sysUser.getUsername()));
        return R.ok(map);
    }
    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/info")
    public R info(HttpServletRequest request) {
        //获取请求头token字符串
        String token = request.getHeader("token");

        //从token字符串获取用户id
        String username = JwtHelper.getUsername(token);

        //根据用户id获取用户信息（基本信息、菜单权限、按钮权限数据）
        Map<String, Object> map = sysUserService.getUserInfo(username);
        return R.ok(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public R logout(){
        return R.ok();
    }

}
