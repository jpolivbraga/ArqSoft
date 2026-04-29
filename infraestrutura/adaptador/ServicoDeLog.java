package biblioteca.infraestrutura.adaptador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ServicoDeLog {
    public void registrar(String mensagem) {
        try (PrintWriter out = new PrintWriter(new FileWriter("biblioteca.log", true))) {
            out.println("[" + LocalDateTime.now() + "] " + mensagem);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}