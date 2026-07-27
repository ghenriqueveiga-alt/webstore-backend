package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.corte.*;
import com.hvs.webstore.back.app.usecase.television.corte.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/corte")
public class CorteApiController {

    private final CreateCorteUseCase createCorteUseCase;
    private final ReadCorteUseCase readCorteUseCase;
    private final ReadAllCorteUseCase readAllCorteUseCase;
    private final UpdateCorteUseCase updateCorteUseCase;
    private final PatchCorteUseCase patchCorteUseCase;
    private final DeleteCorteUseCase deleteCorteUseCase;

    public CorteApiController(
            final CreateCorteUseCase createCorteUseCase,
            final ReadCorteUseCase readCorteUseCase,
            final ReadAllCorteUseCase readAllCorteUseCase,
            final UpdateCorteUseCase updateCorteUseCase,
            final PatchCorteUseCase patchCorteUseCase,
            final DeleteCorteUseCase deleteCorteUseCase) {
        this.createCorteUseCase = createCorteUseCase;
        this.readCorteUseCase = readCorteUseCase;
        this.readAllCorteUseCase = readAllCorteUseCase;
        this.updateCorteUseCase = updateCorteUseCase;
        this.patchCorteUseCase = patchCorteUseCase;
        this.deleteCorteUseCase = deleteCorteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createCorte(
            @RequestBody CreateCorteCommand aInput) {

        return this.createCorteUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readCorteById(
            @PathVariable("id") Long aId) {

        return this.readCorteUseCase.execute(ReadCorteCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readCorteByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.readCorteUseCase.execute(ReadCorteCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllCorte(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllCorteUseCase.execute(new ReadAllCorteCommand(
                        new CorteSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateCorteById(
            @PathVariable("id") Long aId,
            @RequestBody UpdateCorteCommand aInput) {

        return this.updateCorteUseCase.execute(UpdateCorteCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateCorteByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateCorteCommand aInput) {

        return this.updateCorteUseCase.execute(UpdateCorteCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchCorteById(
            @PathVariable("id") Long aId,
            @RequestBody PatchCorteCommand aInput) {

        return this.patchCorteUseCase.execute(PatchCorteCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchCorteByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody PatchCorteCommand aInput) {

        return this.patchCorteUseCase.execute(PatchCorteCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteCorteById(
            @PathVariable("id") Long aId) {

        return this.deleteCorteUseCase.execute(DeleteCorteCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteCorteByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.deleteCorteUseCase.execute(DeleteCorteCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}