package com.hvs.webstore.back.infra.persistence.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.endereco.EnderecoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.endereco.EnderecoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoGatewayConfig {

    @Bean
    public EnderecoDomainGateway enderecoDomainGatewayBean(EnderecoJpaRepository repository) {

        return new EnderecoDomainGatewayImpl(repository);
    }
}
