package com.hvs.webstore.back.infra.persistence.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLoja;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "meta_loja")
public class MetaLojaEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String chave;
    private String valor;
    private String descricao;
    private String tipoDesc;

    public MetaLojaEntity() {

    }

    public MetaLojaEntity(final Long id,
                          final String uuid,
                          final String statusDesc,
                          final String chave,
                          final String valor,
                          final String descricao,
                          final String tipoDesc) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.chave = chave;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoDesc = tipoDesc;
    }

    public static MetaLojaEntity from(MetaLoja aMetaLoja) {

        return new MetaLojaEntity(
                aMetaLoja.getId().getValue() < 0 ? null : aMetaLoja.getId().getValue(),
                aMetaLoja.getUuid().getValue(),
                aMetaLoja.getStatusCode().getDesc(),
                aMetaLoja.getChave(),
                aMetaLoja.getValor(),
                aMetaLoja.getDescricao(),
                aMetaLoja.getTipoMetaLoja() != null ? aMetaLoja.getTipoMetaLoja().getDesc() : null
        );
    }

    public static MetaLojaEntity from(final Long aMetaLojaId) {

        final var metaLoja = new MetaLojaEntity();
        metaLoja.setId(aMetaLojaId);

        return metaLoja;
    }

    public MetaLoja toDomain() {

        return MetaLoja.from(
                getId(),
                uuid,
                statusDesc,
                chave,
                valor,
                descricao,
                tipoDesc
        );
    }

    public MetaLoja toDomainChildren() {

        return MetaLoja.from(
                getId(),
                uuid,
                statusDesc,
                chave,
                valor,
                descricao,
                tipoDesc
        );
    }

    public MetaLoja toDomainSimple() {

        return MetaLoja.from(
                getId(),
                uuid,
                null,
                chave,
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
