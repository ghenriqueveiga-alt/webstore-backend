package com.hvs.webstore.back.app.usecase.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarrinhoUseCaseConfig {

    @Bean
    public CreateCarrinhoUseCase createCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new CreateCarrinhoUseCaseImpl(gateway);
    }
    @Bean
    public ReadCarrinhoUseCase readCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new ReadCarrinhoUseCaseImpl(gateway);
    }
    @Bean
    public AddItemCarrinhoUseCase addItemCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new AddItemCarrinhoUseCaseImpl(gateway);
    }
    @Bean
    public RemoveItemCarrinhoUseCase removeItemCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new RemoveItemCarrinhoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateItemCarrinhoUseCase updateItemCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new UpdateItemCarrinhoUseCaseImpl(gateway);
    }
    @Bean
    public ClearCarrinhoUseCase clearCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new ClearCarrinhoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCarrinhoUseCase deleteCarrinhoUseCaseBean(CarrinhoDomainGateway gateway) {

        return new DeleteCarrinhoUseCaseImpl(gateway);
    }
}
