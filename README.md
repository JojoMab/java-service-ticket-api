![Java CI](https://github.com/JojoMab/java-service-ticket-api/actions/workflows/java-ci.yml/badge.svg)

# Java Service Ticket API

Dieses Bewerberprojekt ist eine einfache Java-REST-API zur Verwaltung von IT-Service-Tickets. Es zeigt Backend-Grundlagen wie Controller, Service-Schicht, Repository, DTOs, Bean Validation, JPA-Datenhaltung mit H2 und automatisierte Tests. Das Projekt ist bewusst als nachvollziehbares Bewerberprojekt für duale Studiengänge in Informatik und Wirtschaftsinformatik aufgebaut.

## Bewerbungskontext

Das Projekt passt zu dualen Studiengängen mit Fokus auf Java, Backend, Software Engineering, IT-Systeme und Banken-/Finanz-IT. Besonders relevant ist es für Atruvia, Allianz, Finanz Informatik, ConSol, CANCOM, Deutsche Telekom, Atos, Deloitte und EY.

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Web
- Bean Validation
- Spring Data JPA
- H2-Datenbank
- Maven
- JUnit 5 und MockMvc
- GitHub Actions

## Funktionen

- IT-Service-Ticket erstellen
- Alle Tickets abrufen
- Einzelnes Ticket nach ID abrufen
- Ticketstatus ändern
- Ticket löschen
- Eingaben per Bean Validation prüfen
- REST-Endpunkte mit MockMvc testen

## Projektstruktur

```txt
java-service-ticket-api/
├── src/main/java/com/example/ticketapi/
│   ├── TicketApiApplication.java
│   ├── controller/TicketController.java
│   ├── service/TicketService.java
│   ├── repository/TicketRepository.java
│   ├── model/Ticket.java
│   └── dto/
├── src/test/java/com/example/ticketapi/
├── pom.xml
├── README.md
├── docs/
└── examples/
```

## Schnellstart

```bash
mvn test
mvn spring-boot:run
```

## Tests ausführen

```bash
mvn test
```

## Beispielausgabe

```txt
POST /api/tickets -> 201 CREATED
GET /api/tickets -> Liste mit offenen Tickets
PATCH /api/tickets/1/status?status=IN_PROGRESS -> Status geändert
```

## Beispiel mit curl

```bash
curl -X POST http://localhost:8080/api/tickets   -H "Content-Type: application/json"   -d '{"title":"VPN Login schlägt fehl","description":"Ein Nutzer kann sich nicht anmelden.","priority":"HIGH"}'

curl http://localhost:8080/api/tickets
curl http://localhost:8080/api/tickets/1
curl -X PATCH "http://localhost:8080/api/tickets/1/status?status=IN_PROGRESS"
curl -X DELETE http://localhost:8080/api/tickets/1
```

## Hinweis auf synthetische Daten

Alle Daten sind synthetisch und dienen ausschließlich der Demonstration.

## English Summary

This project is a small Java Spring Boot REST API for managing IT service tickets. It demonstrates basic backend layers, DTOs, validation, JPA persistence with H2 and automated tests. The project uses synthetic examples and is designed as an applicant portfolio project for dual study programs.
