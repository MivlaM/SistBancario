package br.com.mesttra.banco.controller;

import java.util.ArrayList;

import br.com.mesttra.banco.dao.ClienteDAO;
import br.com.mesttra.banco.dao.PessoaFisicaDAO;
import br.com.mesttra.banco.dao.PessoaJuridicaDAO;
import br.com.mesttra.banco.pojo.ClientePojo;
import br.com.mesttra.banco.pojo.PessoaFisicaPojo;
import br.com.mesttra.banco.pojo.PessoaJuridicaPojo;
import br.com.mesttra.banco.scanner.Scanner;
import br.com.mesttra.banco.validacao.ValidadorCpf;

public class Menu {

  private void limparTela() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public ClientePojo cadastrarCliente() {
    limparTela();

    String numeroConta = Scanner.lerValorAlfanumerico(
      "Insira o número da conta do cliente: "
    );

    int agencia = Scanner.lerValorInteiro("Insira a agência: ");

    float saldo = Scanner.lerValorMonetario("Insira o saldo: ");

    float limiteCheque = Scanner.lerValorMonetario(
      "Insira o limite do cheque: "
    );

    String telefone = Scanner.lerValorAlfanumerico("Insira o telefone: ");

    int tipoCliente = Scanner.lerValorInteiroComLimites(
      1,
      2,
      "Tipo de cliente:\n1- Pessoa física\n2- Pessoa jurídica\n"
    );

    if (tipoCliente == 1) {
      String cpf = ValidadorCpf.validaCpf();
      String nome = Scanner.lerValorAlfanumerico("Insira o nome do cliente: ");
      String dataNascimento = Scanner.lerValorAlfanumerico(
        "Insira a data de nascimento do cliente: "
      );

      PessoaFisicaPojo novoCliente = new PessoaFisicaPojo(
        cpf,
        nome,
        dataNascimento,
        numeroConta,
        agencia,
        telefone,
        saldo,
        limiteCheque
      );

      PessoaFisicaDAO.inserePF(novoCliente);

      return novoCliente;
    } else {
      String cnpj = Scanner.lerValorAlfanumerico("Insira o CNPJ do cliente: ");
      String razaoSocial = Scanner.lerValorAlfanumerico(
        "Insira a razão social: "
      );
      String nomeFantasia = Scanner.lerValorAlfanumerico(
        "Insira o nome fantasia: "
      );

      PessoaJuridicaPojo novoCliente = new PessoaJuridicaPojo(
        cnpj,
        razaoSocial,
        nomeFantasia,
        numeroConta,
        agencia,
        telefone,
        saldo,
        limiteCheque
      );

      PessoaJuridicaDAO.inserePJ(novoCliente);

      return novoCliente;
    }
  }

  public void exibirAtributosPessoaFisica(
    int opcao,
    PessoaFisicaPojo clienteEncontrado
  ) {
    if (opcao == 1) {
      System.out.println(
        "Número de conta: " + clienteEncontrado.getNumeroConta()
      );
    } else if (opcao == 2) {
      System.out.println("Agência: " + clienteEncontrado.getAgencia());
    } else if (opcao == 3) {
      System.out.println(
        "Número de telefone: " + clienteEncontrado.getTelefone()
      );
    } else if (opcao == 4) {
      System.out.println("Saldo: " + clienteEncontrado.getSaldo());
    } else if (opcao == 5) {
      System.out.println(
        "Limite de cheque: " + clienteEncontrado.getLimiteCheque()
      );
    } else if (opcao == 6) {
      System.out.println("CPF: " + clienteEncontrado.getCpf());
    } else if (opcao == 7) {
      System.out.println("Nome: " + clienteEncontrado.getNome());
    } else {
      System.out.println(
        "Data de nascimento: " + clienteEncontrado.getDataNascimento()
      );
    }
  }

  public void exibirPessoaFisica() {
    limparTela();

    String numeroConta = Scanner.lerValorAlfanumerico(
      "Insira o número da conta do cliente procurado: "
    );

    PessoaFisicaPojo clienteEncontrado = PessoaFisicaDAO.consultarCliente(
      numeroConta
    );

    if (clienteEncontrado == null) {
      System.out.println("O cliente procurado não foi encontrado");
    } else {
      System.out.println("1- Número de conta");
      System.out.println("2- Agência");
      System.out.println("3- Telefone");
      System.out.println("4- Saldo");
      System.out.println("5- Limite do cheque");
      System.out.println("6- CPF");
      System.out.println("7- Nome");
      System.out.println("8- Data de nascimento");

      int opcao = Scanner.lerValorInteiroComLimites(
        1,
        8,
        "Selecione um atributo: "
      );

      exibirAtributosPessoaFisica(opcao, clienteEncontrado);
    }
  }

