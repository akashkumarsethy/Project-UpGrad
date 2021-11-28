package com.upgrad.user.controller;

import com.upgrad.user.dto.UserDTO;
import com.upgrad.user.entities.User;
import com.upgrad.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping(value = "/user_app/v1")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/user")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        User savedUser=userService.createUser(user);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }
   @GetMapping(value="/user/{Id}")
    public ResponseEntity getUser(@PathVariable int Id){
        Optional<User> savedUser=userService.getUser(Id);
        return new ResponseEntity(savedUser, HttpStatus.OK);
    }
    @DeleteMapping(value= "/user/{Id}")
    public ResponseEntity deleteUser(@PathVariable int Id){
        userService.deleteUser(Id);
        return ResponseEntity.ok("User Deleted");
    }

//    @PutMapping(value = "/user/{id}")
//    public ResponseEntity updateUser(@PathVariable int Id, @RequestBody UserDTO userDTO) {
//
//        Optional<User> user = userService.getUser(Id);
//
//        updatedUserEntity.setRole(roleEntity);
//
//        userService.updateUser(userUuid, updatedUserEntity);
//        return ResponseBuilder.ok().build();
//    }

}
