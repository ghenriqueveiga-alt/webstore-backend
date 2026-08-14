package com.hvs.webstore.back.infra.persistence.television.canal;

import com.hvs.webstore.back.domain.entity.television.canal.CanalDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CanalGatewayConfig {

    @Bean
    public CanalDomainGateway canalDomainGatewayBean(CanalJpaRepository repository) {

        return new CanalDomainGatewayImpl(repository);
    }
}
