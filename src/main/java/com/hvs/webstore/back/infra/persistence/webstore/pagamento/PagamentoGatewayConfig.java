package com.hvs.webstore.back.infra.persistence.webstore.pagamento;

import com.hvs.webstore.back.domain.entity.webstore.pagamento.PagamentoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.pagamento.PagamentoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.pagamento.PagamentoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoGatewayConfig {

    @Bean
    public PagamentoDomainGateway pagamentoDomainGatewayBean(PagamentoJpaRepository repository) {

        return new PagamentoDomainGatewayImpl(repository);
    }
}
