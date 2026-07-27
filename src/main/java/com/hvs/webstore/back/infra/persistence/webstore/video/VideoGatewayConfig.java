package com.hvs.webstore.back.infra.persistence.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.video.VideoDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.video.VideoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoGatewayConfig {

    @Bean
    public VideoDomainGateway videoDomainGatewayBean(VideoJpaRepository repository) {

        return new VideoDomainGatewayImpl(repository);
    }
}
