package com.hvs.webstore.back.app.usecase.television.corte;

import com.hvs.webstore.back.domain.entity.television.corte.CorteDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorteUseCaseConfig {

    @Bean
    public CreateCorteUseCase createCorteUseCaseBean(CorteDomainGateway gateway) {

        return new CreateCorteUseCaseImpl(gateway);
    }
    @Bean
    public ReadCorteUseCase readCorteUseCaseBean(CorteDomainGateway gateway) {

        return new ReadCorteUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCorteUseCase readAllCorteUseCaseBean(CorteDomainGateway gateway) {

        return new ReadAllCorteUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCorteUseCase updateCorteUseCaseBean(CorteDomainGateway gateway) {

        return new UpdateCorteUseCaseImpl(gateway);
    }
    @Bean
    public PatchCorteUseCase patchCorteUseCaseBean(CorteDomainGateway gateway) {

        return new PatchCorteUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCorteUseCase deleteCorteUseCaseBean(CorteDomainGateway gateway) {

        return new DeleteCorteUseCaseImpl(gateway);
    }
}
