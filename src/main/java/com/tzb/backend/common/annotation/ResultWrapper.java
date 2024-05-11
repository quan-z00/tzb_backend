package com.tzb.backend.common.annotation;

import com.tzb.backend.common.utils.ResultUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记类,方法，指示在返回结果中进行封装。
 * 标记了该注解的类下的方法返回结果将以特定类的对象形式进行封装。
 * 通常与返回结果封装工具类结合使用，以便在返回结果中正确地封装数据。
 * 示例使用：
 * <pre>
 *     {@code
 *     @RestController
 *     @RequestMapping("/api")
 *     @ResultWrapper
 *     public class UserController {
 *         @GetMapping("/user/{id}")
 *         public User getUser(@PathVariable Long id) {
 *             // 从数据库或其他数据源获取用户信息
 *             return userRepository.findById(id).orElse(null);
 *         }
 *     }
 *     }
 * </pre>
 * 在这个例子中，UserController类被标记为封装返回结果的类，该类下的getUser方法返回的结果将以特定类的对象形式进行封装。
 * <p>
 * 注意：该注解仅用于标记控制器类或方法，不会对类本身产生任何影响，需要结合相应的处理逻辑来实现封装的功能。
 *
 * @author 29002
 * @see ResultUtil
 * @see com.tzb.backend.common.aop.ResultInterceptor
 * @see com.tzb.backend.common.core.ResultWrapper
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ResultWrapper {
}

