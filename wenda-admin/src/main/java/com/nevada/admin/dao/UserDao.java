package com.nevada.admin.dao;


import com.nevada.admin.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface UserDao {
    String TABLE_NAME="user";
    String INSERT_FIELDS="id,name,password,salt,head_url";
    String SELECT_FIELDS="id"+INSERT_FIELDS;


    @Insert({"insert into",TABLE_NAME,"(",INSERT_FIELDS,") values (#{id},#{name},#{password},#{headURL},#{phone})"})
    int insert(User recode);

    @Select({"select * from ",TABLE_NAME,"where name =?1 and password= ?2"})
    User selectUser(String name, String password);

}
