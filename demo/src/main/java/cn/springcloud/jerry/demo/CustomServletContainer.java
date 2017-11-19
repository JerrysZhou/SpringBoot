package cn.springcloud.jerry.demo;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoujx
 * @date 2017-11-19 23:37
 */
public class CustomServletContainer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8080);
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
        container.setSessionTimeout(10, TimeUnit.MINUTES);
    }
}
