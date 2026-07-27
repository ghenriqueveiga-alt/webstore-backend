package com.hvs.webstore.back.app.usecase.webstore.cupompedido;

import com.hvs.webstore.back.domain.entity.webstore.cupompedido.CupomPedidoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CupomPedidoUseCaseConfig {

    @Bean
    public CreateCupomPedidoUseCase createCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new CreateCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadCupomPedidoUseCase readCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new ReadCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCupomPedidoUseCase readAllCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new ReadAllCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadByCupomIdCupomPedidoUseCase readByCupomIdCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new ReadByCupomIdCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public ReadByPedidoIdCupomPedidoUseCase readByPedidoIdCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new ReadByPedidoIdCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCupomPedidoUseCase updateCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new UpdateCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public PatchCupomPedidoUseCase patchCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new PatchCupomPedidoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCupomPedidoUseCase deleteCupomPedidoUseCaseBean(CupomPedidoDomainGateway gateway) {

        return new DeleteCupomPedidoUseCaseImpl(gateway);
    }
}
