package com.nevada.admin.dao;


import com.nevada.admin.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    String TABLE_NAME="user";
    String INSERT_FIELDS="id,name,password,head_url,phone";


    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (#{id},#{name},#{password},#{headUrl},#{phone})"})
    int insert(User recode);

    @Select({"select *  from ",TABLE_NAME,"where name=#{name} "})
    User selectUser(String name);



}
