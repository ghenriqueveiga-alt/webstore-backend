package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.CreateFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.CreateFormaPagamentoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoId;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.API;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Try;

public class CreateFormaPagamentoUseCaseImpl extends CreateFormaPagamentoUseCase {

    private final FormaPagamentoDomainGateway gateway;
    private final CartaoDomainGateway cartaoGateway;
    private final PixDomainGateway pixGateway;
    private final BoletoDomainGateway boletoGateway;

    public CreateFormaPagamentoUseCaseImpl(FormaPagamentoDomainGateway gateway,
                                           CartaoDomainGateway cartaoGateway,
                                           PixDomainGateway pixGateway,
                                           BoletoDomainGateway boletoGateway) {

        this.gateway = gateway;
        this.cartaoGateway = cartaoGateway;
        this.pixGateway = pixGateway;
        this.boletoGateway = boletoGateway;
    }

    @Override
    public Either<Notification, CreateFormaPagamentoOutput> execute(CreateFormaPagamentoCommand aFormaPagamentoCommand) {

        var notification = Notification.create();
        Optional<Cartao> cartaoDb;

        if (aFormaPagamentoCommand.aCartaoId() != null) {
            cartaoDb = cartaoGateway.read(CartaoId.from(aFormaPagamentoCommand.aCartaoId()));
            if (cartaoDb.isEmpty()) {
                return API.Left(Notification.create(
                        new Error("The Cartao with id: " + aFormaPagamentoCommand.aCartaoId() + " could not be found.")));
            }
        }

        Long pixId;
        if (aFormaPagamentoCommand.aChavePix() != null) {
            var pix = pixGateway.create(Pix.create(aFormaPagamentoCommand.aChavePix(),
                                                   aFormaPagamentoCommand.aTipoChavePix()));
            pixId = pix.getId().getValue();
        } else {
            pixId = null;
        }

        Long boletoId;
        if (aFormaPagamentoCommand.aCodigoBarras() != null) {
            var boleto = boletoGateway.create(Boleto.create(aFormaPagamentoCommand.aCodigoBarras(),
                                                            aFormaPagamentoCommand.aVencimento() != null ? aFormaPagamentoCommand.aVencimento().toString() : null));
            boletoId = boleto.getId().getValue();
        } else {
            boletoId = null;
        }

        var formaPagamento = FormaPagamento.create(aFormaPagamentoCommand.aUsuarioId(),
                                                   aFormaPagamentoCommand.aTipo(),
                                                   aFormaPagamentoCommand.aCartaoId(), pixId, boletoId,
                                                   aFormaPagamentoCommand.aPrincipal());
        formaPagamento.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(formaPagamento);
    }

    @Transactional
    private Either<Notification, CreateFormaPagamentoOutput> create(FormaPagamento aFormaPagamento) {

        return Try(() -> gateway.create(aFormaPagamento))
                .toEither().bimap(Notification::create, CreateFormaPagamentoOutput::from);
    }
}
