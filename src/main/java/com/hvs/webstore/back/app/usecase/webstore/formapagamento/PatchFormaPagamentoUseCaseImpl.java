package com.hvs.webstore.back.app.usecase.webstore.formapagamento;

import com.hvs.webstore.back.app.command.webstore.formapagamento.PatchFormaPagamentoCommand;
import com.hvs.webstore.back.app.output.webstore.formapagamento.PatchFormaPagamentoOutput;
import com.hvs.webstore.back.domain.entity.webstore.boleto.Boleto;
import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.Cartao;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.cartao.CartaoId;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamento;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoId;
import com.hvs.webstore.back.domain.entity.webstore.formapagamento.FormaPagamentoUuid;
import com.hvs.webstore.back.domain.entity.webstore.pix.Pix;
import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class PatchFormaPagamentoUseCaseImpl extends PatchFormaPagamentoUseCase {

    private final FormaPagamentoDomainGateway gateway;
    private final CartaoDomainGateway cartaoGateway;
    private final PixDomainGateway pixGateway;
    private final BoletoDomainGateway boletoGateway;

    public PatchFormaPagamentoUseCaseImpl(
            FormaPagamentoDomainGateway gateway,
            CartaoDomainGateway cartaoGateway,
            PixDomainGateway pixGateway,
            BoletoDomainGateway boletoGateway) {

        this.gateway = gateway;
        this.cartaoGateway = cartaoGateway;
        this.pixGateway = pixGateway;
        this.boletoGateway = boletoGateway;
    }

    @Override
    public Either<Notification, PatchFormaPagamentoOutput> execute(PatchFormaPagamentoCommand aFormaPagamentoCommand) {

        Optional<FormaPagamento> aFormaPagamentoDB = aFormaPagamentoCommand.aId() != null ?
                gateway.read(FormaPagamentoId.from(aFormaPagamentoCommand.aId())) : gateway.readByUuid(FormaPagamentoUuid.from(aFormaPagamentoCommand.aUuid()));

        if (aFormaPagamentoDB.isEmpty())
            return Either.left(Notification.create(new Error("FormaPagamento not found: " + (aFormaPagamentoCommand.aId() != null ?
                    aFormaPagamentoCommand.aId() : aFormaPagamentoCommand.aUuid()))));

        var notification = Notification.create();
        Optional<Cartao> cartaoDb;

        if (aFormaPagamentoCommand.aCartaoId() != null) {
            cartaoDb = cartaoGateway.read(CartaoId.from(aFormaPagamentoCommand.aCartaoId()));
            if (cartaoDb.isEmpty()) {
                return Left(Notification.create(
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

        var formaPagamento = FormaPagamento.patch(aFormaPagamentoCommand.aStatusCode(),
                                                  aFormaPagamentoCommand.aTipo(),
                                                  aFormaPagamentoCommand.aCartaoId(), pixId, boletoId,
                                                  aFormaPagamentoCommand.aPrincipal(),
                                                  aFormaPagamentoDB.get());
        formaPagamento.validate(notification);

        return notification.hasError() ? Left(notification) : patch(formaPagamento);
    }

    @Transactional
    private Either<Notification, PatchFormaPagamentoOutput> patch(FormaPagamento aFormaPagamento) {

        return Try(() -> gateway.patch(aFormaPagamento))
                .toEither().bimap(Notification::create, PatchFormaPagamentoOutput::from);
    }
}
