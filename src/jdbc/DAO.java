package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

    private Connection conexao;

    public int incluir(String sql, Object... atributos) {
        try {
            PreparedStatement ps = getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            adicionarAtributos(ps, atributos);
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            getConexao().close();
        } catch (SQLException e) {
        } finally {
            conexao = null;
        }
    }

    private void adicionarAtributos(PreparedStatement ps, Object[] atributos) throws SQLException {
        int i = 1;
        for (Object atributo : atributos) {
            if (atributo instanceof String) {
                ps.setString(i, (String) atributo);
            } else if (atributo instanceof Integer) {
                ps.setInt(i, (Integer) atributo);
            }
            i++;
        }
    }

    private Connection getConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                return conexao;
            }
        } catch (SQLException e) {
        }
        conexao = FabricaConexao.getConexao();
        return conexao;
    }
}
