package com.hvs.webstore.back.infra.persistence.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.notafiscal.NotaFiscalDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.notafiscal.NotaFiscalJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotaFiscalGatewayConfig {

    @Bean
    public NotaFiscalDomainGateway notaFiscalDomainGatewayBean(NotaFiscalJpaRepository repository) {

        return new NotaFiscalDomainGatewayImpl(repository);
    }
}
