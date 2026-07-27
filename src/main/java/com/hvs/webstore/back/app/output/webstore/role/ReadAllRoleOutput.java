package com.hvs.webstore.back.app.output.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.Role;
import com.hvs.webstore.back.domain.pagination.Pagination;
import java.util.ArrayList;

public record ReadAllRoleOutput(int aCurrentPage,
                                long aPerPage,
                                long aTotal,
                                java.util.List<ReadRoleOutput> aRoles) {

    public static ReadAllRoleOutput from(Pagination<Role> aPagination) {

        var list = new ArrayList<ReadRoleOutput>();

        for (var aRole : aPagination.aContent())
            list.add(ReadRoleOutput.from(aRole));

        return new ReadAllRoleOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
