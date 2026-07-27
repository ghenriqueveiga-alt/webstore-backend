package com.hvs.webstore.back.infra.persistence.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.categoria.CategoriaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.categoria.CategoriaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaGatewayConfig {

    @Bean
    public CategoriaDomainGateway categoriaDomainGatewayBean(CategoriaJpaRepository repository) {

        return new CategoriaDomainGatewayImpl(repository);
    }
}
