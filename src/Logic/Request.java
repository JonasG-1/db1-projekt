package Logic;

import DAO.Factory.ConnectionFactory;
import DAO.Factory.OracleConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Request {

    ConnectionFactory connectionFactory;

    public Request(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public String startRequest() {
        try (Connection connection = connectionFactory.createConnection()) {

        } catch (SQLException e) {
            System.out.println("[ConnectionFactory] Failed to establish connection: \n" + e.getMessage());
        }

        return "";
    }
}
