package com.hvs.webstore.back.app.usecase.webstore.historicopedido;

import com.hvs.webstore.back.domain.entity.webstore.historicopedido.HistoricoPedidoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HistoricoPedidoUseCaseConfig {

    @Bean
    public CreateHistoricoPedidoUseCase createHistoricoPedidoUseCaseBean(HistoricoPedidoDomainGateway gateway) {

        return new CreateHistoricoPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadHistoricoPedidoUseCase readHistoricoPedidoUseCaseBean(HistoricoPedidoDomainGateway gateway) {

        return new ReadHistoricoPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllHistoricoPedidoUseCase readAllHistoricoPedidoUseCaseBean(HistoricoPedidoDomainGateway gateway) {

        return new ReadAllHistoricoPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadHistoricoPedidoByPedidoIdUseCase readHistoricoPedidoByPedidoIdUseCaseBean(HistoricoPedidoDomainGateway gateway) {

        return new ReadHistoricoPedidoByPedidoIdUseCaseImpl(gateway);
    }
}
