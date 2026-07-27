package com.hvs.webstore.back.app.command.webstore.role;

public record ReadRoleCommand(Long aId,
                              String aUuid) {

    public static ReadRoleCommand from(final Long aId) {

        return new ReadRoleCommand(
                aId,
                null
        );
    }

    public static ReadRoleCommand from(final String aUuid) {

        return new ReadRoleCommand(
                null,
                aUuid
        );
    }
}
