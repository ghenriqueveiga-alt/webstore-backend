package com.hvs.webstore.back.app.usecase.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarrinhoFreteUseCaseConfig {

    @Bean
    public CreateCarrinhoFreteUseCase createCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

return new CreateCarrinhoFreteUseCaseImpl(gateway);
    }
    @Bean
    public ReadCarrinhoFreteUseCase readCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

        return new ReadCarrinhoFreteUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCarrinhoFreteUseCase readAllCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

        return new ReadAllCarrinhoFreteUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCarrinhoFreteUseCase updateCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

        return new UpdateCarrinhoFreteUseCaseImpl(gateway);
    }
    @Bean
    public PatchCarrinhoFreteUseCase patchCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

        return new PatchCarrinhoFreteUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCarrinhoFreteUseCase deleteCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

        return new DeleteCarrinhoFreteUseCaseImpl(gateway);
    }
    @Bean
    public ReadByCarrinhoIdCarrinhoFreteUseCase readByCarrinhoIdCarrinhoFreteUseCaseBean(CarrinhoFreteDomainGateway gateway) {

        return new ReadByCarrinhoIdCarrinhoFreteUseCaseImpl(gateway);
    }
}
