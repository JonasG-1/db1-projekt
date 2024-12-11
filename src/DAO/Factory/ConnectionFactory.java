package DAO.Factory;

import java.sql.Connection;

/**
 * Autoren:
 *
 * @author Jonas Goldbach, Matrikelnummer: 7217641
 * @author Jan Schulze, Matrikelnummer: 7217725
 */
public abstract class ConnectionFactory {

    public Connection createConnection() {
        return createGetConnectionObject();
    }

    protected abstract Connection createGetConnectionObject();

}
