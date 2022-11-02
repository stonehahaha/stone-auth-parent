package com.stone.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.model.system.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author stonestart
 * @create 2022/11/2 - 16:29
 */
@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
}
