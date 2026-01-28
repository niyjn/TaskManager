package ui;

import service.TarefaService;
import java.util.Scanner;
import java.util.ArrayList;
import model.Tarefa.Tarefas;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);
    
    public static void mostrarMenu() {
        System.out.println("GERENCIADOR DE TAREFAS V1\n");
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

    public static int capturarInteiro() {
        try {

            int valor = sc.nextInt();
            sc.nextLine(); 
            return valor;

        } catch (java.util.InputMismatchException e) {

            sc.nextLine();
            
            throw new IllegalArgumentException(" O valor deve ser um número inteiro.");

        }
    }

    public static String capturarTexto() {

        String entrada = sc.nextLine();

        if(entrada == null || entrada.isEmpty()) {
            throw new IllegalArgumentException(" A entrada não pode ser vazia.");
        }

        return entrada;
    }

    private static int pedirIndice(String msg) {

        while (true) {

            try {

                System.out.println(msg + " (-1 para voltar)");
                int v = capturarInteiro();
                return v; 

            } catch (IllegalArgumentException e) {

                System.out.println("Erro: " + e.getMessage());

            }
        }
    }


    public static void executarMenu(ArrayList<Tarefas> lista) {

        int opc = -1;

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
                    System.out.println("Digite a descrição da tarefa:");
                    String entrada = capturarTexto();
                    TarefaService.AdicionarTarefa(lista, entrada);
                    break; 
                }

                case 2: {
                    limparTela();
                    TarefaService.MostrarTarefa(lista);
                    break; 
                }

                case 3: {
                    limparTela();
                    TarefaService.MostrarTarefa(lista);
                    int idx = pedirIndice("Digite o número da tarefa a concluir");
                    if (idx == -1) break;
                    TarefaService.ConcluirTarefa(lista, idx);
                    break;
                }

                case 4: {
                    limparTela();
                    TarefaService.MostrarTarefa(lista);
                    int idx = pedirIndice("Digite o número da tarefa a ser removida.");
                    if (idx == -1) break;
                    TarefaService.RemoverTarefa(lista, idx);
                    break; 
                }

                default:
                    System.out.println("Opção Inválida.");
                    break;
                }
            } catch (IllegalArgumentException e) {
                
                System.out.println("Erro: " + e.getMessage());

            }
        } 
    }

}