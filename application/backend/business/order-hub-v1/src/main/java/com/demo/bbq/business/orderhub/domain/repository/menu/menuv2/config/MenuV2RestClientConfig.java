package com.demo.bbq.business.orderhub.domain.repository.menu.menuv2.config;

import com.demo.bbq.business.orderhub.domain.repository.menu.menuv2.MenuV2Repository;
import com.demo.bbq.support.httpclient.retrofit.reactive.SupportHttpClient;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MenuV2RestClientConfig {

  private final MenuV2RestClientProperties properties;

  @Bean
  MenuV2Repository menuOptionV2Api(OkHttpClient.Builder builder) {
    return SupportHttpClient.builder()
        .clientBuilder(builder)
        .baseUrl(properties.getBaseURL())
        .connectTimeout(Duration.ofMillis(300L))
        .readTimeout(Duration.ofMillis(1200L))
        .writeTimeout(Duration.ofMillis(700L))
        .buildProxy(MenuV2Repository.class);
  }
}