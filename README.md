# NLW Microservice Quiz

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

Esse projeto foi desenvolvido durante o NLW da RocketSeat, focado no Desenvolvimento de um microserviço trabalhando com Arquiterura de microserviço além de trabalhar com o relacionamento de entidades.**

## Instação

1. Clone o repositório:

2. Instale as depêndencias com o Maven.

3. Tenha o docker instalado.

4. Entre na pasta do projeto e rode no terminal:
```bash
docker build --tag=ms-quiz-app:latest .
```

```bash
docker compose up -d
```

## Como usar

1. A API ficará disponível na URL: http://localhost:8081


## API Endpoints
Os endpoints da API são os seguintes:

**API Ranking**
```markdown
GET /ranking/top10 - Retorna o top 10 usuários com maior nota

```


**API Questions**
```markdown
GET /questions/technology/{tech} - Retorna todas as questões de acordo com a linguagem
```


**API Certification**
```markdown
POST /certification/verifyCertification - Retorna se o usuario pode realizar ou não o teste para receber a certificação
```

**BODY**
```json
{
    "email" : "gabriel@gmail.com",
    "tech" : "JAVA"
}
```

```markdown
POST /certification/answer - Realiza a prova enviando as alternativas e recebendo a certificação
```

**BODY**
```json
{
    "email" : "gabriel@gmail.com",
    "tech" : "JAVA",
    "questionsAnswer": [
        {
        "questionID": "c5f02721-6dc3-4fa6-b46d-6f2e8dca9c66",
        "alternativeID" : "98f6891b-5f14-4b8e-bb87-46456a2610d4"
        },
        {
        "questionID": "b0ec9e6b-721c-43c7-9432-4d0b6eb15b01",
        "alternativeID" : "f8e6e9b3-199b-4f0d-97ce-7e5bdc080da9"
        },
        {
        "questionID": "f85e9434-1711-4e02-9f9e-7831aa5c743a",
        "alternativeID" : "3528a132-9c12-4c8a-aa93-9f6e813c43d1"
        }
    ]
}
```






