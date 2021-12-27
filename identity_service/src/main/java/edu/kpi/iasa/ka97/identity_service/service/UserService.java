package edu.kpi.iasa.ka97.identity_service.service;

import edu.kpi.iasa.ka97.identity_service.dto.UserDto;
import edu.kpi.iasa.ka97.identity_service.model.User;
import edu.kpi.iasa.ka97.identity_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private static final String DEFAULT_ROLE = "USER";
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new IllegalArgumentException("User not found");
    }
    public User updateUserById(Long id, UserDto updatedUser) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User oldUser = user.get();
            updateUser(oldUser, updatedUser);
            return userRepository.save(oldUser);
        }
        throw new IllegalArgumentException("User not found");
    }
    public void updateUser(User oldUser, UserDto updatedUser) {
        oldUser.setFirstname(updatedUser.getFirstname());
        oldUser.setLastname(updatedUser.getLastname());
        oldUser.setEmail(updatedUser.getEmail());
        oldUser.setPassword(updatedUser.getPassword());
        oldUser.setPhone(updatedUser.getPhone());
        oldUser.setUsername(updatedUser.getUsername());
    }
    public User createUser(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .build();
        user.setRoles(Collections.singleton(roleService.getRoleByName(DEFAULT_ROLE)));
        return user;
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}