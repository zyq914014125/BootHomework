package com.home.module.home3.mapper;

import com.home.module.home3.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mr.X
 **/
@Repository
@Mapper
public interface roleMapper {
    @Select("SELECT * FROM role where role_id=#{roleId}")
    public Role getRoleById(int roleId);
    @Select("Select * from role")
    List<Role> getRoles();
}
