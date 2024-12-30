import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class JanelaComGrid3x1 extends JFrame {
    private JLabel Nome, Marca, Categoria, Quantidade;
    private JTextField tNome, tMarca, tCategoria, tQuantidade;
    private JButton Sair, Visualizar, Limpar, Salvar;

    private void exibirTabelaCMD() {
        String sql = "SELECT * FROM Cadastro_de_Produtos";

        try (Connection con = getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nTabela: Cadastro_de_Produtos");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-15s | %-15s | %-10s |\n",
                    "ID", "Nome", "Marca", "Categoria", "Quantidade");
            System.out.println("---------------------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                String marca = rs.getString("Marca");
                String categoria = rs.getString("Categoria");
                int quantidade = rs.getInt("Quantidade");

                System.out.printf("| %-5d | %-20s | %-15s | %-15s | %-10d |\n",
                        id, nome, marca, categoria, quantidade);
            }
            System.out.println("---------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.err.println("Erro ao exibir a tabela: " + e.getMessage());
        }
    }

    public JanelaComGrid3x1() {
        super("Sistema de Controle de Estoque");

        // Inicializando componentes
        Nome = new JLabel("Nome:");
        Marca = new JLabel("Marca:");
        Categoria = new JLabel("Categoria:");
        Quantidade = new JLabel("Quantidade:");

        tNome = new JTextField(15);
        tMarca = new JTextField(15);
        tCategoria = new JTextField(15);
        tQuantidade = new JTextField(15);

        Sair = new JButton("Sair");
        Visualizar = new JButton("Visualizar");
        Limpar = new JButton("Limpar");
        Salvar = new JButton("Salvar");

        // Configurando o layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adicionando os componentes à janela
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(Nome, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(Marca, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(tMarca, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(Categoria, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(tCategoria, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(Quantidade, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(tQuantidade, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(Salvar, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(Limpar, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        add(Visualizar, gbc);

        gbc.gridx = 3;
        gbc.gridy = 4;
        add(Sair, gbc);

        // Adicionando ações aos botões
        Salvar.addActionListener(e -> salvarDados());
        Visualizar.addActionListener(e -> exibirTabelaCMD());
        Sair.addActionListener(e -> System.exit(0));
        Limpar.addActionListener(e -> limparCampos());

        // Configurações da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void limparCampos() {
        tNome.setText("");
        tCategoria.setText("");
        tMarca.setText("");
        tQuantidade.setText("");
    }

    private void salvarDados() {
        String Nome = tNome.getText();
        String Marca = tMarca.getText();
        String Categoria = tCategoria.getText();
        String quantidadeText = tQuantidade.getText();
        int Quantidade;

        try {
            Quantidade = Integer.parseInt(quantidadeText); // Verifica se é número
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Quantidade deve ser um número!");
            return;
        }

        String sql = "INSERT INTO Cadastro_de_Produtos (nome, marca, categoria, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, Nome);
            pstmt.setString(2, Marca);
            pstmt.setString(3, Categoria);
            pstmt.setInt(4, Quantidade);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao salvar os dados!");
        }
    }

    private void listarTabelas() {
        StringBuilder tabelas = new StringBuilder("Tabelas existentes no banco de dados:\n");
        try (Connection conexao = getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES")) {
            while (rs.next()) {
                tabelas.append("- ").append(rs.getString(1)).append("\n");
            }
            JOptionPane.showMessageDialog(this, tabelas.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao listar tabelas: " + e.getMessage());
        }
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/controle_estoque"; // Atualize para o seu banco
        String usuario = "root"; // Atualize para o seu usuário
        String senha = "12345"; // Atualize para sua senha
        return DriverManager.getConnection(url, usuario, senha);
    }

    public static void main(String[] args) {
        new JanelaComGrid3x1();
    }
}
