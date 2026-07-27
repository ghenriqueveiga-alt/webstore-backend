package com.hvs.webstore.back.infra.persistence.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.pix.PixDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.pix.PixJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixGatewayConfig {

    @Bean
    public PixDomainGateway pixDomainGatewayBean(PixJpaRepository repository) {

        return new PixDomainGatewayImpl(repository);
    }
}
