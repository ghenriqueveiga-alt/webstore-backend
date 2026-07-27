package com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreferenciaNotificacaoUseCaseConfig {

    @Bean
    public CreatePreferenciaNotificacaoUseCase createPreferenciaNotificacaoUseCaseBean(PreferenciaNotificacaoDomainGateway gateway) {

        return new CreatePreferenciaNotificacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadPreferenciaNotificacaoUseCase readPreferenciaNotificacaoUseCaseBean(PreferenciaNotificacaoDomainGateway gateway) {

        return new ReadPreferenciaNotificacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllPreferenciaNotificacaoUseCase readAllPreferenciaNotificacaoUseCaseBean(PreferenciaNotificacaoDomainGateway gateway) {

        return new ReadAllPreferenciaNotificacaoUseCaseImpl(gateway);
    }
    @Bean
    public UpdatePreferenciaNotificacaoUseCase updatePreferenciaNotificacaoUseCaseBean(PreferenciaNotificacaoDomainGateway gateway) {

        return new UpdatePreferenciaNotificacaoUseCaseImpl(gateway);
    }
    @Bean
    public PatchPreferenciaNotificacaoUseCase patchPreferenciaNotificacaoUseCaseBean(PreferenciaNotificacaoDomainGateway gateway) {

        return new PatchPreferenciaNotificacaoUseCaseImpl(gateway);
    }
    @Bean
    public DeletePreferenciaNotificacaoUseCase deletePreferenciaNotificacaoUseCaseBean(PreferenciaNotificacaoDomainGateway gateway) {

        return new DeletePreferenciaNotificacaoUseCaseImpl(gateway);
    }
}
