package com.hvs.webstore.back.infra.persistence.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.metaloja.MetaLojaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.metaloja.MetaLojaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetaLojaGatewayConfig {

    @Bean
    public MetaLojaDomainGateway metaLojaDomainGatewayBean(MetaLojaJpaRepository repository) {

        return new MetaLojaDomainGatewayImpl(repository);
    }
}
