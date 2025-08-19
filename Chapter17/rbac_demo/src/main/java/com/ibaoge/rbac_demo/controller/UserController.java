package com.ibaoge.rbac_demo.controller;

import com.ibaoge.rbac_demo.aop.RequiresPermission;
import com.ibaoge.rbac_demo.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// UserController.java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    @RequiresPermission("user:read") // 需要 user:read 权限
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // ... 业务逻辑
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @RequiresPermission("user:write") // 需要 user:write 权限
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // ... 业务逻辑
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @RequiresPermission("user:delete") // 需要 user:delete 权限
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // ... 业务逻辑
        return ResponseEntity.ok().build();
    }
}