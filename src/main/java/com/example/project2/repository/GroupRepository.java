package com.example.project2.repository;

import com.example.project2.model.GroupModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Repository
public class GroupRepository {
    private final List<GroupModel> GROUPS = new ArrayList<>();

    public List<GroupModel> findAllGroups() {
        return GROUPS;
    }

    public GroupModel findGroupById(UUID id) {
        return GROUPS.stream().filter(group -> group.getId().equals(id)).findFirst().orElse(null);
    }

    public GroupModel createGroup(GroupModel group) {
        GROUPS.add(group);
        return group;
    }

    public GroupModel updateGroup(GroupModel group) {
        var groupIndex = IntStream.range(0, GROUPS.size())
                .filter(index -> GROUPS.get(index).getId().equals(group.getId()))
                .findFirst()
                .orElse(-1);
        if (groupIndex != -1) {
            GROUPS.set(groupIndex, group);
        }
        return group;
    }

    public void deleteGroup(UUID id) {
        var group = findGroupById(id);
        if (group != null) {
            GROUPS.remove(group);
        }
    }
}
