# Algamoney-api

Fonte do aprendizado: https://www.algaworks.com/curso/fullstack-angular-e-spring

O projeto ainda está em construção (**40% concluido**)

Projeto com arquitetura SOFEA, no qual o frontend (Angular) da aplicação irá manipular dados de um servidor via requisições REST.<br/>
Será feito deploy no Heroku, tanto do frontend como da API (backend).

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


## Frontend (em construção)

## TODO
- No Lombok, adicionar instância do Logger para fazer log de execução dos serviços
- No Lombok, adicionar anotação @AllArgsConstructor(onConstructor = @__(@Autowired))
- Criar testes automáticos testando o Mapper (utilizar builder do lombok para construir objetos)
- Gerar documentação swagger da API usando SpringFox / io.swagger.annotations