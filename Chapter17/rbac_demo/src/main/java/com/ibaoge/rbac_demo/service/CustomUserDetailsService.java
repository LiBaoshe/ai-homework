package com.ibaoge.rbac_demo.service;

import com.ibaoge.rbac_demo.entity.Permission;
import com.ibaoge.rbac_demo.entity.Role;
import com.ibaoge.rbac_demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

// CustomUserDetailsService.java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // 假设你有这个Repository

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在: " + username));

        // 将你的User实体转换为Spring Security需要的UserDetails
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(getAuthorities(user)) // 这里封装用户的权限（角色和权限）
                .build();
    }

    private String[] getAuthorities(User user) {
        // 将用户的角色和权限合并成一个字符串列表，格式为 ROLE_ADMIN, user:read 等
        Set<String> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add("ROLE_" + role.getName()); // 添加角色，Spring Security需要以ROLE_开头
            for (Permission permission : role.getPermissions()) {
                authorities.add(permission.getName()); // 添加权限
            }
        }
        return authorities.toArray(new String[0]);
    }
}