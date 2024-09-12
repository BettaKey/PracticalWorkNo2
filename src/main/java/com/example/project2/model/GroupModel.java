package com.example.project2.model;

import java.util.List;
import java.util.UUID;

public class GroupModel {
    private UUID id;
    private String name;
    private UUID directionId;

    public GroupModel(String name, UUID directionId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.directionId = directionId;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public UUID getDirectionId() { return directionId; }

    public void setDirectionId(UUID directionId) { this.directionId = directionId; }

    public String getDirectionName(List<DirectionModel> directions) {
        return directions.stream()
                .filter(direction -> direction.getId().equals(this.directionId))
                .map(DirectionModel::getName)
                .findFirst()
                .orElse("направление не выбрано");
    }
}
