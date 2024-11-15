package com.cardiosense.cardiosense.controller;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import com.cardiosense.cardiosense.service.RookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rook")
@RestController
@AllArgsConstructor
public class RookController {
    private final RookService rookService;

    @PostMapping()
    public ResponseEntity<?> saveRookEvents(@RequestBody PhysicalActivity physicalActivity) {
        rookService.saveRookEvents(physicalActivity);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> getRookEvents() {
        return ResponseEntity.ok(rookService.getRookEvents());
    }

}
