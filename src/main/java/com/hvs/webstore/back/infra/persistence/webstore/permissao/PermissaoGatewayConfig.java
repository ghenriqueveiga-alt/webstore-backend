package com.hvs.webstore.back.infra.persistence.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.permissao.PermissaoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.permissao.PermissaoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissaoGatewayConfig {

    @Bean
    public PermissaoDomainGateway permissaoDomainGatewayBean(PermissaoJpaRepository repository) {

        return new PermissaoDomainGatewayImpl(repository);
    }
}
