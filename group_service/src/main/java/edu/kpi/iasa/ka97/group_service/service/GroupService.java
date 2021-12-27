package edu.kpi.iasa.ka97.group_service.service;


import edu.kpi.iasa.ka97.group_service.model.Group;
import edu.kpi.iasa.ka97.group_service.repository.GroupRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.simple.JSONObject;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }
    public List<Group> getGroup(Long idtrain) {

        return groupRepository.findAllByIdtrain(idtrain);
    }
    public List<Group> getGroups() {

        return groupRepository.findAll();
    }
    public Group getGroupById(Long id) {
        return groupRepository.findGroupById(id).orElseThrow(IllegalArgumentException::new);
    }
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroupById(Long id, Long id_user){
        Group group = groupRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        return groupRepository.save(group);
    }
    public Group updateGroupById(Long id, Group updatedGroup) {
        Optional<Group> group = groupRepository.findById(id);
        if (group.isPresent()) {
            Group oldGroup = group.get();
            updateGroup(oldGroup, updatedGroup);
            return groupRepository.save(oldGroup);
        }
        throw new IllegalArgumentException("Group not found");
    }
    public void updateGroup(Group oldGroup, Group updatedGroup) {
        oldGroup.setIdtrain(updatedGroup.getIdtrain());
        oldGroup.setMin_age(updatedGroup.getMin_age());
        oldGroup.setMax_age(updatedGroup.getMax_age());
        oldGroup.setId_hall(updatedGroup.getId_hall());
    }
    public Group createGroup(Group group) {

        Group new_group = Group.builder()
                .idtrain(group.getIdtrain())
                .min_age(group.getMin_age())
                .max_age(group.getMax_age())
                .id_hall(group.getId_hall())
                .build();
        return new_group;
    }

    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }
}
