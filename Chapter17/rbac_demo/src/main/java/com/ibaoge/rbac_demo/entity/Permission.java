package com.ibaoge.rbac_demo.entity;

import jakarta.persistence.*;
import lombok.Data;

// Permission.java 权限实体
@Entity
@Table(name = "permissions")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 权限标识符，例如：user:read, user:write, product:delete
    private String description;
}