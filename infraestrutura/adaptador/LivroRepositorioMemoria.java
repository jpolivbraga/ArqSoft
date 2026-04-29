package biblioteca.infraestrutura.adaptador;

import biblioteca.dominio.Livro;
import biblioteca.dominio.porta.saida.PortaLivroRepositorio;
import java.util.*;

public class LivroRepositorioMemoria implements PortaLivroRepositorio {
    private final Map<Long, Livro> livros = new HashMap<>();

    @Override
    public void salvar(Livro livro) {
        livros.put(livro.getId(), livro);
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return Optional.ofNullable(livros.get(id));
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(livros.values());
    }
}