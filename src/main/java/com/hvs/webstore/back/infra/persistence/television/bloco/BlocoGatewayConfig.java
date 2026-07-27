package com.hvs.webstore.back.infra.persistence.television.bloco;

import com.hvs.webstore.back.domain.entity.television.bloco.BlocoDomainGateway;
import com.hvs.webstore.back.infra.persistence.television.bloco.BlocoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.television.bloco.BlocoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlocoGatewayConfig {

    @Bean
    public BlocoDomainGateway blocoDomainGatewayBean(BlocoJpaRepository repository) {

        return new BlocoDomainGatewayImpl(repository);
    }
}