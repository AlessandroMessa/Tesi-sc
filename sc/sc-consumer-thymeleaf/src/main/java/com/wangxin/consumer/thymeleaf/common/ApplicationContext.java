package com.wangxin.consumer.thymeleaf.common;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 将version版本号写入application中，给css,js引用时用
 */
@Component
public class ApplicationContext implements ServletContextAware {

    private static final Logger log = LoggerFactory.getLogger(ApplicationContext.class);

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    // @Autowired
    // private DiscoveryClient client;

    @Override
    public void setServletContext(ServletContext context) {
        String datetime = ZonedDateTime.now().format(FORMATTER);
        String contextPath = context.getContextPath();
        context.setAttribute("version_css", datetime);
        context.setAttribute("version_js", datetime);

        // FIXME 此处可以不要
        // if (StringUtils.isBlank(contextPath)) {
        // ServiceInstance instance = client.getLocalServiceInstance();
        // contextPath = "http://" + instance.getHost() + ":" + instance.getPort();
        // }

        log.info("# version={} , contextPath={}", datetime, contextPath);
        context.setAttribute("ctx", contextPath);
    }

}