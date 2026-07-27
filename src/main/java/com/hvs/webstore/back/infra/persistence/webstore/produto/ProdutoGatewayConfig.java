package com.hvs.webstore.back.infra.persistence.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoGatewayConfig {

    @Bean
    public ProdutoDomainGateway produtoDomainGatewayBean(ProdutoJpaRepository repository) {

        return new ProdutoDomainGatewayImpl(repository);
    }
}
