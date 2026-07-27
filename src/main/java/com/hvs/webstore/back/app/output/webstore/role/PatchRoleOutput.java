package com.hvs.webstore.back.app.output.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;

public record PatchRoleOutput(Long aId,
                              String aUuid,
                              String aMessage) {

    public static PatchRoleOutput from(Role aRole) {

        return new PatchRoleOutput(
                aRole.getId().getValue(),
                aRole.getUuid().getValue(),
                "Role patched: " + aRole.getUuid().getValue());
    }
}
