package com.hvs.webstore.back.infra.persistence.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.marca.MarcaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.marca.MarcaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarcaGatewayConfig {

    @Bean
    public MarcaDomainGateway marcaDomainGatewayBean(MarcaJpaRepository repository) {

        return new MarcaDomainGatewayImpl(repository);
    }
}
