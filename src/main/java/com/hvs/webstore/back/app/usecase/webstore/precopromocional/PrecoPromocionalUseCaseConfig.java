package com.hvs.webstore.back.app.usecase.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrecoPromocionalUseCaseConfig {

    @Bean
    public CreatePrecoPromocionalUseCase createPrecoPromocionalUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new CreatePrecoPromocionalUseCaseImpl(gateway);
    }
    @Bean
    public ReadPrecoPromocionalUseCase readPrecoPromocionalUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new ReadPrecoPromocionalUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllPrecoPromocionalUseCase readAllPrecoPromocionalUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new ReadAllPrecoPromocionalUseCaseImpl(gateway);
    }
    @Bean
    public ReadPrecoPromocionalByProdutoIdUseCase readPrecoPromocionalByProdutoIdUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new ReadPrecoPromocionalByProdutoIdUseCaseImpl(gateway);
    }
    @Bean
    public UpdatePrecoPromocionalUseCase updatePrecoPromocionalUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new UpdatePrecoPromocionalUseCaseImpl(gateway);
    }
    @Bean
    public PatchPrecoPromocionalUseCase patchPrecoPromocionalUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new PatchPrecoPromocionalUseCaseImpl(gateway);
    }
    @Bean
    public DeletePrecoPromocionalUseCase deletePrecoPromocionalUseCaseBean(PrecoPromocionalDomainGateway gateway) {

        return new DeletePrecoPromocionalUseCaseImpl(gateway);
    }
}
