package com.hvs.webstore.back.infra.persistence.webstore.cartao;

import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.cartao.CartaoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.cartao.CartaoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartaoGatewayConfig {

    @Bean
    public CartaoDomainGateway cartaoDomainGatewayBean(CartaoJpaRepository repository) {

        return new CartaoDomainGatewayImpl(repository);
    }
}
