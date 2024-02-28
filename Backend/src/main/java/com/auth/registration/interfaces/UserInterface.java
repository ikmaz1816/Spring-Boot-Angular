package com.auth.registration.interfaces;


import com.auth.registration.customexception.UserNotFoundException;
import com.auth.registration.dto.UserDTO;
import com.auth.registration.entity.User;

import java.util.List;

public interface UserInterface {

    UserDTO getUser(long id) throws UserNotFoundException;

    List<UserDTO> getUserList() throws UserNotFoundException;

    UserDTO saveUser(User user);
}
