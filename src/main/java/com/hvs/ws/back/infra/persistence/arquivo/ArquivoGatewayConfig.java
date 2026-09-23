package com.hvs.ws.back.infra.persistence.arquivo;

import com.hvs.ws.back.domain.entity.arquivo.ArquivoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoGatewayConfig {

    @Bean
    public ArquivoDomainGateway arquivoDomainGatewayBean(ArquivoJpaRepository repository) {

        return new ArquivoDomainGatewayImpl(repository);
    }
}