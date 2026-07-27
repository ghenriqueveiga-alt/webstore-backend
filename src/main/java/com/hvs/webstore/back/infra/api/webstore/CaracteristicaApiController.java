package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.caracteristica.*;
import com.hvs.webstore.back.app.usecase.webstore.caracteristica.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/caracteristica")
public class CaracteristicaApiController {

    private final CreateCaracteristicaUseCase createUseCase;
    private final ReadCaracteristicaUseCase readUseCase;
    private final UpdateCaracteristicaUseCase updateUseCase;
    private final PatchCaracteristicaUseCase patchUseCase;
    private final DeleteCaracteristicaUseCase deleteUseCase;

    public CaracteristicaApiController(final CreateCaracteristicaUseCase aCreateUseCase,
                                       final ReadCaracteristicaUseCase aReadUseCase,
                                       final UpdateCaracteristicaUseCase aUpdateUseCase,
                                       final PatchCaracteristicaUseCase aPatchUseCase,
                                       final DeleteCaracteristicaUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCaracteristicaCommand aCreateCaracteristicaCommand) {

        return createUseCase.execute(aCreateCaracteristicaCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadCaracteristicaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadCaracteristicaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateCaracteristicaCommand aUpdateCaracteristicaCommand) {

        return updateUseCase.execute(UpdateCaracteristicaCommand.from(aId, aUpdateCaracteristicaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateCaracteristicaCommand aUpdateCaracteristicaCommand) {

        return updateUseCase.execute(UpdateCaracteristicaCommand.from(aUuid, aUpdateCaracteristicaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchCaracteristicaCommand aPatchCaracteristicaCommand) {

        return patchUseCase.execute(PatchCaracteristicaCommand.from(aId, aPatchCaracteristicaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchCaracteristicaCommand aPatchCaracteristicaCommand) {

        return patchUseCase.execute(PatchCaracteristicaCommand.from(aUuid, aPatchCaracteristicaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteCaracteristicaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteCaracteristicaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
