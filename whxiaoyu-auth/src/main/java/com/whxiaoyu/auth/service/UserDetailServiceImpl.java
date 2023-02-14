package com.whxiaoyu.auth.service;

import com.whxiaoyu.auth.mapper.UserMapper;
import com.whxiaoyu.auth.provider.CustomizeUserDetails;
import com.whxiaoyu.component.core.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户查询
 * @author jinxiaoyu
 */
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserDto(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        return new CustomizeUserDetails(user);
    }
}
