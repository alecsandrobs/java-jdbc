package jdbc;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FornecedorCadastrar {
    public static void main(String[] args) throws SQLException {
        String sql = "INSERT INTO fornecedores (name) VALUES(?)";

        String nome = JOptionPane.showInputDialog("Nome");

        if (nome != null && !nome.trim().isEmpty()) {
            DAO dao = new DAO();
            int id = dao.incluir(sql, nome.trim());
            System.out.println(id != -1 ? "Fornecedor cadastrado com sucesso." : "Não foi possível cadastrar o fornecedor.");
        } else {
            System.out.println("O nome não foi informado.");
        }
    }
}
