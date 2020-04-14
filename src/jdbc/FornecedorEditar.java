package jdbc;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FornecedorEditar {

    public static void main(String[] args) throws SQLException {
        String sqlSeleciona = "select * from fornecedores where upper(name) like upper(?)";
        String sqlAtualiza = "update fornecedores set name = ? where id = ?";

        String nome = JOptionPane.showInputDialog("Nome");
        if (nome != null && !nome.trim().isEmpty()) {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement(sqlSeleciona);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            int id = 0;
            while (rs.next()) {
                id = rs.getInt("id");

                nome = JOptionPane.showInputDialog(String.format("Substitua '%s' por: ", rs.getString("name")));

                if (nome != null && !nome.trim().isEmpty()) {
                    ps = conexao.prepareStatement(sqlAtualiza);
                    ps.setString(1, nome);
                    ps.setInt(2, id);
                    ps.execute();
                    System.out.println("Fornecedor atualizado com sucesso!");
                } else {
                    System.out.println("O nome não foi informado.");
                }
            }
            ps.close();
            conexao.close();
        } else {
            System.out.println("Nome inválido para pesquisa.");
        }
    }
}
