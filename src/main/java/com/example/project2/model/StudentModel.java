package com.example.project2.model;

import java.util.List;
import java.util.UUID;

public class StudentModel {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UUID groupId;

    public StudentModel(String name, String email, String password, UUID groupId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.groupId = groupId;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public UUID getGroupId() { return groupId; }

    public void setGroupId(UUID groupId) { this.groupId = groupId; }

    public String getGroupName(List<GroupModel> groups) {
        return groups.stream()
                .filter(group -> group.getId().equals(this.groupId))
                .map(GroupModel::getName)
                .findFirst()
                .orElse("группа не выбрана");
    }


}
