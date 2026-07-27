package com.hvs.webstore.back.infra.persistence.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.historicopedido.HistoricoPedidoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.historicopedido.HistoricoPedidoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoricoPedidoGatewayConfig {

    @Bean
    public HistoricoPedidoDomainGateway historicoPedidoDomainGatewayBean(HistoricoPedidoJpaRepository repository) {

        return new HistoricoPedidoDomainGatewayImpl(repository);
    }
}
