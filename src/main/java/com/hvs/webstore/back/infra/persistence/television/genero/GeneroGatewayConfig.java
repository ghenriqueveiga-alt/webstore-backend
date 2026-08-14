package com.hvs.webstore.back.infra.persistence.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneroGatewayConfig {

    @Bean
    public GeneroDomainGateway generoDomainGatewayBean(GeneroJpaRepository repository) {

        return new GeneroDomainGatewayImpl(repository);
    }
}
