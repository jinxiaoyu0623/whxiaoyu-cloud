package com.whxiaoyu.auth.provider;

import com.whxiaoyu.component.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义用户查询
 * @author jinxiaoyu
 */
public class CustomUserDetailService implements UserDetailsService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserDto(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        return new CustomizeUserDetails(user);
    }
}
