package com.home.module.home3.Controller;

/**
 * @author Mr.X
 **/

import com.home.module.home3.Service.impl.RoleServiceImpl;
import com.home.module.home3.entity.Role;
import com.home.module.home3.entity.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;
    //获取单个
    @GetMapping("/{roleId}")
    public Role getUserById(@PathVariable int roleId){
        return roleService.getRoleById(roleId);
    }
    //全获取
    @GetMapping("/roles")
    public List<Role> getRoles(){
        return roleService.getRoles();
    }
}
