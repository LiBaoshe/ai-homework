package com.ibaoge.rbac_demo.aop;

import com.ibaoge.rbac_demo.entity.User;
import com.ibaoge.rbac_demo.service.YourUserDetails;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

// PermissionAspect.java
@Aspect
@Component
public class PermissionAspect {

    @Around("@annotation(requiresPermission)") // 环绕通知，拦截带有@RequiresPermission注解的方法
    public Object checkPermission(ProceedingJoinPoint joinPoint, RequiresPermission requiresPermission) throws Throwable {
        // 1. 从注解中获取需要的权限
        String requiredPermission = requiresPermission.value();

        // 2. 获取当前登录的用户信息（需要与Spring Security集成）
        // 这里假设你已经有了一个从SecurityContext中获取当前用户的方法
        User currentUser = getCurrentUser();

        if (currentUser == null) {
            throw new AccessDeniedException("用户未登录");
        }

        // 3. 检查用户是否拥有所需权限
        boolean hasPermission = currentUser.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .anyMatch(permission -> requiredPermission.equals(permission.getName()));

        // 4. 如果拥有权限，继续执行方法；否则，抛出拒绝访问异常
        if (hasPermission) {
            return joinPoint.proceed();
        } else {
            throw new AccessDeniedException("权限不足，需要权限: " + requiredPermission);
        }
    }

    // 这是一个示例方法，实际中你需要从Spring Security的SecurityContext中获取
    private User getCurrentUser() {
        // 实际实现：从SecurityContextHolder中获取Authentication，再获取用户详情
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // 假设你的UserDetailsService返回的是自定义的UserDetails实现，其中包含了User实体
            Object principal = authentication.getPrincipal();
            if (principal instanceof YourUserDetails) { // 你需要实现UserDetails
                return ((YourUserDetails) principal).getUser();
            }
        }
        return null;
    }
}