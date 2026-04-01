<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" />
  <img src="https://img.shields.io/badge/JPA-59666C?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" />
</div>

<div align="center">

# Burn Book
### Arquitetura de Software e Desenvolvimento Back-end

Desenvolvido por: Ana Beatriz de Oliveira Ribeiro, Emanuelle Cristina Hostin e Hugo Deleon Geminiani de Souza Pain  

</div>

---

## Contexto

O projeto **Burn Book API** foi desenvolvido com o objetivo de estruturar e disponibilizar uma API robusta para suporte a uma aplicação mobile construída em Kotlin. A solução busca centralizar regras de negócio, gerenciamento de dados e comunicação com o front-end, garantindo organização, escalabilidade e manutenção do sistema.

O desenvolvimento foi conduzido com foco em boas práticas de engenharia de software, incluindo separação de responsabilidades, padronização de código e utilização de arquitetura em camadas.



## Arquitetura da Aplicação

A API segue o padrão de arquitetura em camadas, promovendo desacoplamento e clareza estrutural. 


### Estrutura de Diretórios

- **controller**: responsável pelo tratamento das requisições HTTP  
- **service**: implementação das regras de negócio  
- **repository**: acesso e manipulação dos dados  
- **model**: entidades persistidas no banco de dados  
- **dto**: objetos de transferência de dados  
- **mapper**: conversão entre entidades e DTOs  

---

## Tecnologias Utilizadas

### Back-end
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

### Integração
- API REST (JSON)

### Front-end
- Kotlin (Android Studio)

---

## Gerenciamento de Projeto

### Jira
https://ffocp.atlassian.net

O Jira foi utilizado para:
- Organização de tarefas
- Definição de responsabilidades
- Acompanhamento do progresso
- Controle de issues e bugs

---

## Prototipação

### Figma
https://www.figma.com/design/UvCW4LOtiQkDBERYpVwilB/Untitled

Responsável pela definição da interface e experiência do usuário, garantindo alinhamento entre front-end e back-end.

---

## Repositórios

### Back-end
https://github.com/HugoDeleonP/burn-book.git

### Front-end
https://github.com/Ana18022008/burn-book-front.git

---

## Versionamento e Git Flow

O projeto adota o padrão **Git Flow** como estratégia de versionamento, sendo um ponto fundamental para a organização do desenvolvimento em equipe.

### Estrutura de Branches

- `main`: versão estável da aplicação  
- `develop`: integração contínua das funcionalidades  
- `feature/*`: desenvolvimento de novas funcionalidades  

### Fluxo de Trabalho

1. Criação de uma branch a partir da `develop`  
2. Implementação da funcionalidade  
3. Realização de commits padronizados  
4. Envio para o repositório remoto  
5. Abertura de Pull Request para `develop`  
6. Revisão de código  
7. Integração após aprovação  

---

## Uso de Issues

As issues foram utilizadas como ferramenta de documentação e controle, permitindo:

- Registro de funcionalidades  
- Organização do backlog  
- Identificação e correção de erros  
- Acompanhamento do desenvolvimento  

---

### Considerações Finais

O desenvolvimento da Burn Book API prioriza a aplicação de conceitos sólidos de arquitetura de software, organização em equipe e boas práticas de desenvolvimento. O uso de ferramentas como Git Flow, Jira e Figma contribuiu diretamente para a qualidade, rastreabilidade e evolução do projeto.

Este projeto possui caráter acadêmico, com foco no aprendizado e aplicação prática de conceitos modernos de desenvolvimento back-end.

