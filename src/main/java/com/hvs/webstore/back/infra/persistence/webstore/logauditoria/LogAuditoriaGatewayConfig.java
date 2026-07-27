package com.hvs.webstore.back.infra.persistence.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.logauditoria.LogAuditoriaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.logauditoria.LogAuditoriaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAuditoriaGatewayConfig {

    @Bean
    public LogAuditoriaDomainGateway logAuditoriaDomainGatewayBean(LogAuditoriaJpaRepository repository) {

        return new LogAuditoriaDomainGatewayImpl(repository);
    }
}
