#language: pt

Funcionalidade: Dashboard
	Como um usuário autenticado no sistema
	Eu quero acessar o dashboard principal
	Para que eu possa visualizar os indicadores da aplicação
	
Contexto: Autenticação do usuário
	Dado eu estou na página de login do sistema
	E eu informo o nome de usuário "Admin"
	E eu informo a senha "admin123"
	Quando eu solicito o acesso ao sistema
	Então eu sou autenticado com sucesso
	
Cenário: Acesso ao dashboard principal
	Dado eu estou na página do dashboard
	Então o sistema exibe os indicadores principais da aplicação
	
Cenário: Visualizar ações pendentes no dashboard
	Dado eu estou na página do dashboard
	Quando eu acesso a opção de visualizar ações pendentes
	Então o sistema exibe uma lista das ações

