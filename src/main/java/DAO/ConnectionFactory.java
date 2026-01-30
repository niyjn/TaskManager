package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    public static Connection getConnection() {
        Properties props = new Properties();

        try (InputStream input = ConnectionFactory.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException("Arquivo database.properties nao encontrado em src/main/resources");
            }

            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler database.properties", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco de dados", e);
        }
    }
}