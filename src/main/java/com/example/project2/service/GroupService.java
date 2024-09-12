package com.example.project2.service;

import com.example.project2.model.GroupModel;
import java.util.List;
import java.util.UUID;

public interface GroupService {
    List<GroupModel> findAllGroups();
    GroupModel findGroupById(UUID id);
    GroupModel createGroup(GroupModel group);
    GroupModel updateGroup(GroupModel group);
    void deleteGroup(UUID id);
    List<GroupModel> findGroupsByDirectionId(UUID directionId);
}

