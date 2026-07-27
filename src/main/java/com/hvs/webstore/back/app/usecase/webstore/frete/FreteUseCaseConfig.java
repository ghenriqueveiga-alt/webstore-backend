package com.hvs.webstore.back.app.usecase.webstore.frete;

import com.hvs.webstore.back.domain.entity.webstore.frete.FreteDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreteUseCaseConfig {

    @Bean
    public CalcularFreteUseCase calcularFreteUseCaseBean(FreteDomainGateway gateway) {

        return new CalcularFreteUseCaseImpl(gateway);
    }
    @Bean
    public ReadFreteUseCase readFreteUseCaseBean(FreteDomainGateway gateway) {

        return new ReadFreteUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllFreteUseCase readAllFreteUseCaseBean(FreteDomainGateway gateway) {

        return new ReadAllFreteUseCaseImpl(gateway);
    }
}
