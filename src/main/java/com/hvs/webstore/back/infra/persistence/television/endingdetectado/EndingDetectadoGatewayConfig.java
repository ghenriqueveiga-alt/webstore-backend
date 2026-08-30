package com.hvs.webstore.back.infra.persistence.television.endingdetectado;

import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndingDetectadoGatewayConfig {

    @Bean
    public EndingDetectadoDomainGateway endingDetectadoDomainGatewayBean(final EndingDetectadoJpaRepository repository) {

        return new EndingDetectadoDomainGatewayImpl(repository);
    }
}
