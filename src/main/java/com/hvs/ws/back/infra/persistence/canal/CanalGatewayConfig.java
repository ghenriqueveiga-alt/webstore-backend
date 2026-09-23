package com.hvs.ws.back.infra.persistence.canal;

import com.hvs.ws.back.domain.entity.canal.CanalDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CanalGatewayConfig {

    @Bean
    public CanalDomainGateway canalDomainGatewayBean(CanalJpaRepository repository) {

        return new CanalDomainGatewayImpl(repository);
    }
}
