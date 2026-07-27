package com.hvs.webstore.back.infra.persistence.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.imagem.ImagemDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.imagem.ImagemJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImagemGatewayConfig {

    @Bean
    public ImagemDomainGateway imagemDomainGatewayBean(ImagemJpaRepository repository) {

        return new ImagemDomainGatewayImpl(repository);
    }
}
