package com.demo.bbq.business.invoice.application.rules.properties;

import lombok.Data;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties("business")
@Data
public class RuleProperties {

  private Map<String, RuleInfo> rules;

}
