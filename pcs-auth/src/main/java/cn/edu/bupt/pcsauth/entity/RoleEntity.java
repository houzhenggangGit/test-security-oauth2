package cn.edu.bupt.pcsauth.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author arron
 * @date 19-5-13
 * @description
 */
@Entity
@Table(name = "role", schema = "pcs", catalog = "")
public class RoleEntity{
    private int id;
    private String name;
    private String roleDesc;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "role_desc")
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(roleDesc, that.roleDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roleDesc);
    }


    @Override
    public String toString() {
        return "RoleEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }

}
