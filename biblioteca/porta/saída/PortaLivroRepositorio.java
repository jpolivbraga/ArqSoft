package biblioteca.dominio.porta.saida;
import biblioteca.dominio.Livro;
import java.util.Optional;
import java.util.List;

public interface PortaLivroRepositorio {
    void salvar(Livro livro);
    Optional<Livro> buscarPorId(Long id);
    List<Livro> listarTodos();
}