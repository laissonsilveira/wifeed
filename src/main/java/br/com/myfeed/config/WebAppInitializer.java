package br.com.myfeed.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({
        "br.com.myfeed.controller",
        "br.com.myfeed.service",
        "br.com.myfeed.model"
})
public class WebAppInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebAppInitializer.class, args);
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//
//	TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//
//	Connector ajpConnector = new Connector("AJP/1.3");
//	ajpConnector.setProtocol("AJP/1.3");
//	ajpConnector.setPort(9090);
//	ajpConnector.setSecure(false);
//	ajpConnector.setAllowTrace(false);
//	ajpConnector.setScheme("http");
//	tomcat.addAdditionalTomcatConnectors(ajpConnector);
//
//	return tomcat;
//    }

}
