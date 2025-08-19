package com.ibaoge.rbac_demo.service.impl;

import com.ibaoge.rbac_demo.entity.Permission;
import com.ibaoge.rbac_demo.entity.Role;
import com.ibaoge.rbac_demo.entity.User;
import com.ibaoge.rbac_demo.service.UserRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    // 初始化一些测试数据
    public InMemoryUserRepository() {
        initializeTestData();
    }

    private void initializeTestData() {
        // 创建权限
        Permission readPermission = new Permission();
        readPermission.setId(1L);
        readPermission.setName("user:read");
        readPermission.setDescription("读取用户权限");

        Permission writePermission = new Permission();
        writePermission.setId(2L);
        writePermission.setName("user:write");
        writePermission.setDescription("写入用户权限");

        Permission deletePermission = new Permission();
        deletePermission.setId(3L);
        deletePermission.setName("user:delete");
        deletePermission.setDescription("删除用户权限");

        // 创建角色
        Role userRole = new Role();
        userRole.setId(1L);
        userRole.setName("USER");
        userRole.setPermissions(new HashSet<>(Arrays.asList(readPermission)));

        Role adminRole = new Role();
        adminRole.setId(2L);
        adminRole.setName("ADMIN");
        adminRole.setPermissions(new HashSet<>(Arrays.asList(readPermission, writePermission, deletePermission)));

        Role managerRole = new Role();
        managerRole.setId(3L);
        managerRole.setName("MANAGER");
        managerRole.setPermissions(new HashSet<>(Arrays.asList(readPermission, writePermission)));

        // 创建用户
        User user1 = new User();
        user1.setId(idCounter.getAndIncrement());
        user1.setUsername("alice");
        user1.setPassword("$2a$10$YourHashedPasswordHere"); // 密码通常是加密的
        user1.setRoles(new HashSet<>(Arrays.asList(userRole)));

        User user2 = new User();
        user2.setId(idCounter.getAndIncrement());
        user2.setUsername("bob");
        user2.setPassword("$2a$10$YourHashedPasswordHere");
        user2.setRoles(new HashSet<>(Arrays.asList(adminRole)));

        User user3 = new User();
        user3.setId(idCounter.getAndIncrement());
        user3.setUsername("charlie");
        user3.setPassword("$2a$10$YourHashedPasswordHere");
        user3.setRoles(new HashSet<>(Arrays.asList(managerRole)));

        users.put(user1.getId(), user1);
        users.put(user2.getId(), user2);
        users.put(user3.getId(), user3);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter.getAndIncrement());
        }
        users.put(user.getId(), user);
        return user;
    }
}