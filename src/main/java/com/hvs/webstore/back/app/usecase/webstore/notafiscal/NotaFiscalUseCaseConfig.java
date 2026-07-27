package com.hvs.webstore.back.app.usecase.webstore.notafiscal;

import com.hvs.webstore.back.domain.entity.webstore.notafiscal.NotaFiscalDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotaFiscalUseCaseConfig {

    @Bean
    public CreateNotaFiscalUseCase createNotaFiscalUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new CreateNotaFiscalUseCaseImpl(gateway);
    }
    @Bean
    public ReadNotaFiscalUseCase readNotaFiscalUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new ReadNotaFiscalUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllNotaFiscalUseCase readAllNotaFiscalUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new ReadAllNotaFiscalUseCaseImpl(gateway);
    }
    @Bean
    public ReadNotaFiscalByChaveAcessoUseCase readNotaFiscalByChaveAcessoUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new ReadNotaFiscalByChaveAcessoUseCaseImpl(gateway);
    }
    @Bean
    public ReadNotaFiscalByPedidoIdUseCase readNotaFiscalByPedidoIdUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new ReadNotaFiscalByPedidoIdUseCaseImpl(gateway);
    }
    @Bean
    public UpdateNotaFiscalUseCase updateNotaFiscalUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new UpdateNotaFiscalUseCaseImpl(gateway);
    }
    @Bean
    public PatchNotaFiscalUseCase patchNotaFiscalUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new PatchNotaFiscalUseCaseImpl(gateway);
    }
    @Bean
    public DeleteNotaFiscalUseCase deleteNotaFiscalUseCaseBean(NotaFiscalDomainGateway gateway) {

        return new DeleteNotaFiscalUseCaseImpl(gateway);
    }
}
