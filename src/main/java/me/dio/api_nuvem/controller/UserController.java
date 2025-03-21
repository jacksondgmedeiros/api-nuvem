package me.dio.api_nuvem.controller;

import me.dio.api_nuvem.model.User;
import me.dio.api_nuvem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        var userCreated = userService.create(user);

//        para criar uma URI informando o path do usuário
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userCreated.getId()).toUri();

        // retorna o status code de created com a URI e os dados criados
        return ResponseEntity.created(location).body(userCreated);
    }
}
