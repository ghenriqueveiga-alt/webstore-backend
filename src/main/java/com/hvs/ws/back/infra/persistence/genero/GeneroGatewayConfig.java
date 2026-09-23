package com.hvs.ws.back.infra.persistence.genero;

import com.hvs.ws.back.domain.entity.genero.GeneroDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneroGatewayConfig {

    @Bean
    public GeneroDomainGateway generoDomainGatewayBean(GeneroJpaRepository repository) {

        return new GeneroDomainGatewayImpl(repository);
    }
}
