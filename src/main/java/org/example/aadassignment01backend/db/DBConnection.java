package org.example.aadassignment01backend.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);
    private Connection connection;
    private static DBConnection dbConnection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:/comp/env/jdbc/pos");
            this.connection = pool.getConnection();
        } catch (NamingException e) {
            logger.error("Failed to get the DataSource", e);
            throw new SQLException("Failed to get the DataSource", e);
        }
    }

    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
