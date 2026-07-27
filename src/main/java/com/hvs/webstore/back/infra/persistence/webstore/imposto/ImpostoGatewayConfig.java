package com.hvs.webstore.back.infra.persistence.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.imposto.ImpostoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.imposto.ImpostoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImpostoGatewayConfig {

    @Bean
    public ImpostoDomainGateway impostoDomainGatewayBean(ImpostoJpaRepository repository) {

        return new ImpostoDomainGatewayImpl(repository);
    }
}
