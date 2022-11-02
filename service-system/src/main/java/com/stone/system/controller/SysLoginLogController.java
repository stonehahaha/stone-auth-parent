package com.stone.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.stone.common.result.R;
import com.stone.model.system.SysLoginLog;
import com.stone.model.vo.SysLoginLogQueryVo;
import com.stone.system.service.AsyncLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stonestart
 * @create 2022/11/2 - 19:56
 */
@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginLogController {
    @Autowired
    private AsyncLoginLogService loginLogService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "sysLoginLogQueryVo", value = "查询对象", required = false)
            SysLoginLogQueryVo sysLoginLogQueryVo) {
        IPage<SysLoginLog> pageModel = loginLogService.selectPage(page,limit, sysLoginLogQueryVo);
        return R.ok(pageModel);
    }
}

