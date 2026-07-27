package com.hvs.webstore.back.infra.persistence.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.categoriahierarquia.CategoriaHierarquiaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.categoriahierarquia.CategoriaHierarquiaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaHierarquiaGatewayConfig {

    @Bean
    public CategoriaHierarquiaDomainGateway categoriaHierarquiaDomainGatewayBean(CategoriaHierarquiaJpaRepository repository) {

        return new CategoriaHierarquiaDomainGatewayImpl(repository);
    }
}
