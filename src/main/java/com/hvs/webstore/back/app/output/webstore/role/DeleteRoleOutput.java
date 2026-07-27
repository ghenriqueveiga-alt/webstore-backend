package com.hvs.webstore.back.app.output.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;

public record DeleteRoleOutput(Long aId,
                               String aUuid,
                               String aMessage) {

    public static DeleteRoleOutput from(Role aRole) {

        return new DeleteRoleOutput(
                aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                "Role deleted: " + aRole.getUuid().getValue());
    }
}
