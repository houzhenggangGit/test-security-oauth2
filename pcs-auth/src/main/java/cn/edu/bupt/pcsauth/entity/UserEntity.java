package cn.edu.bupt.pcsauth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author arron
 * @date 19-5-13
 * @description
 */
@Entity
@Table(name = "user", schema = "pcs", catalog = "")
public class UserEntity implements UserDetails {

    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "username")
    private String username;

    /**
     * 多对多表,一个用户具有多个角色
     * 不要在同一个持久化类中混合使用字段和方法注释。
     * Hibernate在这里生成一个不明确的错误。如果你以前没有面对它，很难弄清楚错误的原因。
     * @author arron
     * @date 19-5-13
     * @param null
     * @return
     */

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private List<RoleEntity> roles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                status == that.status &&
                Objects.equals(password, that.password) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, password, status, username);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<RoleEntity> roles = this.getRoles();
        for (RoleEntity roleEntity : roles) {
            auths.add(new SimpleGrantedAuthority(roleEntity.getName()));
        }
        return auths;
    }

    /**
     * 账号过期
     * 例如长时间未登录
     * @author arron
     * @date 19-5-13
     * @param
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    /**
     * 账号被锁定
     * @author arron
     * @date 19-5-13
     * @param
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 密码过期
     * @author arron
     * @date 19-5-13
     * @param
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /**
     * 账号被删除
     * @author arron
     * @date 19-5-13
     * @param
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
