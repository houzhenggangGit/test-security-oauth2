package cn.edu.bupt.pcsauth.service.impl;

import cn.edu.bupt.pcsauth.entity.UserEntity;
import cn.edu.bupt.pcsauth.repository.UserRepository;
import cn.edu.bupt.pcsauth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author arron
 * @date 19-5-8
 * @description
 */
@Service
@Primary
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService, UserService {


    @Resource
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (!exist(username)) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        return userRepository.findByUsername(username);
    }

    @Override
    public void insert(UserEntity userEntity) {

        String username = userEntity.getUsername();
        if (exist(username)){
            throw new RuntimeException("用户名已存在！");
        }

        //密码加密
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }

    private boolean exist(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return (userEntity != null);
    }
}
