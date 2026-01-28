package service;

import java.util.ArrayList;
import model.Tarefa.Tarefas;

public class TarefaService {

    private static void garantirListaNaoVazia(ArrayList<Tarefas> lista) {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("A lista está vazia.");
        }
    }

    private static void validarIndice(ArrayList<Tarefas> lista, int idx) {
        if (idx < 0 || idx >= lista.size()) {
            throw new IllegalArgumentException("Essa chave não existe, tente novamente.");
        }
    }

    public static void MostrarTarefa(ArrayList<Tarefas> lista) {
        System.out.println("Tarefas: ");
        for(int i = 0; i < lista.size(); i++) {
            Tarefas t = lista.get(i);
            System.out.println(i + " | " + t.toString());
        }
    }

    public static void AdicionarTarefa(ArrayList<Tarefas> lista, String texto) {
        Tarefas p1 = new Tarefas(texto);
        lista.add(p1);
        System.out.println("Tarefa adicionada com sucesso: " + texto + "\n");
        }

    public static void ConcluirTarefa(ArrayList<Tarefas> lista, int val) {
        garantirListaNaoVazia(lista);
        validarIndice(lista, val);
        lista.get(val).concluir();
        System.out.println("Tarefa concluida.");

    }

    public static void RemoverTarefa(ArrayList<Tarefas> lista, int val) {
        garantirListaNaoVazia(lista);
        validarIndice(lista, val);
        lista.remove(val);
        System.out.println("Tarefa removida com sucesso.");
    }

    public static boolean listaVazia(ArrayList<Tarefas> lista) {
        if(lista.isEmpty()) {
            System.out.println("A lista está vazia.");
            return true;
        }
        return false;
    }

    public static boolean validarOpcao(int opc) {
        if(opc == 0) {
            System.out.println("Encerrando...");
            return true;
        }
        if(opc > 4 || opc < 0) {
            throw new IllegalArgumentException("O valor deve estar entre 0 e 4.");
        } 	
        return false;
    }

}
