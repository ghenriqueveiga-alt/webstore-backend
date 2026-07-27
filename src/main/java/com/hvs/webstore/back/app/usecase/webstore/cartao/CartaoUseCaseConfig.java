package com.hvs.webstore.back.app.usecase.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartaoUseCaseConfig {

    @Bean
    public CreateCartaoUseCase createCartaoUseCaseBean(CartaoDomainGateway gateway) {

        return new CreateCartaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadCartaoUseCase readCartaoUseCaseBean(CartaoDomainGateway gateway) {

        return new ReadCartaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCartaoUseCase readAllCartaoUseCaseBean(CartaoDomainGateway gateway) {

        return new ReadAllCartaoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCartaoUseCase updateCartaoUseCaseBean(CartaoDomainGateway gateway) {

        return new UpdateCartaoUseCaseImpl(gateway);
    }
    @Bean
    public PatchCartaoUseCase patchCartaoUseCaseBean(CartaoDomainGateway gateway) {

        return new PatchCartaoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCartaoUseCase deleteCartaoUseCaseBean(CartaoDomainGateway gateway) {

        return new DeleteCartaoUseCaseImpl(gateway);
    }
}
