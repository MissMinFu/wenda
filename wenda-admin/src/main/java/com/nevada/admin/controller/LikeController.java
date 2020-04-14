package com.nevada.admin.controller;

import com.nevada.admin.async.EventModel;
import com.nevada.admin.async.EventProducer;
import com.nevada.admin.async.EventType;
import com.nevada.admin.common.CommonResult;
import com.nevada.admin.domain.HostHolder;
import com.nevada.admin.dto.EntityType;
import com.nevada.admin.entity.Comment;
import com.nevada.admin.service.CommentService;
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
    @Autowired
    EventProducer eventProducer;
    @Autowired
    HostHolder hostHolder;

    @Autowired
    CommentService commentService;

    @ApiOperation(value = "点赞")
    @RequestMapping(value = "/like",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> like(int userId, int entityId){
        if(hostHolder.getUser()==null){
            return CommonResult.failed("没有登陆");
        }
        Comment comment=commentService.getCommentsByEntity(entityId);
        eventProducer.fireEvent(new EventModel(EventType.LIKE).setActorId(hostHolder.getUser().getId()).setEntityId(entityId).
                setEntityType(EntityType.ENTITY_COMMENT).setEntityType(comment.getUserId()).
                setExt("questionId",String.valueOf(comment.getEntityId())));
        redisService.like(userId,entityId);
        return CommonResult.success("点赞成功");
    }
    @ApiOperation(value = "取消点赞")
    @RequestMapping(value = "/dislike",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> dislike(int userId, int entityId){
        redisService.unLike(userId,entityId);
        return CommonResult.success("点赞成功");
    }

    @ApiOperation(value = "实体点赞数")
    @RequestMapping(value = "/count",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Long> count(Integer entityId){
        Long count = redisService.count(entityId);
        return CommonResult.success(count);
    }


}
