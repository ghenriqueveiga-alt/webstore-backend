package com.hvs.webstore.back.app.usecase.webstore.pedido;

import com.hvs.webstore.back.domain.entity.webstore.pedido.PedidoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoUseCaseConfig {

    @Bean
    public CreatePedidoUseCase createPedidoUseCaseBean(PedidoDomainGateway gateway) {

        return new CreatePedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadPedidoUseCase readPedidoUseCaseBean(PedidoDomainGateway gateway) {

        return new ReadPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllPedidoUseCase readAllPedidoUseCaseBean(PedidoDomainGateway gateway) {

        return new ReadAllPedidoUseCaseImpl(gateway);
    }
    @Bean
    public UpdatePedidoUseCase updatePedidoUseCaseBean(PedidoDomainGateway gateway) {

        return new UpdatePedidoUseCaseImpl(gateway);
    }
    @Bean
    public PatchPedidoUseCase patchPedidoUseCaseBean(PedidoDomainGateway gateway) {

        return new PatchPedidoUseCaseImpl(gateway);
    }
    @Bean
    public DeletePedidoUseCase deletePedidoUseCaseBean(PedidoDomainGateway gateway) {

        return new DeletePedidoUseCaseImpl(gateway);
    }
}
