package com.nevada.admin.service;

import com.nevada.admin.entity.Question;

/**
 * @program:wenda
 * @description: 问题
 * @author: nevada
 * @create: 2020-04-03 16:52
 **/
public interface QuestionService {

    int addQuestion(Question question);

    public int updateCommentCount(int id, int count);
}
