package com.hvs.ws.back.app.usecase.grade;

import com.hvs.ws.back.app.command.grade.ReadGradeCommand;
import com.hvs.ws.back.app.output.grade.ReadGradeOutput;
import com.hvs.ws.back.domain.entity.grade.Grade;
import com.hvs.ws.back.domain.entity.grade.GradeDomainGateway;
import com.hvs.ws.back.domain.entity.grade.GradeId;
import com.hvs.ws.back.domain.entity.grade.GradeUuid;
import com.hvs.ws.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import java.util.Optional;
import static io.vavr.API.Try;

public class ReadGradeUseCaseImpl extends ReadGradeUseCase {

    private final GradeDomainGateway gateway;

    public ReadGradeUseCaseImpl(
            GradeDomainGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Either<Notification, ReadGradeOutput> execute(ReadGradeCommand aIn) {

        Optional<Grade> gradeDb;

        if (aIn.aId() != null) {

            gradeDb = this.gateway.read(GradeId.from(aIn.aId()));
        } else {

            gradeDb = this.gateway.readByUuid(GradeUuid.from(aIn.aUuid()));
        }

        if (gradeDb.isPresent()) {

            return Try(gradeDb::get).toEither().bimap(Notification::create, ReadGradeOutput::from);
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
