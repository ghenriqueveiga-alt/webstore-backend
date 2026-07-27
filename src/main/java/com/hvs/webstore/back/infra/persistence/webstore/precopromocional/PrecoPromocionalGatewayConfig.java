package com.hvs.webstore.back.infra.persistence.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocionalDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.precopromocional.PrecoPromocionalDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.precopromocional.PrecoPromocionalJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrecoPromocionalGatewayConfig {

    @Bean
    public PrecoPromocionalDomainGateway precoPromocionalDomainGatewayBean(PrecoPromocionalJpaRepository repository) {

        return new PrecoPromocionalDomainGatewayImpl(repository);
    }
}
