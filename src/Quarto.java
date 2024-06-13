import java.io.*;
import java.util.ArrayList;

public class Quarto {
    private int codigo;
    private Categoria categoria;
    private String status;

    public int getCodigo() {

        return codigo;
    }
    public void setCodigo(int codigo) {

        this.codigo = codigo;
    }
    public Categoria getCategoria() {

        return categoria;
    }
    public void setCategoria(Categoria categoria) {

        this.categoria = categoria;
    }
    public String getStatus() {

        return status;
    }
    public void setStatus(String status) {

        this.status = status;
    }

    public Quarto(int codigo, Categoria categoria, String status) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Quarto{" +
                "codigo=" + codigo +
                ", categoria=" + categoria +
                ", status='" + status + '\'' +
                '}';
    }

    //transformar objeto em string -- padronizar o armazenamento
    public String toFileString() {
        return getCodigo() + "," + getCategoria().toFileString() + "," + getStatus();
    }

    // Criar um objeto a partir de uma string -- facilitar edição e consulta
    private static Quarto fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        int codigo = Integer.parseInt(campo[0]);
        int catCodigo = Integer.parseInt(campo[1]);
        String catDescricao = campo[2];
        double catValor = Double.parseDouble(campo[3]);
        Categoria categoria = new Categoria(catCodigo, catDescricao, catValor);
        String status = campo[4];
        return new Quarto(codigo, categoria, status);
    }

    // CADASTRAR
    public static boolean cadastrarQuarto(Quarto quarto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("quartos.txt", true))) {
            writer.write(quarto.toFileString());
            writer.newLine();
            System.out.println("Quarto -" + quarto.getCodigo() + "- cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public static Quarto consultarQuarto(int codigo) {

        try (BufferedReader reader = new BufferedReader(new FileReader("quartos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Quarto quarto = fromFileString(linha);
                if (quarto.getCodigo() == codigo) {
                    System.out.println("Quarto consultado:");
                    System.out.println("Código: " + quarto.getCodigo());
                    System.out.println("Categoria: " + quarto.getCategoria());
                    System.out.println("Status: " + quarto.getStatus());
                    System.out.println("-------------------");
                    return quarto;
                } else {
                    System.out.println("Quarto não corresponde.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Quarto> listarQuartos() {
        ArrayList<Quarto> quartos = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("quartos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Quarto quarto = fromFileString(linha);
                quartos.add(quarto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de quartos:");
        for (Quarto h : quartos) {
            System.out.println("Código: " + h.getCodigo());
            System.out.println("Categoria: " + h.getCategoria());
            System.out.println("Status: " + h.getStatus());
            System.out.println("-------------------");
        }
        return quartos;
    }

    // EDITAR
    public static boolean editarQuarto(int codigo, Categoria novaCategoria, String novoStatus) {
        File inputFile = new File("quartos.txt");
        File tempFile = new File("quartos_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                Quarto quarto = fromFileString(linha);

                if (quarto.getCodigo() == codigo) {

                    encontrado = true;
                    quarto.setCategoria(novaCategoria);
                    quarto.setStatus(novoStatus);

                    writer.write(quarto.toFileString());

                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Categoria com o código " + codigo + " não encontrada.");
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

            System.out.println("Quarto atualizado.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
