# Post Service

## Descrção do serviço

Esse é uma demo simples criada para extrair dados das APIs de *comments* e *posts* da API [JSON
 Placeholder](https://jsonplaceholder.typicode.com).
 
### Endpoints

```
Popular DB: POST /comments

Buscar comentários do DB: GET /comments[?page=size=sort=]
Buscar posts do DB: GET /posts[?page=size=sort=]

Buscar 5 posts com mais comentários: GET /posts/top

```

## Ferramentas utilizadas
Linguagem: Java
IDE: Intellij IDEA 2019.2 Ultimate
SO: MacOS High Sierra 10.13.6

Frameworks, ferramentas, etc.:
- Spring suite (`~v5.2`): aspects, data-jpa, web e suas dependências
- Spring suite (`~v2.2`): boot, boot-devtools, boot-configuration-processor e suas dependências
- Flyway (`~v6.0`)
- MySQL (`~v5.7`)
- Docker (`~19.03`)
- Hibernate e Hibernate Validations (apesar de não ter inputs do usuário)

## Cloud
Principais serviços AWS: 
- CodeBuild,
- CodePipeline,
- Elastic Beanstalk (Load Balancer, RDS/MySQL)
- Demais serviços (Cloudwatch, CloudFormation, Systems Manager...)

## Dificuldades
- Configurar ambiente do Jenkins usando ECS diretamente pelo terraform
  - Deveria ser mais simples, porém eu quis buscar infras mais complexas que não necessariamente
   conheceria vários detalhes. Por isso, resolvi abortar esses passos (apesar de querer
    automatizar a infra atual com terraform)
     
- Twitter API
  - Fiz o pedido do acesso a API do Twitter logo após receber a proposta do projeto porém não
   recebi aprovação. Felizmente, pude utilizar outra API. Eu poderia ter entrado em contato com o
    suporte do Twitter para tentar priorizar este processo, porém pensei que para este use case
     seria melhor outra API. Vale ressaltar: https://betanews.com/2018/07/25/twitter-api-access-developer/
     
- Problemas com ambiente durante upgrade do MySQL, intellij, etc.

- Maven spotify dockerfile plugin: por algum motivo, a última versão do plugin não está pegando
 as varáveis de autenticação do Docker da forma documentada.

## Possíveis improvements
- Utilizar filas para realizar interações com terceiros de forma asíncrona (SQS, ActiveMQ, etc.)
- Utilizar um DB orientado a documentos (DocumentDB, Mongo, etc.)
