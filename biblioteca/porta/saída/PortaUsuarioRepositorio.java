package biblioteca.dominio.porta.saida;

import biblioteca.dominio.Usuario;
import java.util.Optional;
import java.util.List;

public interface PortaUsuarioRepositorio {
    void salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
}