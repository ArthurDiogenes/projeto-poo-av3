import java.io.*;
import java.util.ArrayList;

public class Item {

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

    public Item(int codigo, String descricao, double valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Item{" +
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
    public static Item fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        int codigo = Integer.parseInt(campo[0]);
        String descricao = campo[1];
        double valor = Double.parseDouble(campo[2]);
        return new Item(codigo, descricao, valor);
    }

    // CADASTRAR
    public static boolean cadastrarItem(Item item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("itens.txt", true))) {
            writer.write(item.toFileString());
            writer.newLine();
            System.out.println("Item -" + item.getDescricao() + "- cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR (pelo cpf)
    public static Item consultarItem(int codigo) {

        try (BufferedReader reader = new BufferedReader(new FileReader("itens.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Item item = fromFileString(linha);
                if (item.getCodigo() == codigo) {
                    System.out.println("Item consultado:");
                    System.out.println("Código: " + item.getCodigo());
                    System.out.println("Descrição: " + item.getDescricao());
                    System.out.println("Valor: " + item.getValor());
                    System.out.println("-------------------");
                    return item;
                } else {
                    System.out.println("Item não corresponde.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Item> listarItens() {
        ArrayList<Item> itens = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("itens.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Item item = fromFileString(linha);
                itens.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de itens:");
        for (Item h : itens) {
            System.out.println("Código: " + h.getCodigo());
            System.out.println("Descrição: " + h.getDescricao());
            System.out.println("Valor: " + h.getValor());
            System.out.println("-------------------");
        }
        return itens;
    }

    // EDITAR
    public static boolean editarItens(int codigo, String novaDescricao, double novoValor) {
        File inputFile = new File("itens.txt");
        File tempFile = new File("itens_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                Item item = fromFileString(linha);

                if (item.getCodigo() == codigo) {

                    encontrado = true;
                    item.setDescricao(novaDescricao);
                    item.setValor(novoValor);

                    writer.write(item.toFileString());

                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Item com o código " + codigo + " não encontrado.");
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

            System.out.println("Item atualizado.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
