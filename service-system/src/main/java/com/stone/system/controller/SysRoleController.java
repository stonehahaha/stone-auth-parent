package com.stone.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.common.result.R;
import com.stone.model.system.SysRole;
import com.stone.model.vo.AssginRoleVo;
import com.stone.model.vo.SysRoleQueryVo;
import com.stone.system.annotation.Log;
import com.stone.system.enums.BusinessType;
import com.stone.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author stonestart
 * @create 2022/10/27 - 18:24
 */
@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //查询所有记录
    @ApiOperation(value = "获取全部角色列表")
    @GetMapping("findAll")
    public R findAll() {
        List<SysRole> roleList = sysRoleService.list();
        return R.ok(roleList);
    }
    //逻辑删除
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "逻辑删除角色")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable long id){
        boolean isSuccess = sysRoleService.removeById(id);
        if (isSuccess){
            return R.ok();
        }else {
            return R.fail();
        }
    }
    //条件分页查询
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "条件分页查询")
    @GetMapping("{page}/{limit}")
    public R findPageQueryRole(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
            SysRoleQueryVo roleQueryVo){
        Page<SysRole> pageParam = new Page<>(page, limit);
        IPage<SysRole> pageModel = sysRoleService.selectPage(pageParam,roleQueryVo);
        return R.ok(pageModel);
    }
    //添加
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation(value = "添加")
    @PostMapping("save")
    public R saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if (isSuccess){
            return R.ok();
        }else {
            return R.fail();
        }
    }
    //修改-根据id查询
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation(value = "根据id查询")
    @GetMapping("get/{id}")
    public R get(@PathVariable long id){
        SysRole role = sysRoleService.getById(id);
        return R.ok(role);
    }
    //修改
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation(value = "修改角色")
    @PostMapping("update")
    public R updateById(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if (isSuccess){
            return R.ok();
        }else {
            return R.fail();
        }
    }
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public R batchRemove(@RequestBody List<Long> idList) {
        sysRoleService.removeByIds(idList);
        return R.ok();
    }
    @ApiOperation(value = "根据用户获取角色数据")
    @GetMapping("/toAssign/{userId}")
    public R toAssign(@PathVariable Long userId) {
        Map<String, Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return R.ok(roleMap);
    }

    @ApiOperation(value = "根据用户分配角色")
    @PostMapping("/doAssign")
    public R doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.doAssign(assginRoleVo);
        return R.ok();
    }


}
