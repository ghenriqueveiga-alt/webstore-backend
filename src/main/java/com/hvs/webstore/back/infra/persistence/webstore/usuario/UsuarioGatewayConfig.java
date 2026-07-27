package com.hvs.webstore.back.infra.persistence.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioGatewayConfig {

    @Bean
    public UsuarioDomainGateway usuarioDomainGatewayBean(UsuarioJpaRepository repository) {

        return new UsuarioDomainGatewayImpl(repository);
    }
}
