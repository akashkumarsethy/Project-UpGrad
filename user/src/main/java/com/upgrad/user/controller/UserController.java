package com.upgrad.user.controller;

import com.upgrad.user.dto.UserDTO;
import com.upgrad.user.entities.User;
import com.upgrad.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @PostMapping(value="/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        User user = modelMapper.map(userDTO,User.class);
        User savedUser=userService.createUser(user);
        UserDTO savedUserDTO= modelMapper.map(savedUser,UserDTO.class);
        return new ResponseEntity(savedUserDTO, HttpStatus.OK);
    }

   @GetMapping(value="/user/{Id}")
    public ResponseEntity getUser(@PathVariable(name = "Id") int Id){
        User user=userService.getUser(Id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping(value="/users")
    public ResponseEntity getAllUsers(){
        List<User> userList= userService.getAllUsers();
        List<UserDTO> userDTOList=new ArrayList<>();
        for(User user:userList){
            userDTOList.add(modelMapper.map(user,UserDTO.class));
        }
        return new ResponseEntity(userDTOList, HttpStatus.OK);
    }

    @DeleteMapping(value= "/user/{Id}")
    public ResponseEntity deleteUser(@PathVariable(name = "Id") int Id){
        userService.deleteUser(Id);
        return ResponseEntity.ok("User Deleted");
    }

    @PutMapping(value = "/user/{Id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable(name = "Id") int Id, @RequestBody UserDTO userDTO) {

        User user = modelMapper.map(userDTO,User.class);
        User updatedUser=userService.updateUser(Id, user);
        UserDTO updatedUserDTO=modelMapper.map(updatedUser,UserDTO.class);
        return new ResponseEntity(updatedUserDTO, HttpStatus.OK);
    }

}
