package com.hvs.webstore.back.infra.persistence.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import com.hvs.webstore.back.infra.persistence.television.corte.CorteDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.television.corte.CorteJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorteGatewayConfig {

    @Bean
    public CorteDomainGateway corteDomainGatewayBean(CorteJpaRepository repository) {

        return new CorteDomainGatewayImpl(repository);
    }
}