package biblioteca.infraestrutura.adaptador;

import biblioteca.dominio.Usuario;
import biblioteca.dominio.porta.saida.PortaUsuarioRepositorio;
import java.util.*;

public class UsuarioRepositorioMemoria implements PortaUsuarioRepositorio {
    private final Map<Long, Usuario> usuarios = new HashMap<>();

    @Override
    public void salvar(Usuario usuario) {
        usuarios.put(usuario.id(), usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios.values());
    }
}