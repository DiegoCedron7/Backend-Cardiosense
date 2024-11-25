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

    @PostMapping
    public ResponseEntity<?> saveRook(@RequestBody Object request) {
        try {
            rookService.saveRookEntity(request);
            return ResponseEntity.ok("Data saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/sleep-summary/{userId}")
    public ResponseEntity<?> getSleepSummaryByUser(@PathVariable String userId, @RequestParam String date) {
        try {
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
            }
            return ResponseEntity.ok(rookService.getSleepSummaryByUser(userId, date));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/physical-summary/{userId}")
    public ResponseEntity<?> getPhysicalSummaryByUser(@PathVariable String userId, @RequestParam String date) {
        try {
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
            }
            return ResponseEntity.ok(rookService.getPhysicalSummaryByUser(userId, date));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body("Internal server error");
        }
    }

    @GetMapping("/physical-activity/{userId}")
    public ResponseEntity<?> getPhysicalActivityByUser(@PathVariable String userId, @RequestParam String date) {
        try {
            if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Invalid date format. Expected format: YYYY-MM-DD");
            }
            return ResponseEntity.ok(rookService.getPhysicalActivityByUser(userId, date));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }


    @GetMapping("/health-score/{userId}")
    public ResponseEntity<?> getHealthScoreByUser(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(rookService.getHealthScoreByUser(userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error" + e.getMessage());
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }

}
