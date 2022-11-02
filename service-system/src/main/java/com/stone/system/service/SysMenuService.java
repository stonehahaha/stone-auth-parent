package com.stone.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.model.system.SysMenu;
import com.stone.model.vo.AssginMenuVo;
import com.stone.model.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-30
 */
public interface SysMenuService extends IService<SysMenu> {
    //获取菜单
    List<SysMenu> findNodes();
    //删除菜单
    void removeMenuById(Long id);
    //根据角色获取菜单
    List<SysMenu> findSysMenuByRoleId(String roleId);
    //给角色分配权限
    void doAssign(AssginMenuVo assginMenuVo);
    //根据用户id获取菜单权限值
    List<RouterVo> findUserMenuList(String id);
    //根据用户id获取用户按钮权限
    List<String> findUserPermsList(String id);
}
