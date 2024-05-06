package com.tzb.backend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记在某个方法上，指示在返回结果中不进行封装。
 * 标记了该注解的方法返回的结果将不会被封装，而是直接返回原始数据。
 *
 * 示例使用：
 * <pre>
 *     {@code
 *     @RestController
 *     @RequestMapping("/api")
 *     public class ApiController {
 *         @ExcludeResultWrapper
 *         @GetMapping("/user/{id}")
 *         public User getUser(@PathVariable Long id) {
 *             // 从数据库或其他数据源获取用户信息
 *             return userRepository.findById(id).orElse(null);
 *         }
 *     }
 *     }
 * </pre>
 *
 * 注意：该注解仅用于标记方法，用于指示在返回结果中不进行封装。
 *
 * @see com.tzb.backend.annotation.ResultWrapper 返回结果封装注解，用于指示在返回结果中进行封装。
 * @author 29002
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ExcludeResultWrapper {
}
