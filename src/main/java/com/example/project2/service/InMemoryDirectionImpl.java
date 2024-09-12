package com.example.project2.service;

import com.example.project2.model.DirectionModel;
import com.example.project2.repository.DirectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryDirectionImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    public InMemoryDirectionImpl(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    @Override
    public List<DirectionModel> findAllDirections() {
        return directionRepository.findAllDirections();
    }

    @Override
    public DirectionModel findDirectionById(UUID id) {
        return directionRepository.findDirectionById(id);
    }

    @Override
    public DirectionModel createDirection(DirectionModel direction) {
        return directionRepository.createDirection(direction);
    }

    @Override
    public DirectionModel updateDirection(DirectionModel direction) {
        return directionRepository.updateDirection(direction);
    }

    @Override
    public void deleteDirection(UUID id) {
        directionRepository.deleteDirection(id);
    }
}
