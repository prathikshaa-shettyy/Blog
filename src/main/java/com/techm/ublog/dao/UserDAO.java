package com.techm.ublog.dao;

import com.techm.ublog.dto.UserDTO;

import java.sql.SQLException;

public interface UserDAO {
    UserDTO create(UserDTO userDTO) throws SQLException;
    UserDTO findByEmail(String emailId) throws SQLException;
}
