package sdv.db;

// Java Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

// Other Imports
import conf.Configuration;
import sdv.conf.DBConf;
import shared.util.ConfFileParser;
import shared.util.Log;

/**
 * The GameDB class configures the database connection by using information
 * parsed from the configuration file.
 */
public class GameDB {

    // Singleton Instance
    private static GameDB gameDB;
    // GameDB Variables
    private final DBConf configuration = new DBConf();
    private static DataSource dataSource;

    /**
     * Configures the database connection.
     */
    public GameDB() {
        // Load the configuration file into memory
        configure();
        // Create a connection to the database
        String connectURI = String.format(
                "jdbc:mysql://%s/%s?user=%s&password=%s",
                Configuration.DBURL, Configuration.DBName,
                Configuration.DBUsername, Configuration.DBPassword
        );
        
        Log.println("\nRemote DB URL: " + connectURI);
        
        dataSource = ConnectionPool.setupDataSource(connectURI);
        
        if(dataSource != null) {
        		Log.println("\nConnection to DB successful");
        } else {
        		Log.println("\nConnection to DB not successful");
        }
    }

    /**
     * Parses the database configuration file and store those values into
     * memory.
     */
    private void configure() {
        // ConfFileParser confFileParser = new ConfFileParser("conf/db.conf");
        // configuration.setConfRecords(confFileParser.parse());
    }
    
    /**
     * Retrieve the data source for database access.
     *
     * @return the data source
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Retrieve a connection from data source for database access.
     *
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (gameDB == null) {
            gameDB = new GameDB();
        }

        return dataSource.getConnection();
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                Log.println_e(ex.getMessage());
            }
        }
    }

    public static void closeConnection(Connection con, PreparedStatement pstmt) {
        GameDB.closeConnection(con, pstmt, null);
    }
}
