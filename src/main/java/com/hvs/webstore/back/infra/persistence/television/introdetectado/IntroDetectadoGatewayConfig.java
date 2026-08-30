package com.hvs.webstore.back.infra.persistence.television.introdetectado;

import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntroDetectadoGatewayConfig {

    @Bean
    public IntroDetectadoDomainGateway introDetectadoDomainGatewayBean(final IntroDetectadoJpaRepository repository) {

        return new IntroDetectadoDomainGatewayImpl(repository);
    }
}