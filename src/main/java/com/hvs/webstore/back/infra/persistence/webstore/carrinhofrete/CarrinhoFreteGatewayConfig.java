package com.hvs.webstore.back.infra.persistence.webstore.carrinhofrete;

import com.hvs.webstore.back.domain.entity.webstore.carrinhofrete.CarrinhoFreteDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.carrinhofrete.CarrinhoFreteDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.carrinhofrete.CarrinhoFreteJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarrinhoFreteGatewayConfig {

    @Bean
    public CarrinhoFreteDomainGateway carrinhoFreteDomainGatewayBean(CarrinhoFreteJpaRepository repository) {

        return new CarrinhoFreteDomainGatewayImpl(repository);
    }
}
