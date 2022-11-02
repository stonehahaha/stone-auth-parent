package com.stone.system.controller;


import com.stone.common.result.R;
import com.stone.model.system.SysMenu;
import com.stone.model.vo.AssginMenuVo;
import com.stone.system.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-10-30
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public R toAssign(@PathVariable String roleId) {
        List<SysMenu> list = sysMenuService.findSysMenuByRoleId(roleId);
        return R.ok(list);
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public R doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return R.ok();
    }

    @ApiOperation(value = "获取菜单")
    @GetMapping("findNodes")
    public R findNodes() {
        List<SysMenu> list = sysMenuService.findNodes();
        return R.ok(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public R save(@RequestBody SysMenu permission) {
        sysMenuService.save(permission);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询菜单")
    @PostMapping("findNode/{id}")
    public R findNode(@PathVariable String id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return R.ok(sysMenu);
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("update")
    public R updateById(@RequestBody SysMenu permission) {
        sysMenuService.updateById(permission);
        return R.ok();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable Long id) {
        sysMenuService.removeMenuById(id);
        return R.ok();
    }

}

