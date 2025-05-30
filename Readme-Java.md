Detalhamento do Código Java:

•  A estrutura Principal é a janela principal do aplicativo usando a classe JanelaComGrid3x1, que herda de JFrame. Ela contém:

- Rótulos (JLabel) para os detalhes do produto (Nome, Marca, Categoria, Quantidade).
Campos de texto (JTextField) para a entrada de dados do usuário.

- Botões (JButton) para as ações de Salvar, Visualizar, Limpar e Sair.

•  Funcionalidades Chave:

- exibirTabelaCMD(): Consulta e exibe os dados dos produtos da tabela Cadastro_de_Produtos diretamente no console.

- JanelaComGrid3x1() (Construtor): Inicializa os componentes da GUI, organiza-os usando GridBagLayout e atribui as ações aos botões.

- salvarDados(): Pega os dados dos campos de entrada, valida a "Quantidade" como um número inteiro e insere o produto no banco de dados usando uma instrução SQL preparada.

- limparCampos(): Limpa todos os campos de texto da entrada.

- getConnection(): Estabelece a conexão com o banco de dados MySQL. Importante: As credenciais do banco de dados (URL, usuário, senha) neste método precisam ser atualizadas para as suas configurações reais.

- main(): O ponto de entrada da aplicação, que cria e exibe a janela principal.

•  Dependências para que este código funcione, você precisará de:

- JDK (Java Development Kit).

- Driver JDBC para MySQL (um arquivo .jar, como mysql-connector-java-x.x.x.jar, que deve ser adicionado ao classpath do seu projeto).

- Um servidor MySQL em execução com um banco de dados chamado controle_estoque e uma tabela chamada Cadastro_de_Produtos (com as colunas ID (auto-incremento, chave primária), Nome, Marca, Categoria e Quantidade).