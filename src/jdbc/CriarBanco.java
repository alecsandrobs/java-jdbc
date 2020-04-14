package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarBanco {

    public static void main(String[] args) throws SQLException {
        Connection conexao = FabricaConexao.getConexao();
        System.out.println("Conexção realizada com sucesso!");

        Statement st = conexao.createStatement();

        st.execute("CREATE DATABASE IF NOT EXISTS contas");
        System.out.println("Banco criado com sucesso!");

        conexao.close();
    }
}
