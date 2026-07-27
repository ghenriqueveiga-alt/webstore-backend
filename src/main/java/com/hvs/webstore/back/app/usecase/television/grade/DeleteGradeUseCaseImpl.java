package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.app.command.television.grade.DeleteGradeCommand;
import com.hvs.webstore.back.app.output.television.grade.DeleteGradeOutput;
import com.hvs.webstore.back.domain.entity.television.grade.Grade;
import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import com.hvs.webstore.back.domain.entity.television.grade.GradeId;
import com.hvs.webstore.back.domain.entity.television.grade.GradeUuid;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class DeleteGradeUseCaseImpl extends DeleteGradeUseCase {

    private final GradeDomainGateway gateway;

    public DeleteGradeUseCaseImpl(
            GradeDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, DeleteGradeOutput> execute(DeleteGradeCommand aIn) {

        Optional<Grade> gradeDb;

        if (aIn.aId() != null) {

            gradeDb = this.gateway.read(GradeId.from(aIn.aId()));
        } else {

            gradeDb = this.gateway.readByUuid(GradeUuid.from(aIn.aUuid()));
        }

        if (gradeDb.isPresent()) {

            this.gateway.delete(gradeDb.get());

            return Try(gradeDb::get)
                    .toEither()
                    .bimap(Notification::create, DeleteGradeOutput::from);
        } else {

            String responseId;

            if (aIn.aId() != null) {

                responseId = String.valueOf(aIn.aId());
            } else {

                responseId = aIn.aUuid();
            }

            return Either.left(Notification
                    .create(new Error("The Grade with id: " + responseId + " could not be found.")));
        }
    }
}
