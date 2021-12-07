package com.banking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Hannah ORourke
 */
public class Configuration {

    private static String FILE_LOCATION = "src/main/resources/config.properties";

    private String dbDriver;
    private String dbConnection;
    private String dbUser;
    private String dbPassword;
    private String environment;

    public Configuration() {
        load();
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getDbConnection() {
        return dbConnection;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public boolean isTesting() {
        return environment.equals("testing") ? true:false;
    }

    private void load() {
        try {
            Properties properties = new Properties();
            File file = new File(FILE_LOCATION);
            InputStream inputStream = new FileInputStream(file);

            properties.load(inputStream);
            inputStream.close();
            setProperties(properties);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setProperties(Properties properties) {
        dbDriver = properties.getProperty("DB_DRIVER");
        dbConnection = properties.getProperty("DB_CONNECTION");
        dbUser= properties.getProperty("DB_USER");
        dbPassword = properties.getProperty("DB_PASSWORD");
        environment = properties.getProperty("ENVIRONMENT");
    }
}
