package model;

public class Tarefa {
    
    public static class Tarefas {
        private String nome;
        private boolean concluiu = false;
        private int id;
        
        public Tarefas(String nome) {
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

        public String getNome() {
            return nome;
        }

        public boolean isConcluiu() {
            return concluiu;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    } 
}
