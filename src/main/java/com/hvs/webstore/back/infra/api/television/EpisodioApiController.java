package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.episodio.*;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioCortesDetectadosOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioOutput;
import com.hvs.webstore.back.app.usecase.television.episodio.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/episodio")
@Tag(name = "Episódios", description = "Operações de CRUD, cortes de tempo e detecção de comerciais dos episódios")
public class EpisodioApiController {

private final CreateEpisodioUseCase createEpisodioUseCase;
    private final ReadEpisodioUseCase readEpisodioUseCase;
    private final ReadEpisodioCortesTempoUseCase readEpisodioCortesTempoUseCase;
    private final ReadEpisodioCortesDetectadosUseCase readEpisodioCortesDetectadosUseCase;
    private final ReadAllEpisodioUseCase readAllEpisodioUseCase;
    private final UpdateEpisodioUseCase updateEpisodioUseCase;
    private final PatchEpisodioUseCase patchEpisodioUseCase;
    private final DeleteEpisodioUseCase deleteEpisodioUseCase;

    public EpisodioApiController(
            final CreateEpisodioUseCase createEpisodioUseCase,
            final ReadEpisodioUseCase readEpisodioUseCase,
            final ReadEpisodioCortesTempoUseCase readEpisodioCortesTempoUseCase,
            final ReadEpisodioCortesDetectadosUseCase readEpisodioCortesDetectadosUseCase,
            final ReadAllEpisodioUseCase readAllEpisodioUseCase,
            final UpdateEpisodioUseCase updateEpisodioUseCase,
            final PatchEpisodioUseCase patchEpisodioUseCase,
            final DeleteEpisodioUseCase deleteEpisodioUseCase) {
        this.createEpisodioUseCase = createEpisodioUseCase;
        this.readEpisodioUseCase = readEpisodioUseCase;
        this.readEpisodioCortesTempoUseCase = readEpisodioCortesTempoUseCase;
        this.readEpisodioCortesDetectadosUseCase = readEpisodioCortesDetectadosUseCase;
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
    @Operation(summary = "Busca episódio por id",
            responses = @ApiResponse(responseCode = "200", description = "Episódio encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReadEpisodioOutput.class))))
    public ResponseEntity<?> readEpisodioById(
            @PathVariable("id") Long aId) {

        return this.readEpisodioUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    @Operation(summary = "Busca episódio por uuid",
            responses = @ApiResponse(responseCode = "200", description = "Episódio encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReadEpisodioOutput.class))))
    public ResponseEntity<?> readEpisodioByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.readEpisodioUseCase.execute(ReadEpisodioCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}/cortes-tempo")
    public ResponseEntity<?> readEpisodioCortesTempo(
            @PathVariable("id") Long aId) {

        return this.readEpisodioCortesTempoUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}/cortes-detectados")
    @Operation(summary = "Cortes de tempo e detecções de um episódio",
            description = "Retorna os cortes mapeados do episódio junto com as detecções automáticas (silêncios/possíveis comerciais) e o campo processado. Não executa detecção nova.",
            responses = @ApiResponse(responseCode = "200", description = "Cortes e detecções do episódio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReadEpisodioCortesDetectadosOutput.class))))
    public ResponseEntity<?> readEpisodioCortesDetectados(
            @PathVariable("id") Long aId) {

        return this.readEpisodioCortesDetectadosUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    @Operation(summary = "Lista episódios paginados",
            responses = @ApiResponse(responseCode = "200", description = "Lista paginada de episódios",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.hvs.webstore.back.app.output.television.episodio.ReadAllEpisodioOutput.class))))
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