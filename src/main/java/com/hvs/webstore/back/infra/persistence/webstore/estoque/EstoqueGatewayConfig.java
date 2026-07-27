package com.hvs.webstore.back.infra.persistence.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.estoque.EstoqueDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.estoque.EstoqueJpaRepository;
import com.hvs.webstore.back.infra.persistence.webstore.estoque.MovimentoEstoqueJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueGatewayConfig {

    @Bean
    public EstoqueDomainGateway estoqueDomainGatewayBean(EstoqueJpaRepository repository,
                                                         MovimentoEstoqueJpaRepository movimentoRepository) {

        return new EstoqueDomainGatewayImpl(
                repository,
                movimentoRepository);
    }
}
