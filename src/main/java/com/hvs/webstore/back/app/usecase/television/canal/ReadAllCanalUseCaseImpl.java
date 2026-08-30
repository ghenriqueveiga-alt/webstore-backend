package com.hvs.webstore.back.app.usecase.television.canal;

import com.hvs.webstore.back.app.command.television.canal.ReadAllCanalCommand;
import com.hvs.webstore.back.app.output.television.canal.ReadAllCanalOutput;
import com.hvs.webstore.back.domain.entity.television.canal.Canal;
import com.hvs.webstore.back.domain.entity.television.canal.CanalDomainGateway;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.List;

public class ReadAllCanalUseCaseImpl extends ReadAllCanalUseCase {

    private final CanalDomainGateway gateway;

    public ReadAllCanalUseCaseImpl(CanalDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadAllCanalOutput> execute(ReadAllCanalCommand aCanalCommand) {

        Pagination<Canal> canalPagination = this.gateway.readAll(aCanalCommand.aCanalSearchQuery());
        List<Canal> lista = canalPagination.aContent()
                .stream().filter(canal -> canal.getStatus().getDesc().equals("Active")).toList();

        if (!lista.isEmpty()) {
            return Either.right(ReadAllCanalOutput.from(Pagination.from(
                    canalPagination.aPageNumber(),
                    canalPagination.aTotalElements(),
                    canalPagination.aTotalPages(),
                    lista)));
        } else {
            return Either.left(Notification.create(new Error("No Canal was found.")));
        }
    }
}
