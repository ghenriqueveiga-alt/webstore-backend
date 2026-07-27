package com.hvs.webstore.back.app.usecase.webstore.preco;

import com.hvs.webstore.back.domain.entity.webstore.preco.PrecoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrecoUseCaseConfig {

    @Bean
    public CreatePrecoUseCase createPrecoUseCaseBean(PrecoDomainGateway gateway) {

        return new CreatePrecoUseCaseImpl(gateway);
    }
    @Bean
    public ReadPrecoUseCase readPrecoUseCaseBean(PrecoDomainGateway gateway) {

        return new ReadPrecoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllPrecoUseCase readAllPrecoUseCaseBean(PrecoDomainGateway gateway) {

        return new ReadAllPrecoUseCaseImpl(gateway);
    }
    @Bean
    public UpdatePrecoUseCase updatePrecoUseCaseBean(PrecoDomainGateway gateway) {

        return new UpdatePrecoUseCaseImpl(gateway);
    }
    @Bean
    public PatchPrecoUseCase patchPrecoUseCaseBean(PrecoDomainGateway gateway) {

        return new PatchPrecoUseCaseImpl(gateway);
    }
    @Bean
    public DeletePrecoUseCase deletePrecoUseCaseBean(PrecoDomainGateway gateway) {

        return new DeletePrecoUseCaseImpl(gateway);
    }
}
