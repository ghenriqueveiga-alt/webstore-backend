package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvaliacaoUseCaseConfig {

    @Bean
    public CreateAvaliacaoUseCase createAvaliacaoUseCaseBean(AvaliacaoDomainGateway gateway) {

        return new CreateAvaliacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAvaliacaoUseCase readAvaliacaoUseCaseBean(AvaliacaoDomainGateway gateway) {

        return new ReadAvaliacaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllAvaliacaoUseCase readAllAvaliacaoUseCaseBean(AvaliacaoDomainGateway gateway) {

        return new ReadAllAvaliacaoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateAvaliacaoUseCase updateAvaliacaoUseCaseBean(AvaliacaoDomainGateway gateway, ProdutoDomainGateway p, UsuarioDomainGateway u) {

        return new UpdateAvaliacaoUseCaseImpl(gateway, p, u);
    }
    @Bean
    public PatchAvaliacaoUseCase patchAvaliacaoUseCaseBean(AvaliacaoDomainGateway gateway, ProdutoDomainGateway p, UsuarioDomainGateway u) {

        return new PatchAvaliacaoUseCaseImpl(gateway, p, u);
    }
    @Bean
    public DeleteAvaliacaoUseCase deleteAvaliacaoUseCaseBean(AvaliacaoDomainGateway gateway) {

        return new DeleteAvaliacaoUseCaseImpl(gateway);
    }
}
