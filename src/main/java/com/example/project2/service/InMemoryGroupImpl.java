package com.example.project2.service;

import com.example.project2.model.GroupModel;
import com.example.project2.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InMemoryGroupImpl implements GroupService {

    private final GroupRepository groupRepository;

    public InMemoryGroupImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<GroupModel> findAllGroups() {
        return groupRepository.findAllGroups();
    }

    @Override
    public GroupModel findGroupById(UUID id) {
        return groupRepository.findGroupById(id);
    }

    @Override
    public GroupModel createGroup(GroupModel group) {
        return groupRepository.createGroup(group);
    }

    @Override
    public GroupModel updateGroup(GroupModel group) {
        return groupRepository.updateGroup(group);
    }

    @Override
    public void deleteGroup(UUID id) {
        groupRepository.deleteGroup(id);
    }

    @Override
    public List<GroupModel> findGroupsByDirectionId(UUID directionId) {
        return groupRepository.findAllGroups().stream()
                .filter(group -> directionId.equals(group.getDirectionId()))
                .collect(Collectors.toList());
    }
}
