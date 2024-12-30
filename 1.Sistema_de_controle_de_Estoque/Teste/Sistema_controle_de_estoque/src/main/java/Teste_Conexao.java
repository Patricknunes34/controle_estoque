import java.sql.*;

public class Teste_Conexao {
    public static void main(String[] args) {
        // Dados da conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/controle_estoque"; // Substitua "localhost" e "nome_do_banco"
        String usuario = "root"; // Substitua pelo seu usuário do banco de dados
        String senha = "12345"; // Substitua pela sua senha do banco de dados

        System.out.println("Conectando ao banco de dados e listando tabelas...");

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha);
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

            System.out.println("Tabelas existentes no banco de dados:");
            while (rs.next()) {
                String tabela = rs.getString(1); // Obtém o nome da tabela
                System.out.println("- " + tabela);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ou listar tabelas: " + e.getMessage());
        }
    }
}