import java.io.*;
import java.util.ArrayList;

public class Servico {

    private int codigo;
    private String descricao;
    private double valor;

    public int getCodigo() {

        return codigo;
    }
    public void setCodigo(int codigo) {

        this.codigo = codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }
    public double getValor() {

        return valor;
    }
    public void setValor(double valor) {

        this.valor = valor;
    }

    public Servico(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servico{" +
                "codigo=" + codigo +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }

    //transformar objeto em string -- padronizar o armazenamento
    public String toFileString() {

        return getCodigo() + "," + getDescricao() + "," + getValor();
    }

    // criar um objeto a partir de uma string -- facilitar edição e consulta
    public static Servico fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        int codigo = Integer.parseInt(campo[0]);
        String descricao = campo[1];
        double valor = Double.parseDouble(campo[2]);
        return new Servico(codigo, descricao, valor);
    }

    // CADASTRAR
    public static boolean cadastrarServico(Servico servico) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("servicos.txt", true))) {
            writer.write(servico.toFileString());
            writer.newLine();
            System.out.println("Serviço -" + servico.getDescricao() + "- cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public static Servico consultarServico(int codigo) {

        try (BufferedReader reader = new BufferedReader(new FileReader("servicos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Servico servico = fromFileString(linha);
                if (servico.getCodigo() == codigo) {
                    System.out.println("Serviço consultado:");
                    System.out.println("Código: " + servico.getCodigo());
                    System.out.println("Descrição: " + servico.getDescricao());
                    System.out.println("Valor: " + servico.getValor());
                    System.out.println("-------------------");
                    return servico;
                } else {
                    System.out.println("Serviço não corresponde.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Servico> listarServicos() {
        ArrayList<Servico> servicos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("servicos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Servico servico = fromFileString(linha);
                servicos.add(servico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de serviços:");
        for (Servico h : servicos) {
            System.out.println("Código: " + h.getCodigo());
            System.out.println("Descrição: " + h.getDescricao());
            System.out.println("Valor: " + h.getValor());
            System.out.println("-------------------");
        }
        return servicos;
    }

    // EDITAR
    public static boolean editarServicos(int codigo, String novaDescricao, double novoValor) {
        File inputFile = new File("servicos.txt");
        File tempFile = new File("servicos_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                Servico servico = fromFileString(linha);

                if (servico.getCodigo() == codigo) {

                    encontrado = true;
                    servico.setDescricao(novaDescricao);
                    servico.setValor(novoValor);

                    writer.write(servico.toFileString());

                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Serviço com o código " + codigo + " não encontrado.");
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

            System.out.println("Serviço atualizado.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
