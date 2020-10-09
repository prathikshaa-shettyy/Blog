package com.upgrad.ublog.dao;

/**
 * TODO: 6.19. Implement the PostsDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  PostDAOImpl and a public static getInstance() method which returns the instance attribute.)
 *   Note: getPostDAO() method of the DAOFactory should return the PostDAOImpl object using
 *   getInstance() method of the PostDAOImpl class
 * TODO: 6.20. Define the following methods and return null for each of them. You will provide a
 *  proper implementation for each of these methods, later in this project.
 *  a. findByEmail()
 *  b. findByTag()
 *  c. findAllTags()
 *  d. findById()
 *  e. deleteById() (return false for this method for now)
 * TODO: 6.21. create() method should take post details as input and insert these details into
 *  the UBLOG_POSTS table. Return the same PostDTO object which was passed as an input argument.
 *  (Hint: You should get the connection using the DatabaseConnection class)
 */

/**
 * TODO: 7.1. Implement findByEmail() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the UBLOG_POSTS table defined
 *  in the database.
 */

/**
 * TODO: 7.13. Implement the deleteById() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false.
 * TODO: 7.14. Implement the findById() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return an PostDTO object
 *  without setting any of its attributes.
 */

import com.upgrad.ublog.db.DatabaseConnection;
import com.upgrad.ublog.dto.PostDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl {

    private static PostDAOImpl instance;

    public PostDAOImpl() {}

    public static PostDAOImpl getInstance() {
        if (instance == null) {
            instance = new PostDAOImpl();
        }
        return  instance;
    }

    public PostDTO create(PostDTO post) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO UBLOG_POSTS (email_id, title,description,tag,timestamp) VALUES (" +
                post.getEmailId() + ", '" +
                post.getTitle() + "', " +
                post.getDescription() + ", '" +
                post.getTag() + "', " +
                post.getTimestamp()+
                ")";
        statement.executeUpdate(sql);
        return post;
    }

    public List<PostDTO> findByEmail (String emailId) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM UBLOG_POSTS WHERE email_id = " + emailId;
        ResultSet resultSet = statement.executeQuery(sql);

        List<PostDTO> posts = new ArrayList<>();
        if (resultSet.next()) {
            PostDTO post = new PostDTO();
            post.setPostId(resultSet.getInt("id"));
            post.setEmailId(resultSet.getString("email_id"));
            post.setTitle(resultSet.getString("title"));
            post.setDescription(resultSet.getString("description"));
            post.setTag(resultSet.getString("tag"));
            post.setTimestamp(resultSet.getString("timestamp"));
            posts.add(post);
        } else {
            return null;
        }
        return posts;
    }
    public String findByTag () { return null; }
    public String findAllTags () { return null; }

    public PostDTO findById (int id) throws SQLException{
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM UBLOG_POSTS WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        PostDTO posts = new PostDTO();
        if (resultSet.next()) {
            PostDTO post = new PostDTO();
            post.setPostId(resultSet.getInt("id"));
            post.setEmailId(resultSet.getString("email_id"));
            post.setTitle(resultSet.getString("title"));
            post.setDescription(resultSet.getString("description"));
            post.setTag(resultSet.getString("tag"));
            post.setTimestamp(resultSet.getString("timestamp"));
        } else {
            return null;
        }
        return posts;
    }

    public boolean deleteById (int id) throws SQLException
    {
        Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "DELETE * FROM UBLOG_POSTS WHERE id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        PostDTO posts = new PostDTO();
        if (resultSet.next())
        {
            PostDTO post = new PostDTO();
            post.setPostId(resultSet.getInt("id"));
            post.setEmailId(resultSet.getString("email_id"));
            post.setTitle(resultSet.getString("title"));
            post.setDescription(resultSet.getString("description"));
            post.setTag(resultSet.getString("tag"));
            post.setTimestamp(resultSet.getString("timestamp"));
        } else {
            return true ;
        }
        return false;
    }
}
