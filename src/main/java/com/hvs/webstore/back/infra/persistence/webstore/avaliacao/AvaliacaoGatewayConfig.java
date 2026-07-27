package com.hvs.webstore.back.infra.persistence.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.avaliacao.AvaliacaoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.avaliacao.AvaliacaoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoGatewayConfig {

    @Bean
    public AvaliacaoDomainGateway avaliacaoDomainGatewayBean(AvaliacaoJpaRepository repository) {

        return new AvaliacaoDomainGatewayImpl(repository);
    }
}
