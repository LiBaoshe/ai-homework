# 使用 Spring Boot 实现一个简单的、基于RBAC的API权限控制装饰器。  
## 1.定义数据模型（RBAC）：  
定义用户、角色、权限等实体及其关系。  
## 2.创建自定义注解 @RequiresPermission：  
用于标记需要特定权限才能访问的接口。  
## 3.实现权限验证逻辑：  
创建一个切面（Aspect）或一个 Spring Security 的 AuthorizationManager，在接口被调用前拦截并验证当前用户是否拥有注解指定的权限。  
## 4.集成 Spring Security：  
配置 Spring Security 来处理用户认证和整合我们的权限验证逻辑。  
