package uts.isd.model.dao;

import uts.isd.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDBManager extends DBManager<User> {
    public int getUserCount() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");
        resultSet.next();
        return resultSet.getInt(1);
    }
    public UserDBManager(Connection connection) throws SQLException {
        super(connection);
    }

    // CREATE
    public User add(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO USERS (Name, Email, Password, DOB, Gender) VALUES (?, ?, ?, ?, ?)"
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getDob());
        preparedStatement.setString(5, user.getGender());
        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("SELECT MAX(Id) FROM USERS");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int userId = resultSet.getInt(1);
        user.setId(userId);
        return user;
    }

    // READ
    public User get(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE Id = ?");
        preparedStatement.setInt(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("Id"),
                    resultSet.getString("Name"),
                    resultSet.getString("Email"),
                    resultSet.getString("Password"),
                    resultSet.getString("DOB"),
                    resultSet.getString("Gender")
            );
        }
        return null;
    }

    // UPDATE
    public void update(User oldUser, User newUser) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE USERS SET Name = ?, Email = ?, Password = ?, DOB = ?, Gender = ? WHERE Id = ?"
        );
        preparedStatement.setString(1, newUser.getName());
        preparedStatement.setString(2, newUser.getEmail());
        preparedStatement.setString(3, newUser.getPassword());
        preparedStatement.setString(4, newUser.getDob());
        preparedStatement.setString(5, newUser.getGender());
        preparedStatement.setInt(6, oldUser.getId());
        preparedStatement.executeUpdate();
    }

    // DELETE
    public void delete(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE Id = ?");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }
}
