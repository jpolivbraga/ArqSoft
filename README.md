# Sistema de Gerenciamento de Biblioteca - PUC Goiás

Este projeto foi desenvolvido para a disciplina de Análise e Desenvolvimento de Sistemas da PUC Goiás. O objetivo é demonstrar a aplicação e evolução de padrões arquiteturais em Java puro: **Arquitetura em Camadas**, **Arquitetura Hexagonal (Ports & Adapters)** e **Arquitetura Orientada a Eventos**.

## 🚀 Como Compilar e Executar

### Pré-requisitos
* **Java JDK 17** ou superior (necessário para suporte a `records`).
* **Git** instalado para clonagem e gestão de histórico.

### Passo a Passo
1.  **Clonar o repositório:**
    ```bash
    git clone [https://github.com/jpolivbraga/ArqSoft.git](https://github.com/jpolivbraga/ArqSoft.git)
    ```
2.  **Aceder à pasta do projeto:**
    ```bash
    cd ArqSoft.git
    ```
3.  **Compilar o código:**
    ```bash
    javac -d bin src/biblioteca/**/*.java
    ```
4.  **Executar a aplicação:**
    ```bash
    java -cp bin biblioteca.apresentacao.Main
    ```

---

## 🏗️ Evolução Arquitetural e Decisões de Design

O sistema foi construído de forma incremental em três fases:

### 1. Arquitetura em Camadas (Etapa 1)
A estrutura inicial focou na separação de responsabilidades em:
- **Domínio:** Entidades (`Livro`, `Usuario`, `Emprestimo`) contendo as regras de negócio.
- **Infraestrutura:** Persistência em memória utilizando `HashMap`.
- **Apresentação:** Interface via console para interação com o utilizador.

### 2. Arquitetura Hexagonal / Ports & Adapters (Etapa 2)
Refatorámos o sistema para isolar o Domínio de tecnologias externas:
- **Portas (Ports):** Interfaces definidas dentro do domínio (`PortaLivroRepositorio`, `PortaEmprestimo`, etc.).
- **Adaptadores (Adapters):** Implementações concretas na infraestrutura, permitindo a troca de persistência (ex: de Memória para **CSV**) sem alterar as regras de negócio.



### 3. Comunicação Orientada a Eventos (Etapa 3)
Introduzimos o desacoplamento total para efeitos secundários:
- **EventBus:** Um barramento genérico que gere assinantes e publicação de mensagens.
- **Mensajaria Assíncrona:** Ao realizar um empréstimo, o sistema publica um `EmprestimoRealizadoEvento`.
- **Log com Timestamp:** O `ServicoDeLog` reage aos eventos gravando logs detalhados no ficheiro `biblioteca.log`.

---

## 📂 Organização de Pastas (Estrutura de Pacotes)

```text
src/biblioteca/
├── apresentacao/
│   └── Main.java                      # Ponto de entrada e configuração
├── dominio/
│   ├── evento/                        # Barramento e records de eventos
│   ├── porta/
│   │   ├── entrada/                   # Interfaces para casos de uso
│   │   └── saida/                    # Interfaces para repositórios e serviços externos
│   ├── servico/                       # Implementação da lógica de negócio
│   └── ...                            # Entidades (Livro, Usuario) e Enums
└── infraestrutura/
    └── adaptador/                     # Implementações CSV, Memória, Log e Console