package com.hvs.webstore.back.infra.persistence.television.grade;

import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import com.hvs.webstore.back.infra.persistence.television.grade.GradeDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.television.grade.GradeJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeGatewayConfig {

    @Bean
    public GradeDomainGateway gradeDomainGatewayBean(GradeJpaRepository repository) {

        return new GradeDomainGatewayImpl(repository);
    }
}