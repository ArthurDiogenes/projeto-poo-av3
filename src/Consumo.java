import java.io.*;
import java.util.ArrayList;

public class Consumo {
    private Item item;
    private Categoria categoria;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private int dataConsumo;

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

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public int getDataConsumo() {
        return dataConsumo;
    }

    public void setDataConsumo(int dataConsumo) {
        this.dataConsumo = dataConsumo;
    }

    public Consumo(Item item, Categoria categoria, Reserva reserva, int quantidadeSolicitada, int dataConsumo) {
        this.item = item;
        this.categoria = categoria;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataConsumo = dataConsumo;
    }

    @Override
    public String toString() {
        return "Consumo{" +
                "item=" + item +
                ", categoria=" + categoria +
                ", reserva=" + reserva +
                ", quantidadeSolicitada=" + quantidadeSolicitada +
                ", dataConsumo=" + dataConsumo +
                '}';
    }

    private String toFileString() {
        return item.getCodigo() + "," + categoria.getCodigo() + "," + reserva.getCodigo() + "," +
                quantidadeSolicitada + "," + dataConsumo;
    }

    private static Consumo fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        Item item = Item.consultarItem(Integer.parseInt(campo[0])); // Assumindo que esse método exista
        Categoria categoria = Categoria.consultarCategoria(Integer.parseInt(campo[1])); // Assumindo que esse método exista
        Reserva reserva = Reserva.consultarReserva(Integer.parseInt(campo[2])); // Assumindo que esse método exista
        int quantidadeSolicitada = Integer.parseInt(campo[3]);
        int dataConsumo = Integer.parseInt(campo[4]);

        return new Consumo(item, categoria, reserva, quantidadeSolicitada, dataConsumo);
    }

    // CADASTRAR
    public static boolean cadastrarConsumo(Consumo consumo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("consumo.txt", true))) {
            writer.write(consumo.toFileString());
            writer.newLine();
            System.out.println("Consumo cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // CONSULTAR
    public static Consumo consultarConsumo(int itemCodigo, int reservaCodigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Consumo consumo = fromFileString(linha);
                if (consumo.getItem().getCodigo() == itemCodigo &&
                        consumo.getReserva().getCodigo() == reservaCodigo) {
                    System.out.println("Consumo consultado:");
                    System.out.println(consumo);
                    System.out.println("-------------------");
                    return consumo;
                }
            }
            System.out.println("Consumo não encontrado.");
            System.out.println("-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Consumo> listarConsumo() {
        ArrayList<Consumo> consumos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Consumo consumo = fromFileString(linha);
                consumos.add(consumo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de Consumos:");
        for (Consumo consumo : consumos) {
            System.out.println(consumo);
            System.out.println("-------------------");
        }
        return consumos;
    }

    // EDITAR
    public static boolean editarConsumo(int itemCodigo, int reservaCodigo, Categoria novaCategoria, int novaQuantidadeSolicitada, int novaDataConsumo) {
        File inputFile = new File("consumo.txt");
        File tempFile = new File("consumo_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {
                Consumo consumo = fromFileString(linha);
                if (consumo.getItem().getCodigo() == itemCodigo &&
                        consumo.getReserva().getCodigo() == reservaCodigo) {
                    encontrado = true;
                    consumo.setCategoria(novaCategoria);
                    consumo.setQuantidadeSolicitada(novaQuantidadeSolicitada);
                    consumo.setDataConsumo(novaDataConsumo);
                    writer.write(consumo.toFileString());
                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Consumo com o item " + itemCodigo + " e reserva " + reservaCodigo + " não encontrado.");
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            if (!inputFile.delete()) {
                System.out.println("Não foi possível deletar o arquivo original");
                return false;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Não foi possível renomear o arquivo temporário.");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        System.out.println("Consumo atualizado.");
        System.out.println("-----------------");
        return true;
    }
}
