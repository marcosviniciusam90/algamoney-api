# Algamoney-api

Fonte do aprendizado: https://www.algaworks.com/curso/fullstack-angular-e-spring

O projeto ainda est� em constru��o (**40% concluido**)

Projeto com arquitetura SOFEA, no qual o frontend (Angular) da aplica��o ir� manipular dados de um servidor via requisi��es REST.<br/>
Ser� feito deploy no Heroku, tanto do frontend como da API (backend).

## Backend (https://algamoney-api-mvam.herokuapp.com)

**Tecnologias**<br/>
- Java
- Spring
- REST
- MySQL
- Flyway
- Hibernate/JPA
- Maven
- Lombok
- ModelMapper

- entre outras;

**Conceitos**<br/>
- Bean Validation
- Bean Configuration
- Properties Configuration
- Exceptions Handler
- Spring Events
- Query Methods
- Criteria Query (com uso de classes Metamodel)
- Pagination/Sorting
- CORS
- Spring Security (Basic, OAuth2 + JWT, Roles/Privileges)
- Spring Profiles
- entre outros;


## Frontend (em constru��o)

## TODO
- No Lombok, adicionar inst�ncia do Logger para fazer log de execu��o dos servi�os
- No Lombok, adicionar anota��o @AllArgsConstructor(onConstructor = @__(@Autowired))
- Criar testes autom�ticos testando o Mapper (utilizar builder do lombok para construir objetos)
- Gerar documenta��o swagger da API usando SpringFox / io.swagger.annotations