  public void exibirAtributosPessoaJuridica(
    int opcao,
    PessoaJuridicaPojo clienteEncontrado
  ) {
    if (opcao == 1) {
      System.out.println(
        "Número de conta: " + clienteEncontrado.getNumeroConta()
      );
    } else if (opcao == 2) {
      System.out.println("Agência: " + clienteEncontrado.getAgencia());
    } else if (opcao == 3) {
      System.out.println(
        "Número de telefone: " + clienteEncontrado.getTelefone()
      );
    } else if (opcao == 4) {
      System.out.println("Saldo: " + clienteEncontrado.getSaldo());
    } else if (opcao == 5) {
      System.out.println(
        "Limite de cheque: " + clienteEncontrado.getLimiteCheque()
      );
    } else if (opcao == 6) {
      System.out.println("CNPJ: " + clienteEncontrado.getCnpj());
    } else if (opcao == 7) {
      System.out.println("Razão social: " + clienteEncontrado.getRazaoSocial());
    } else {
      System.out.println(
        "Nome fantasia: " + clienteEncontrado.getNomeFantasia()
      );
    }
  }

  public void exibirPessoaJuridica() {
    limparTela();

    String numeroConta = Scanner.lerValorAlfanumerico(
      "Insira o número da conta do cliente procurado: "
    );

    PessoaJuridicaPojo clienteEncontrado = PessoaJuridicaDAO.consultarCliente(
      numeroConta
    );

    if (clienteEncontrado == null) {
      System.out.println("O cliente procurado não foi encontrado");
    } else {
      System.out.println("1- Número de conta");
      System.out.println("2- Agência");
      System.out.println("3- Telefone");
      System.out.println("4- Saldo");
      System.out.println("5- Limite do cheque");
      System.out.println("6- CNPJ");
      System.out.println("7- Razão social");
      System.out.println("8- Nome fantasia");

      int opcao = Scanner.lerValorInteiroComLimites(
        1,
        8,
        "Selecione um atributo: "
      );

      exibirAtributosPessoaJuridica(opcao, clienteEncontrado);
    }
  }

  public ClientePojo pesquisaCliente(boolean fazerLoop, String mensagem) {
    do {
      String numeroConta = Scanner.lerValorAlfanumerico(mensagem);

      PessoaJuridicaPojo pessoaJuridicaEncontrada = PessoaJuridicaDAO.consultarCliente(
        numeroConta
      );
      PessoaFisicaPojo pessoaFisicaEncontrada = PessoaFisicaDAO.consultarCliente(
        numeroConta
      );

      ClientePojo clienteEncontrado = pessoaJuridicaEncontrada != null
        ? pessoaJuridicaEncontrada
        : pessoaFisicaEncontrada != null ? pessoaFisicaEncontrada : null;

      if (clienteEncontrado != null) {
        return clienteEncontrado;
      } else if (!fazerLoop) {
        return null;
      } else {
        System.out.println("Cliente não encontrado");

        int opcao = Scanner.lerValorInteiroComLimites(
          1,
          2,
          "Cadastrar novo cliente?\n1 - Sim\n2 - Não\n"
        );

        if (opcao == 1) {
          return cadastrarCliente();
        }
      }
    } while (true);
  }

  public void adicionarSaldo() {
    limparTela();

    ClientePojo clienteEncontrado = pesquisaCliente(
      false,
      "Insira o número da conta do cliente: "
    );

    if (clienteEncontrado == null) {
      System.out.println("O cliente procurado não foi encontrado");
    } else {
      float valorAdicionado = Scanner.lerValorMonetario(
        "Insira o valor a ser adicionado: "
      );

      float novoSaldo = clienteEncontrado.getSaldo() + valorAdicionado;

      ClienteDAO.atualizarCliente(clienteEncontrado, novoSaldo, "saldo");

      System.out.println("Saldo atualizado com sucesso");
    }
  }

  public void realizaTransferencia(
    ClientePojo contaTransferidora,
    ClientePojo contaReceptora,
    float valor
  ) {
    if (contaTransferidora.getSaldo() < valor) {
      System.err.println("Saldo insuficiente");
    } else {
      ClienteDAO.atualizarCliente(
        contaTransferidora,
        contaTransferidora.getSaldo() - valor,
        "saldo"
      );
      ClienteDAO.atualizarCliente(
        contaReceptora,
        contaReceptora.getSaldo() + valor,
        "saldo"
      );

      System.out.println("\n[Transferência realizada com sucesso]\n");
    }
  }

