package cn.springboot.jerry.demo.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author zhoujx
 * @date 2017-11-21 22:32
 */
@Aspect
@Component
public class WebLogAspect {

    private final Logger log = Logger.getLogger(getClass());

    @Pointcut("execution(public * cn.springboot.jerry.demo.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        final ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = attributes.getRequest();
        log.info("url:" + request.getRequestURL().toString());
        log.info("http_method:" + request.getMethod());
        log.info("ip:" + request.getRemoteAddr());

        final Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            final String name = parameterNames.nextElement();
            log.info(String.format("name:%s,value:%s", name, request.getParameter(name)));
        }
    }

    @AfterReturning(pointcut = "webLog()", returning = "rst")
    public void doAfter(Object rst) {
        log.info("response : " + rst.toString());
    }

}
