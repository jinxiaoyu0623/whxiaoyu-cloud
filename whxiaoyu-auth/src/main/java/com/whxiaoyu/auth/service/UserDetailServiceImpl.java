package com.whxiaoyu.auth.service;

import com.whxiaoyu.auth.mapper.UserMapper;
import com.whxiaoyu.common.core.dto.UserDto;
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
        UserDto userDto = userMapper.getUserDto(username);
        if (userDto == null) {
            throw new UsernameNotFoundException("username not found");
        }
        return new CustomUserDetails(userDto);
    }
}
