package com.hvs.webstore.back.app.usecase.webstore.estoque;

import com.hvs.webstore.back.domain.entity.webstore.estoque.EstoqueDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueUseCaseConfig {

    @Bean
    public CreateEstoqueUseCase createEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new CreateEstoqueUseCaseImpl(gateway);
    }
    @Bean
    public ReadEstoqueUseCase readEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new ReadEstoqueUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllEstoqueUseCase readAllEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new ReadAllEstoqueUseCaseImpl(gateway);
    }
    @Bean
    public UpdateEstoqueUseCase updateEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new UpdateEstoqueUseCaseImpl(gateway);
    }
    @Bean
    public PatchEstoqueUseCase patchEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new PatchEstoqueUseCaseImpl(gateway);
    }
    @Bean
    public DeleteEstoqueUseCase deleteEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new DeleteEstoqueUseCaseImpl(gateway);
    }
    @Bean
    public MovimentarEstoqueUseCase movimentarEstoqueUseCaseBean(EstoqueDomainGateway gateway) {

        return new MovimentarEstoqueUseCaseImpl(gateway);
    }
}
