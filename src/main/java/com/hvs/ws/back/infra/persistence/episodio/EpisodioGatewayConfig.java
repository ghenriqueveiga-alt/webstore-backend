package com.hvs.ws.back.infra.persistence.episodio;

import com.hvs.ws.back.domain.entity.episodio.EpisodioDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EpisodioGatewayConfig {

    @Bean
    public EpisodioDomainGateway episodioDomainGatewayBean(EpisodioJpaRepository repository) {

        return new EpisodioDomainGatewayImpl(repository);
    }
}