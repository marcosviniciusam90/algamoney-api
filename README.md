# Algamoney-api (ainda em constru��o)

Fonte do aprendizado: https://www.algaworks.com/curso/fullstack-angular-e-spring

> Projeto com arquitetura SOFEA, no qual o frontend (Angular) da aplica��o ir� manipular dados de um servidor via requisi��es REST.


## Em produ��o
[![Netlify Status](https://api.netlify.com/api/v1/badges/f58316e1-0e43-45dc-9230-433e5c43ffdd/deploy-status)](https://app.netlify.com/sites/mvam-algamoney/deploys)
- Aplica��o (frontend): https://mvam-algamoney.netlify.app
- API (backend): https://mvam-algamoney-api.herokuapp.com 
- Cole��o e vari�veis do Postman: [Link](https://github.com/marcosviniciusam90/algamoney-api/blob/master/backend/doc)
- **Instru��es para acesso � aplica��o**: [Link](https://github.com/marcosviniciusam90/algamoney-api#autorizacao)


## Backend (90% conclu�do)

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
- Spring Security (Basic e OAuth2/JWT + controle de permiss�es por usu�rio)
- Spring Profiles (alterna os tipos de seguran�a da aplica��o entre Basic (basic-security) e OAuth2 (oauth-security)
- Spring MockMvc (para os testes autom�ticos das requisi��es REST)
- entre outros;


## Frontend (80% conclu�do)

### O que foi utilizado?
- Angular 11
- JavaScript/TypeScript
- HTML
- CSS
- PrimeNG 11
- NPM

### Recursos
- Property binding e two-way data binding
- Requisi��es REST
- HttpInterceptor (intercepta requisi��es HTTP e adiciona o access token)
- Diretivas
- Pipes
- Servi�os (inje��o de depend�ncias)
- Formul�rios com valida��o dos dados de entrada
- Tabelas de dados com pagina��o Lazy
- Toast
- Rotas com guarda de rotas (CanActivate)

<h3 id="autorizacao">Acesso � aplica��o</h3>

- Usu�rio/senha: `admin@algamoney.com/admin` ou `maria@algamoney.com/maria`<br>
Obs: o usu�rio **maria** s� tem permiss�o para realizar as consultas, j� o usu�rio **admin** possui todas as permiss�es.


## TODO
- Gerar documenta��o swagger da API com SpringFox / Spring OpenAPI