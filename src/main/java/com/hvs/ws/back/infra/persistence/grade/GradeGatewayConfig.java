package com.hvs.ws.back.infra.persistence.grade;

import com.hvs.ws.back.domain.entity.grade.GradeDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeGatewayConfig {

    @Bean
    public GradeDomainGateway gradeDomainGatewayBean(GradeJpaRepository repository) {

        return new GradeDomainGatewayImpl(repository);
    }
}