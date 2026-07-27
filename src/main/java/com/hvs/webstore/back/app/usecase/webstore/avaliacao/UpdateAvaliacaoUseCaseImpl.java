package com.hvs.webstore.back.app.usecase.webstore.avaliacao;

import com.hvs.webstore.back.app.command.webstore.avaliacao.UpdateAvaliacaoCommand;
import com.hvs.webstore.back.app.output.webstore.avaliacao.UpdateAvaliacaoOutput;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoId;
import com.hvs.webstore.back.domain.entity.webstore.avaliacao.AvaliacaoUuid;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.produto.ProdutoId;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioId;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import jakarta.transaction.Transactional;
import java.util.Optional;
import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class UpdateAvaliacaoUseCaseImpl extends UpdateAvaliacaoUseCase {

    private final AvaliacaoDomainGateway gateway;
    private final ProdutoDomainGateway ProdutoDomainGateway;
    private final UsuarioDomainGateway UsuarioDomainGateway;

    public UpdateAvaliacaoUseCaseImpl(AvaliacaoDomainGateway gateway,
                                      ProdutoDomainGateway ProdutoDomainGateway,
                                      UsuarioDomainGateway UsuarioDomainGateway) {

        this.gateway = gateway;
        this.ProdutoDomainGateway = ProdutoDomainGateway;
        this.UsuarioDomainGateway = UsuarioDomainGateway;
    }

    @Override
    public Either<Notification, UpdateAvaliacaoOutput> execute(UpdateAvaliacaoCommand aAvaliacaoCommand) {

        Optional<Avaliacao> aAvaliacaoDB = aAvaliacaoCommand.aId() != null ?
                gateway.read(AvaliacaoId.from(aAvaliacaoCommand.aId())) : gateway.readByUuid(AvaliacaoUuid.from(aAvaliacaoCommand.aUuid()));

        if (aAvaliacaoDB.isEmpty())
            return Either.left(Notification.create(new Error("Avaliacao not found: " + (aAvaliacaoCommand.aId() != null ?
                    aAvaliacaoCommand.aId() : aAvaliacaoCommand.aUuid()))));

        var existing = aAvaliacaoDB.get();
        var produto = aAvaliacaoCommand.aProdutoId() != null ?
                ProdutoDomainGateway.read(ProdutoId.from(aAvaliacaoCommand.aProdutoId())).orElse(existing.getProduto())
                : existing.getProduto();
        var usuario = aAvaliacaoCommand.aUsuarioId() != null ?
                UsuarioDomainGateway.read(UsuarioId.from(aAvaliacaoCommand.aUsuarioId())).orElse(existing.getUsuario())
                : existing.getUsuario();
        var notification = Notification.create();
        var avaliacao = Avaliacao.from(existing.getId().getValue(),
                                       existing.getUuid().getValue(),
                                       aAvaliacaoCommand.aStatusCode() != null ? aAvaliacaoCommand.aStatusCode() : existing.getStatusCode().getCode(),
                                       produto,
                                       usuario,
                                       aAvaliacaoCommand.aNota() != null ? aAvaliacaoCommand.aNota() : existing.getNota(),
                                       aAvaliacaoCommand.aTitulo() != null ? aAvaliacaoCommand.aTitulo() : existing.getTitulo(),
                                       aAvaliacaoCommand.aComentario() != null ? aAvaliacaoCommand.aComentario() : existing.getComentario(),
                                       existing.getVerificada(),
                                       existing.getCriadoEm(),
                                       java.time.Instant.now());
        avaliacao.validate(notification);

        return notification.hasError() ? Left(notification) : update(avaliacao);
    }

    @Transactional
    private Either<Notification, UpdateAvaliacaoOutput> update(Avaliacao aAvaliacao) {

        return Try(() -> gateway.update(aAvaliacao))
                .toEither().bimap(Notification::create, UpdateAvaliacaoOutput::from);
    }
}
