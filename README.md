# TaskManager - Gerenciador de Tarefas com PostgreSQL

Aplicacao de console em Java para gerenciamento de tarefas com banco de dados PostgreSQL.

## Requisitos

- Java 11 ou superior
- Maven 3.6 ou superior
- PostgreSQL 10 ou superior

## Instalacao e Configuracao

### 1. Clonar o Repositorio

```bash
git clone https://github.com/niyjn/TaskManager
cd TaskManager
```

### 2. Configurar Banco de Dados

Renomear o arquivo de exemplo na pasta src/main/resources:

```bash
cd src/main/resources
mv database.properties.example database.properties
cd ../../..
```

Editar `src/main/resources/database.properties`:

```properties
db.url=jdbc:postgresql://localhost:5432/taskmanager
db.user=postgres
db.password=sua_senha
```

Criar o banco de dados no PostgreSQL:

```sql
CREATE DATABASE taskmanager;
```

A aplicacao cria a tabela automaticamente na primeira execucao.

### 3. Compilar e Executar

```bash
mvn clean compile
mvn exec:java
```

Ou em um comando:

```bash
mvn clean compile exec:java
```

## Uso

Apos iniciar a aplicacao, escolha uma opcao no menu:

1. Adicionar tarefa
2. Ver todas as tarefas
3. Marcar tarefa como concluida
4. Remover tarefa
0. Sair

## Arquitetura

### Estrutura de Pastas

```
src/main/java/
├── Main.java                 Ponto de entrada
├── DAO/
│   ├── ConnectionFactory.java   Conexao com banco
│   └── JavaBridge.java          Operacoes CRUD
├── ui/
│   └── Menu.java                Interface com usuario
├── service/
│   └── TarefaService.java       Validacoes
└── model/
    └── Tarefa.java              Estrutura de dados
```

### Como Funciona

1. **ConnectionFactory** le o arquivo `database.properties` e cria conexoes com PostgreSQL
2. **JavaBridge** executa operacoes CRUD usando PreparedStatement
3. **Menu** gerencia a interface e fluxo de usuario
4. **TarefaService** valida entradas
5. **Tarefa** define a estrutura de dados

## Seguranca

O arquivo `database.properties` contem credenciais do banco de dados e nao deve ser commitado ao repositorio. Ele esta ignorado pelo `.gitignore`.

Para compartilhar o projeto com outros:

1. Mantenha `database.properties` localmente (nao commitado)
2. Use `database.properties.example` como template
3. Cada pessoa cria seu proprio `database.properties` com suas credenciais

## Implantacao em Outro Computador

### Para usuario que clona o repositorio

```bash
# Clonar
git clone https://github.com/niyjn/TaskManager
cd TaskManager

# Renomear arquivo de configuracao
cd src/main/resources
mv database.properties.example database.properties
cd ../../..

# Editar com suas credenciais
nano src/main/resources/database.properties  # ou outro editor

# Executar
mvn clean compile exec:java
```

Cada computador/usuario pode ter um banco de dados diferente (desenvolvimento, teste, producao).

## Operacoes de Banco de Dados

### CREATE (Inserir)

```
INSERT INTO tarefas (tarefa, status) VALUES (?, ?)
```

### READ (Consultar)

```
SELECT * FROM tarefas ORDER BY id
```

### UPDATE (Atualizar)

```
UPDATE tarefas SET status = ? WHERE id = ?
```

### DELETE (Deletar)

```
DELETE FROM tarefas WHERE id = ?
```

## Estrutura do Banco

Tabela `tarefas`:

| Coluna | Tipo | Descricao |
|--------|------|-----------|
| id | SERIAL | ID unico (chave primaria) |
| tarefa | VARCHAR(255) | Descricao da tarefa |
| status | BOOLEAN | Falso = pendente, Verdadeiro = concluida |

## Solucao de Problemas

### Erro: "Arquivo database.properties nao encontrado"

Certifique-se de que o arquivo existe em `src/main/resources/`:

```bash
cd src/main/resources
mv database.properties.example database.properties
```

### Erro: "Conexao recusada"

Verifique se PostgreSQL esta rodando e se as credenciais estao corretas em `database.properties`.

### Erro: "Database does not exist"

Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE taskmanager;
```

## Dependencias

- PostgreSQL JDBC Driver 42.7.1
- JUnit 4.13.2 (testes)

## Commits e Versionamento

Nunca commitir `database.properties`. O arquivo ja esta no `.gitignore`.

Para usar em um novo computador:

1. Clonar o repositorio
2. Renomear `database.properties.example` para `database.properties` em `src/main/resources/`
3. Editar com suas credenciais
4. Executar normalmente

## Tecnologias Utilizadas

- Java 11
- Maven
- PostgreSQL
- JDBC
- PreparedStatement (seguranca contra SQL Injection)
