package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.pix.*;
import com.hvs.webstore.back.app.usecase.webstore.pix.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pix")
public class PixApiController {

    private final CreatePixUseCase createUseCase;
    private final ReadPixUseCase readUseCase;
    private final UpdatePixUseCase updateUseCase;
    private final PatchPixUseCase patchUseCase;
    private final DeletePixUseCase deleteUseCase;

    public PixApiController(final CreatePixUseCase aCreateUseCase,
                            final ReadPixUseCase aReadUseCase,
                            final UpdatePixUseCase aUpdateUseCase,
                            final PatchPixUseCase aPatchUseCase,
                            final DeletePixUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePixCommand aCreatePixCommand) {

        return createUseCase.execute(aCreatePixCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadPixCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadPixCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdatePixCommand aUpdatePixCommand) {

        return updateUseCase.execute(UpdatePixCommand.from(aId, aUpdatePixCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdatePixCommand aUpdatePixCommand) {

        return updateUseCase.execute(UpdatePixCommand.from(aUuid, aUpdatePixCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchPixCommand aPatchPixCommand) {

        return patchUseCase.execute(PatchPixCommand.from(aId, aPatchPixCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchPixCommand aPatchPixCommand) {

        return patchUseCase.execute(PatchPixCommand.from(aUuid, aPatchPixCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeletePixCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeletePixCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
