package biblioteca.infraestrutura.adaptador;
import biblioteca.dominio.Livro;
import biblioteca.dominio.porta.saida.PortaLivroRepositorio;
import java.io.*;
import java.util.*;

public class LivroRepositorioCsv implements PortaLivroRepositorio {
    private final String PATH;

    public LivroRepositorioCsv(String path) { this.PATH = path; }

    @Override
    public void salvar(Livro livro) {
        try (PrintWriter out = new PrintWriter(new FileWriter(PATH, true))) {
            out.println(livro.getId() + ";" + livro.getTitulo() + ";" + livro.getQuantidadeDisponivel());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        // Lógica de leitura de arquivo simples para demonstração
        return Optional.empty(); 
    }

    @Override public List<Livro> listarTodos() { return new ArrayList<>(); }
}