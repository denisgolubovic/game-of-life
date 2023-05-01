# game-of-life

This project uses [Quarkus](https://quarkus.io/) with JDK19 and the following extensions

````pom
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-resteasy-reactive-jsonb</artifactId>
</dependency>

<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-resteasy-reactive</artifactId>
</dependency>
````

To run the code in dev-mode locally:

```shell script
./mvnw compile quarkus:dev
```

## Implementation

This is a simple implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life). It
holds a state of the grid and allows for next
generation to be called by the ``/next`` api-call.

### Folder structure

The folder structure of the code follows these guidelines (note that we could divide this into domains if the project
was larger):

```
├── src
│   ├── common //holds common classes such as producers, error handling etc.
│   ├── model // holds model classes
│   ├── resource // the REST-API endpoints
│   ├── resource // the business logic for each endpoint
├── resources //holds META-INF and application.properties
│   ├── META-INF //Standard meta-inf content
├── pom.xml
├── README.md
└── .gitignore

```

## Testing

Testing is done with quarkus-junit and [QuarkusTest](https://quarkus.io/guides/getting-started-testing).
Quarkus is run with continuous testing, which means that the test cases are all being run during the backend is running
and the endpoints are being called.

## TODOS
- Add columns, rows and probability as input parameters from GUI to let the user set the game board.
- Add a bit more "fun" styling and welcoming in the GUI :-)
- More specific error handling
- Secure endpoints with OpenID Connect and OAuth 2.0