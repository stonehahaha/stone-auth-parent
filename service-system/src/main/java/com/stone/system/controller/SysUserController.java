package com.stone.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.result.R;
import com.stone.common.utils.MD5;
import com.stone.model.system.SysUser;
import com.stone.model.vo.SysUserQueryVo;
import com.stone.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-29
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
@CrossOrigin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public R list(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
            SysUserQueryVo sysUserQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, sysUserQueryVo);
        return R.ok(pageModel);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public R get(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return R.ok(user);
    }

    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public R save(@RequestBody SysUser user) {
        //密码进行md5加密
        String encrypt = MD5.encrypt(user.getPassword());
        user.setPassword(encrypt);
        boolean isSuccess = sysUserService.save(user);
        if(isSuccess){
            return R.ok();
        }else {
            return R.fail();
        }
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("/update")
    public R updateById(@RequestBody SysUser user) {
        boolean isSuccess = sysUserService.updateById(user);
        if(isSuccess){
            return R.ok();
        }else {
            return R.fail();
        }
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public R remove(@PathVariable Long id) {
        boolean isSuccess =sysUserService.removeById(id);
        if(isSuccess){
            return R.ok();
        }else {
            return R.fail();
        }
    }

    @ApiOperation(value = "更新状态")
    @GetMapping("updateStatus/{id}/{status}")
    public R updateStatus(@PathVariable String id, @PathVariable Integer status) {
        sysUserService.updateStatus(id, status);
        return R.ok();
    }

}

