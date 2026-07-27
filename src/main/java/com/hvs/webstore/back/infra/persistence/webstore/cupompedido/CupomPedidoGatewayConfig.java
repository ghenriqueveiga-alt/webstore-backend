package com.hvs.webstore.back.infra.persistence.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.cupompedido.CupomPedidoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.cupompedido.CupomPedidoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CupomPedidoGatewayConfig {

    @Bean
    public CupomPedidoDomainGateway cupomPedidoDomainGatewayBean(CupomPedidoJpaRepository repository) {

        return new CupomPedidoDomainGatewayImpl(repository);
    }
}
