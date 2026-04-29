package biblioteca.infraestrutura.adaptador;

import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.porta.saida.PortaEmprestimoRepositorio;
import java.util.*;

public class EmprestimoRepositorioMemoria implements PortaEmprestimoRepositorio {
    private final Map<Long, Emprestimo> emprestimos = new HashMap<>();

    @Override
    public void salvar(Emprestimo emprestimo) {
        emprestimos.put(emprestimo.id(), emprestimo);
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id) {
        return Optional.ofNullable(emprestimos.get(id));
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return new ArrayList<>(emprestimos.values());
    }
}