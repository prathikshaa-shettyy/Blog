package com.techm.ublog.services;

import com.techm.ublog.dto.UserDTO;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO) throws Exception;
    UserDTO getUser(String emailId) throws Exception;
}
