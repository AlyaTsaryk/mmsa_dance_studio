package edu.kpi.iasa.ka97.request_service.controller;

import edu.kpi.iasa.ka97.request_service.dto.RequestDto;
import edu.kpi.iasa.ka97.request_service.model.Request;
import edu.kpi.iasa.ka97.request_service.service.RequestService;
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
@RequestMapping(value = "/request")
public class RequestController {
    private RequestService requestService;
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    @GetMapping
    public ResponseEntity<List<Request>> getAll() {
        final List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Request> getById(@PathVariable long id) {
        try {
            Request request = requestService.getRequestById(id);

            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<Request> create(@RequestBody RequestDto request) {
        try{
            long group = request.getId_group();
            long user = request.getIduser();

            ResponseEntity<JSONObject> response = new RestTemplate().exchange(
                    "http://localhost:8081/group/" + group, HttpMethod.GET, null, JSONObject.class
            );
            ResponseEntity<JSONObject> response1 = new RestTemplate().exchange(
                    "http://localhost:8080/user/student/" + user, HttpMethod.GET, null, JSONObject.class
            );
            Request new_group = requestService.createRequest(request);
            return ResponseEntity.ok(requestService.saveRequest(new_group));
        }catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest().build();

    }}

    @PatchMapping("/{id}")
    public ResponseEntity<Request> change(@PathVariable long id, @RequestBody Request request) {
        try {
            long group = request.getId_group();
            long user = request.getIduser();

            ResponseEntity<JSONObject> response = new RestTemplate().exchange(
                    "http://localhost:8081/group/" + group, HttpMethod.GET, null, JSONObject.class
            );
            ResponseEntity<JSONObject> response1 = new RestTemplate().exchange(
                    "http://localhost:8080/user/student/" + user, HttpMethod.GET, null, JSONObject.class
            );
            return ResponseEntity.ok( requestService.updateRequestById(id, request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest().build();}
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        requestService.deleteRequestById(id);
        return ResponseEntity.noContent().build();
    }
}