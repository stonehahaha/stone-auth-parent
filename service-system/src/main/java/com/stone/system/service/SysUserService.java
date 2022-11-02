package com.stone.system.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.model.system.SysUser;
import com.stone.model.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-10-29
 */
public interface SysUserService extends IService<SysUser> {
    //获取分页列表
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);
    //更新状态
    void updateStatus(String id, Integer status);
    //根据用户名称查询
    SysUser getByUsername(String username);
    //根据用户id获取用户信息（基本信息、菜单权限、按钮权限数据）
    Map<String, Object> getUserInfo(String username);
}
