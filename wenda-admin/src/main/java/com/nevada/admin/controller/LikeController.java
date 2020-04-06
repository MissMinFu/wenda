package com.nevada.admin.controller;

import com.nevada.admin.common.CommonResult;
import com.nevada.admin.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program:wenda
 * @description: 点赞
 * @author: nevada
 * @create: 2020-04-06 14:00
 **/
@RestController
@Api(tags = "/赞操作")
@RequestMapping(value = "/operationLike")
public class LikeController {
    @Autowired
    RedisService redisService;

    @ApiOperation(value = "点赞")
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> like(int userId, int entityId){
        redisService.like(userId,entityId);
        return CommonResult.success("点赞成功");
    }


}
