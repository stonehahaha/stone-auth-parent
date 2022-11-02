package com.stone.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.model.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author stone
 * @since 2022-10-30
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //根据用户id查询菜单权限
    List<SysMenu> findListByUserId(@Param("userId") String userId);
}
