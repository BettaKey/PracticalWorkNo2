package com.example.project2.repository;

import com.example.project2.model.DirectionModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Repository
public class DirectionRepository {
    private final List<DirectionModel> DIRECTIONS = new ArrayList<>();

    public List<DirectionModel> findAllDirections() {
        return DIRECTIONS;
    }

    public DirectionModel findDirectionById(UUID id) {
        return DIRECTIONS.stream()
                .filter(direction -> direction.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public DirectionModel createDirection(DirectionModel direction) {
        DIRECTIONS.add(direction);
        return direction;
    }

    public DirectionModel updateDirection(DirectionModel direction) {
        int directionIndex = IntStream.range(0, DIRECTIONS.size())
                .filter(index -> DIRECTIONS.get(index).getId().equals(direction.getId()))
                .findFirst()
                .orElse(-1);
        if (directionIndex != -1) {
            DIRECTIONS.set(directionIndex, direction);
            return direction;
        }
        return null;
    }

    public void deleteDirection(UUID id) {
        DirectionModel direction = findDirectionById(id);
        if (direction != null) {
            DIRECTIONS.remove(direction);
        }
    }
}