  public void obtemDadosParaAtransferencia() {
    limparTela();

    ClientePojo contaTransferidora = pesquisaCliente(
      true,
      "Insira o número da conta do cliente que fará a transferência: "
    );
    ClientePojo contaReceptora = pesquisaCliente(
      true,
      "Insira o número da conta do cliente que receberá o montante: "
    );

    float valorTransferido = Scanner.lerValorMonetario(
      "Valor a ser transferido: "
    );

    realizaTransferencia(contaTransferidora, contaReceptora, valorTransferido);
  }

  public void alterarValorDoChequeEspecial () {
    limparTela();

    ClientePojo clienteEncontrado = pesquisaCliente(true, "Insira o número da conta do cliente a ter seu cheque especial alterado: ");

    float valor = Scanner.lerValorMonetario("Valor a ser acrescentado/reduzido: ");

    int opcao = Scanner.lerValorInteiroComLimites(1, 2, "Seleciona uma opção:\n1- Aumentar valor do cheque especial\n2- Reduzir valor do cheque especial\n");

    if (opcao == 1) {
      float novoValor = clienteEncontrado.getLimiteCheque() + valor;

      ClienteDAO.atualizarCliente(clienteEncontrado, novoValor, "limiteCheque");

      System.out.println("Cheque especial acrescido com sucesso");
    } else {
      
      if (clienteEncontrado.getLimiteCheque() >= valor) {
        float novoValor = clienteEncontrado.getLimiteCheque() - valor;

        ClienteDAO.atualizarCliente(clienteEncontrado, novoValor, "limiteCheque");

        System.out.println("Cheque especial reduzido com sucesso");
      } else {
        System.out.println("Não foi possível realizar a operação.\nO valor removido é inferior ao valor do cheque especial do cliente");
      }
    }
  }

  public void exibirTodosOsClientesCadastrados () {
    limparTela();

    ArrayList<PessoaFisicaPojo> pessoasFisicas = PessoaFisicaDAO.obterPessoasFisicas();
    ArrayList<PessoaJuridicaPojo> pessoasJuridicas = PessoaJuridicaDAO.obterPessoasJuridicas();

    ArrayList<ClientePojo> listaClientes = new ArrayList<>(pessoasFisicas);

    listaClientes.addAll(pessoasJuridicas);

    for (ClientePojo cliente : listaClientes) {
      System.out.println(cliente);
    }
  }

  public void excluirCliente () {
    limparTela();

    ClientePojo clienteEncontrado = pesquisaCliente(false, "Insira o número da conta do cliente a ser excluído: ");

    if (clienteEncontrado != null) {
      ClienteDAO.removerCliente(clienteEncontrado);

      System.out.println("Cliente removido com sucesso");
    } else {
      System.out.println("Cliente não encontrado, não foi possível remover o cliente.");
    }
  }

  public void exibirMenu() {
    do {
      int opcao = Scanner.lerValorInteiroComLimites(
        1,
        7,
        "Bem vindo, gerente.\nEscolha uma opção:\n1- Cadastrar cliente\n2- Remover cliente\n3- Alterar valor do cheque especial\n4- Fazer transferência\n5- Adicionar saldo\n6- Imprimir relatório\n7- Consultar cliente\n"
      );

      switch (opcao) {
        case 1:
          cadastrarCliente();

          break;
        case 2:
          excluirCliente();

          break;
        case 3:
          alterarValorDoChequeEspecial();

          break;
        case 4:
          obtemDadosParaAtransferencia();

          break;
        case 5:
          adicionarSaldo();

          break;
        case 6:
          exibirTodosOsClientesCadastrados();

          break;
        case 7:
          limparTela();

          int tipoCliente = Scanner.lerValorInteiroComLimites(
            1,
            2,
            "Tipo de cliente:\n1 - Físico\n2- Jurídico\n"
          );

          if (tipoCliente == 1) {
            exibirPessoaFisica();
          } else {
            exibirPessoaJuridica();
          }

          break;
      }

      opcao =
        Scanner.lerValorInteiroComLimites(
          1,
          2,
          "Realizar nova operação?\n1- Sim\n2- Não\n"
        );

      if (opcao == 2) break;
    } while (true);
  }

  public static void main(String[] args) {
    new Menu().exibirMenu();
  }
}
