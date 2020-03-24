package com.nevada.dao;


import com.nevada.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface UserDao {
    String TABLE_NAME="user";
    String INSERT_FIELDS="id,name,password,salt,head_url";
    String SELECT_FIELDS="id"+INSERT_FIELDS;


    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (#{id},#{name},#{password},#{salt},#{headURL})"})
    void insert(User recode);

    User selectByName(String name);
}
