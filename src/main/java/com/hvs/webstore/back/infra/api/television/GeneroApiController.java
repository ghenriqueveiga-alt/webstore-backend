package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.genero.*;
import com.hvs.webstore.back.app.usecase.television.genero.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genero")
public class GeneroApiController {

    private final CreateGeneroUseCase createGeneroUseCase;
    private final ReadGeneroUseCase readGeneroUseCase;
    private final ReadAllGeneroUseCase readAllGeneroUseCase;
    private final UpdateGeneroUseCase updateGeneroUseCase;
    private final PatchGeneroUseCase patchGeneroUseCase;
    private final DeleteGeneroUseCase deleteGeneroUseCase;

    public GeneroApiController(final CreateGeneroUseCase createGeneroUseCase,
                               final ReadGeneroUseCase readGeneroUseCase,
                               final ReadAllGeneroUseCase readAllGeneroUseCase,
                               final UpdateGeneroUseCase updateGeneroUseCase,
                               final PatchGeneroUseCase patchGeneroUseCase,
                               final DeleteGeneroUseCase deleteGeneroUseCase) {

        this.createGeneroUseCase = createGeneroUseCase;
        this.readGeneroUseCase = readGeneroUseCase;
        this.readAllGeneroUseCase = readAllGeneroUseCase;
        this.updateGeneroUseCase = updateGeneroUseCase;
        this.patchGeneroUseCase = patchGeneroUseCase;
        this.deleteGeneroUseCase = deleteGeneroUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createGenero(@RequestBody CreateGeneroCommand aInput) {

        return this.createGeneroUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readGeneroById(@PathVariable("id") Long aId) {

        return this.readGeneroUseCase.execute(ReadGeneroCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readGeneroByUuid(@PathVariable("uuid") String aUuid) {

        return this.readGeneroUseCase.execute(ReadGeneroCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllGenero(@RequestParam(required = false) String search,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sort,
                                           @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllGeneroUseCase.execute(new ReadAllGeneroCommand(
                        new GeneroSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateGeneroById(@PathVariable("id") Long aId,
                                              @RequestBody UpdateGeneroCommand aInput) {

        return this.updateGeneroUseCase.execute(UpdateGeneroCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateGeneroByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateGeneroCommand aInput) {

        return this.updateGeneroUseCase.execute(UpdateGeneroCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchGeneroById(@PathVariable("id") Long aId,
                                             @RequestBody PatchGeneroCommand aInput) {

        return this.patchGeneroUseCase.execute(PatchGeneroCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchGeneroByUuid(@PathVariable("uuid") String aUuid,
                                               @RequestBody PatchGeneroCommand aInput) {

        return this.patchGeneroUseCase.execute(PatchGeneroCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteGeneroById(@PathVariable("id") Long aId) {

        return this.deleteGeneroUseCase.execute(DeleteGeneroCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteGeneroByUuid(@PathVariable("uuid") String aUuid) {

        return this.deleteGeneroUseCase.execute(DeleteGeneroCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}
