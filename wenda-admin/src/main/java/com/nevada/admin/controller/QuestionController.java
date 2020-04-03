package com.nevada.admin.controller;

import com.nevada.admin.common.CommonResult;
import com.nevada.admin.entity.Question;
import com.nevada.admin.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-03 17:03
 **/
@RestController
@Api(tags = "问题管理")
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @ApiOperation(value = "增加主题")
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> addQuestion(@RequestParam("title") String title, @RequestParam("context") String context,
                                            @RequestParam("userId") int userId)

    {
        Question question=new Question();
        question.setTitle(title);
        question.setContext(context);
        question.setUserId(userId);
        question.setCommentCount(0);
        question.setCreatedDate(new Date());
        questionService.addQuestion(question);
        return  CommonResult.success("成功插入问题");
    }

}
