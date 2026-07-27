package com.hvs.webstore.back.app.usecase.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenVerificacaoUseCaseConfig {

    @Bean
    public CreateTokenVerificacaoUseCase createTokenVerificacaoUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new CreateTokenVerificacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadTokenVerificacaoUseCase readTokenVerificacaoUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new ReadTokenVerificacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllTokenVerificacaoUseCase readAllTokenVerificacaoUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new ReadAllTokenVerificacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadByTokenUseCase readByTokenUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new ReadByTokenUseCaseImpl(gateway);
    }
    @Bean
    public ReadByUsuarioIdUseCase readByUsuarioIdUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new ReadByUsuarioIdUseCaseImpl(gateway);
    }
    @Bean
    public UpdateTokenVerificacaoUseCase updateTokenVerificacaoUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new UpdateTokenVerificacaoUseCaseImpl(gateway);
    }
    @Bean
    public PatchTokenVerificacaoUseCase patchTokenVerificacaoUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new PatchTokenVerificacaoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteTokenVerificacaoUseCase deleteTokenVerificacaoUseCaseBean(TokenVerificacaoDomainGateway gateway) {

        return new DeleteTokenVerificacaoUseCaseImpl(gateway);
    }
}
