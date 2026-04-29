package biblioteca.apresentacao;

import biblioteca.dominio.*;
import biblioteca.dominio.evento.*;
import biblioteca.dominio.servico.EmprestimoServico;
import biblioteca.infraestrutura.adaptador.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1. Configuração do Barramento de Eventos
        EventBus<EmprestimoRealizadoEvento> busEmprestimo = new EventBus<>();
        ServicoDeLog logger = new ServicoDeLog();

        // 2. Registro de Handlers (Desacoplados)
        busEmprestimo.assinar(ev -> logger.registrar("Empréstimo ID " + ev.emprestimoId() + " criado."));
        busEmprestimo.assinar(ev -> System.out.println("Notificação enviada ao usuário " + ev.usuarioId()));

        // 3. Escolha do Adaptador (Demonstração da Etapa 2)
        // Para trocar para CSV, basta usar: new LivroRepositorioCsv("livros.csv")
        var livroRepo = new LivroRepositorioMemoria();

        // 4. Injeção de Dependência no Serviço
        EmprestimoServico servico = new EmprestimoServico(livroRepo, busEmprestimo);

        // 5. Execução do Fluxo
        System.out.println("--- Iniciando Sistema de Biblioteca ---");
        
        Livro javaBook = new Livro(101L, "Java Efetivo", "Joshua Bloch", "978-0134685991", 2);
        livroRepo.salvar(javaBook);
        
        System.out.println("Estoque inicial: " + javaBook.getQuantidadeDisponivel());
        
        Emprestimo emp = servico.realizarEmprestimo(1L, 101L);
        
        if (emp != null) {
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Novo estoque: " + javaBook.getQuantidadeDisponivel());
        }
    }
}