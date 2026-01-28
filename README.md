
## Visão Geral da Arquitetura

O projeto segue uma arquitetura modular, separando responsabilidades em pacotes distintos (Model, Service e UI). A persistência de dados é realizada em memória durante o tempo de execução.

### Estrutura de Pacotes

*   **model**: Contém as definições de estruturas de dados.
    *   `Tarefa.java`: Define a classe aninhada estática `Tarefas`, que representa a unidade de dados principal. Encapsula o estado da tarefa (descrição e status de conclusão) e comportamento de formatação de string.
*   **service**: Contém a lógica de negócios e manipulação de dados.
    *   `TarefaService.java`: Classe utilitária composta inteiramente por métodos estáticos. Centraliza as operações de CRUD (Create, Read, Update, Delete) sobre a lista de tarefas e implementa validações de entrada e integridade da lista.
*   **ui**: Gerencia a interação com o usuário via terminal.
    *   `Menu.java`: Implementa o loop principal da aplicação, captura de entradas via `Scanner`, tratamento de exceções de entrada e renderização do menu.

## Detalhes de Implementação

### Gerenciamento de Dados

O estado da aplicação é mantido em uma estrutura `ArrayList<Tarefas>` instanciada na classe `Main` e injetada nas camadas subsequentes. Isso demonstra o uso de coleções do Java Collections Framework para manipulação dinâmica de dados.

### Tratamento de Exceções

O projeto utiliza `try-catch` para garantir a robustez da aplicação, especificamente na classe `Menu`.
*   `InputMismatchException`: Capturada para evitar falhas quando o usuário insere tipos de dados incorretos.
*   `IllegalArgumentException`: Lançada explicitamente pelo `TarefaService` e `Menu` para sinalizar violações de regras de negócio (ex: índices inválidos, entradas vazias) e capturada no loop do menu para feedback ao usuário.

### Modelagem de Objetos

A classe `Tarefas` é definida como `public static class` dentro de `Tarefa`. A decisão de design centraliza a entidade, permitindo encapsulamento dos atributos `nome` e `concluiu`. O método `toString()` foi sobrescrito para fornecer uma representação visual do estado da tarefa (`[X]` ou `[ ]`), facilitando a exibição direta na interface.

### Interface de Usuário (Console)

A interface utiliza sequências de escape ANSI (`\033[H\033[2J`) no método `limparTela` para resetar o buffer do terminal, proporcionando uma experiência de usuário mais limpa entre as transições de menu.

## Considerações de Design

1.  **Stateless Services**: A classe `TarefaService` não mantém estado interno, operando puramente sobre os argumentos passados, o que facilita testes e previsibilidade.
2.  **Separação de Interesses**: A lógica de apresentação (`System.out.println`) está majoritariamente segregada da lógica de dados, embora o serviço ainda contenha algumas chamadas de impressão para feedback direto.

## Como Executar

Para compilar e executar o projeto, utilize o JDK (Java Development Kit).

1.  Compile os arquivos:
    ```bash
    javac -d out src/Main.java src/model/*.java src/service/*.java src/ui/*.java
    ```
2.  Execute a classe principal:
    ```bash
    java -cp out Main
    ```

    :)
