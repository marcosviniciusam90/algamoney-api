# Algamoney-api (ainda em construção)

Fonte do aprendizado: https://www.algaworks.com/curso/fullstack-angular-e-spring

> Projeto com arquitetura SOFEA, no qual o frontend (Angular) da aplicação irá manipular dados de um servidor via requisições REST.


## Em produção
[![Netlify Status](https://api.netlify.com/api/v1/badges/f58316e1-0e43-45dc-9230-433e5c43ffdd/deploy-status)](https://app.netlify.com/sites/mvam-algamoney/deploys)
- Aplicação (frontend): https://mvam-algamoney.netlify.app
- API (backend): https://mvam-algamoney-api.herokuapp.com 
- Coleção e variáveis do Postman: [Link](https://github.com/marcosviniciusam90/algamoney-api/blob/master/backend/doc)
- **Instruções para acesso à aplicação**: [Link](https://github.com/marcosviniciusam90/algamoney-api#autorizacao)


## Backend (90% concluído)

### O que foi utilizado?
- Java
- Spring
- REST
- MySQL
- Flyway
- Hibernate/JPA
- Maven
- Lombok
- MapStruct
- Mockito
- entre outros;

### Recursos
- Bean Validation
- Bean Configuration
- Properties Configuration
- Exceptions Handler
- Spring Events
- Query Methods
- Criteria Query (com uso de classes Metamodel)
- Pagination/Sorting
- CORS
- Spring Security (Basic e OAuth2/JWT + controle de permissões por usuário)
- Spring Profiles (alterna os tipos de segurança da aplicação entre Basic (basic-security) e OAuth2 (oauth-security)
- Spring MockMvc (para os testes automáticos das requisições REST)
- entre outros;


## Frontend (80% concluído)

### O que foi utilizado?
- Angular 11
- JavaScript/TypeScript
- HTML
- CSS
- PrimeNG 11
- NPM

### Recursos
- Property binding e two-way data binding
- Requisições REST
- HttpInterceptor (intercepta requisições HTTP e adiciona o access token)
- Diretivas
- Pipes
- Serviços (injeção de dependências)
- Formulários com validação dos dados de entrada
- Tabelas de dados com paginação Lazy
- Toast
- Rotas com guarda de rotas (CanActivate)

<h3 id="autorizacao">Acesso à aplicação</h3>

- Usuário/senha: `admin@algamoney.com/admin` ou `maria@algamoney.com/maria`<br>
Obs: o usuário **maria** só tem permissão para realizar as consultas, já o usuário **admin** possui todas as permissões.


## TODO
- Gerar documentação swagger da API com SpringFox / Spring OpenAPI