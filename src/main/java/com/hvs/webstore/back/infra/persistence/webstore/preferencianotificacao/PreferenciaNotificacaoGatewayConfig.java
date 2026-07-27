package com.hvs.webstore.back.infra.persistence.webstore.preferencianotificacao;

import com.hvs.webstore.back.domain.entity.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.preferencianotificacao.PreferenciaNotificacaoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.preferencianotificacao.PreferenciaNotificacaoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PreferenciaNotificacaoGatewayConfig {

    @Bean
    public PreferenciaNotificacaoDomainGateway preferenciaNotificacaoDomainGatewayBean(PreferenciaNotificacaoJpaRepository repository) {

        return new PreferenciaNotificacaoDomainGatewayImpl(repository);
    }
}
