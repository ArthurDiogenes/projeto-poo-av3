import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // TESTES HÓSPEDE ----------------------------------------------------------
        //--------------------------------------------------------------------------
        Hospede arthur = new Hospede("02618369351", "Arthur Diogenes", "arthurdfleite@gmail.com", "Rua 8 de setembro 1130");
        Hospede john = new Hospede("01234567891", "John Smith", "johnsmith@gmail.com", "123av color st");
        //Hospede.cadastrarHospede(arthur);
        //Hospede.cadastrarHospede(john);
        //Hospede.consultarHospede("02618369351");
        //Hospede.listarHospede();
        //Hospede.editarHospede("01234567891", "Johnny Smith", "johnnysmith@gmail.com", "terraDoNunca");


        // TESTES FUNCIONARIO ---------------------------------------------------------
        //-----------------------------------------------------------------------------
        Funcionario elba = new Funcionario("02618369352", "Elba Cambraia", "elba.cambraia@gmail.com", "Recepção");
        Funcionario beatriz = new Funcionario("02618369353", "Beatriz Glória", "btrzglr@gmail.com", "Limpeza");
        //Funcionario.cadastrarFuncionario(Beatriz);
        //Funcionario.consultarFuncionario("02618369353");
        //Funcionario.listarFuncionario();
        //Funcionario.editarFuncionario("02618369353", "Beatriz Glória", "btrzglr@gmail.com", "Limpeza");

        // TESTES CATEGORIA ----------------------------------------------------------
        //----------------------------------------------------------------------------
        Categoria simples = new Categoria(1, "Simples", 100);
        Categoria luxo = new Categoria(2, "Luxo", 200);
        //Categoria.cadastrarCategoria(luxo);
        //Categoria.consultarCategoria(1);
        //Categoria.listarCategorias();
        //Categoria.editarCategoria(1, "Simples", 100);

        // TESTES QUARTO -------------------------------------------------------------
        //----------------------------------------------------------------------------
        Quarto q1 = new Quarto(01, simples, "Alugado");
        Quarto q2 = new Quarto(02, luxo, "Livre");
        //Quarto.cadastrarQuarto(q2);
        //Quarto.consultarQuarto(1);
        //Quarto.listarQuartos();
        //Quarto.editarQuarto(2, luxo, "Livre");

        // TESTES ITEM ---------------------------------------------------------------
        //----------------------------------------------------------------------------
        Item agua = new Item(1, "Água", 4);
        Item cerveja = new Item(2, "Cerveja", 12);
        //Item.cadastrarItem(cerveja);
        //Item.consultarItem(1);
        //Item.listarItens();
        //Item.editarItens(1, "Água", 4);

        //TESTES CATEGORIAITEM--------------------------------------------------------
        //----------------------------------------------------------------------------
        CategoriaItem ci1 = new CategoriaItem(1, agua, simples, 4);
        CategoriaItem ci2 = new CategoriaItem(2, cerveja, luxo, 37);
        //CategoriaItem.cadastrarCategoriaItem(ci2);
        //CategoriaItem.consultarCategoriaItem(2);
        //CategoriaItem.listarCategoriaItem();
        //CategoriaItem.editarCategoriaItem(1, agua, simples, 6);

        //TESTES SERVIÇOS-------------------------------------------------------------
        //----------------------------------------------------------------------------
        Servico limpeza = new Servico(1, "Limpeza", 150);
        Servico massagem = new Servico(2, "Massagem", 400);
        //Servico.cadastrarServico(massagem);
        //Servico.consultarServico(2);
        //Servico.listarServicos();
        //Servico.editarServicos(1,"Limpeza", 150);

        //TESTES RESERVA--------------------------------------------------------------
        //----------------------------------------------------------------------------
        Reserva reserva1 = new Reserva(1, arthur, q1, elba, beatriz, 20240101, 20240102, 20240101, 20240102, 1000, 0);
        Reserva reserva2 = new Reserva(1, john, q2, elba, beatriz, 20240101, 20240102, 20240101, 20240102, 1500, 0);
        Reserva.cadastrarReserva(reserva2);
        //Reserva.consultarReserva(1);
        //Reserva.listarReservas();
        //Reserva.editarReserva();
        //Reserva.pagarReserva(1);

        //TESTES CONSUMO_SERVICO-----------------------------------------------------
        //---------------------------------------------------------------------------
        ConsumoServico cs1 = new ConsumoServico(limpeza, simples, reserva1, 2, 20240101);
        ConsumoServico cs2 = new ConsumoServico(massagem, luxo, reserva2, 3, 20240101);
        //ConsumoServico.cadastrarConsumoServico(cs2);
        //ConsumoServico.consultarConsumoServico(1, 1);
        //ConsumoServico.listarConsumoServico();


        //SCANNER e MENU -------------------------------------------------
        //----------------------------------------------------------------
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu");
            System.out.println("1: Categoria");
            System.out.println("2: CategoriaItem");
            System.out.println("3: Consumo");
            System.out.println("4: ConsumoServico");
            System.out.println("5: Funcionario");
            System.out.println("6: Hospede");
            System.out.println("7: Item");
            System.out.println("8: Quarto");
            System.out.println("9: Reserva");
            System.out.println("10: Servico");
            System.out.println("11: Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    menuCategoria(scanner);
                    break;
                case 2:
                    menuCategoriaItem(scanner);
                    break;
                case 3:
                    menuConsumo(scanner);
                    break;
                case 4:
                    menuConsumoServico(scanner);
                    break;
                case 5:
                    menuFuncionario(scanner);
                    break;
                case 6:
                    menuHospede(scanner);
                    break;
                case 7:
                    menuItem(scanner);
                    break;
                case 8:
                    menuQuarto(scanner);
                    break;
                case 9:
                    menuReserva(scanner);
                    break;
                case 10:
                    menuServico(scanner);
                    break;
                case 11:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 11);

        scanner.close();
    }

    private static void menuCategoria(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Categoria");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    Categoria categoria = new Categoria(codigo, descricao, valor);
                    Categoria.cadastrarCategoria(categoria);
                    break;
                case 2:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    Categoria.consultarCategoria(codigo);
                    break;
                case 3:
                    Categoria.listarCategorias();
                    break;
                case 4:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nova Descrição: ");
                    descricao = scanner.nextLine();
                    System.out.print("Novo Valor: ");
                    valor = scanner.nextDouble();
                    Categoria.editarCategoria(codigo, descricao, valor);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuCategoriaItem(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu CategoriaItem");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("ID da CategoriaItem: ");
                    int id = scanner.nextInt();
                    System.out.print("Código do Item: ");
                    int itemCodigo = scanner.nextInt();
                    System.out.print("Código da Categoria: ");
                    int categoriaCodigo = scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    Item item = Item.consultarItem(itemCodigo);
                    Categoria categoria = Categoria.consultarCategoria(categoriaCodigo);
                    CategoriaItem categoriaItem = new CategoriaItem(id, item, categoria, quantidade);
                    CategoriaItem.cadastrarCategoriaItem(categoriaItem);
                    break;
                case 2:
                    System.out.print("ID CategoriaItem: ");
                    id = scanner.nextInt();
                    CategoriaItem.consultarCategoriaItem(id);
                    break;
                case 3:
                    CategoriaItem.listarCategoriaItem();
                    break;
                case 4:
                    System.out.println("O sistema ainda não consegue editar CategoriaItem pelo menu");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuConsumo(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Consumo");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código do Item: ");
                    int itemCodigo = scanner.nextInt();
                    System.out.print("Código da Categoria: ");
                    int categoriaCodigo = scanner.nextInt();
                    System.out.print("Código da Reserva: ");
                    int reservaCodigo = scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Data Consumo (yyyyMMdd): ");
                    int dataConsumo = scanner.nextInt();
                    Item item = Item.consultarItem(itemCodigo);
                    Categoria categoria = Categoria.consultarCategoria(categoriaCodigo);
                    Reserva reserva = Reserva.consultarReserva(reservaCodigo);
                    Consumo consumo = new Consumo(item, categoria, reserva, quantidade, dataConsumo);
                    Consumo.cadastrarConsumo(consumo);
                    break;
                case 2:
                    System.out.print("Código do Item: ");
                    itemCodigo = scanner.nextInt();
                    System.out.print("Código da Reserva: ");
                    reservaCodigo = scanner.nextInt();
                    Consumo.consultarConsumo(itemCodigo, reservaCodigo);
                    break;
                case 3:
                    Consumo.listarConsumo();
                    break;
                case 4:
                    System.out.print("O sistema ainda não consegue editar Consumo pelo menu");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuConsumoServico(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu ConsumoServico");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código do Serviço: ");
                    int servicoCodigo = scanner.nextInt();
                    System.out.print("Código da Categoria: ");
                    int categoriaCodigo = scanner.nextInt();
                    System.out.print("Código da Reserva: ");
                    int reservaCodigo = scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Data Serviço (yyyyMMdd): ");
                    int dataServico = scanner.nextInt();
                    Servico servico = Servico.consultarServico(servicoCodigo);
                    Categoria categoria = Categoria.consultarCategoria(categoriaCodigo);
                    Reserva reserva = Reserva.consultarReserva(reservaCodigo);
                    ConsumoServico consumoServico = new ConsumoServico(servico, categoria, reserva, quantidade, dataServico);
                    ConsumoServico.cadastrarConsumoServico(consumoServico);
                    break;
                case 2:
                    System.out.print("Código do Serviço: ");
                    servicoCodigo = scanner.nextInt();
                    System.out.print("Código da Reserva: ");
                    reservaCodigo = scanner.nextInt();
                    ConsumoServico.consultarConsumoServico(servicoCodigo, reservaCodigo);
                    break;
                case 3:
                    ConsumoServico.listarConsumoServico();
                    break;
                case 4:
                    System.out.print("O sistema ainda não consegue editar ConsumoServico pelo menu");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuFuncionario(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Funcionario");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("CPF: ");
                    String cpf = scanner.next();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Setor: ");
                    String setor = scanner.nextLine();
                    Funcionario funcionario = new Funcionario(cpf, nome, email, setor);
                    Funcionario.cadastrarFuncionario(funcionario);
                    break;
                case 2:
                    System.out.print("CPF: ");
                    cpf = scanner.next();
                    Funcionario.consultarFuncionario(cpf);
                    break;
                case 3:
                    Funcionario.listarFuncionario();
                    break;
                case 4:
                    System.out.print("CPF: ");
                    cpf = scanner.next();
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    nome = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    email = scanner.nextLine();
                    System.out.print("Novo Setor: ");
                    setor = scanner.nextLine();
                    Funcionario.editarFuncionario(cpf, nome, email, setor);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuHospede(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Hospede");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("CPF: ");
                    String cpf = scanner.next();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Endereço Completo: ");
                    String enderecoCompleto = scanner.nextLine();
                    Hospede hospede = new Hospede(cpf, nome, email, enderecoCompleto);
                    Hospede.cadastrarHospede(hospede);
                    break;
                case 2:
                    System.out.print("CPF: ");
                    cpf = scanner.next();
                    Hospede.consultarHospede(cpf);
                    break;
                case 3:
                    Hospede.listarHospede();
                    break;
                case 4:
                    System.out.print("CPF: ");
                    cpf = scanner.next();
                    scanner.nextLine();
                    System.out.print("Novo Nome: ");
                    nome = scanner.nextLine();
                    System.out.print("Novo Email: ");
                    email = scanner.nextLine();
                    System.out.print("Novo Endereço Completo: ");
                    enderecoCompleto = scanner.nextLine();
                    Hospede.editarHospede(cpf, nome, email, enderecoCompleto);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuItem(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Item");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    Item item = new Item(codigo, descricao, valor);
                    Item.cadastrarItem(item);
                    break;
                case 2:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    Item.consultarItem(codigo);
                    break;
                case 3:
                    Item.listarItens();
                    break;
                case 4:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nova Descrição: ");
                    descricao = scanner.nextLine();
                    System.out.print("Novo Valor: ");
                    valor = scanner.nextDouble();
                    Item.editarItens(codigo, descricao, valor);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuQuarto(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Quarto");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    System.out.print("Código da Categoria: ");
                    int categoriaCodigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
                    Categoria categoria = Categoria.consultarCategoria(categoriaCodigo);
                    Quarto quarto = new Quarto(codigo, categoria, status);
                    Quarto.cadastrarQuarto(quarto);
                    break;
                case 2:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    Quarto.consultarQuarto(codigo);
                    break;
                case 3:
                    Quarto.listarQuartos();
                    break;
                case 4:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    System.out.print("Código da Categoria: ");
                    categoriaCodigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Status: ");
                    status = scanner.nextLine();
                    categoria = Categoria.consultarCategoria(categoriaCodigo);
                    Quarto.editarQuarto(codigo, categoria, status);
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuReserva(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Reserva");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    System.out.print("CPF do Hospede: ");
                    String cpfHospede = scanner.next();
                    System.out.print("Código do Quarto: ");
                    int codigoQuarto = scanner.nextInt();
                    System.out.print("CPF do Funcionario Reserva: ");
                    String cpfFuncionarioReserva = scanner.next();
                    System.out.print("CPF do Funcionario Fechamento: ");
                    String cpfFuncionarioFechamento = scanner.next();
                    System.out.print("Data Entrada Reserva (yyyyMMdd): ");
                    int dataEntradaReserva = scanner.nextInt();
                    System.out.print("Data Saida Reserva (yyyyMMdd): ");
                    int dataSaidaReserva = scanner.nextInt();
                    System.out.print("Data Checkin (yyyyMMdd): ");
                    int dataCheckin = scanner.nextInt();
                    System.out.print("Data Checkout (yyyyMMdd): ");
                    int dataCheckout = scanner.nextInt();
                    System.out.print("Valor Reserva: ");
                    double valorReserva = scanner.nextDouble();
                    System.out.print("Valor Pago: ");
                    double valorPago = scanner.nextDouble();
                    Hospede hospede = Hospede.consultarHospede(cpfHospede);
                    Quarto quarto = Quarto.consultarQuarto(codigoQuarto);
                    Funcionario funcionarioReserva = Funcionario.consultarFuncionario(cpfFuncionarioReserva);
                    Funcionario funcionarioFechamento = Funcionario.consultarFuncionario(cpfFuncionarioFechamento);
                    Reserva reserva = new Reserva(codigo, hospede, quarto, funcionarioReserva, funcionarioFechamento, dataEntradaReserva, dataSaidaReserva, dataCheckin, dataCheckout, valorReserva, valorPago);
                    Reserva.cadastrarReserva(reserva);
                    break;
                case 2:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    Reserva.consultarReserva(codigo);
                    break;
                case 3:
                    Reserva.listarReservas();
                    break;
                case 4:
                    System.out.print("O sistema ainda não consegue editar Reserva pelo menu");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuServico(Scanner scanner) {
        int opcao;
        do {
            System.out.println("\nMenu Servico");
            System.out.println("1: Cadastrar");
            System.out.println("2: Consultar");
            System.out.println("3: Listar");
            System.out.println("4: Editar");
            System.out.println("5: Voltar");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    Servico servico = new Servico(codigo, descricao, valor);
                    Servico.cadastrarServico(servico);
                    break;
                case 2:
                    System.out.print("Código: ");
                    codigo = scanner.nextInt();
                    Servico.consultarServico(codigo);
                    break;
                case 3:
                    Servico.listarServicos();
                    break;
                case 4:
                    System.out.print("O sistema ainda não consegue editar Servico pelo menu");
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
}