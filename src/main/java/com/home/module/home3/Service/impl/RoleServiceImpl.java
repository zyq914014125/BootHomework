package com.home.module.home3.Service.impl;

import com.home.module.home3.entity.Role;
import com.home.module.home3.entity.user;
import com.home.module.home3.mapper.roleMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.X
 */
@Service
public class RoleServiceImpl {
    @Autowired
    roleMapper roleMapper;

    public Role getRoleById(int roleId) {
        return roleMapper.getRoleById(roleId);
    }

    public List<Role> getRoles() {
        return roleMapper.getRoles();
    }
}
