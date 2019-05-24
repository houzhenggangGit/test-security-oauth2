package cn.edu.bupt.pcsauth.repository;

import cn.edu.bupt.pcsauth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author arron
 * @date 19-5-13
 * @description
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * 通过usename 找到用户
     * @author arron
     * @date 19-5-13
     * @param username
     * @return cn.edu.bupt.cn.edu.bupt.pcsauth.entity.UserEntity
     */
    UserEntity findByUsername(String username);
}
