package com.hvs.ws.back.infra.persistence.programa;

import com.hvs.ws.back.domain.entity.programa.ProgramaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramaGatewayConfig {

    @Bean
    public ProgramaDomainGateway programaDomainGatewayBean(ProgramaJpaRepository repository) {

        return new ProgramaDomainGatewayImpl(repository);
    }
}
