package com.hvs.webstore.back.app.usecase.television.episodio;

import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EpisodioUseCaseConfig {

    @Bean
    public CreateEpisodioUseCase createEpisodioUseCaseBean(EpisodioDomainGateway gateway) {

        return new CreateEpisodioUseCaseImpl(gateway);
    }
    @Bean
    public ReadEpisodioUseCase readEpisodioUseCaseBean(EpisodioDomainGateway gateway) {

        return new ReadEpisodioUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllEpisodioUseCase readAllEpisodioUseCaseBean(EpisodioDomainGateway gateway) {

        return new ReadAllEpisodioUseCaseImpl(gateway);
    }
    @Bean
    public UpdateEpisodioUseCase updateEpisodioUseCaseBean(EpisodioDomainGateway gateway) {

        return new UpdateEpisodioUseCaseImpl(gateway);
    }
    @Bean
    public PatchEpisodioUseCase patchEpisodioUseCaseBean(EpisodioDomainGateway gateway) {

        return new PatchEpisodioUseCaseImpl(gateway);
    }
    @Bean
    public DeleteEpisodioUseCase deleteEpisodioUseCaseBean(EpisodioDomainGateway gateway) {

        return new DeleteEpisodioUseCaseImpl(gateway);
    }
}
