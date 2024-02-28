package com.auth.registration.controller;

import com.auth.registration.customexception.UserNotFoundException;
import com.auth.registration.dto.UserDTO;
import com.auth.registration.entity.User;
import com.auth.registration.interfaces.UserInterface;
import com.auth.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="user")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) throws UserNotFoundException {
        UserDTO userDTO=this.userService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUser() throws UserNotFoundException {
        List<UserDTO> userDTOS=this.userService.getUserList();
        return ResponseEntity.ok(userDTOS);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody User user)
    {
        UserDTO userDTO=this.userService.saveUser(user);
        return ResponseEntity.ok(userDTO);
    }
}
