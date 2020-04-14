package jdbc;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorConsultar {

    public static void main(String[] args) throws SQLException {
        String sql = "select * from fornecedores where upper(name) like upper(?)";

        String nome = JOptionPane.showInputDialog("Pesquisar por: ");

        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, "%" + nome.trim() + "%");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(String.format("%d - %s", rs.getLong("id"), rs.getString("name")));
        }

        ps.close();
        conexao.close();
    }

}
