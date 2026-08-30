package com.hvs.webstore.back.app.usecase.television.introdetectado;

import com.hvs.webstore.back.app.service.IntroDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntroDetectadoUseCaseConfig {

    @Bean
    public ProcessDetectIntroUseCase processDetectIntroUseCaseBean(
            EpisodioDomainGateway episodioGateway,
            IntroDetectadoDomainGateway introDetectadoGateway,
            IntroDetector introDetector,
            MediaPathResolver mediaPathResolver) {

        return new ProcessDetectIntroUseCaseImpl(episodioGateway, introDetectadoGateway, introDetector, mediaPathResolver);
    }

    @Bean
    public ProcessDetectAllIntroUseCase processDetectAllIntroUseCaseBean(
            EpisodioDomainGateway episodioGateway,
            IntroDetectadoDomainGateway introDetectadoGateway,
            IntroDetector introDetector,
            MediaPathResolver mediaPathResolver) {

        return new ProcessDetectAllIntroUseCaseImpl(episodioGateway, introDetectadoGateway, introDetector, mediaPathResolver);
    }

    @Bean
    public ProcessDetectNaoDetectadosIntroUseCase processDetectNaoDetectadosIntroUseCaseBean(
            EpisodioDomainGateway episodioGateway,
            IntroDetectadoDomainGateway introDetectadoGateway,
            IntroDetector introDetector,
            MediaPathResolver mediaPathResolver) {

        return new ProcessDetectNaoDetectadosIntroUseCaseImpl(episodioGateway, introDetectadoGateway, introDetector, mediaPathResolver);
    }

    @Bean
    public ProcessDetectProgramasIntroUseCase processDetectProgramasIntroUseCaseBean(
            ProgramaDomainGateway programaGateway,
            EpisodioDomainGateway episodioGateway,
            IntroDetectadoDomainGateway introDetectadoGateway,
            ProcessDetectAllIntroUseCase processDetectAllIntroUseCase) {

        return new ProcessDetectProgramasIntroUseCaseImpl(
                programaGateway, episodioGateway, introDetectadoGateway, processDetectAllIntroUseCase);
    }
}