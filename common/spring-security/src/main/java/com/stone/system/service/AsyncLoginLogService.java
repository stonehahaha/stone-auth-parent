package com.stone.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stone.model.system.SysLoginLog;
import com.stone.model.vo.SysLoginLogQueryVo;

/**
 * @author stonestart
 * @create 2022/11/2 - 16:25
 */
public interface AsyncLoginLogService {
    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param ipaddr ip
     * @param message 消息内容
     * @return
     */
    void recordLoginLog(String username, Integer status, String ipaddr, String message);
    //获取分页列表
    IPage<SysLoginLog> selectPage(Long page, Long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
