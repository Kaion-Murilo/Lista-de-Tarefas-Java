import java.util.ArrayList;
import java.util.Scanner;

class Tarefa {
  private String descricao;
  private boolean concluida;
  private TipoTarefa tipo;

  public Tarefa(String descricao, TipoTarefa tipo) {
    this.descricao = descricao;
    this.concluida = false;
    this.tipo = tipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public boolean estaConcluida() {
    return concluida;
  }

  public void marcarConcluida() {
    concluida = true;
  }

  public void desmarcarConcluida() {
    concluida = false;
  }

  public TipoTarefa getTipo() {
    return tipo;
  }
}

enum TipoTarefa {
  COMUM, PRIORITARIA, RECORRENTE
}

class ListaDeTarefas {
  private ArrayList<Tarefa> tarefas;

  public ListaDeTarefas() {
    tarefas = new ArrayList<>();
  }

  public void adicionarTarefa(Tarefa tarefa) {
    tarefas.add(tarefa);
    System.out.println("Tarefa adicionada: " + tarefa.getDescricao());
  }

  public void modificarTarefa(int indice, Tarefa novaTarefa) {
    if (indice >= 0 && indice < tarefas.size()) {
      Tarefa tarefaAntiga = tarefas.get(indice);
      tarefas.set(indice, novaTarefa);
      System.out.println("Tarefa modificada: " + tarefaAntiga.getDescricao() + " -> " + novaTarefa.getDescricao());
    } else {
      System.out.println("Índice inválido.");
    }
  }

  public void excluirTarefa(int indice) {
    if (indice >= 0 && indice < tarefas.size()) {
      Tarefa tarefaRemovida = tarefas.remove(indice);
      System.out.println("Tarefa removida: " + tarefaRemovida.getDescricao());
    } else {
      System.out.println("Índice inválido.");
    }
  }

  public void listarTarefas() {
    if (tarefas.isEmpty()) {
      System.out.println("Lista de tarefas vazia.");
    } else {
      System.out.println("Lista de tarefas:");
      for (int i = 0; i < tarefas.size(); i++) {
        Tarefa tarefa = tarefas.get(i);
        System.out.println((i + 1) + ". " + tarefa.getDescricao() + " (" + tarefa.getTipo() + ")");
      }
    }
  }
}

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    ListaDeTarefas lista = new ListaDeTarefas();

    while (true) {
      System.out.println("\nEscolha uma opção:");
      System.out.println("1. Adicionar tarefa");
      System.out.println("2. Modificar tarefa");
      System.out.println("3. Excluir tarefa");
      System.out.println("4. Listar tarefas");
      System.out.println("5. Sair");

      int opcao = scanner.nextInt();
      scanner.nextLine();

      switch (opcao) {
        case 1:
          System.out.println("Digite a descrição da tarefa:");
          String descricao = scanner.nextLine();
          System.out.println("Escolha o tipo da tarefa (1 - Comum, 2 - Prioritária, 3 - Recorrente):");
          int tipoInt = scanner.nextInt();
          TipoTarefa tipo;
          switch (tipoInt) {
            case 1:
              tipo = TipoTarefa.COMUM;
              break;
            case 2:
              tipo = TipoTarefa.PRIORITARIA;
              break;
            case 3:
              tipo = TipoTarefa.RECORRENTE;
              break;
            default:
              System.out.println("Tipo inválido, a tarefa será considerada comum.");
              tipo = TipoTarefa.COMUM;
          }
          lista.adicionarTarefa(new Tarefa(descricao, tipo));
          break;
        case 2:
          System.out.println("Digite o índice da tarefa a ser modificada:");
          int indiceModificacao = scanner.nextInt();
          scanner.nextLine(); // Consumir a nova linha após o número
          System.out.println("Digite a nova descrição da tarefa:");
          String novaDescricao = scanner.nextLine();
          System.out.println("Escolha o novo tipo da tarefa (1 - Comum, 2 - Prioritária, 3 - Recorrente):");
          int novoTipoInt = scanner.nextInt();
          TipoTarefa novoTipo;
          switch (novoTipoInt) {
            case 1:
              novoTipo = TipoTarefa.COMUM;
              break;
            case 2:
              novoTipo = TipoTarefa.PRIORITARIA;
              break;
            case 3:
              novoTipo = TipoTarefa.RECORRENTE;
              break;
            default:
              System.out.println("Tipo inválido, a tarefa será considerada comum.");
              novoTipo = TipoTarefa.COMUM;
          }
          lista.modificarTarefa(indiceModificacao - 1, new Tarefa(novaDescricao, novoTipo));
          break;
        case 3:
          System.out.println("Digite o índice da tarefa a ser removida:");
          int indiceRemocao = scanner.nextInt();
          lista.excluirTarefa(indiceRemocao - 1);
          break;
        case 4:
          lista.listarTarefas();
          break;
        case 5:
          System.out.println("Saindo...");
          System.exit(0);
        default:
          System.out.println("Opção inválida.");
      }
    }
  }
}
