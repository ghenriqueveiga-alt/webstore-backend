package com.hvs.webstore.back.domain.entity.webstore.avaliacao;

import com.hvs.webstore.back.domain.Entity;
import com.hvs.webstore.back.domain.entity.webstore.produto.Produto;
import com.hvs.webstore.back.domain.entity.webstore.usuario.Usuario;
import com.hvs.webstore.back.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Avaliacao extends Entity<AvaliacaoId> {

    private final AvaliacaoUuid uuid;
    private final AvaliacaoStatus statusCode;
    private final Produto produto;
    private final Usuario usuario;
    private final Integer nota;
    private final String titulo;
    private final String comentario;
    private final Boolean verificada;
    private final Instant criadoEm;
    private final Instant atualizadoEm;

    private Avaliacao(final AvaliacaoId id,
                      final AvaliacaoUuid uuid,
                      final AvaliacaoStatus statusCode,
                      final Produto produto,
                      final Usuario usuario,
                      final Integer nota,
                      final String titulo,
                      final String comentario,
                      final Boolean verificada,
                      final Instant criadoEm,
                      final Instant atualizadoEm) {

        super(id);
        this.uuid = uuid;
        this.statusCode = statusCode;
        this.produto = produto;
        this.usuario = usuario;
        this.nota = nota;
        this.titulo = titulo;
        this.comentario = comentario;
        this.verificada = verificada;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public static Avaliacao create(final Long aProdutoId,
                                    final Long aUsuarioId,
                                    final Integer aNota,
                                    final String aTitulo,
                                    final String aComentario) {

        return new Avaliacao(
                AvaliacaoId.from(-1L),
                AvaliacaoUuid.unique(),
                AvaliacaoStatus.ACTIVE,
                aProdutoId != null ? Produto.from(aProdutoId) : null,
                aUsuarioId != null ? Usuario.from(aUsuarioId) : null,
                aNota,
                aTitulo,
                aComentario,
                null,
                Instant.now(),
                null);
    }

    public static Avaliacao from(final Long aId,
                                  final String aUuid,
                                  final String aStatusCode,
                                  final Produto aProduto,
                                  final Usuario aUsuario,
                                  final Integer aNota,
                                  final String aTitulo,
                                  final String aComentario,
                                  final Boolean aVerificada,
                                  final Instant aCriadoEm,
                                  final Instant aAtualizadoEm) {

        return new Avaliacao(
                aId != null ? AvaliacaoId.from(aId) : null,
                aUuid != null ? AvaliacaoUuid.from(aUuid) : null,
                aStatusCode != null ? AvaliacaoStatus.findByCode(aStatusCode) : null,
                aProduto,
                aUsuario,
                aNota,
                aTitulo,
                aComentario,
                aVerificada,
                aCriadoEm,
                aAtualizadoEm);
    }

    public static Avaliacao from(final Long aId) {

        return new Avaliacao(
                aId != null ? AvaliacaoId.from(aId) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static Avaliacao from(final String aUuid) {

        return new Avaliacao(
                null,
                aUuid != null ? AvaliacaoUuid.from(aUuid) : null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    @Override
    public void validate(ValidationHandler aHandler) {

        new AvaliacaoValidator(aHandler, this).validate();
    }

    public AvaliacaoUuid getUuid() {
        return uuid;
    }
    public AvaliacaoStatus getStatusCode() {
        return statusCode;
    }
    public Produto getProduto() {
        return produto;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public Integer getNota() {
        return nota;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getComentario() {
        return comentario;
    }
    public Boolean getVerificada() {
        return verificada;
    }
    public Instant getCriadoEm() {
        return criadoEm;
    }
    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass())
            return false;

        if (!super.equals(o))
            return false;

        Avaliacao avaliacao = (Avaliacao) o;

        return Objects.equals(uuid, avaliacao.uuid) &&
                statusCode == avaliacao.statusCode &&
                Objects.equals(produto, avaliacao.produto) &&
                Objects.equals(usuario, avaliacao.usuario) &&
                Objects.equals(nota, avaliacao.nota) &&
                Objects.equals(titulo, avaliacao.titulo) &&
                Objects.equals(comentario, avaliacao.comentario) &&
                Objects.equals(verificada, avaliacao.verificada) &&
                Objects.equals(criadoEm, avaliacao.criadoEm) &&
                Objects.equals(atualizadoEm, avaliacao.atualizadoEm);
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                super.hashCode(),
                uuid,
                statusCode,
                produto,
                usuario,
                nota,
                titulo,
                comentario,
                verificada,
                criadoEm,
                atualizadoEm);
    }
}
