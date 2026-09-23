package com.hvs.ws.back.infra.persistence.bloco;

import com.hvs.ws.back.domain.entity.bloco.BlocoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlocoGatewayConfig {

    @Bean
    public BlocoDomainGateway blocoDomainGatewayBean(BlocoJpaRepository repository) {

        return new BlocoDomainGatewayImpl(repository);
    }
}