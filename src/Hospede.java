import java.io.*;
import java.util.ArrayList;

public class Hospede extends Pessoa {

    private String enderecoCompleto;


    // get e set
    public String getEnderecoCompleto() {

        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {

        this.enderecoCompleto = enderecoCompleto;
    }

    // construtor
    public Hospede(String cpf, String nome, String email, String enderecoCompleto) {
        super(cpf, nome, email);
        this.enderecoCompleto = enderecoCompleto;
    }

    public String toString() {
        return "Hospede{" +
                "cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", enderecoCompleto='" + enderecoCompleto + '\'' +
                '}';
    }

    //transformar objeto em string -- padronizar o armazenamento
    private String toFileString() {

        return getCpf() + "," + getNome() + "," + getEmail() + "," + enderecoCompleto;
    }

    // criar um objeto a partir de uma string -- facilitar edicao e consulta
    private static Hospede fromFileString(String fileString) {
        String[] campo = fileString.split(",");
            return new Hospede(campo[0], campo[1], campo[2], campo[3]);
    }

    // CADASTRAR
    public static boolean cadastrarHospede(Hospede hospede) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hospedes.txt", true))) {
            writer.write(hospede.toFileString());
            writer.newLine();
            System.out.println("Hóspede -" + hospede.getNome() + "- cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR (pelo cpf)
    public static Hospede consultarHospede(String cpf) {

        try (BufferedReader reader = new BufferedReader(new FileReader("hospedes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Hospede hospede = fromFileString(linha);
                if (hospede.getCpf().equals(cpf)) {
                    System.out.println("Hóspede consultado:");
                    System.out.println("CPF: " + hospede.getCpf());
                    System.out.println("Nome: " + hospede.getNome());
                    System.out.println("Email: " + hospede.getEmail());
                    System.out.println("Endereço Completo: " + hospede.getEnderecoCompleto());
                    System.out.println("-------------------");
                    return hospede;
                } else {
                    System.out.println("Hóspede não encontrado.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Hospede> listarHospede() {
        ArrayList<Hospede> hospedes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("hospedes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Hospede hospede = fromFileString(linha);
                hospedes.add(hospede);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de Hóspedes:");
        for (Hospede h : hospedes) {
            System.out.println("CPF: " + h.getCpf());
            System.out.println("Nome: " + h.getNome());
            System.out.println("Email: " + h.getEmail());
            System.out.println("Endereço Completo: " + h.getEnderecoCompleto());
            System.out.println("-------------------");
        }
        return hospedes;
    }

    // EDITAR
    public static boolean editarHospede(String cpf, String novoNome, String novoEmail, String novoEndereco) {
        File inputFile = new File("hospedes.txt");
        File tempFile = new File("hospedes_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                Hospede hospede = fromFileString(linha);

                if (hospede.getCpf().equals(cpf)) {

                    encontrado = true;
                    hospede.setNome(novoNome);
                    hospede.setEmail(novoEmail);
                    hospede.setEnderecoCompleto(novoEndereco);

                    writer.write(hospede.toFileString());

                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Hóspede com o CPF " + cpf + " não encontrado.");
                return false;
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Não foi possível deletar o arquivo");
                return false;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Não foi possível renomear o arquivo temp.");
                return false;
            }

            System.out.println("Hóspede atualizado.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
