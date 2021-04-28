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
- entre outras;

**Conceitos**<br/>
- Bean Validation
- Bean Configuration
- Properties Configuration
- Exceptions Handler
- Spring Events
- Metamodels
- Query Methods
- Criteria Query
- Pagination/Sorting
- CORS
- Spring Security (Basic, OAuth2 + JWT, Roles/Privileges)
- Spring Profiles
- Lombok
- ModelMapper
- entre outros;


## Frontend (em construção)

## TODO
- No Lombok, adicionar instância do Logger para fazer log de execução dos serviços
- No Lombok, adicionar anotação @AllArgsConstructor(onConstructor = @__(@Autowired))
- Converter Entidade/DTO para trabalhar nas requisições -> Mapper (https://mapstruct.org/)
- Criar testes automáticos testando o Mapper (utilizar builder do lombok para construir objetos)
- Gerar documentação swagger da API usando SpringFox / io.swagger.annotations