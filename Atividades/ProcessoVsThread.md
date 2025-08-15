# Processo Vs Thread - Conceitos e Diferenças

<h2 align="center"> Processo </h2>
Processso é uma instância de um programa em execução. É como um contêiner que agrupa todos os recursos necessários para que o programa funcione, sendo eles: espaço de endereçamento de memória, recursos de sistema e estado de execução.
Cada processo é independente e isolado dos outros. A falha de um processo não afeta outros processos em execução no sistema.
</br></br>


<h2 align="center"> Thread </h2>
Thread é a menor unidade de execução que o sistema operacional pode gerenciar. As threads existem dentro de um processo. Um processo tradicional possui uma única thread, mas processos modernos podem ter múltiplas threads rodando
simultaneamente. Todas as threads dentro de um mesmo processo compartilham os mesmos recursos desse processo, incluindo seu espaço de endereçamento de memória.
</br></br>


<h2 align="center"> Diferenças </h2>

| Característica | Processo | Thread |
| :--- | :--- | :--- |
| **Definição** | Uma instância de um programa em execução. | Uma unidade de execução dentro de um processo. |
| **Memória** | Possui um espaço de memória próprio e isolado. | Compartilha o espaço de memória do processo ao qual pertence. |
| **Criação** | A criação de um processo é uma operação "pesada" e lenta, pois o sistema operacional precisa alocar um novo espaço de memória e estruturas de dados. | A criação de uma thread é "leve" e rápida, pois apenas cria um novo fluxo de execução dentro de um processo já existente. |
| **Comunicação** | A comunicação entre processos (IPC) é complexa e lenta, pois o sistema operacional precisa mediar a troca de dados para não violar o isolamento de memória. | A comunicação entre threads é simples e rápida, pois elas podem ler e escrever nas mesmas variáveis e estruturas de dados diretamente na memória compartilhada. |
| **Isolamento** | Alto. Uma falha em um processo não afeta outros processos. | Baixo. Uma falha em uma thread pode causar o travamento de todo o processo, derrubando todas as outras threads junto. |
| **Recursos** | Consome mais recursos do sistema (memória, tempo de processador para gerenciamento). | Consome menos recursos, sendo mais eficiente para executar tarefas concorrentes. |
