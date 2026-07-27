package com.hvs.webstore.back.infra.persistence.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.pedido.PedidoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.pedido.PedidoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoGatewayConfig {

    @Bean
    public PedidoDomainGateway pedidoDomainGatewayBean(PedidoJpaRepository repository) {

        return new PedidoDomainGatewayImpl(repository);
    }
}
