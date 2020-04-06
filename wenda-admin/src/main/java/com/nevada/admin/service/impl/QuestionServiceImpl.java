package com.nevada.admin.service.impl;

import com.nevada.admin.dao.QuestionDao;
import com.nevada.admin.entity.Question;
import com.nevada.admin.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-03 16:54
 **/
@Service
public class QuestionServiceImpl implements QuestionService {


    @Autowired
    QuestionDao questionDao;
    public int addQuestion(Question question) {
        return  questionDao.addQuestion(question);
    }
    public int updateCommentCount(int id, int count) {
        return questionDao.updateCommentCount(id, count);
    }

}
