package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.episodio.*;
import com.hvs.webstore.back.app.usecase.television.episodio.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/episodio")
public class EpisodioApiController {

    private final CreateEpisodioUseCase createEpisodioUseCase;
    private final ReadEpisodioUseCase readEpisodioUseCase;
    private final ReadAllEpisodioUseCase readAllEpisodioUseCase;
    private final UpdateEpisodioUseCase updateEpisodioUseCase;
    private final PatchEpisodioUseCase patchEpisodioUseCase;
    private final DeleteEpisodioUseCase deleteEpisodioUseCase;

    public EpisodioApiController(
            final CreateEpisodioUseCase createEpisodioUseCase,
            final ReadEpisodioUseCase readEpisodioUseCase,
            final ReadAllEpisodioUseCase readAllEpisodioUseCase,
            final UpdateEpisodioUseCase updateEpisodioUseCase,
            final PatchEpisodioUseCase patchEpisodioUseCase,
            final DeleteEpisodioUseCase deleteEpisodioUseCase) {
        this.createEpisodioUseCase = createEpisodioUseCase;
        this.readEpisodioUseCase = readEpisodioUseCase;
        this.readAllEpisodioUseCase = readAllEpisodioUseCase;
        this.updateEpisodioUseCase = updateEpisodioUseCase;
        this.patchEpisodioUseCase = patchEpisodioUseCase;
        this.deleteEpisodioUseCase = deleteEpisodioUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createEpisodio(
            @RequestBody CreateEpisodioCommand aInput) {

        return this.createEpisodioUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readEpisodioById(
            @PathVariable("id") Long aId) {

        return this.readEpisodioUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readEpisodioByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.readEpisodioUseCase.execute(ReadEpisodioCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllEpisodio(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllEpisodioUseCase.execute(new ReadAllEpisodioCommand(
                        new EpisodioSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateEpisodioById(
            @PathVariable("id") Long aId,
            @RequestBody UpdateEpisodioCommand aInput) {

        return this.updateEpisodioUseCase.execute(UpdateEpisodioCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateEpisodioByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateEpisodioCommand aInput) {

        return this.updateEpisodioUseCase.execute(UpdateEpisodioCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchEpisodioById(
            @PathVariable("id") Long aId,
            @RequestBody PatchEpisodioCommand aInput) {

        return this.patchEpisodioUseCase.execute(PatchEpisodioCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchEpisodioByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody PatchEpisodioCommand aInput) {

        return this.patchEpisodioUseCase.execute(PatchEpisodioCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteEpisodioById(
            @PathVariable("id") Long aId) {

        return this.deleteEpisodioUseCase.execute(DeleteEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteCorteByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.deleteEpisodioUseCase.execute(DeleteEpisodioCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}