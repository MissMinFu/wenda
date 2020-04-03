package com.nevada.admin.dao;

import com.nevada.admin.entity.Question;
import org.apache.ibatis.annotations.*;

/**
 * @program:wenda
 * @description: 问题
 * @author: nevada
 * @create: 2020-04-03 16:42
 **/
@Mapper
public interface QuestionDao {

    String TABLE_NAME="question";
    String INSERT_FIELD=" title, context, created_date, user_id, comment_count ";
    String SELECT_FIELDS = " id, " + INSERT_FIELD;
    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELD,") values (#{title},#{context},#{createdDate},#{userId},#{status},#{commentCount}"})
    int addQuestion(Question question);

    @Select({"select ", SELECT_FIELDS, " from", TABLE_NAME, " where id=#{id}"})
    Question selectById(int id);

    @Update({"update ", TABLE_NAME, " set comment_count=#{commentCount} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);
}
