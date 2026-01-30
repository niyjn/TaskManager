package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Tarefa.Tarefas;

public class JavaBridge {

    public static void criarTabelaTarefas() throws SQLException {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS tarefas (" +
                "id SERIAL PRIMARY KEY, " +
                "tarefa VARCHAR(255) NOT NULL, " +
                "status BOOLEAN DEFAULT FALSE" +
                ")";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement statement = conn.createStatement()) {
            
            statement.execute(sqlQuery);
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela: " + e.getMessage(), e);
        }
    }

    public static void inserirTarefa(String tarefa) throws SQLException {
        String sqlQuery = "INSERT INTO tarefas (tarefa, status) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            
            pstmt.setString(1, tarefa);
            pstmt.setBoolean(2, false);
            
            int linhasAfetadas = pstmt.executeUpdate();
            System.out.println("Tarefa inserida com sucesso! (" + linhasAfetadas + " linha afetada)");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tarefa: " + e.getMessage(), e);
        }
    }

    public static ArrayList<Tarefas> obterTodasAsTarefas() throws SQLException {
        ArrayList<Tarefas> tarefas = new ArrayList<>();
        String sqlQuery = "SELECT * FROM tarefas ORDER BY id";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sqlQuery)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String tarefa = rs.getString("tarefa");
                boolean status = rs.getBoolean("status");
                
                Tarefas t = new Tarefas(tarefa);
                t.setId(id);
                if (status) {
                    t.concluir();
                }
                tarefas.add(t);
            }
            
            System.out.println(tarefas.size() + " tarefa(s) carregada(s)");
            return tarefas;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tarefas: " + e.getMessage(), e);
        }
    }

    public static void atualizarStatusTarefa(int id, boolean status) throws SQLException {
        String sqlQuery = "UPDATE tarefas SET status = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            
            pstmt.setBoolean(1, status);
            pstmt.setInt(2, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma tarefa encontrada com ID: " + id);
            } else {
                System.out.println("Tarefa atualizada! Status: " + (status ? "Concluida" : "Pendente"));
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tarefa: " + e.getMessage(), e);
        }
    }

    public static void deletarTarefa(int id) throws SQLException {
        String sqlQuery = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
            
            pstmt.setInt(1, id);
            
            int linhasAfetadas = pstmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Nenhuma tarefa encontrada com ID: " + id);
            } else {
                System.out.println("Tarefa deletada com sucesso!");
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tarefa: " + e.getMessage(), e);
        }
    }

    public static void exibirTodasAsTarefas() throws SQLException {
        ArrayList<Tarefas> tarefas = obterTodasAsTarefas();
        
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa no banco de dados");
            return;
        }
        
        System.out.println("\nTAREFAS NO BANCO DE DADOS:");
        System.out.println("================================");
        for (Tarefas t : tarefas) {
            System.out.println(t.getId() + " | " + t.toString());
        }
        System.out.println("================================\n");
    }

}
