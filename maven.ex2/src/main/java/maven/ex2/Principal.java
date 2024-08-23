package maven.ex2;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.conectar();

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 5) {
            System.out.println("Menu:");
            System.out.println("1) Listar");
            System.out.println("2) Inserir");
            System.out.println("3) Excluir");
            System.out.println("4) Atualizar");
            System.out.println("5) Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (option) {
                case 1:
                    System.out.println("Listando itens...");
                    // Implementação de listagem dos itens
                    listarPessoas(dao);
                    break;
                case 2:
                    System.out.println("Inserindo item...");
                    // Implementação de inserção de novo item
                    inserirPessoa(scanner, dao);
                    break;
                case 3:
                    System.out.println("Excluindo item...");
                    // Implementação de exclusão de item
                    excluirPessoa(scanner, dao);
                    break;
                case 4:
                    System.out.println("Atualizando item...");
                    // Implementação de atualização de item
                    atualizarPessoa(scanner, dao);
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 5.");
                    break;
            }
        }

        dao.close();
        scanner.close();
    }

    private static void listarPessoas(DAO dao) {
        try {
            Statement st = dao.conexao.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pessoa");
            while (rs.next()) {
                System.out.println("Código: " + rs.getInt("codigo") + ", Nome: " + rs.getString("nome") + ", Idade: " + rs.getInt("idade"));
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
        }
    }

    private static void inserirPessoa(Scanner scanner, DAO dao) {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o código: ");
        int codigo = scanner.nextInt();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();

        Pessoa pessoa = new Pessoa(nome, codigo, idade);
        if (dao.inserirUsuario(pessoa)) {
            System.out.println("Pessoa inserida com sucesso!");
        } else {
            System.out.println("Erro ao inserir pessoa.");
        }
    }

    private static void excluirPessoa(Scanner scanner, DAO dao) {
        System.out.print("Digite o código da pessoa a ser excluída: ");
        int codigo = scanner.nextInt();

        try {
            Statement st = dao.conexao.createStatement();
            int rows = st.executeUpdate("DELETE FROM pessoa WHERE codigo = " + codigo);
            if (rows > 0) {
                System.out.println("Pessoa excluída com sucesso!");
            } else {
                System.out.println("Pessoa não encontrada.");
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao excluir pessoa: " + e.getMessage());
        }
    }

    private static void atualizarPessoa(Scanner scanner, DAO dao) {
        System.out.print("Digite o código da pessoa a ser atualizada: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite a nova idade: ");
        int idade = scanner.nextInt();

        Pessoa pessoa = new Pessoa(nome, codigo, idade);
        if (dao.atualizarUsuario(pessoa)) {
            System.out.println("Pessoa atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar pessoa.");
        }
    }
}
