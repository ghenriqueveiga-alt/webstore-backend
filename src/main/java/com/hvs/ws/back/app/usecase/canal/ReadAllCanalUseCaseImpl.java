package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.app.command.canal.ReadAllCanalCommand;
import com.hvs.ws.back.app.output.canal.ReadAllCanalOutput;
import com.hvs.ws.back.domain.entity.canal.Canal;
import com.hvs.ws.back.domain.entity.canal.CanalDomainGateway;
import com.hvs.ws.back.domain.pagination.Pagination;
import com.hvs.ws.back.domain.validation.notification.Notification;
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
