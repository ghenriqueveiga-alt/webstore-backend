package com.hvs.webstore.back.infra.persistence.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.anexo.AnexoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.anexo.AnexoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnexoGatewayConfig {

    @Bean
    public AnexoDomainGateway anexoDomainGatewayBean(AnexoJpaRepository repository) {

        return new AnexoDomainGatewayImpl(repository);
    }
}
