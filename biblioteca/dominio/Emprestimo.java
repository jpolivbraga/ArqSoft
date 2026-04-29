package biblioteca.dominio;
import java.time.LocalDate;

public record Usuario(Long id, String nome, String email, SituacaoUsuario situacao) {}

public record Emprestimo(Long id, Livro livro, Long usuarioId, LocalDate dataRetirada, LocalDate dataDevolucao, SituacaoEmprestimo situacao) {}