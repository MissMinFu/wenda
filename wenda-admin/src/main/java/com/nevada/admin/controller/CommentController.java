package com.nevada.admin.controller;

import com.nevada.admin.common.CommonResult;
import com.nevada.admin.domain.HostHolder;
import com.nevada.admin.dto.EntityType;
import com.nevada.admin.entity.Comment;
import com.nevada.admin.service.CommentService;
import com.nevada.admin.service.QuestionService;
import com.nevada.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @program:wenda
 * @description: 评论功能
 * @author: nevada
 * @create: 2020-04-03 14:15
 **/
@RestController
@Api(tags = "评论")
@RequestMapping(value = "/comment")
public class CommentController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService commentService;
    @Autowired
    QuestionService questionService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    @ApiOperation(value = "增加评论")
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String>  addComment(@RequestParam("questionId")Integer questionId,
                                             @RequestParam("content") String content){
        Comment comment=new Comment();
        comment.setContext(content);
        if(hostHolder.getUser()!=null){
            comment.setUserId(hostHolder.getUser().getId());
        }else{
            comment.setUserId(0);
        }
        comment.setEntityId(questionId);
        comment.setEntityType(EntityType.ENTITY_QUESTION);
        commentService.addComment(comment);
        LOGGER.debug(""+comment);
        int count = commentService.getCommentCount(comment.getEntityId(), comment.getEntityType());
        questionService.updateCommentCount(questionId,count);
        return CommonResult.success("评论成功 redirect:/question/");
    }

}
