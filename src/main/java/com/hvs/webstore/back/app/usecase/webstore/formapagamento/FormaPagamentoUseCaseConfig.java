package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormaPagamentoUseCaseConfig {

    @Bean
    public CreateFormaPagamentoUseCase createFormaPagamentoUseCaseBean(FormaPagamentoDomainGateway gateway, CartaoDomainGateway ca, PixDomainGateway pi, BoletoDomainGateway b) {

        return new CreateFormaPagamentoUseCaseImpl(gateway, ca, pi, b);
    }
    @Bean
    public ReadFormaPagamentoUseCase readFormaPagamentoUseCaseBean(FormaPagamentoDomainGateway gateway) {

        return new ReadFormaPagamentoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllFormaPagamentoUseCase readAllFormaPagamentoUseCaseBean(FormaPagamentoDomainGateway gateway) {

        return new ReadAllFormaPagamentoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateFormaPagamentoUseCase updateFormaPagamentoUseCaseBean(FormaPagamentoDomainGateway gateway, CartaoDomainGateway ca, PixDomainGateway pi, BoletoDomainGateway b) {

        return new UpdateFormaPagamentoUseCaseImpl(gateway, ca, pi, b);
    }
    @Bean
    public PatchFormaPagamentoUseCase patchFormaPagamentoUseCaseBean(FormaPagamentoDomainGateway gateway, CartaoDomainGateway ca, PixDomainGateway pi, BoletoDomainGateway b) {

        return new PatchFormaPagamentoUseCaseImpl(gateway, ca, pi, b);
    }
    @Bean
    public DeleteFormaPagamentoUseCase deleteFormaPagamentoUseCaseBean(FormaPagamentoDomainGateway gateway) {

        return new DeleteFormaPagamentoUseCaseImpl(gateway);
    }
}
