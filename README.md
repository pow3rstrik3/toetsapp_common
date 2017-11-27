# Toetsapp common package

Dit package bevat functionaliteiten die bruikbaar zijn voor meerdere microservices binnen het project.

## Opzetten

Hieronder staan de instructies die nodig zijn voor het installeren en werkend krijgen van het package binnen een microservice.

### Benodigdheden

- [Java](https://java.com/)
- [Maven](https://maven.apache.org/)

### Installatie

Om het package te gebruiken moet deze gebuild worden via Maven. Het wordt aangeraden om de nieuwste versie van de master branch te gebruiken.
Het bouwen kan gedaan worden met Maven:

```
mvn clean package
```

Het .jar bestand dat wordt aangemaakt door Maven kan geïmporteerd worden in een project. Het makkelijkste is om dit ook via Maven te doen.

De gebuilde library kan geïmporteerd worden met Maven door deze in de lib folder in het project te zetten. Vervolgens kan de volgende code aan Maven toegevoegd worden:

```
<dependencies>
    ...
    <dependency>
      <groupId>nl.han.asd.toetsapp.common</groupId>
      <artifactId>toetsapp-common</artifactId>
      <version>1.0</version>
      <scope>system</scope>
      <systemPath>${project.basedir}/lib/toetsapp-common-1.0.jar</systemPath>
    </dependency>
</dependencies>
```
Natuurlijk moeten het version nummer (zowel in version als in systemPath) worden aangepast aan de gebruikte versie.

## Uitvoeren van de tests

De tests worden uitgevoerd met JUnit 4.11 (automatisch gedownload via Maven). Deze kunnen uitgevoerd worden met de test functionaliteit in de eigen IDE, of met Maven met het volgende commando:

```
mvn test
```

## Contributing

Zie CONTRIBUTING.md voor details over het bijdragen aan het common package.