# 📚 Sistema de Biblioteca – Spring Boot

![Java](https://img.shields.io/badge/Java-17-red?logo=openjdk) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot) 
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql) 
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)

---

## 🎯 Objetivo do Projeto
Este projeto tem como objetivo desenvolver um **Sistema de Biblioteca** utilizando **Java com Spring Boot**.  
A aplicação será dividida em dois perfis principais:

- 👨‍💼 **Funcionário** → gerenciar acervo, usuários, empréstimos e devoluções.  
- 📖 **Cliente** → consultar livros disponíveis, verificar disponibilidade e realizar empréstimos.

---

## 🛠️ Tecnologias Utilizadas
- **Java 17** – Linguagem principal  
- **Spring Boot 3.5.6** – Framework backend  
- **Spring Web** – Criação de APIs REST  
- **Spring Data JPA** – Acesso ao banco de dados  
- **MySQL** – Banco relacional  
- **Lombok** – Redução de boilerplate  
- **Thymeleaf** – Templates HTML (interface simples)  
- **JUnit + Mockito** – Testes automatizados  
- **Maven** – Gerenciador de dependências  

---

## ⚙️ Funcionalidades

### 👨‍💼 Funcionário
- Cadastrar, editar e remover **livros**  
- Cadastrar **usuários/clientes**  
- Gerenciar **empréstimos** e **devoluções**  

### 📖 Cliente
- Pesquisar livros por **título, autor ou gênero**  
- Verificar **disponibilidade** de exemplares  
- Solicitar **empréstimo**  

---
## 📅 Etapas do Projeto
1. **Pré-projeto** → Definição do escopo e protótipo inicial.  
2. **Modelagem** → Diagramas de Classes e ER.  
3. **Implementação** → CRUD de livros, usuários, empréstimos e devoluções.  
4. **Testes** → Validação de funcionalidades.  
5. **Entrega Final** → Apresentação + Documentação.  

---

## ✅ Resultados Esperados
- Sistema funcional para **gerenciamento de biblioteca**  
- **Organização eficiente** de empréstimos e devoluções  
- Interface inicial amigável  
- Estrutura modular para futuras expansões 🚀  

---

## 🛠️ Configuração do IntelliJ IDEA (Project Structure)

1. **Abrir Project Structure**
    - `File > Project Structure` ou `Ctrl + Alt + Shift + S (Windows)` / `Cmd + ; (Mac)`

2. **Configurar SDK**
    - Em **Project Settings → Project → Project SDK**, selecione **Java 17**
    - Se não existir, clique em **New → JDK** e selecione a pasta do JDK

3. **Configurar Language Level**
    - **Project Settings → Project → Project language level → 17**

4. **Configurar Módulo**
    - **Project Settings → Modules → Sources**: `src/main/java` → Sources
    - **Test Sources**: `src/test/java` → Test Sources
    - **Dependencies**: verificar que o módulo usa **Java 17**

5. **Aplicar e salvar**
    - Clique **Apply → OK**

---
