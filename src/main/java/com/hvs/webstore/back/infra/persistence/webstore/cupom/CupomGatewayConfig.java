package com.hvs.webstore.back.infra.persistence.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.cupom.CupomDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.cupom.CupomJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CupomGatewayConfig {

    @Bean
    public CupomDomainGateway cupomDomainGatewayBean(CupomJpaRepository repository) {

        return new CupomDomainGatewayImpl(repository);
    }
}
