# 🏠 IPTU Scrap

> Automação desenvolvida em Java com Selenium para consulta de registros de IPTU e consolidação de informações relacionadas a imóveis.

---

## 📌 Sobre o projeto

O **IPTU Scrap** é um projeto de automação desenvolvido para facilitar a consulta de informações relacionadas a **IPTUs de imóveis**.

A aplicação utilizava **Selenium WebDriver** para automatizar a navegação no sistema de consulta, permitindo realizar consultas de diferentes registros de IPTU e reunir os resultados em uma única lista.

O projeto foi desenvolvido com foco em **automação de tarefas repetitivas, web scraping e processamento de informações imobiliárias**.

---

## 🎯 Objetivo

O objetivo principal era automatizar um processo que, quando realizado manualmente, exigia diversas consultas individuais.

Com a automação, era possível:

- 🏠 Consultar diferentes registros de IPTU;
- 🔎 Automatizar a navegação no sistema de consulta;
- 📋 Coletar os resultados das consultas;
- 👤 Associar os resultados às informações disponibilizadas pelo sistema consultado;
- 📊 Consolidar os imóveis encontrados em uma lista;
- ⚡ Reduzir o trabalho manual envolvido no processo.

---

## ⚙️ Como funcionava

O fluxo básico da aplicação era:

```text
             ┌─────────────────────┐
             │ Lista de registros  │
             │       de IPTU       │
             └──────────┬──────────┘
                        │
                        ▼
             ┌─────────────────────┐
             │ Selenium WebDriver  │
             │ inicia o navegador  │
             └──────────┬──────────┘
                        │
                        ▼
             ┌─────────────────────┐
             │ Sistema de consulta │
             │       de IPTU       │
             └──────────┬──────────┘
                        │
                        ▼
             ┌─────────────────────┐
             │ Consulta dos        │
             │ registros           │
             └──────────┬──────────┘
                        │
                        ▼
             ┌─────────────────────┐
             │ Extração dos dados  │
             │ disponibilizados    │
             └──────────┬──────────┘
                        │
                        ▼
             ┌─────────────────────┐
             │ Consolidação dos    │
             │ resultados          │
             └──────────┬──────────┘
                        │
                        ▼
             ┌─────────────────────┐
             │ Lista de imóveis    │
             │ encontrados         │
             └─────────────────────┘
