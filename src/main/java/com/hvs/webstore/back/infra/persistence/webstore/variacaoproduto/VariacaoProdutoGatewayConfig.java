package com.hvs.webstore.back.infra.persistence.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.variacaoproduto.VariacaoProdutoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.variacaoproduto.VariacaoProdutoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VariacaoProdutoGatewayConfig {

    @Bean
    public VariacaoProdutoDomainGateway variacaoProdutoDomainGatewayBean(VariacaoProdutoJpaRepository repository) {

        return new VariacaoProdutoDomainGatewayImpl(repository);
    }
}
