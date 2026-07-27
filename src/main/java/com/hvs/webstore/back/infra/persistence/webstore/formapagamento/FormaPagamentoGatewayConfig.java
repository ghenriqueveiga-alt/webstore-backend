package com.hvs.webstore.back.infra.persistence.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.formapagamento.FormaPagamentoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.formapagamento.FormaPagamentoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormaPagamentoGatewayConfig {

    @Bean
    public FormaPagamentoDomainGateway formaPagamentoDomainGatewayBean(FormaPagamentoJpaRepository repository) {

        return new FormaPagamentoDomainGatewayImpl(repository);
    }
}
