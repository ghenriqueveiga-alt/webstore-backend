package com.hvs.webstore.back.infra.persistence.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.infra.persistence.television.programa.ProgramaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.television.programa.ProgramaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramaGatewayConfig {

    @Bean
    public ProgramaDomainGateway programaDomainGatewayBean(ProgramaJpaRepository repository) {

        return new ProgramaDomainGatewayImpl(repository);
    }
}
