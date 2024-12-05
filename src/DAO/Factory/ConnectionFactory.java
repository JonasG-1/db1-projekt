package DAO.Factory;

import java.io.Closeable;
import java.sql.Connection;

public abstract class ConnectionFactory {

    public Connection createConnection() {
        return createGetConnectionObject();
    }

    public boolean closeConnection(Connection connection) {
        return closeConnectionObject(connection);
    }

    protected abstract Connection createGetConnectionObject();
    protected abstract boolean closeConnectionObject(Connection connection);
}
