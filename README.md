# kyrosb-01
Desenvolvimento de uma API para gerenciamento de dados de clientes.

## Como funciona

O projeto consiste de uma api contendo 4 *endpoints*, os quais são listados abaixo: 
* DELETE: api/v1/client/clientId
* POST: api/v1/client
* PUT: api/v1/client/clientId
* GET: api/v1/client/clientId

## Persitência de dados

A persistência de dados é feita através do banco de dados *in-memory* **H2**.

Como a persistência é feita em memória os dados são apagados na reincialização do sistema, isso facilita bastante o ambiente de testes e desenvolvimento. 

Além disso, como o H2 é uma dependencia do maven, não é necessária a instalação de um banco de dados.

## Dados

`Importante: A inserção, deleção, e atualização de dados foram realizadas através do aplicativo Postman.`

A inserção de dados também foi feita através do arquivo *data.sql*, que se encontra em `src/main/resources`.

## Execução

Para o desenvolvimento do projeto, foi utilizado o Spring Boot. Portanto, há duas formas de execução: 

### Aplicação
- Terminal:
 > mvn spring-boot:run
 - IDE: A utilizada foi o IntelliJ
 > Shift + F10 ou botão direito em KyrosApplication -> Run KyrosApplication

### Testes
Os testes encontram-se no diretório: src/test/java/com.example.kyros/controller/ClientControllerTest

- Terminal
> mvn -Dtest=ClientControllerTest test
- IDE
> Shift + F10 ou botão direito em ClientControllerTest -> Run ClientControllerTest
