package com.hvs.webstore.back.infra.persistence.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.permissao.Permissao;
import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.permissao.PermissaoEntity;
import jakarta.persistence.*;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String statusDesc;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RolePermissaoEntity> permissoes;

    public RoleEntity() {

    }

    public RoleEntity(final Long id,
                      final String uuid,
                      final String statusDesc,
                      final String nome,
                      final String descricao,
                      final List<RolePermissaoEntity> permissoes) {

        this.id = id;
        this.uuid = uuid;
        this.statusDesc = statusDesc;
        this.nome = nome;
        this.descricao = descricao;
        this.permissoes = permissoes;
    }

    public static RoleEntity from(Role aRole) {

        List<RolePermissaoEntity> permissaoEntities = null;
        if (aRole.getPermissoes() != null) {
            permissaoEntities = aRole.getPermissoes().stream()
                    .map(p -> new RolePermissaoEntity(null, null, PermissaoEntity.from(p.getId().getValue())))
                    .toList();
        }

        final var entity = new RoleEntity(
                aRole.getId().getValue() < 0 ? null : aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                aRole.getStatusCode().getDesc(),
                aRole.getNome(),
                aRole.getDescricao(),
                permissaoEntities
        );

        if (permissaoEntities != null) {
            permissaoEntities.forEach(rp -> rp.setRole(entity));
        }

        return entity;
    }

    public static RoleEntity from(final Long aRoleId) {

        final var role = new RoleEntity();
        role.setId(aRoleId);

        return role;
    }

    public Role toDomain() {

        final var permissoes = this.permissoes != null ?
                this.permissoes.stream().map(rp -> rp.getPermissao().toDomainChildren()).toList() :
                Collections.<Permissao>emptyList();

        return Role.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                permissoes
        );
    }

    public Role toDomainChildren() {

        final var permissoes = this.permissoes != null ?
                this.permissoes.stream().map(rp -> rp.getPermissao().toDomainSimple()).toList() :
                Collections.<Permissao>emptyList();

        return Role.from(
                getId(),
                uuid,
                statusDesc,
                nome,
                descricao,
                permissoes
        );
    }

    public Role toDomainSimple() {

        return Role.from(
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
