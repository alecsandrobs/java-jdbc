package jdbc;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FornecedorExcluir {

    public static void main(String[] args) throws SQLException {
        String sql = "delete from fornecedores where upper(name) like upper(?)";

        String nome = JOptionPane.showInputDialog("Nome");

        if (nome != null && !nome.trim().isEmpty()) {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            int quantidade = ps.executeUpdate();
            System.out.println(String.format("Linhas afetadas: %d", quantidade));
            ps.close();
            conexao.close();
        } else {
            System.out.println("Nome inválido para pesquisa.");
        }
    }
}
