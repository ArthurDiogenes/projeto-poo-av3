import java.io.*;
import java.util.ArrayList;

public class Funcionario extends Pessoa {

    private String setor;

    public String getSetor() {

        return setor;
    }
    public void setSetor(String setor) {

        this.setor = setor;
    }

    public Funcionario(String cpf, String nome, String email, String setor) {
        super(cpf, nome, email);
        this.setor = setor;
    }

    public String toString() {
        return "Funcionario{" +
                "cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", setor='" + setor + '\'' +
                '}';
    }

    //transformar objeto em string -- padronizar o armazenamento
    private String toFileString() {

        return getCpf() + "," + getNome() + "," + getEmail() + "," + setor;
    }

    // criar um objeto a partir de uma string -- facilitar edicao e consulta
    private static Funcionario fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        return new Funcionario(campo[0], campo[1], campo[2], campo[3]);
    }

    // CADASTRAR
    public static boolean cadastrarFuncionario(Funcionario funcionario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("funcionarios.txt", true))) {
            writer.write(funcionario.toFileString());
            writer.newLine();
            System.out.println("Funcionario(a) -" + funcionario.getNome() + "- cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR (pelo cpf)
    public static Funcionario consultarFuncionario(String cpf) {

        try (BufferedReader reader = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Funcionario funcionario = fromFileString(linha);
                if (funcionario.getCpf().equals(cpf)) {
                    System.out.println("Funcionario(a) consultado:");
                    System.out.println("CPF: " + funcionario.getCpf());
                    System.out.println("Nome: " + funcionario.getNome());
                    System.out.println("Email: " + funcionario.getEmail());
                    System.out.println("Setor: " + funcionario.getSetor());
                    System.out.println("-------------------");
                    return funcionario;
                } else {
                    System.out.println("Funcionario(a) não encontrado.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Funcionario> listarFuncionario() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("funcionarios.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Funcionario funcionario = fromFileString(linha);
                funcionarios.add(funcionario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de Funcionarios:");
        for (Funcionario h : funcionarios) {
            System.out.println("CPF: " + h.getCpf());
            System.out.println("Nome: " + h.getNome());
            System.out.println("Email: " + h.getEmail());
            System.out.println("Setor: " + h.getSetor());
            System.out.println("-------------------");
        }
        return funcionarios;
    }

    // EDITAR
    public static boolean editarFuncionario(String cpf, String novoNome, String novoEmail, String novoSetor) {
        File inputFile = new File("funcionarios.txt");
        File tempFile = new File("funcionarios_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                Funcionario funcionario = fromFileString(linha);

                if (funcionario.getCpf().equals(cpf)) {

                    encontrado = true;
                    funcionario.setNome(novoNome);
                    funcionario.setEmail(novoEmail);
                    funcionario.setSetor(novoSetor);

                    writer.write(funcionario.toFileString());

                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Funcionario(a) com o CPF " + cpf + " não encontrado(a).");
                return false;
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Não foi possível deletar o arquivo");
                return false;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Não foi possível renomear o arquivo 'temp'.");
                return false;
            }

            System.out.println("Funcionario(a) atualizado.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
