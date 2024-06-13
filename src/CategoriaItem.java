import java.io.*;
import java.util.ArrayList;

public class CategoriaItem{

    private int id;
    private Item item;
    private Categoria categoria;
    private int quantidade;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Item getItem() {

        return item;
    }
    public void setItem(Item item) {

        this.item = item;
    }
    public Categoria getCategoria() {

        return categoria;
    }
    public void setCategoria(Categoria categoria) {

        this.categoria = categoria;
    }
    public int getQuantidade() {

        return quantidade;
    }
    public void setQuantidade(int quantidade) {

        this.quantidade = quantidade;
    }

    public CategoriaItem(int id, Item item, Categoria categoria, int quantidade) {
        this.id = id;
        this.item = item;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "CategoriaItem{" +
                "id=" + id +
                ", item=" + item +
                ", categoria=" + categoria +
                ", quantidade=" + quantidade +
                '}';
    }

    //transformar objeto em string -- padronizar o armazenamento
    public String toFileString() {
        return getId() + "," + getItem().toFileString() + "," + getCategoria().toFileString() + "," + getQuantidade();
    }

    // Criar um objeto a partir de uma string -- facilitar edição e consulta
    public static CategoriaItem fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        int id = Integer.parseInt(campo[0]);
        Item item = Item.fromFileString(campo[1] + "," + campo[2] + "," + campo[3]);
        Categoria categoria = new Categoria(Integer.parseInt(campo[4]), campo[5], Double.parseDouble(campo[6]));
        int quantidade = Integer.parseInt(campo[7]);
        return new CategoriaItem(id, item, categoria, quantidade);
    }

    // CADASTRAR
    public static boolean cadastrarCategoriaItem(CategoriaItem categoriaItem) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("categoriaItem.txt", true))) {
            writer.write(categoriaItem.toFileString());
            writer.newLine();
            System.out.println("CategoriaItem -" + categoriaItem.getId() + "- cadastrada com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    // CONSULTAR
    public static CategoriaItem consultarCategoriaItem(int id) {

        try (BufferedReader reader = new BufferedReader(new FileReader("categoriaItem.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                CategoriaItem categoriaItem = fromFileString(linha);
                if (categoriaItem.getId() == id) {
                    System.out.println("Quarto consultado:");
                    System.out.println("Código: " + categoriaItem.getId());
                    System.out.println("Item: " + categoriaItem.getItem());
                    System.out.println("Categoria: " + categoriaItem.getCategoria());
                    System.out.println("Quantidade: " + categoriaItem.getQuantidade());
                    System.out.println("-------------------");
                    return categoriaItem;
                } else {
                    System.out.println("Não encontrado.");
                    System.out.println("-------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<CategoriaItem> listarCategoriaItem() {
        ArrayList<CategoriaItem> categoriasItem = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("categoriaItem.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                CategoriaItem categoriaItem = fromFileString(linha);
                categoriasItem.add(categoriaItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de quartos:");
        for (CategoriaItem h : categoriasItem) {
            System.out.println("Id: " + h.getId());
            System.out.println("item: " + h.getItem());
            System.out.println("Categoria: " + h.getCategoria());
            System.out.println("Quantidade: " + h.getQuantidade());
            System.out.println("-------------------");
        }
        return categoriasItem;
    }

    // EDITAR
    public static boolean editarCategoriaItem(int id, Item novoItem, Categoria novaCategoria, int novaQuantidade) {
        File inputFile = new File("categoriaItem.txt");
        File tempFile = new File("categoriaItem_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {

                CategoriaItem categoriaItem = fromFileString(linha);

                if (categoriaItem.getId() == id) {

                    encontrado = true;
                    categoriaItem.setItem(novoItem);
                    categoriaItem.setCategoria(novaCategoria);
                    categoriaItem.setQuantidade(novaQuantidade);

                    writer.write(categoriaItem.toFileString());

                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("CategoriaItem com o id " + id + " não encontrada.");
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

            System.out.println("CategoriaItem atualizado.");
            System.out.println("-----------------");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
