import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

public class Main {
    
    public static class Tarefa {
        private String nome;
        private boolean concluiu = false;
        
        public Tarefa(String nome) {
            this.nome = nome;
            this.concluiu = false;
        }
        
        @Override
        public String toString() {
            if(concluiu) {
                return ("[X] " + nome);
            } else {
                return ("[ ] " + nome);
            }
        }
        
        public void concluir() {
        this.concluiu = true;
        }
    }

    public static void MostrarTarefa(ArrayList<Tarefa> lista) {

            System.out.println("Tarefas: ");
            for(int i = 0; i < lista.size(); i++) {
                Tarefa t = lista.get(i);
                System.out.println(i + " | " + t.toString());
            }
        
    }

    public static void AdicionarTarefa(ArrayList<Tarefa> lista, String texto) {
        Tarefa p1 = new Tarefa(texto);
        lista.add(p1);
        System.out.println("Tarefa adicionada com sucesso: " + texto + "\n");
    }

    public static void ConcluirTarefa(ArrayList<Tarefa> lista, int val) {
        lista.get(val).concluir();
        System.out.println("Tarefa concluida.");
    }

    public static void RemoverTarefa(ArrayList<Tarefa> lista, int val) {
        lista.remove(val);
        System.out.println("Tarefa removida com sucesso.");
    }

    public static boolean listaVazia(ArrayList<Tarefa> lista) {
        if(lista.isEmpty()) {
            System.out.println("A lista está vazia.");
            return true;
        }
        return false;
    }

    
    
    public static void main(String[] args) {
       
        Scanner sc = new Scanner(System.in); 
        
        ArrayList<Tarefa> lista = new ArrayList<>();
        
        System.out.println("GERENCIADOR DE TAREFAS V1\n");
        System.out.println("Digite o que deseja:\n");
        System.out.println("1| Adicionar tarefa.");
        System.out.println("2| Ver tarefas.");
        System.out.println("3| Marcar tarefa como concluida.");
        System.out.println("4| Remover tarefas.");
        System.out.println("0| Sair.\n");
        
        while(true) {
            
            int opc = -1;
            
            try {
                
                opc = sc.nextInt();
                sc.nextLine();
                if(opc == 0) { 
                    System.out.println("Encerrando..."); 
                    break; 
                }
                
                if(opc > 4 || opc < 0) {
                    throw new IllegalArgumentException("O valor deve estar entre 0 e 4.");
                }
                
                
            switch(opc) {
                
                case 1: {
                
                    System.out.println("Digite a descrição da tarefa:");
                    AdicionarTarefa(lista, sc.nextLine());
                    break;
                    
                }
                
                case 2: {

                    if(listaVazia(lista)) break;
                    else {
                    MostrarTarefa(lista);
                    break;
                    }
                }
                
                case 3:
                    if(listaVazia(lista)) break;
                    else {
                        MostrarTarefa(lista);
                        System.out.println("Digite o numero da tarefa a ser concluida.");
                        try {
                            int val = sc.nextInt();
                            sc.nextLine();
                            if(val >= lista.size() || val < 0) {
                                throw new IllegalArgumentException("O valor da tarefa deve ser válido.");
                            }
                            ConcluirTarefa(lista, val);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;
                    
                case 4:
                    if(!listaVazia(lista)) {
                        MostrarTarefa(lista);
                        System.out.println("Digite o numero da tarefa a ser removida.");
                        try {
                            int val = sc.nextInt();
                            sc.nextLine();
                            if(val >= 0 && val < lista.size()) {
                                RemoverTarefa(lista, val);
                            } else {
                                throw new IllegalArgumentException("O valor da tarefa deve ser válido.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;
                
                default: {
                    break;
                }
                
                
            }
                
            } catch (InputMismatchException e) {
                
                System.out.println("Erro: A entrada deve ser um número.");
                sc.next();
                    
            } catch (IllegalArgumentException e) {
                
                System.out.println("Erro: " + e.getMessage());
                
            }
            
            
        }
        
    }
}
