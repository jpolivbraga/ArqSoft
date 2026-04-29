package biblioteca.apresentacao;
import biblioteca.dominio.Livro;
import biblioteca.dominio.evento.*;
import biblioteca.dominio.servico.EmprestimoServico;
import biblioteca.infraestrutura.adaptador.LivroRepositorioCsv;

public class Main {
    public static void main(String[] args) {
        // Inicialização
        var eventBus = new EventBus<EmprestimoRealizadoEvento>();
        var repoCsv = new LivroRepositorioCsv("livros.csv");
        
        // Assinando eventos (Handlers desacoplados) [cite: 84]
        eventBus.assinar(ev -> System.out.println("[LOG] Empréstimo registrado para Livro ID: " + ev.livroId()));

        var servico = new EmprestimoServico(repoCsv, eventBus);

        // Fluxo
        Livro l1 = new Livro(1L, "Arquitetura Limpa", "Uncle Bob", "123", 5);
        repoCsv.salvar(l1);
        
        System.out.println("Realizando empréstimo...");
        servico.realizarEmprestimo(10L, 1L);
    }
}