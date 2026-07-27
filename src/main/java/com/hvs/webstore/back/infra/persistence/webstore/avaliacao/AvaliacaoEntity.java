package com.hvs.webstore.back.infra.persistence.webstore.avaliacao;

import com.hvs.webstore.back.domain.entity.webstore.avaliacao.Avaliacao;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.produto.ProdutoEntity;
import com.hvs.webstore.back.infra.persistence.webstore.usuario.UsuarioEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "avaliacao")
public class AvaliacaoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoEntity produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    private Integer nota;
    private String titulo;
    private String comentario;
    private Boolean verificada;
    private Instant criadoEm;
    private Instant atualizadoEm;

    public AvaliacaoEntity() {

    }

    public AvaliacaoEntity(final Long id,
                            final String uuid,
                            final String statusDesc,
                            final ProdutoEntity produto,
                            final UsuarioEntity usuario,
                            final Integer nota,
                            final String titulo,
                            final String comentario,
                            final Boolean verificada,
                            final Instant criadoEm,
                            final Instant atualizadoEm) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.produto = produto;
        this.usuario = usuario;
        this.nota = nota;
        this.titulo = titulo;
        this.comentario = comentario;
        this.verificada = verificada;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public static AvaliacaoEntity from(final Avaliacao aAvaliacao) {

        return new AvaliacaoEntity(
                aAvaliacao.getId().getValue() < 0 ? null : aAvaliacao.getId().getValue(),
                aAvaliacao.getUuid().getValue(),
                aAvaliacao.getStatusCode().getDesc(),
                aAvaliacao.getProduto() != null ? ProdutoEntity.from(aAvaliacao.getProduto().getId().getValue()) : null,
                aAvaliacao.getUsuario() != null ? UsuarioEntity.from(aAvaliacao.getUsuario().getId().getValue()) : null,
                aAvaliacao.getNota(),
                aAvaliacao.getTitulo(),
                aAvaliacao.getComentario(),
                aAvaliacao.getVerificada(),
                aAvaliacao.getCriadoEm(),
                aAvaliacao.getAtualizadoEm()
        );
    }

    public static AvaliacaoEntity from(final Long aAvaliacaoId) {

        final var avaliacao = new AvaliacaoEntity();
        avaliacao.setId(aAvaliacaoId);

        return avaliacao;
    }

    public Avaliacao toDomain() {

        return Avaliacao.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainChildren() : null,
                usuario != null ? usuario.toDomainChildren() : null,
                nota,
                titulo,
                comentario,
                verificada,
                criadoEm,
                atualizadoEm
        );
    }

    public Avaliacao toDomainChildren() {

        return Avaliacao.from(
                getId(),
                uuid,
                statusDesc,
                produto != null ? produto.toDomainSimple() : null,
                usuario != null ? usuario.toDomainSimple() : null,
                nota,
                titulo,
                comentario,
                verificada,
                criadoEm,
                atualizadoEm
        );
    }

    public Avaliacao toDomainSimple() {

        return Avaliacao.from(
                getId(),
                uuid,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
    public void setStatusDesc(final String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
