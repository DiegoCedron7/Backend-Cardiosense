package com.cardiosense.cardiosense.service;

import com.cardiosense.cardiosense.model.Rook.PhysicalActivity;
import com.cardiosense.cardiosense.repository.RookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RookService {
    private final RookRepository rookRepository;
    private final UserService userService;

    public void saveRookEvents(PhysicalActivity physicalActivity) {
        // Primero del evento que me pasen debo obtener el user
        // Luego debo obtener el id del user
        rookRepository.save(physicalActivity);
    }


    public List<PhysicalActivity> getRookEvents() {
        return rookRepository.findAll();
    }
}
