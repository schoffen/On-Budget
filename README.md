# Montrabudget

## Descrição
O Montrabudget é um aplicativo de controle financeiro desenvolvido em Kotlin, utilizando Jetpack Compose como framework de UI. O objetivo principal é permitir que os usuários gerenciem suas finanças pessoais de forma simples e eficiente, com funcionalidades para adicionar transações, criar budgets, gerenciar contas bancárias, e muito mais.
Este aplicativo teve como inicio um kit de UI disponibilizado pelo Figma (https://www.figma.com/community/file/998557875473123405), criado pelo Braja Omar Justico.
O projeto terá seu estilo modificado após o término de implementacao das funcoes principais.

## Funcionalidades
- **Adicionar Transações**: Permite adicionar receitas e despesas, categorizando-as para melhor organização.
- **Gerenciamento de Contas**: Criação e controle de múltiplas contas (e.g., Carteira, Banco).
- **Criação de Orçamentos (Budget)**: Definição de limites de gasto para diferentes categorias, com acompanhamento em tempo real.
- **Cadastro e Autenticação de Usuários**: Integração futura com Firebase para login e cadastro de novos usuários.
- **Validação de Formulários**: Validações customizadas para garantir a consistência dos dados inseridos pelos usuários.

## Tecnologias Utilizadas
- **Linguagem**: Kotlin
- **Interface de Usuário**: Jetpack Compose
- **Arquitetura**: MVVM (Model-View-ViewModel)
- **Injeção de Dependência**: Dagger/Hilt
- **Banco de Dados Local**: Room
- **Navegação**: Jetpack Navigation
- **Autenticação**: Integração futura com Firebase Authentication
- **Armazenamento na Nuvem**: Integração futura com Firebase Firestore
