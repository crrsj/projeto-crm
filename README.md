🚀 CRM Elite - Backend APIAPI robusta para Gestão de Relacionamento com Clientes (CRM), desenvolvida com o que há de mais moderno no ecossistema Java. Focada em alta performance, segurança e escalabilidade.
🛠 Tecnologias UtilizadasJava 21 (LTS): Utilizando Virtual Threads para alta concorrência com baixo consumo de recursos.
Spring Boot 3.x: Base da aplicação para facilitar o desenvolvimento e configuração.
Spring Security & JWT: Autenticação e autorização robusta baseada em tokens.
PostgreSQL: Banco de dados relacional para persistência de dados críticos.
Docker & Docker Compose: Containerização completa para facilitar o deploy e ambiente de desenvolvimento.
SpringDoc / Swagger: Documentação interativa da API (OpenAPI 3.1).
🚀 Como Rodar o ProjetoGraças ao Docker, você não precisa instalar o PostgreSQL ou Java localmente para testar.
Pré-requisitosDocker e Docker Compose instalados.Passo a PassoClone o repositório:Bashgit clone https://github.com/seu-usuario/crm-elite-backend.git
Navegue até a pasta do projeto e suba os containers:Bashdocker-compose up --build
A API estará disponível em: http://localhost:8080
📖 Documentação (Swagger)Com a aplicação rodando, você pode acessar a interface do Swagger para explorar e testar todos os endpoints:
🔗 http://localhost:8080/swagger-ui/index.htmlNota: Para testar rotas protegidas, utilize o botão "Authorize" no topo do Swagger e insira o seu Bearer Token JWT.
⚙️ Variáveis de AmbienteAs principais configurações podem ser ajustadas via variáveis de ambiente no docker-compose.yml:
VariávelDescriçãoValor PadrãoDB_HOSTHost do banco de dadosdbDB_NAMENome do bancocrm_dbJWT_SECRETChave secreta para assinatura dos tokensminha-chave-secreta-123.

![crm1](https://github.com/user-attachments/assets/a6cb8ce2-3976-40dc-9866-3ed529afe193)

![crm2](https://github.com/user-attachments/assets/bc63552d-cd76-46a6-889c-0ee0391bb652)

![crm3](https://github.com/user-attachments/assets/b78ce878-c31e-43d3-b937-baea65719e4c)
