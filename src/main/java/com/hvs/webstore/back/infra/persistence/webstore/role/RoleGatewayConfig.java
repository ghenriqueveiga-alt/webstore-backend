package com.hvs.webstore.back.infra.persistence.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.role.RoleDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.role.RoleJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleGatewayConfig {

    @Bean
    public RoleDomainGateway roleDomainGatewayBean(RoleJpaRepository repository) {

        return new RoleDomainGatewayImpl(repository);
    }
}
