package com.hvs.webstore.back.infra.persistence.webstore.carrinho;

import com.hvs.webstore.back.domain.entity.webstore.carrinho.CarrinhoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.carrinho.CarrinhoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.carrinho.CarrinhoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarrinhoGatewayConfig {

    @Bean
    public CarrinhoDomainGateway carrinhoDomainGatewayBean(CarrinhoJpaRepository repository) {

        return new CarrinhoDomainGatewayImpl(repository);
    }
}
