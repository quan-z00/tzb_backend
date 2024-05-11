package com.tzb.backend.common.aop;

import com.tzb.backend.common.annotation.ExcludeResultWrapper;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.common.utils.ResultUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 用于处理返回结果的切面拦截器。
 * 当被标记为@ResultWrapper的类或方法执行后，该拦截器将对其返回的结果进行封装。
 * 如果方法被标记了@ExcludeResultWrapper注解，则不会对返回结果进行封装。
 *
 * 示例使用：
 * <pre>
 *     {@code
 *     @RestController
 *     @RequestMapping("/api")
 *     @ResultWrapper
 *     public class ApiController {
 *         @GetMapping("/user/{id}")
 *         @ExcludeResultWrapper
 *         public User getUser(@PathVariable Long id) {
 *             // 从数据库或其他数据源获取用户信息
 *             return userRepository.findById(id).orElse(null);
 *         }
 *     }
 *     }
 * </pre>
 *
 * 注意：该切面拦截器仅用于处理被标记为@ResultWrapper的类或方法，不会对其他类或方法产生影响。
 *
 * @see ResultWrapper 返回结果封装注解，用于指示在返回结果中进行封装。
 * @see ExcludeResultWrapper 排除结果封装注解，用于指示不对返回结果进行封装。
 * @see ResultUtil 返回结果封装工具类，用于生成封装后的返回结果。
 */
@Aspect
@Component
public class ResultInterceptor {

    /**
     * 定义切入点，匹配被标记为@ResultWrapper的类。
     */
    @Pointcut("@within(com.tzb.backend.common.annotation.ResultWrapper)")
    public void resultWrapper() {
    }

    /**
     * 在被标记为@ResultWrapper的类或方法执行后，对其返回的结果进行封装。
     * 如果类或方法被标记了@ExcludeResultWrapper注解，则不会对返回结果进行封装。
     * @param joinPoint 切点
     * @return 封装后的返回结果对象
     * @throws Throwable 如果处理过程中发生异常
     */
    @Around("resultWrapper()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        Method method = getMethod(joinPoint);
        if ((method != null && method.isAnnotationPresent(ExcludeResultWrapper.class)) || clazz.isAnnotationPresent(ExcludeResultWrapper.class)) {
            return joinPoint.proceed();
        } else {
            return ResultUtil.success(joinPoint.proceed());
        }
    }

    /**
     * 获取切点对应的方法。
     * @param joinPoint 切点
     * @return 切点对应的方法，如果无法获取则返回null
     */
    private Method getMethod(ProceedingJoinPoint joinPoint) {
        try {
            Signature signature = joinPoint.getSignature();
            if (!(signature instanceof MethodSignature methodSignature)) {
                return null;
            }
            return methodSignature.getMethod();
        } catch (Exception e) {
            return null;
        }
    }
}
