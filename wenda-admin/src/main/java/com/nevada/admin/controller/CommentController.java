package com.nevada.admin.controller;

import com.nevada.admin.common.CommonResult;
import com.nevada.admin.entity.Comment;
import com.nevada.admin.service.CommentService;
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
public class CommentController {
    private static final Logger LOGGER= LoggerFactory.getLogger(CommentController.class);
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "增加评论")
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String>  addComment(@RequestParam("questionId") int questionId,
                                             @RequestParam("content") String content){
        Comment comment=new Comment();
        comment.setContext(content);
        comment.setEntityId(questionId);
        commentService.addComment(comment);
        return CommonResult.success("评论成功");
    }




}
