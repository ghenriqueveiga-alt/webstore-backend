package com.hvs.webstore.back.infra.persistence.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.FreteDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.frete.FreteDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.frete.FreteJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreteGatewayConfig {

    @Bean
    public FreteDomainGateway freteDomainGatewayBean(FreteJpaRepository repository) {

        return new FreteDomainGatewayImpl(repository);
    }
}
