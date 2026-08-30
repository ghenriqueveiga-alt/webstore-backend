package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.service.EndingDetector;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndingDetectadoUseCaseConfig {

    @Bean
    public ProcessDetectEndingUseCase processDetectEndingUseCaseBean(
            EpisodioDomainGateway episodioGateway,
            EndingDetectadoDomainGateway endingDetectadoGateway,
            EndingDetector endingDetector,
            MediaPathResolver mediaPathResolver) {

        return new ProcessDetectEndingUseCaseImpl(episodioGateway, endingDetectadoGateway, endingDetector, mediaPathResolver);
    }

    @Bean
    public ProcessDetectAllEndingUseCase processDetectAllEndingUseCaseBean(
            EpisodioDomainGateway episodioGateway,
            EndingDetectadoDomainGateway endingDetectadoGateway,
            EndingDetector endingDetector,
            MediaPathResolver mediaPathResolver) {

        return new ProcessDetectAllEndingUseCaseImpl(episodioGateway, endingDetectadoGateway, endingDetector, mediaPathResolver);
    }

    @Bean
    public ProcessDetectNaoDetectadosEndingUseCase processDetectNaoDetectadosEndingUseCaseBean(
            EpisodioDomainGateway episodioGateway,
            EndingDetectadoDomainGateway endingDetectadoGateway,
            EndingDetector endingDetector,
            MediaPathResolver mediaPathResolver) {

        return new ProcessDetectNaoDetectadosEndingUseCaseImpl(episodioGateway, endingDetectadoGateway, endingDetector, mediaPathResolver);
    }

    @Bean
    public ProcessDetectProgramasEndingUseCase processDetectProgramasEndingUseCaseBean(
            ProgramaDomainGateway programaGateway,
            EpisodioDomainGateway episodioGateway,
            EndingDetectadoDomainGateway endingDetectadoGateway,
            ProcessDetectAllEndingUseCase processDetectAllEndingUseCase) {

        return new ProcessDetectProgramasEndingUseCaseImpl(
                programaGateway, episodioGateway, endingDetectadoGateway, processDetectAllEndingUseCase);
    }
}
