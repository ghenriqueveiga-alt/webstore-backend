package com.hvs.webstore.back.infra.persistence.television.episodio;

import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EpisodioGatewayConfig {

    @Bean
    public EpisodioDomainGateway episodioDomainGatewayBean(EpisodioJpaRepository repository) {

        return new EpisodioDomainGatewayImpl(repository);
    }
}