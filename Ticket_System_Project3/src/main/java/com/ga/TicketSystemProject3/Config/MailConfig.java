package com.ga.TicketSystemProject3.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class MailConfig {

    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
    private String defaultEncoding;
    private String from;

    // for: spring.mail.properties.*
    private Map<String, String> properties;

    public String getFrom() {
        return from;
    }
}