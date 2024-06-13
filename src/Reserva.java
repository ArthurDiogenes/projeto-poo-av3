import java.io.*;
import java.util.ArrayList;

public class Reserva {

    private int codigo;
    private Hospede hospede;
    private Quarto quarto;
    private Funcionario funcionarioReserva;
    private Funcionario funcionarioFechamento;
    private int dataEntradaReserva;
    private int dataSaidaReserva;
    private int dataCheckin;
    private int dataCheckout;
    private double valorReserva;
    private double valorPago;


    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Hospede getHospede() {
        return hospede;
    }
    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }
    public Quarto getQuarto() {
        return quarto;
    }
    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }
    public Funcionario getFuncionarioReserva() {
        return funcionarioReserva;
    }
    public void setFuncionarioReserva(Funcionario funcionarioReserva) {
        this.funcionarioReserva = funcionarioReserva;
    }
    public Funcionario getFuncionarioFechamento() {
        return funcionarioFechamento;
    }
    public void setFuncionarioFechamento(Funcionario funcionarioFechamento) {
        this.funcionarioFechamento = funcionarioFechamento;
    }
    public int getDataEntradaReserva() {
        return dataEntradaReserva;
    }
    public void setDataEntradaReserva(int dataEntradaReserva) {
        this.dataEntradaReserva = dataEntradaReserva;
    }
    public int getDataSaidaReserva() {
        return dataSaidaReserva;
    }
    public void setDataSaidaReserva(int dataSaidaReserva) {
        this.dataSaidaReserva = dataSaidaReserva;
    }
    public int getDataCheckin() {
        return dataCheckin;
    }
    public void setDataCheckin(int dataCheckin) {
        this.dataCheckin = dataCheckin;
    }
    public int getDataCheckout() {
        return dataCheckout;
    }
    public void setDataCheckout(int dataCheckout) {
        this.dataCheckout = dataCheckout;
    }
    public double getValorReserva() {
        return valorReserva;
    }
    public void setValorReserva(double valorReserva) {
        this.valorReserva = valorReserva;
    }
    public double getValorPago() {
        return valorPago;
    }
    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }


    public Reserva(int codigo, Hospede hospede, Quarto quarto, Funcionario funcionarioReserva, Funcionario funcionarioFechamento, int dataEntradaReserva, int dataSaidaReserva, int dataCheckin, int dataCheckout, double valorReserva, double valorPago) {
        this.codigo = codigo;
        this.hospede = hospede;
        this.quarto = quarto;
        this.funcionarioReserva = funcionarioReserva;
        this.funcionarioFechamento = funcionarioFechamento;
        this.dataEntradaReserva = dataEntradaReserva;
        this.dataSaidaReserva = dataSaidaReserva;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.valorReserva = valorReserva;
        this.valorPago = valorPago;
    }

    private String toFileString() {
        return codigo + "," + hospede.getCpf() + "," + quarto.getCodigo() + "," +
                funcionarioReserva.getCpf() + "," + funcionarioFechamento.getCpf() + "," +
                dataEntradaReserva + "," + dataSaidaReserva + "," +
                dataCheckin + "," + dataCheckout + "," +
                valorReserva + "," + valorPago;
    }

    private static Reserva fromFileString(String fileString) {
        String[] campo = fileString.split(",");
        int codigo = Integer.parseInt(campo[0]);
        Hospede hospede = Hospede.consultarHospede(campo[1]); // Assumindo que esse método exista
        Quarto quarto = Quarto.consultarQuarto(Integer.parseInt(campo[2])); // Assumindo que esse método exista
        Funcionario funcionarioReserva = Funcionario.consultarFuncionario(campo[3]);
        Funcionario funcionarioFechamento = Funcionario.consultarFuncionario(campo[4]);
        int dataEntradaReserva = Integer.parseInt(campo[5]);
        int dataSaidaReserva = Integer.parseInt(campo[6]);
        int dataCheckin = Integer.parseInt(campo[7]);
        int dataCheckout = Integer.parseInt(campo[8]);
        double valorReserva = Double.parseDouble(campo[9]);
        double valorPago = Double.parseDouble(campo[10]);

        return new Reserva(codigo, hospede, quarto, funcionarioReserva, funcionarioFechamento, dataEntradaReserva, dataSaidaReserva, dataCheckin, dataCheckout, valorReserva, valorPago);
    }

    // CADASTRAR
    public static boolean cadastrarReserva(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservas.txt", true))) {
            writer.write(reserva.toFileString());
            writer.newLine();
            System.out.println("Reserva cadastrada com sucesso");
            System.out.println("-------------------");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // CONSULTAR
    public static Reserva consultarReserva(int codigo) {
        try (BufferedReader reader = new BufferedReader(new FileReader("reservas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Reserva reserva = fromFileString(linha);
                if (reserva.getCodigo() == codigo) {
                    System.out.println("Reserva consultada:");
                    System.out.println("Código: " + reserva.getCodigo());
                    System.out.println("Hospede: " + reserva.getHospede().getNome());
                    System.out.println("Quarto: " + reserva.getQuarto().getCodigo());
                    System.out.println("Funcionario Reserva: " + reserva.getFuncionarioReserva().getNome());
                    System.out.println("Funcionario Fechamento: " + reserva.getFuncionarioFechamento().getNome());
                    System.out.println("Data Entrada: " + reserva.getDataEntradaReserva());
                    System.out.println("Data Saida: " + reserva.getDataSaidaReserva());
                    System.out.println("Data Checkin: " + reserva.getDataCheckin());
                    System.out.println("Data Checkout: " + reserva.getDataCheckout());
                    System.out.println("Valor Reserva: " + reserva.getValorReserva());
                    System.out.println("Valor Pago: " + reserva.getValorPago());
                    System.out.println("-------------------");
                    return reserva;
                }
            }
            System.out.println("Reserva não encontrada.");
            System.out.println("-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // LISTAR
    public static ArrayList<Reserva> listarReservas() {
        ArrayList<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("reservas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Reserva reserva = fromFileString(linha);
                reservas.add(reserva);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lista de Reservas:");
        for (Reserva reserva : reservas) {
            System.out.println("Código: " + reserva.getCodigo());
            System.out.println("Hospede: " + reserva.getHospede().getNome());
            System.out.println("Quarto: " + reserva.getQuarto().getCodigo());
            System.out.println("Funcionario Reserva: " + reserva.getFuncionarioReserva().getNome());
            System.out.println("Funcionario Fechamento: " + reserva.getFuncionarioFechamento().getNome());
            System.out.println("Data Entrada: " + reserva.getDataEntradaReserva());
            System.out.println("Data Saida: " + reserva.getDataSaidaReserva());
            System.out.println("Data Checkin: " + reserva.getDataCheckin());
            System.out.println("Data Checkout: " + reserva.getDataCheckout());
            System.out.println("Valor Reserva: " + reserva.getValorReserva());
            System.out.println("Valor Pago: " + reserva.getValorPago());
            System.out.println("-------------------");
        }
        return reservas;
    }

    // EDITAR
    public static boolean editarReserva(int codigo, Hospede novoHospede, Quarto novoQuarto, Funcionario novoFuncionarioReserva, Funcionario novoFuncionarioFechamento, int novaDataEntradaReserva, int novaDataSaidaReserva, int novaDataCheckin, int novaDataCheckout, double novoValorReserva, double novoValorPago) {
        File inputFile = new File("reservas.txt");
        File tempFile = new File("reservas_temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String linha;
            boolean encontrado = false;

            while ((linha = reader.readLine()) != null) {
                Reserva reserva = fromFileString(linha);
                if (reserva.getCodigo() == codigo) {
                    encontrado = true;
                    reserva.setHospede(novoHospede);
                    reserva.setQuarto(novoQuarto);
                    reserva.setFuncionarioReserva(novoFuncionarioReserva);
                    reserva.setFuncionarioFechamento(novoFuncionarioFechamento);
                    reserva.setDataEntradaReserva(novaDataEntradaReserva);
                    reserva.setDataSaidaReserva(novaDataSaidaReserva);
                    reserva.setDataCheckin(novaDataCheckin);
                    reserva.setDataCheckout(novaDataCheckout);
                    reserva.setValorReserva(novoValorReserva);
                    reserva.setValorPago(novoValorPago);
                    writer.write(reserva.toFileString());
                } else {
                    writer.write(linha);
                }
                writer.newLine();
            }

            if (!encontrado) {
                System.out.println("Reserva com o código " + codigo + " não encontrada.");
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

        System.out.println("Reserva atualizada.");
        System.out.println("-----------------");
        return true;
    }

    // PAGAR
    public static void pagarReserva(int codigo) {
        Reserva reserva = consultarReserva(codigo);
        if (reserva != null) {
            reserva.setValorPago(reserva.getValorReserva());
            editarReserva(reserva.getCodigo(), reserva.getHospede(), reserva.getQuarto(), reserva.getFuncionarioReserva(), reserva.getFuncionarioFechamento(), reserva.getDataEntradaReserva(), reserva.getDataSaidaReserva(), reserva.getDataCheckin(), reserva.getDataCheckout(), reserva.getValorReserva(), reserva.getValorPago());
            System.out.println("Reserva paga com sucesso.");
        } else {
            System.out.println("Reserva não encontrada para pagamento.");
        }
    }
}
