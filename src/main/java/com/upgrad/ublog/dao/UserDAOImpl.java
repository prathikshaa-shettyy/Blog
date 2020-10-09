package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 6.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 6.6. findByEmail() method should take email id as an input parameter and
 *  return the user details corresponding to the email id from the UBLOG_USERS table defined
 *  in the database. (Hint: You should get the connection using the DatabaseConnection class)
 * TODO: 6.7. create() method should take user details as input and insert these details
 *  into the UBLOG_USERS table. Return the same UserDTO object which was passed as an input
 *  argument. (Hint: You should get the connection using the DatabaseConnection class)
 */

public class UserDAOImpl {
    private static UserDAOImpl instance;

    private UserDAOImpl () {

    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return  instance;
    }

    public static UserDTO create(UserDTO user) throws SQLException{

            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO UBLOG_USERS (id, email_id, password) VALUES (" +
                    user.getUserId() + ", '" +
                    user.getEmailId() + "', " +
                    user.getPassword()+
                    ")";
            statement.executeUpdate(sql);
            return user;
        }


        public List<UserDTO> findByEmail (String emailId) throws SQLException {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM UBLOG_USERS WHERE email_id = " + emailId;
            ResultSet resultSet = statement.executeQuery(sql);

            List<UserDTO> users = new ArrayList<>();
            if (resultSet.next()) {
                UserDTO user = new UserDTO();
                user.setUserId(resultSet.getInt("id"));
                user.setEmailId(resultSet.getString("email_id"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            } else {
                return null;
            }
            return users;
        }


    public static void main(String[] args) {
		try {
			UserDAO userDAO = (UserDAO) new UserDAOImpl();
			UserDTO temp = new UserDTO();
			temp.setUserId(1);
			temp.setEmailId("temp@temp.temp");
			temp.setPassword("temp");
			userDAO.create(temp);
			System.out.println(userDAO.findByEmail("temp@temp.temp"));
		} catch (Exception e) {
			System.out.println("FAILED");
		}

		 // Following should be the desired output of the main method.
		 // UserDTO{userId=11, emailId='temp@temp.temp', password='temp'}
	}
}


