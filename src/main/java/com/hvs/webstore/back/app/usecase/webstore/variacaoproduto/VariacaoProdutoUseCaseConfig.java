package com.hvs.webstore.back.app.usecase.webstore.variacaoproduto;

import com.hvs.webstore.back.domain.entity.webstore.variacaoproduto.VariacaoProdutoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VariacaoProdutoUseCaseConfig {

    @Bean
    public CreateVariacaoProdutoUseCase createVariacaoProdutoUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new CreateVariacaoProdutoUseCaseImpl(gateway);
    }
    @Bean
    public ReadVariacaoProdutoUseCase readVariacaoProdutoUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new ReadVariacaoProdutoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllVariacaoProdutoUseCase readAllVariacaoProdutoUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new ReadAllVariacaoProdutoUseCaseImpl(gateway);
    }
    @Bean
    public ReadVariacaoProdutoByProdutoIdUseCase readVariacaoProdutoByProdutoIdUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new ReadVariacaoProdutoByProdutoIdUseCaseImpl(gateway);
    }
    @Bean
    public UpdateVariacaoProdutoUseCase updateVariacaoProdutoUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new UpdateVariacaoProdutoUseCaseImpl(gateway);
    }
    @Bean
    public PatchVariacaoProdutoUseCase patchVariacaoProdutoUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new PatchVariacaoProdutoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteVariacaoProdutoUseCase deleteVariacaoProdutoUseCaseBean(VariacaoProdutoDomainGateway gateway) {

        return new DeleteVariacaoProdutoUseCaseImpl(gateway);
    }
}
