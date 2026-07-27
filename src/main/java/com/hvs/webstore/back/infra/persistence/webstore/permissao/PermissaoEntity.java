package com.hvs.webstore.back.infra.persistence.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "permissao")
public class PermissaoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String chave;
    private String descricao;

    public PermissaoEntity() {

    }

    public PermissaoEntity(final Long id,
                           final String uuid,
                           final String statusDesc,
                           final String nome,
                           final String chave,
                           final String descricao) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.chave = chave;
        this.descricao = descricao;
    }

    public static PermissaoEntity from(Permissao aPermissao) {

        return new PermissaoEntity(
                aPermissao.getId().getValue() < 0 ? null : aPermissao.getId().getValue(),
                aPermissao.getUuid().getValue(),
                aPermissao.getStatusCode().getDesc(),
                aPermissao.getNome(),
                aPermissao.getChave(),
                aPermissao.getDescricao()
        );
    }

    public static PermissaoEntity from(final Long aPermissaoId) {

        final var permissao = new PermissaoEntity();
        permissao.setId(aPermissaoId);

        return permissao;
    }

    public Permissao toDomain() {

        return Permissao.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                chave,
                descricao
        );
    }

    public Permissao toDomainChildren() {

        return Permissao.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                chave,
                descricao
        );
    }

    public Permissao toDomainSimple() {

        return Permissao.from(
                getId(),
                uuid,
                null,
                nome,
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
