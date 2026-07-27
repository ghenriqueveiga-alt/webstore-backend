package com.hvs.webstore.back.infra.persistence.webstore.tokenverificacao;

import com.hvs.webstore.back.domain.entity.webstore.tokenverificacao.TokenVerificacaoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.tokenverificacao.TokenVerificacaoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.tokenverificacao.TokenVerificacaoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenVerificacaoGatewayConfig {

    @Bean
    public TokenVerificacaoDomainGateway tokenVerificacaoDomainGatewayBean(TokenVerificacaoJpaRepository repository) {

        return new TokenVerificacaoDomainGatewayImpl(repository);
    }
}
