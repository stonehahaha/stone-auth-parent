package com.stone.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.model.system.SysRole;
import com.stone.model.vo.AssginRoleVo;
import com.stone.model.vo.SysRoleQueryVo;

import java.util.Map;

/**
 * @author stonestart
 * @create 2022/10/27 - 18:20
 */
public interface SysRoleService extends IService<SysRole> {
    //条件分页查询
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo roleQueryVo);
    //根据用户获取角色数据
    Map<String, Object> getRolesByUserId(Long userId);
    //根据用户分配角色
    void doAssign(AssginRoleVo assginRoleVo);
}
