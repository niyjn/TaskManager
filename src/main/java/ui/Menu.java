package ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.sql.SQLException;
import model.Tarefa.Tarefas;
import service.TarefaService;
import DAO.JavaBridge;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);
    
    public static void mostrarMenu() {
        System.out.println("GERENCIADOR DE TAREFAS V2\n");
        System.out.println("Digite o que deseja:\n");
        System.out.println("1| Adicionar tarefa.");
        System.out.println("2| Ver tarefas.");
        System.out.println("3| Marcar tarefa como concluida.");
        System.out.println("4| Remover tarefas.");
        System.out.println("0| Sair.\n");
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void AguardarInput() {
        System.out.println("Pressione enter para voltar ao menu...");
        sc.nextLine();
    }

    public static int capturarInteiro() {
        try {

            int valor = sc.nextInt();
            sc.nextLine(); 
            return valor;

        } catch (java.util.InputMismatchException e) {

            sc.nextLine();
            
            throw new IllegalArgumentException(" O valor deve ser um numero inteiro.");

        }
    }

    public static String capturarTexto() {

        String entrada = sc.nextLine();

        if(entrada == null || entrada.isEmpty()) {
            throw new IllegalArgumentException(" A entrada nao pode ser vazia.");
        }

        return entrada;
    }

    private static int pedirIndice(String msg) {

        while (true) {

            try {

                System.out.println(msg + " (-1 para voltar)");
                return capturarInteiro();

            } catch (IllegalArgumentException e) {

                System.out.println("Erro: " + e.getMessage());

            }
        }
    }


    public static void executarMenu() {
        int opc;

        try {
            JavaBridge.criarTabelaTarefas();
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar banco de dados: " + e.getMessage());
            return;
        }

        while(true) {
            mostrarMenu();

            try {
                opc = capturarInteiro();
                
                if(TarefaService.validarOpcao(opc)) {
                    break;
                }

                switch(opc) {
                    case 1: {
                        limparTela();
                        System.out.println("Digite a descricao da tarefa:");
                        String entrada = capturarTexto();
                        JavaBridge.inserirTarefa(entrada);
                        AguardarInput();
                        break; 
                    }

                    case 2: {
                        limparTela();
                        JavaBridge.exibirTodasAsTarefas();
                        AguardarInput();
                        break; 
                    }

                    case 3: {
                        limparTela();
                        JavaBridge.exibirTodasAsTarefas();
                        int id = pedirIndice("Digite o ID da tarefa a concluir");
                        if (id == -1) break;
                        JavaBridge.atualizarStatusTarefa(id, true);
                        AguardarInput();
                        break;
                    }

                    case 4: {
                        limparTela();
                        JavaBridge.exibirTodasAsTarefas();
                        int id = pedirIndice("Digite o ID da tarefa a ser removida");
                        if (id == -1) break;
                        JavaBridge.deletarTarefa(id);
                        AguardarInput();
                        break; 
                    }

                    default:
                        System.out.println("Opcao Invalida.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }
        } 
    }

}
