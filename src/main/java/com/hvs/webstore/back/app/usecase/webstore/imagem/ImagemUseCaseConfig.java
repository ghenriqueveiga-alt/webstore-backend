package com.hvs.webstore.back.app.usecase.webstore.imagem;

import com.hvs.webstore.back.domain.entity.webstore.imagem.ImagemDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImagemUseCaseConfig {

    @Bean
    public CreateImagemUseCase createImagemUseCaseBean(ImagemDomainGateway gateway) {

        return new CreateImagemUseCaseImpl(gateway);
    }
    @Bean
    public ReadImagemUseCase readImagemUseCaseBean(ImagemDomainGateway gateway) {

        return new ReadImagemUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllImagemUseCase readAllImagemUseCaseBean(ImagemDomainGateway gateway) {

        return new ReadAllImagemUseCaseImpl(gateway);
    }
    @Bean
    public UpdateImagemUseCase updateImagemUseCaseBean(ImagemDomainGateway gateway) {

        return new UpdateImagemUseCaseImpl(gateway);
    }
    @Bean
    public PatchImagemUseCase patchImagemUseCaseBean(ImagemDomainGateway gateway) {

        return new PatchImagemUseCaseImpl(gateway);
    }
    @Bean
    public DeleteImagemUseCase deleteImagemUseCaseBean(ImagemDomainGateway gateway) {

        return new DeleteImagemUseCaseImpl(gateway);
    }
}
