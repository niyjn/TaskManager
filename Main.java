import java.util.ArrayList;
import model.Tarefa.Tarefas;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tarefas> lista = new ArrayList<>();
        Menu.executarMenu(lista);
    }
}