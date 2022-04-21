# Rss Reader Aplication <img src="https://upload.wikimedia.org/wikipedia/en/thumb/4/43/Feed-icon.svg/128px-Feed-icon.svg.png" alt width="20" height="20"/>
Rss Reader Application is an API that manages users accounts and provides them xml+rss as a json.

## Built with
- This software was made using JAX-RS
- Java 17.0.2
- Gradle 7.4

## Installation
- Download Java [rssreader.war](./app/build/libs/rssreader.war) and made the deploy
- It is recommended to use Wildfly 26.0.1.Final as your server for deploy

## How it works
- This project how built using [RSS 2.0 specification](https://validator.w3.org/feed/docs/rss2.html)
- When it is request links of a rss feed, the server provides these link contents as a json
- It is necessary create a account to use the Endpoints.

## Security
- The user authentication is made by JWT token, which is generated when user login or update your information<br>
- The program also works with a internal Rate limit technology, a bucket token that permits each user request 50 requests per minute<br><br>

## Author
- Made by [Yann Gabriel](https://www.linkedin.com/in/yann-gabriel-764abab6/)

## Licensing
- Rss Reader Application is licensed under the Apache 2.0 License. See [LICENSE](LICENSE) for the full license text.