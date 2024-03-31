package com.whxiaoyu.cloud.auth.provider;

import cn.hutool.core.collection.CollectionUtil;
import com.whxiaoyu.cloud.commons.core.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义用户查询
 * @author jinxiaoyu
 */
public class CustomUserDetailService implements UserDetailsService {

    private UserMapper userMapper;
    private RoleMapper roleMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username not found");
        }
        List<String> roles = roleMapper.selectByUserId(user.getUserId());
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        if (CollectionUtil.isNotEmpty(roles)) {
            for (String role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }
        return new CustomizeUserDetails(user,grantedAuthorities);
    }
}
