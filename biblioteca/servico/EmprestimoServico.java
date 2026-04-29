package biblioteca.dominio.servico;
import biblioteca.dominio.*;
import biblioteca.dominio.porta.entrada.PortaEmprestimo;
import biblioteca.dominio.porta.saida.PortaLivroRepositorio;
import biblioteca.dominio.evento.*;
import java.time.LocalDate;
import java.util.List;

public class EmprestimoServico implements PortaEmprestimo {
    private final PortaLivroRepositorio livroRepo;
    private final EventBus<EmprestimoRealizadoEvento> eventBus;

    public EmprestimoServico(PortaLivroRepositorio livroRepo, EventBus<EmprestimoRealizadoEvento> eventBus) {
        this.livroRepo = livroRepo;
        this.eventBus = eventBus;
    }

    @Override
    public Emprestimo realizarEmprestimo(Long usuarioId, Long livroId) {
        return livroRepo.buscarPorId(livroId).filter(Livro::realizarEmprestimo).map(livro -> {
            Emprestimo e = new Emprestimo(System.nanoTime(), livro, usuarioId, LocalDate.now(), LocalDate.now().plusDays(7), SituacaoEmprestimo.ATIVO);
            livroRepo.salvar(livro);
            eventBus.publicar(new EmprestimoRealizadoEvento(e.id(), usuarioId, livroId, e.dataRetirada()));
            return e;
        }).orElse(null);
    }

    @Override public void registrarDevolucao(Long id) { /* Lógica de devolução */ }
    @Override public List<Emprestimo> listarEmprestimosAtivos() { return List.of(); }
}