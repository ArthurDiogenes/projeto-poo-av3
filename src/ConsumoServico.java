import java.io.*;
import java.util.ArrayList;

public class ConsumoServico {

    private Servico servico;
    private Categoria categoria;
    private Reserva reserva;
    private int quantidadeSolicitada;
    private int dataServico;

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
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

    public int getDataServico() {
        return dataServico;
    }

    public void setDataServico(int dataServico) {
        this.dataServico = dataServico;
    }

    // Construtor
    public ConsumoServico(Servico servico, Categoria categoria, Reserva reserva, int quantidadeSolicitada, int dataServico) {
        this.servico = servico;
        this.categoria = categoria;
        this.reserva = reserva;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.dataServico = dataServico;
    }

    @Override
    public String toString() {
        return "ConsumoServico{" +
                "servico=" + servico +
                ", categoria=" + categoria +
                ", reserva=" + reserva +
                ", quantidadeSolicitada=" + quantidadeSolicitada +
                ", dataServico=" + dataServico +
                '}';
    }

    private String toFileString() {
        return servico.getCodigo() + "," + categoria.getCodigo() + "," + reserva.getCodigo() + "," +
                quantidadeSolicitada + "," + dataServico;
    }

    private static ConsumoServico fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        Servico servico = Servico.consultarServico(Integer.parseInt(campo[0])); // Assumindo que esse método exista
        Categoria categoria = Categoria.consultarCategoria(Integer.parseInt(campo[1])); // Assumindo que esse método exista
        Reserva reserva = Reserva.consultarReserva(Integer.parseInt(campo[2])); // Assumindo que esse método exista
        int quantidadeSolicitada = Integer.parseInt(campo[3]);
        int dataServico = Integer.parseInt(campo[4]);

        return new ConsumoServico(servico, categoria, reserva, quantidadeSolicitada, dataServico);
    }

    // CADASTRAR
    public static boolean cadastrarConsumoServico(ConsumoServico consumoServico) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("consumo_servico.txt", true))) {
            writer.write(consumoServico.toFileString());
            writer.newLine();
            System.out.println("Consumo de serviço cadastrado com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // CONSULTAR
    public static ConsumoServico consultarConsumoServico(int servicoCodigo, int reservaCodigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo_servico.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                ConsumoServico consumoServico = fromFileString(linha);
                if (consumoServico.getServico().getCodigo() == servicoCodigo &&
                        consumoServico.getReserva().getCodigo() == reservaCodigo) {
                    System.out.println("Consumo de serviço consultado:");
                    System.out.println(consumoServico);
                    System.out.println("-------------------");
                    return consumoServico;
                }
            }
            System.out.println("Consumo de serviço não encontrado.");
            System.out.println("-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<ConsumoServico> listarConsumoServico() {
        ArrayList<ConsumoServico> consumos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("consumo_servico.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                ConsumoServico consumoServico = fromFileString(linha);
                consumos.add(consumoServico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de Consumos de Serviços:");
        for (ConsumoServico consumoServico : consumos) {
            System.out.println(consumoServico);
            System.out.println("-------------------");
        }
        return consumos;
    }

    // EDITAR
    public static boolean editarConsumoServico(int servicoCodigo, int reservaCodigo, Categoria novaCategoria, int novaQuantidadeSolicitada, int novaDataServico) {
        File inputFile = new File("consumo_servico.txt");
        File tempFile = new File("consumo_servico_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {
                ConsumoServico consumoServico = fromFileString(linha);
                if (consumoServico.getServico().getCodigo() == servicoCodigo &&
                        consumoServico.getReserva().getCodigo() == reservaCodigo) {
                    encontrado = true;
                    consumoServico.setCategoria(novaCategoria);
                    consumoServico.setQuantidadeSolicitada(novaQuantidadeSolicitada);
                    consumoServico.setDataServico(novaDataServico);
                    writer.write(consumoServico.toFileString());
                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Consumo de serviço com o serviço " + servicoCodigo + " e reserva " + reservaCodigo + " não encontrado.");
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

        System.out.println("Consumo de serviço atualizado.");
        System.out.println("-----------------");
        return true;
    }
}
