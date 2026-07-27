package com.hvs.webstore.back.infra.persistence.webstore.transportadora;

import com.hvs.webstore.back.domain.entity.webstore.transportadora.TransportadoraDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.transportadora.TransportadoraDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.transportadora.TransportadoraJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransportadoraGatewayConfig {

    @Bean
    public TransportadoraDomainGateway transportadoraDomainGatewayBean(TransportadoraJpaRepository repository) {

        return new TransportadoraDomainGatewayImpl(repository);
    }
}
