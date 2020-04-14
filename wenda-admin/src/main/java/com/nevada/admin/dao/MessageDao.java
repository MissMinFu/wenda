package com.nevada.admin.dao;

import com.nevada.admin.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-13 14:31
 **/
@Mapper
public interface MessageDao {
    String TABLE="message";
    String INSERT_FIELDS="from_id,to_id,content,created_date,has_read,conversion_id";
    String SELECT_FIELDS="id,"+INSERT_FIELDS;

    int addMessage(Message message);

    List<Message> getConversionDetail(@Param("conversationId") String conversationId,
                                      @Param("offset") int offset,
                                      @Param("limit") int  limit);


}
