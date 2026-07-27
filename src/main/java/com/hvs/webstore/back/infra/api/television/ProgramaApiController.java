package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.programa.*;
import com.hvs.webstore.back.app.usecase.television.programa.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/programa")
public class ProgramaApiController {

    private final CreateProgramaUseCase createProgramaUseCase;
    private final ReadProgramaUseCase readProgramaUseCase;
    private final ReadAllProgramaUseCase readAllProgramaUseCase;
    private final UpdateProgramaUseCase updateProgramaUseCase;
    private final PatchProgramaUseCase patchProgramaUseCase;
    private final DeleteProgramaUseCase deleteProgramaUseCase;

    public ProgramaApiController(final CreateProgramaUseCase createProgramaUseCase,
                                 final ReadProgramaUseCase readProgramaUseCase,
                                 final ReadAllProgramaUseCase readAllProgramaUseCase,
                                 final UpdateProgramaUseCase updateProgramaUseCase,
                                 final PatchProgramaUseCase patchProgramaUseCase,
                                 final DeleteProgramaUseCase deleteProgramaUseCase) {

        this.createProgramaUseCase = createProgramaUseCase;
        this.readProgramaUseCase = readProgramaUseCase;
        this.readAllProgramaUseCase = readAllProgramaUseCase;
        this.updateProgramaUseCase = updateProgramaUseCase;
        this.patchProgramaUseCase = patchProgramaUseCase;
        this.deleteProgramaUseCase = deleteProgramaUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createPrograma(@RequestBody CreateProgramaCommand aInput) {

        return this.createProgramaUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readProgramaById(@PathVariable("id") Long aId) {

        return this.readProgramaUseCase.execute(ReadProgramaCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readProgramaByUuid(@PathVariable("uuid") String aUuid) {

        return this.readProgramaUseCase.execute(ReadProgramaCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllPrograma(@RequestParam(required = false) String search,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sort,
                                             @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllProgramaUseCase.execute(new ReadAllProgramaCommand(
                        new ProgramaSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateProgramaById(@PathVariable("id") Long aId,
                                                @RequestBody UpdateProgramaCommand aInput) {

        return this.updateProgramaUseCase.execute(UpdateProgramaCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateProgramaByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateProgramaCommand aInput) {

        return this.updateProgramaUseCase.execute(UpdateProgramaCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchProgramaById(@PathVariable("id") Long aId,
                                               @RequestBody PatchProgramaCommand aInput) {

        return this.patchProgramaUseCase.execute(PatchProgramaCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchProgramaByUuid(@PathVariable("uuid") String aUuid,
                                                 @RequestBody PatchProgramaCommand aInput) {

        return this.patchProgramaUseCase.execute(PatchProgramaCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteProgramaById(@PathVariable("id") Long aId) {

        return this.deleteProgramaUseCase.execute(DeleteProgramaCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteProgramaByUuid(@PathVariable("uuid") String aUuid) {

        return this.deleteProgramaUseCase.execute(DeleteProgramaCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}
