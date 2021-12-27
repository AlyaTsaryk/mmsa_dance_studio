package edu.kpi.iasa.ka97.identity_service.controller;

import edu.kpi.iasa.ka97.identity_service.dto.UserDto;
import edu.kpi.iasa.ka97.identity_service.model.User;
import edu.kpi.iasa.ka97.identity_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        final List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        try {
            User user = userService.getUserById(id);

            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/trainer/{id}")
    public ResponseEntity<User> getByIdTrainer(@PathVariable long id) {
        try {
            User user = userService.getUserById(id);
            if (user.hasRole("TRAINER")){
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.notFound().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<User> getByIdUser(@PathVariable long id) {
        try {
            User user = userService.getUserById(id);
            if (user.hasRole("USER")){
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.notFound().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody UserDto userDto) {
        try{
            User user = userService.createUser(userDto);
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> change(@PathVariable long id, @RequestBody UserDto userDto) {
        try {
            return ResponseEntity.ok( userService.saveUser(userService.updateUserById(id, userDto)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
