package Logic;

import DAO.Factory.OracleConnectionFactory;
import View.ProjectView;

import javax.swing.*;
import java.sql.Connection;

public class BusinessLogic {
    public static void main(String[] args) {

        OracleConnectionFactory connectionFactory = new OracleConnectionFactory();
        Connection connection = connectionFactory.createConnection();

        SwingUtilities.invokeLater(() -> {
            ProjectView view = new ProjectView();
            view.setVisible(true);
        });


    }
}
