**Pr?-requisito**

1. Instale o cliente do Heroku (www.heroku.com)


**Deploy da API em produ??o**

1. Criando projeto e configurando Heroku (ver imagem abaixo)

![Configuração do Heroku](config.jpg)


2. Configurar no projeto os arquivos:

- application-prod.properties : ativar HTTPS, definir origem, colocar as variáveis referenciadas 
								e setadas no Heroku para conexão ao BD (ver imagem acima)
- Procfile : configurar este arquivo, indicando o nome do profile spring (prod), este nome 
             será utilizado para carregar o arquivo properties apropriado
			 

3. Commitar os arquivos;


4. Fazer push/deploy das alterações.
- `git push heroku [branch]`
- Ex: `git push heroku master`      (vai subir todos os commits para o servidor do Heroku que já está em execução)


5. Para ver os logs da aplicaçãoo:
- `heroku logs --tail`



-------------------------------------------------------------------------------------------------------------
 <span style="color:red">Failed to execute goal org.apache.maven.plugins:maven-resources-plugin:3.2.0:resources (default-resources) 
       on project algamoney-api: Input length = 1</span>

- Tive o erro acima ao tentar fazer push para o Heroku
- Meus arquivos .properties estavam com encoding diferente de UTF-8. 
- **SOLUÇÃO**: Só foi preciso excluir e recriar os arquivos (Bloco de notas) com UTF-8.