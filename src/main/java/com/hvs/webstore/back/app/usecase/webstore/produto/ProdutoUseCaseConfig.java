package com.hvs.webstore.back.app.usecase.webstore.produto;

import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProdutoUseCaseConfig {

    @Bean
    public CreateProdutoUseCase createProdutoUseCaseBean(ProdutoDomainGateway gateway) {

        return new CreateProdutoUseCaseImpl(gateway);
    }
    @Bean
    public ReadProdutoUseCase readProdutoUseCaseBean(ProdutoDomainGateway gateway) {

        return new ReadProdutoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllProdutoUseCase readAllProdutoUseCaseBean(ProdutoDomainGateway gateway) {

        return new ReadAllProdutoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateProdutoUseCase updateProdutoUseCaseBean(ProdutoDomainGateway gateway) {

        return new UpdateProdutoUseCaseImpl(gateway);
    }
    @Bean
    public PatchProdutoUseCase patchProdutoUseCaseBean(ProdutoDomainGateway gateway) {

        return new PatchProdutoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteProdutoUseCase deleteProdutoUseCaseBean(ProdutoDomainGateway gateway) {

        return new DeleteProdutoUseCaseImpl(gateway);
    }
}
