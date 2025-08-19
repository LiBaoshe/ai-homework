package com.ibaoge.rbac_demo.service;

import com.ibaoge.rbac_demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义UserDetails实现，用于将自定义的User实体适配到Spring Security框架中
 */
public class YourUserDetails implements UserDetails {

    private final User user;
    private final Set<GrantedAuthority> authorities;

    public YourUserDetails(User user) {
        this.user = user;
        // 将用户的角色和权限转换为Spring Security需要的GrantedAuthority集合
        this.authorities = convertToAuthorities(user);
    }

    /**
     * 将User实体的角色和权限转换为GrantedAuthority集合
     */
    private Set<GrantedAuthority> convertToAuthorities(User user) {
        if (user == null || user.getRoles() == null) {
            return Collections.emptySet();
        }

        return user.getRoles().stream()
                .flatMap(role -> {
                    // 首先添加角色（格式为ROLE_XXX）
                    Set<GrantedAuthority> roleAuthorities = Collections.singleton(
                            new SimpleGrantedAuthority("ROLE_" + role.getName())
                    );

                    // 然后添加该角色下的所有权限
                    Set<GrantedAuthority> permissionAuthorities = role.getPermissions().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                            .collect(Collectors.toSet());

                    // 合并角色和权限
                    permissionAuthorities.addAll(roleAuthorities);
                    return permissionAuthorities.stream();
                })
                .collect(Collectors.toSet());
    }

    /**
     * 获取用户拥有的所有权限（角色+权限）
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 获取加密后的密码
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * 获取用户名
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // 根据实际需求实现
    }

    /**
     * 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // 根据实际需求实现
    }

    /**
     * 凭证（密码）是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 根据实际需求实现
    }

    /**
     * 账户是否启用
     */
    @Override
    public boolean isEnabled() {
        return true; // 根据实际需求实现
    }

    /**
     * 获取原始的User实体
     */
    public User getUser() {
        return user;
    }

    /**
     * 检查用户是否拥有指定权限
     */
    public boolean hasAuthority(String authority) {
        return authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals(authority));
    }

    /**
     * 检查用户是否拥有指定角色
     */
    public boolean hasRole(String role) {
        String roleWithPrefix = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        return authorities.stream()
                .anyMatch(auth -> auth.getAuthority().equals(roleWithPrefix));
    }

    @Override
    public String toString() {
        return "YourUserDetails{" +
                "username='" + getUsername() + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}