# Contributing

Neem voor het toevoegen van nieuwe functionaliteiten aan dit package graag contact op met Michiel. (Uiteraard moeten toevoegingen en wijzigingen eerst besproken en goedgekeurd worden binnen de subgroep waardoor de wijziging wordt voorgesteld!)

## Proces

1. Zowel nieuwe functionaliteiten als aanpassingen aan bestaande functionaliteiten dienen uitgewerkt te worden in een aparte branch.
2. Wanneer de wijziging gereed is dient deze gemerged te worden met de development branch, waarna er een pull request naar development aangemaakt kan worden.
3. Het versienummer in de pom.xml moet bij ieder pull request worden verhoogd. Zie het hoofdstuk Versioning.
4. Ieder pull request moet gereviewed en goedgekeurd worden door een aantal projectleden voordat deze geaccepteerd wordt. Zie Reviews.

## Versioning

Vóór het aanmaken van een pull request voor een update naar development moet het versienummer dat op dat moment op development staat verhoogd worden.

Bij het maken van een grote wijziging (bijvoorbeeld het toevoegen van nieuwe functionaliteit), wordt het tweede getal van het versienummer verhoogd, bijvoorbeeld van versie 1.0 naar versie 1.1.

Bij het maken van een kleine wijziging (bijvoorbeeld bugfixes of optimalisaties), wordt het derde getal van het versienummer verhoogd, bijvoorbeeld van 1.0 naar 1.0.1.

Indien de gemaakte wijziging niet persee naar master gepusht hoeft te worden (bijvoorbeeld omdat het gaat om het bijwerken van de documentatie of het doorvoeren van een refactoring), is het ook mogelijk om van de versie een snapshot te maken. In dit geval gaat het versienummer van bijvoorbeeld 1.0 naar 1.0-SNAPSHOT.

## Reviews

Bij het aanmaken van een pull request moet deze door minimaal de volgende personen gereviewed en goedgekeurd worden:

- Minimaal één teamlid van de groep die de wijziging heeft voorgesteld/doorgevoerd.
- Michiel

Het wordt ook aangeraden om minimaal één teamlid van een andere groep toe te voegen als deze groep de nieuwe functionaliteit van plan is te gebruiken.