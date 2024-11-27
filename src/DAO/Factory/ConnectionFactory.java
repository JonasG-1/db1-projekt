package DAO.Factory;

import java.sql.Connection;

public abstract class ConnectionFactory {

    public Connection createConnection() {
        return createGetConnectionObject();
    }

    protected abstract Connection createGetConnectionObject();
}
