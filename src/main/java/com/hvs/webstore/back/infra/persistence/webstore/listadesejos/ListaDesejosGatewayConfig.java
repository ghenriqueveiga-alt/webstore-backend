package com.hvs.webstore.back.infra.persistence.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.listadesejos.ListaDesejosDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.listadesejos.ListaDesejosJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaDesejosGatewayConfig {

    @Bean
    public ListaDesejosDomainGateway listaDesejosDomainGatewayBean(ListaDesejosJpaRepository repository) {

        return new ListaDesejosDomainGatewayImpl(repository);
    }
}
