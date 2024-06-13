import java.io.*;
import java.util.ArrayList;

public class Categoria {

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

    public Categoria(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String toString() {
        return "Categoria{" +
                "codigo='" + getCodigo() + '\'' +
                ", descricao='" + getDescricao() + '\'' +
                ", valor='" + getValor() + '\'' +
                '}';
    }

    //transformar objeto em string -- padronizar o armazenamento
    public String toFileString() {

        return getCodigo() + "," + getDescricao() + "," + getValor();
    }

    // criar um objeto a partir de uma string -- facilitar edição e consulta
    private static Categoria fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        int codigo = Integer.parseInt(campo[0]);
        String descricao = campo[1];
        double valor = Double.parseDouble(campo[2]);
        return new Categoria(codigo, descricao, valor);
    }

    // CADASTRAR
    public static boolean cadastrarCategoria(Categoria categoria) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categorias.txt", true))) {
            writer.write(categoria.toFileString());
            writer.newLine();
            System.out.println("Categoria -" + categoria.getDescricao() + "- cadastrada com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar categoria: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR (pelo cpf)
    public static Categoria consultarCategoria(int codigo) {

        try (BufferedReader reader = new BufferedReader(new FileReader("categorias.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Categoria categoria = fromFileString(linha);
                if (categoria.getCodigo() == codigo) {
                    System.out.println("Categoria consultada:");
                    System.out.println("Código: " + categoria.getCodigo());
                    System.out.println("Descrição: " + categoria.getDescricao());
                    System.out.println("Valor: " + categoria.getValor());
                    System.out.println("-------------------");
                    return categoria;
                } else {
                    System.out.println("Categoria não corresponde.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Categoria> listarCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("categorias.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Categoria categoria = fromFileString(linha);
                categorias.add(categoria);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de Categorias:");
        for (Categoria h : categorias) {
            System.out.println("Código: " + h.getCodigo());
            System.out.println("Descrição: " + h.getDescricao());
            System.out.println("Valor: " + h.getValor());
            System.out.println("-------------------");
        }
        return categorias;
    }

    // EDITAR
    public static boolean editarCategoria(int codigo, String novaDescricao, double novoValor) {
        File inputFile = new File("categorias.txt");
        File tempFile = new File("categorias_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                Categoria categoria = fromFileString(linha);

                if (categoria.getCodigo() == codigo) {

                    encontrado = true;
                    categoria.setDescricao(novaDescricao);
                    categoria.setValor(novoValor);

                    writer.write(categoria.toFileString());

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

            System.out.println("Categoria atualizada.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
