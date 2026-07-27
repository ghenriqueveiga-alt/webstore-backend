package com.hvs.webstore.back.infra.persistence.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.preco.PrecoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrecoGatewayConfig {

    @Bean
    public PrecoDomainGateway precoDomainGatewayBean(PrecoJpaRepository repository) {

        return new PrecoDomainGatewayImpl(repository);
    }
}
