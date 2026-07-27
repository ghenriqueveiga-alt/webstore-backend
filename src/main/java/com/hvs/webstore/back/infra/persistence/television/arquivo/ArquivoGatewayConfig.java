package com.hvs.webstore.back.infra.persistence.television.arquivo;

import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import com.hvs.webstore.back.infra.persistence.television.arquivo.ArquivoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.television.arquivo.ArquivoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoGatewayConfig {

    @Bean
    public ArquivoDomainGateway arquivoDomainGatewayBean(ArquivoJpaRepository repository) {

        return new ArquivoDomainGatewayImpl(repository);
    }
}