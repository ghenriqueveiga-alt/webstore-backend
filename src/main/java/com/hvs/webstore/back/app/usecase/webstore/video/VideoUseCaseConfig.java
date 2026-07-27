package com.hvs.webstore.back.app.usecase.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.VideoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VideoUseCaseConfig {

    @Bean
    public CreateVideoUseCase createVideoUseCaseBean(VideoDomainGateway gateway) {

        return new CreateVideoUseCaseImpl(gateway);
    }
    @Bean
    public ReadVideoUseCase readVideoUseCaseBean(VideoDomainGateway gateway) {

        return new ReadVideoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllVideoUseCase readAllVideoUseCaseBean(VideoDomainGateway gateway) {

        return new ReadAllVideoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateVideoUseCase updateVideoUseCaseBean(VideoDomainGateway gateway) {

        return new UpdateVideoUseCaseImpl(gateway);
    }
    @Bean
    public PatchVideoUseCase patchVideoUseCaseBean(VideoDomainGateway gateway) {

        return new PatchVideoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteVideoUseCase deleteVideoUseCaseBean(VideoDomainGateway gateway) {

        return new DeleteVideoUseCaseImpl(gateway);
    }
}
