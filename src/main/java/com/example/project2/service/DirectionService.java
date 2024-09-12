package com.example.project2.service;

import com.example.project2.model.DirectionModel;
import java.util.List;
import java.util.UUID;

public interface DirectionService {
    List<DirectionModel> findAllDirections();
    DirectionModel findDirectionById(UUID id);
    DirectionModel createDirection(DirectionModel direction);
    DirectionModel updateDirection(DirectionModel direction);
    void deleteDirection(UUID id);
}
