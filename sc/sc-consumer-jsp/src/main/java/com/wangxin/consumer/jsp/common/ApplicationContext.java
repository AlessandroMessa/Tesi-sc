package com.wangxin.consumer.jsp.common;

import com.wangxin.consumer.api.common.VersionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class ApplicationContext implements ServletContextAware {

    private static final Logger log = LoggerFactory.getLogger(ApplicationContext.class);
    private final VersionProvider versionProvider;

    public ApplicationContext(VersionProvider versionProvider) {
        this.versionProvider = versionProvider;
    }

    @Override
    public void setServletContext(ServletContext context) {
        String version = versionProvider.generateVersion();
        String contextPath = context.getContextPath();
        log.info("# version={} , contextPath={}", version, contextPath);
        context.setAttribute("version_css", version);
        context.setAttribute("version_js", version);
        context.setAttribute("ctx", contextPath);
    }
}
