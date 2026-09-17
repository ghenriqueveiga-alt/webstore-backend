package com.hvs.webstore.back.infra.api.webstore.anuncio;

import com.hvs.webstore.back.infra.persistence.webstore.anuncio.AnuncioEntity;
import com.hvs.webstore.back.infra.persistence.webstore.anuncio.AnuncioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/anuncio")
public class AnuncioApiController {

    private final AnuncioRepository repository;

    public AnuncioApiController(final AnuncioRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<AnuncioEntity>> listAll(
            @RequestParam(defaultValue = "0") final Integer posicao) {
        if (posicao != null && posicao >= 0) {
            return ResponseEntity.ok(repository.findActiveByPosition(posicao));
        }
        return ResponseEntity.ok(repository.findAllActive());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AnuncioEntity> getById(@PathVariable final Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody final Map<String, Object> payload) {
        try {
            final var entity = new AnuncioEntity();
            entity.setUuid(UUID.randomUUID().toString());
            entity.setTitulo((String) payload.getOrDefault("titulo", ""));
            entity.setDescricao((String) payload.getOrDefault("descricao", ""));
            entity.setImageUrl((String) payload.getOrDefault("imageUrl", ""));
            entity.setLinkUrl((String) payload.getOrDefault("linkUrl", ""));
            entity.setMoeda((String) payload.getOrDefault("moeda", "BTC"));
            entity.setWalletAddress((String) payload.getOrDefault("walletAddress", ""));
            entity.setTxHash((String) payload.getOrDefault("txHash", ""));
            entity.setPosicao((Integer) payload.getOrDefault("posicao", 0));
            entity.setLargura((Integer) payload.getOrDefault("largura", 728));
            entity.setAltura((Integer) payload.getOrDefault("altura", 90));
            entity.setStatusDesc("PE"); // Pendente de verificacao
            final Object valor = payload.get("valorPago");
            entity.setValorPago(valor instanceof Number ? ((Number) valor).doubleValue() : 0.0);

            final var saved = repository.save(entity);
            return ResponseEntity.ok(Map.of(
                    "id", saved.getId(),
                    "uuid", saved.getUuid(),
                    "message", "Anuncio enviado com sucesso. Aguardando verificacao de pagamento."
            ));
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/id/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable final Long id,
                                          @RequestBody final Map<String, String> payload) {
        return repository.findById(id).map(entity -> {
            entity.setStatusDesc(payload.getOrDefault("statusDesc", "IN"));
            repository.save(entity);
            return ResponseEntity.ok(Map.of("message", "Status atualizado com sucesso."));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable final Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok(Map.of("message", "Anuncio removido com sucesso."));
        }
        return ResponseEntity.notFound().build();
    }
}
