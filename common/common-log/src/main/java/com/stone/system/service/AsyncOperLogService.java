package com.stone.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stone.model.system.SysOperLog;
import com.stone.model.vo.SysOperLogQueryVo;

/**
 * @author stonestart
 * @create 2022/11/2 - 16:56
 */
public interface AsyncOperLogService {
    public void saveSysLog(SysOperLog sysOperLog);

    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
