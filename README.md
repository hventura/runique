# Runique

Runique é uma aplicação de gestão de corridas multi-modular para dispositivos móveis e Wear OS (Ainda em desenvolvimento!). Este projeto foi desenvolvido no âmbito do curso Android Essentials Bundle  ([disponivel aqui](https://pl-coding.com/android-essentials-bundle?utm_source=github&utm_medium=readme&utm_campaign=readme_link&utm_id=essentials)).

![Run Feature](https://pl-coding.com/wp-content/uploads/2024/04/run-feature.png)
<table>
  <tr>
    <td>
      <img src="https://pl-coding.com/wp-content/uploads/2024/04/auth-feature.png" alt="Auth Feature" width="500"/>
    </td>
    <td>
      <img src="https://pl-coding.com/wp-content/uploads/2024/04/phone-watch-mockup.png" alt="Phone Watch Mockup" width="300"/>
    </td>
  </tr>
</table>

## O que Aprendi

Durante a realização deste curso, aprofundei os seguintes conceitos e tecnologias:
- **Planeamento de Projetos**: Aprendi a organizar e planear o desenvolvimento de um projeto, desde a conceção até à implementação.
- **Teoria de Arquitetura de Software**: Estudei diferentes padrões e práticas de arquitetura para desenvolver aplicações robustas e escaláveis.
- **Arquitetura Multi-Modular**: Desenvolvi a aplicação utilizando uma arquitetura que separa funcionalidades por módulos, facilitando a manutenção e evolução do projeto.
- **Gradle para Projetos de Grande Escala**: Tornei-me proficiente no uso de Gradle, incluindo catálogos de versões e plugins de convenções, essenciais para gerir projetos complexos.

## Funcionalidades da App

A Runique é equipada com uma série de funcionalidades avançadas:
- **Sistemas de Autenticação**: Implementação de autenticação via OAuth e renovação de tokens para garantir a segurança dos utilizadores.
- **Desenvolvimento Offline-First**: Suporte para funcionamento offline, permitindo que os utilizadores acessem a aplicação mesmo sem conexão à internet.
- **Módulos de Funcionalidade Dinâmica**: Adição e remoção de funcionalidades de forma dinâmica sem necessitar de uma atualização completa da aplicação.
- **Google Maps SDK**: Integração com o Google Maps para funcionalidades de mapeamento e rastreamento de corridas.
- **Jetpack Compose em Projetos Multi-Modulares**: Utilização do Jetpack Compose para construir interfaces de utilizador modernas e responsivas em diferentes módulos.

## Configuração do Projeto

### **Nota importante:** 
A aplicação **não** irá funcionar completamente devido à API privada da Runique, que só é acessível após a compra do curso. 
No entanto, podes explorar o código e a estrutura do projeto. Aqui estão os passos básicos:

1. **Clone o Repositório**: Primeiro, clone o repositório do projeto.
2. **Se tiveres acesso ao curso**: Adiciona estas linhas ao ficheiro `local.properties`:
```
API_KEY=<API_KEY>
BASE_URL=<API_URL>
```
3. **Adicione as Chaves de API**: Para experimentar a integração com o Google Maps, adicione a chave de API correspondente no arquivo `local.properties`:
```
MAPS_API_KEY=<SUA_API_KEY_GOOGLE_MAPS>
```
4. **Compile o Projeto**: Finalmente, compila o projeto. Lembra-te que a aplicação não estará funcional sem a API da Runique, mas poderás explorar e aprender com o código.

A parte de desenvolvimento para Wear OS ainda está em progresso, mas todas as outras funcionalidades estão prontas para explorar.
