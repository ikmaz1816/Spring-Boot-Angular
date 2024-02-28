package com.auth.registration.service;

import com.auth.registration.customexception.UserNotFoundException;
import com.auth.registration.dto.UserDTO;
import com.auth.registration.entity.User;
import com.auth.registration.interfaces.UserInterface;
import com.auth.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDTO getUser(long id) throws UserNotFoundException {
        User user =this.userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException("User Not Found"));
        UserDTO userDTO=this.modelMapper.map(user,UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUserList() throws UserNotFoundException{
        List<User> users=this.userRepository.findAll();
        if(users.size()==0)
        {
            throw new UserNotFoundException("User Not Found");
        }
        List<UserDTO> userDTOS=users.stream().map(user->modelMapper.map(user,UserDTO.class)).toList();
        return userDTOS;
    }

    @Override
    public UserDTO saveUser(User user) {
        User user1=User.builder().firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        UserDTO retrieveUser=this.modelMapper.map(this.userRepository.save(user1),UserDTO.class);
        return retrieveUser;
    }
}
