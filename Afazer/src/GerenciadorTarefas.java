import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Tarefa {
    private boolean status;
    private String categoria;
    private String titulo;
    private String descricao;

    public Tarefa(boolean status, String categoria, String titulo, String descricao) {
        this.status = status;
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}

public class GerenciadorTarefas {
    private static List<Tarefa> listaTarefas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n1. Adicionar Tarefa");
            System.out.println("2. Remover Tarefa");
            System.out.println("3. Marcar/Desmarcar Tarefa");
            System.out.println("4. Consultar Tarefas");
            System.out.println("5. Consultar Tarefas por Categoria");
            System.out.println("6. Sair");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    removerTarefa();
                    break;
                case 3:
                    marcarDesmarcarTarefa();
                    break;
                case 4:
                    consultarTarefas();
                    break;
                case 5:
                    consultarTarefasPorCategoria();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 6);
    }

    private static void adicionarTarefa() {
        System.out.println("\nAdicionar Tarefa:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        listaTarefas.add(new Tarefa(false, categoria, titulo, descricao));
        System.out.println("Tarefa adicionada com sucesso!");
    }

    private static void removerTarefa() {
        System.out.println("\nRemover Tarefa:");
        System.out.print("Índice da Tarefa a ser removida: ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < listaTarefas.size()) {
            listaTarefas.remove(indice);
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void marcarDesmarcarTarefa() {
        System.out.println("\nMarcar/Desmarcar Tarefa:");
        System.out.print("Índice da Tarefa a ser marcada/desmarcada: ");
        int indice = scanner.nextInt();
        if (indice >= 0 && indice < listaTarefas.size()) {
            Tarefa tarefa = listaTarefas.get(indice);
            tarefa.setStatus(!tarefa.getStatus());
            System.out.println("Status da tarefa alterado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }

    private static void consultarTarefas() {
        System.out.println("\nConsultar Tarefas:");
        System.out.println("1. Todas as Tarefas");
        System.out.println("2. Tarefas em Aberto");
        System.out.println("3. Tarefas Finalizadas");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        List<Tarefa> resultado = new ArrayList<>();
        switch (opcao) {
            case 1:
                resultado = listaTarefas;
                break;
            case 2:
                resultado = listaTarefas.stream().filter(tarefa -> !tarefa.getStatus()).collect(Collectors.toList());
                break;
            case 3:
                resultado = listaTarefas.stream().filter(Tarefa::getStatus).collect(Collectors.toList());
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (resultado.isEmpty()) {
            System.out.println("Não há tarefas a serem exibidas.");
        } else {
            System.out.println("Tarefas:");
            for (int i = 0; i < resultado.size(); i++) {
                Tarefa tarefa = resultado.get(i);
                String status = tarefa.getStatus() ? "[Finalizada]" : "[Em Aberto]";
                System.out.println(i + ": " + status + " " + tarefa.getTitulo() + " - " + tarefa.getDescricao());
            }
        }
    }

    private static void consultarTarefasPorCategoria() {
        System.out.println("\nConsultar Tarefas por Categoria:");
        System.out.print("Digite a categoria: ");
        String categoria = scanner.nextLine();

        List<Tarefa> tarefasFiltradas = listaTarefas.stream()
                .filter(tarefa -> tarefa.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

        if (tarefasFiltradas.isEmpty()) {
            System.out.println("Não há tarefas nesta categoria.");
        } else {
            System.out.println("Tarefas da Categoria '" + categoria + "':");
            for (int i = 0; i < tarefasFiltradas.size(); i++) {
                Tarefa tarefa = tarefasFiltradas.get(i);
                String status = tarefa.getStatus() ? "[Finalizada]" : "[Em Aberto]";
                System.out.println(i + ": " + status + " " + tarefa.getTitulo() + " - " + tarefa.getDescricao());
            }
        }
    }
}
