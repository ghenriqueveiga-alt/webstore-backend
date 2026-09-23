package com.hvs.ws.back.infra.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/v1/linha-vermelha")
public class LinhaVermelhaApiController {

    private final AtomicReference<Map<String, Object>> state = new AtomicReference<>(Map.of());

    @GetMapping
    public ResponseEntity<Map<String, Object>> get() {
        return ResponseEntity.ok(state.get());
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> set(@RequestBody Map<String, Object> payload) {
        state.set(payload);
        return ResponseEntity.ok(payload);
    }

    @DeleteMapping
    public ResponseEntity<Void> clear() {
        state.set(Map.of());
        return ResponseEntity.ok().build();
    }
}
