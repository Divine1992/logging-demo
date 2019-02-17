package com.divine.logging.controller;

import com.divine.logging.dao.dto.Success;
import com.divine.logging.dao.dto.UserModel;
import com.divine.logging.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping
    public ResponseEntity<Success> create(@RequestBody @Valid UserModel userModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userModel));
    }

    @PutMapping
    public ResponseEntity<UserModel> update(@RequestBody @Valid UserModel userModel){
        return ResponseEntity.ok(userService.update(userModel));
    }

    @GetMapping
    public ResponseEntity<UserModel> get(@RequestParam("username") String username){
        return ResponseEntity.ok(userService.get(username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Success> delete(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userService.delete(userId));
    }

}

