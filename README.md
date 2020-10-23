# GestaoPedido
API Rest Spring Boot para gestão de pedido

## Projeto

O projeto consiste de um domínio de pedidos de produtos.

A ideia do projeto foi utilizar o principio de responsabilidade única que está presente no SOLID. Por isso, cada classe tem o seu
encapsulamento e é pequena. Também não existem regras de negócio espalhadas, principalmente nas controllers.

Para ajudar em um melhor design, os testes foram criados com o objetivo de ter a inversão de dependência e a diminuição de erros.

# Padrão REST

Respeitando o padrão REST, toda vez que um POST for acionado, o status 201 de criado será enviado e um header chamado Location terá
o caminho para encontrar a entidade salva, exemplo location -> gestao-pedido/pedido/1

Outros endpoints poderá ser acessado

- POST /gestao-pedido/categoria
- GET /gestao-pedido/categoria/{id}
- POST /gestao-pedido/produto
- GET /gestao-pedido/produto/{id}

# Feito no projeto

- Testes automatizados 
- Spring data com MySQL
- Spring security usando JWT
- Git
- Flyway



