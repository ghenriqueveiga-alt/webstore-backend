package com.hvs.webstore.back.app.usecase.webstore.listadesejos;

import com.hvs.webstore.back.domain.entity.webstore.listadesejos.ListaDesejosDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaDesejosUseCaseConfig {

    @Bean
    public CriarListaDesejosUseCase criarListaDesejosUseCaseBean(ListaDesejosDomainGateway gateway) {

        return new CriarListaDesejosUseCaseImpl(gateway);
    }
    @Bean
    public VisualizarListaDesejosUseCase visualizarListaDesejosUseCaseBean(ListaDesejosDomainGateway gateway) {

        return new VisualizarListaDesejosUseCaseImpl(gateway);
    }
    @Bean
    public ListarListasDesejosUseCase listarListasDesejosUseCaseBean(ListaDesejosDomainGateway gateway) {

        return new ListarListasDesejosUseCaseImpl(gateway);
    }
    @Bean
    public AdicionarItemListaUseCase adicionarItemListaUseCaseBean(ListaDesejosDomainGateway gateway) {

        return new AdicionarItemListaUseCaseImpl(gateway);
    }
    @Bean
    public RemoverItemListaUseCase removerItemListaUseCaseBean(ListaDesejosDomainGateway gateway) {

        return new RemoverItemListaUseCaseImpl(gateway);
    }
    @Bean
    public DeletarListaDesejosUseCase deletarListaDesejosUseCaseBean(ListaDesejosDomainGateway gateway) {

        return new DeletarListaDesejosUseCaseImpl(gateway);
    }
}
