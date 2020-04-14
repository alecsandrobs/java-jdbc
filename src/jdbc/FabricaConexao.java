package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {

    public static Connection getConexao() {
        String url = "jdbc:mysql://localhost:3306/curso_java?verifyServerCertificate=false&useSSL=true";
        String usuario = "root";
        String senha = "root";
        Properties prop = null;

        try {
            prop = getProperties();
            url = prop.getProperty("banco.url");
            usuario = prop.getProperty("banco.usuario");
            senha = prop.getProperty("banco.senha");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties getProperties() throws IOException {
        Properties properties = new Properties();
        String caminho = "src/conexao.properties";

        properties.load(new FileInputStream(caminho));
//        properties.load(FabricaConexao.class.getResourceAsStream(caminho));
        return properties;
    }
}
