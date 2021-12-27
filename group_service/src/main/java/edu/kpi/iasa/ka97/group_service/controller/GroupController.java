package edu.kpi.iasa.ka97.group_service.controller;

import edu.kpi.iasa.ka97.group_service.model.Group;
import edu.kpi.iasa.ka97.group_service.service.GroupService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping(value = "/group")
public class GroupController {
    private GroupService groupService;
    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping
    public ResponseEntity<List<Group>> getAll() {
        final List<Group> groups = groupService.getGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Group> getById(@PathVariable long id) {
        try {
            Group group = groupService.getGroupById(id);

            return ResponseEntity.ok(group);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Group> create(@RequestBody Group group) {
        try{
            long trainer = group.getIdtrain();
            ResponseEntity<JSONObject> response = new RestTemplate().exchange(
                    "http://localhost:8080/user/trainer/" + trainer, HttpMethod.GET, null, JSONObject.class
            );
            Group new_group = groupService.createGroup(group);
            return ResponseEntity.ok(groupService.saveGroup(new_group));
        } catch (HttpClientErrorException.NotFound e) {
        return ResponseEntity.badRequest().build();
        }


    }

    @PatchMapping("/{id}")
    public ResponseEntity<Group> change(@PathVariable long id, @RequestBody Group group) {
        try {
            long trainer = group.getIdtrain();
            ResponseEntity<JSONObject> response = new RestTemplate().exchange(
                    "http://localhost:8080/user/trainer/" + trainer, HttpMethod.GET, null, JSONObject.class
            );
            return ResponseEntity.ok( groupService.saveGroup(groupService.updateGroupById(id, group)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        groupService.deleteGroupById(id);
        return ResponseEntity.noContent().build();
    }
}