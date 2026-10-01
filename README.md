# 🏠 IPTUScrap

<p align="center">
  <strong>Automação de consultas imobiliárias e processamento de documentos com Java.</strong>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium">
  <img src="https://img.shields.io/badge/Apache%20PDFBox-00599C?style=for-the-badge" alt="Apache PDFBox">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
</p>

---

## 📖 Sobre o projeto

O **IPTUScrap** é uma aplicação de automação desenvolvida em Java para executar etapas de consultas imobiliárias, processamento de notificações de IPTU e consolidação de informações.

O sistema utiliza o Selenium WebDriver para automatizar a navegação em páginas web e o Apache PDFBox para extrair informações textuais de documentos PDF.

Após a etapa de consulta imobiliária, a aplicação processa os documentos obtidos e utiliza os dados extraídos em consultas adicionais por meio de uma plataforma externa, registrando os resultados em um arquivo CSV.

O projeto reúne conceitos de **web scraping, automação de navegador, processamento de documentos e integração de etapas de consulta**.

## ✨ Principais recursos

* 🌐 Automação de navegação com Selenium WebDriver.
* 🏠 Consulta de informações no portal imobiliário da Prefeitura de São Paulo.
* 📄 Download automatizado de notificações de IPTU em PDF.
* 🔎 Extração e interpretação de texto utilizando Apache PDFBox.
* 🔗 Encadeamento de consultas entre diferentes serviços web.
* 📊 Consolidação dos resultados em arquivo CSV.
* 🧩 Organização da aplicação em classes com responsabilidades distintas.

## ⚙️ Como funciona

O processamento está organizado em etapas:

```
┌──────────────────────────────┐
│       Inicialização         │
└──────────────┬───────────────┘
               ↓
┌──────────────────────────────┐
│ Consulta no portal de IPTU   │
│ da Prefeitura de São Paulo   │
└──────────────┬───────────────┘
               ↓
┌──────────────────────────────┐
│ Identificação dos registros  │
│ imobiliários encontrados     │
└──────────────┬───────────────┘
               ↓
┌──────────────────────────────┐
│ Consulta e download de PDFs  │
└──────────────┬───────────────┘
               ↓
┌──────────────────────────────┐
│ Extração de dados com        │
│ Apache PDFBox                │
└──────────────┬───────────────┘
               ↓
┌──────────────────────────────┐
│ Consulta em serviço externo  │
└──────────────┬───────────────┘
               ↓
┌──────────────────────────────┐
│ Consolidação dos resultados  │
│ em arquivo CSV               │
└──────────────────────────────┘
```

O fluxo depende da disponibilidade dos portais, da estrutura dos documentos e da compatibilidade dos seletores utilizados na automação.

## 🛠️ Tecnologias utilizadas

| Tecnologia                   | Aplicação no projeto                                          |
| ---------------------------- | ------------------------------------------------------------- |
| **Java**                     | Implementação da lógica de automação                          |
| **Selenium WebDriver 4.2.0** | Navegação e interação com páginas web                         |
| **Apache PDFBox 2.0.25**     | Leitura e extração de texto de documentos PDF                 |
| **PDF2DOM 2.0.1**            | Dependência incluída no projeto para processamento de PDF/DOM |
| **Maven**                    | Gerenciamento de dependências                                 |
| **CSV**                      | Persistência dos resultados consolidados                      |

## 📁 Estrutura do projeto

```
IPTUScrap/
├── src/
│   └── main/
│       └── java/
│           ├── Main/
│           │   └── Main.java
│           ├── PrefeituraGeral/
│           │   └── Prefeitura.java
│           ├── PrefeituraSenha/
│           │   └── PrefeituraSenha.java
│           ├── PuxarDados/
│           │   └── Assertiva.java
│           └── ReaderPdf/
│               └── Pdf.java
├── pom.xml
└── README.md
```

### Responsabilidade das classes

**`Main`**

* Ponto de entrada da aplicação.
* Inicializa o fluxo de consulta.

**`Prefeitura`**

* Automatiza a consulta de endereços no portal imobiliário.
* Identifica e armazena os registros encontrados.

**`PrefeituraSenha`**

* Automatiza o acesso ao portal de notificações de IPTU.
* Gerencia o download dos documentos PDF.
* Percorre os registros encontrados para executar as consultas.

**`Pdf`**

* Lê os documentos PDF utilizando PDFBox.
* Extrai informações textuais relevantes para as etapas seguintes.
* Organiza os dados extraídos em estruturas utilizadas pela aplicação.

**`Assertiva`**

* Automatiza a navegação na plataforma externa.
* Executa consultas com os dados previamente processados.
* Consolida os resultados em arquivo CSV.

## ▶️ Como executar

### Pré-requisitos

* JDK compatível com o projeto.
* Maven.
* Google Chrome compatível com a versão do ChromeDriver.
* Acesso aos serviços web utilizados.
* Credenciais e permissões válidas para os serviços que exigem autenticação.

### Passos

1. Clone o repositório:

   ```
   git clone https://github.com/RyanAlvim/IptuScrap.git
   ```

2. Entre na pasta do projeto:

   ```
   cd IptuScrap
   ```

3. Confira as dependências e a configuração do ChromeDriver.

4. Configure as credenciais necessárias por meio de uma configuração segura, fora do código-fonte.

5. Execute a classe `Main.Main` pela IDE ou configure a execução com Maven.

> **Importante:** o projeto foi desenvolvido com seletores e fluxos específicos dos sites utilizados originalmente. Alterações nesses sites podem exigir manutenção no código.

## 🔐 Privacidade e segurança

Como a aplicação processa documentos imobiliários e pode lidar com dados pessoais, sua utilização deve respeitar a legislação aplicável, as permissões de acesso e os termos dos serviços consultados.

* Utilize somente dados para os quais exista autorização e finalidade legítima.
* Não publique CPF, telefones, endereços ou documentos reais no repositório.
* Não versione credenciais, sessões ou arquivos CSV contendo dados pessoais.
* Armazene credenciais em variáveis de ambiente ou em um mecanismo apropriado de configuração.
* Restrinja o acesso aos arquivos produzidos durante a execução.

## 🔧 Melhorias futuras

* [ ] Substituir esperas fixas por esperas explícitas do Selenium.
* [ ] Centralizar seletores e URLs em configurações.
* [ ] Tratar falhas de navegação e documentos ausentes.
* [ ] Implementar logs estruturados.
* [ ] Garantir o fechamento dos recursos em caso de erro.
* [ ] Melhorar a validação dos dados extraídos dos PDFs.
* [ ] Parametrizar o exercício fiscal em vez de utilizar um ano fixo.
* [ ] Adicionar testes automatizados para o processamento dos documentos.
* [ ] Separar credenciais e dados sensíveis do código-fonte.

## 🎯 Objetivos técnicos

Este projeto explora problemas práticos encontrados em automações de processos:

* Automação de aplicações web.
* Extração e interpretação de documentos.
* Integração de diferentes etapas de consulta.
* Tratamento e transformação de dados.
* Geração de arquivos para consumo posterior.
* Organização de código Java em módulos.

## 📌 Estado do projeto

Projeto desenvolvido para automatizar um fluxo de consultas imobiliárias e processamento documental.

O código representa uma implementação baseada na estrutura dos serviços utilizados originalmente e pode exigir adaptações para funcionar com versões atuais dos portais e das ferramentas.

## 👨‍💻 Autor

**Ryan Rodrigues Alvim**

* GitHub: [@RyanAlvim](https://github.com/RyanAlvim)

---

<p align="center">
  <i>Automação, processamento de dados e integração de sistemas com Java.</i>
</p>
