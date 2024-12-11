package DAO.Factory;

import java.io.Closeable;
import java.sql.Connection;

/**
 * Autoren:
 *
 * Jonas Goldbach, Matrikelnummer: 7217641
 * Jan Schulze, Matrikelnummer: 7217725
 */
public abstract class ConnectionFactory {

    public Connection createConnection() {
        return createGetConnectionObject();
    }

    protected abstract Connection createGetConnectionObject();

}
