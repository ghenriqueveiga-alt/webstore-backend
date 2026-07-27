package com.hvs.webstore.back.infra.persistence.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.boleto.BoletoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.boleto.BoletoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoletoGatewayConfig {

    @Bean
    public BoletoDomainGateway BoletoDomainGatewayBean(BoletoJpaRepository repository) {

        return new BoletoDomainGatewayImpl(repository);
    }
}
