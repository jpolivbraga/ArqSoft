package biblioteca.infraestrutura.adaptador;

import biblioteca.dominio.Usuario;
import biblioteca.dominio.Emprestimo;
import biblioteca.dominio.porta.saida.PortaNotificacao;

public class NotificacaoConsole implements PortaNotificacao {
    @Override
    public void notificarAtraso(Usuario usuario, Emprestimo emprestimo) {
        System.out.println("------------------------------------------------");
        System.out.println("NOTIFICAÇÃO DE ATRASO PARA: " + usuario.nome());
        System.out.println("Livro: " + emprestimo.livro().getTitulo());
        System.out.println("Data prevista: " + emprestimo.dataDevolucao());
        System.out.println("Por favor, regularize sua situação na biblioteca.");
        System.out.println("------------------------------------------------");
    }
}