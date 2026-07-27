package com.hvs.webstore.back.infra.persistence.webstore.role;

import com.hvs.webstore.back.infra.persistence.BasicEntity;
import com.hvs.webstore.back.infra.persistence.webstore.permissao.PermissaoEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "role_permissao")
public class RolePermissaoEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "permissao_id")
    private PermissaoEntity permissao;

    public RolePermissaoEntity() {

    }

    public RolePermissaoEntity(final Long id,
                               final RoleEntity role,
                               final PermissaoEntity permissao) {

        this.id = id;
        this.role = role;
        this.permissao = permissao;
    }

    public RoleEntity getRole() {
        return role;
    }
    public void setRole(final RoleEntity role) {
        this.role = role;
    }
    public PermissaoEntity getPermissao() {
        return permissao;
    }

    @Override
    public Long getId() {
        return id;
    }
    public void setId(final Long id) {
        this.id = id;
    }
}
