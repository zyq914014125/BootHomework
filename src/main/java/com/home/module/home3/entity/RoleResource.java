package com.home.module.home3.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Mr.X
 **/
@Entity
@Table(name = "role_resource")
public class RoleResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleResourceId;
    private int roleId;
    private int resourceId;

    public int getRoleResourceId() {
        return roleResourceId;
    }

    public void setRoleResourceId(int roleResourceId) {
        this.roleResourceId = roleResourceId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
