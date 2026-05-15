# Java Service Ticket API

![Java CI](https://github.com/JojoMab/java-service-ticket-api/actions/workflows/java-ci.yml/badge.svg)

Java Service Ticket API ist ein Bewerberprojekt für duale Studiengänge in Informatik und Wirtschaftsinformatik. Die Anwendung zeigt eine einfache REST-API zur Verwaltung von IT-Service-Tickets und verbindet Java, Spring Boot, JPA, H2-Datenbank, Validierung und Tests in einer nachvollziehbaren Projektstruktur.

## Bewerbungskontext

Das Projekt passt zu Studien- und Ausbildungsumfeldern, in denen Backend-Entwicklung, Software Engineering, IT-Service-Prozesse, Datenbanken und Testing relevant sind. Besonders passend ist es für Bewerbungen bei Atruvia, Allianz, Finanz Informatik, ConSol, CANCOM, Deutsche Telekom, Atos, Deloitte und EY.

## Tech Stack

- Java 21
- Spring Boot 3
- Maven
- Spring Web
- Spring Data JPA
- H2-Datenbank
- Bean Validation
- JUnit 5
- MockMvc
- GitHub Actions CI

## Funktionen

- IT-Service-Tickets erstellen
- alle Tickets anzeigen
- einzelnes Ticket nach ID anzeigen
- Ticketstatus ändern
- Ticket löschen
- Eingaben mit Bean Validation prüfen
- Daten über ein JPA-Repository speichern
- Service-Logik mit JUnit testen
- REST-Endpunkte mit MockMvc testen

## API-Endpunkte

| Methode | Pfad | Zweck |
|---|---|---|
| POST | `/api/tickets` | Ticket erstellen |
| GET | `/api/tickets` | alle Tickets anzeigen |
| GET | `/api/tickets/{id}` | einzelnes Ticket anzeigen |
| PATCH | `/api/tickets/{id}/status` | Status ändern |
| DELETE | `/api/tickets/{id}` | Ticket löschen |

## Projektstruktur

```text
java-service-ticket-api/
├── pom.xml
├── README.md
├── docs/
│   ├── application_fit.md
│   └── recruiter_summary_de.md
├── examples/
│   └── api_examples.md
├── src/main/java/com/example/ticketapi/
│   ├── TicketApiApplication.java
│   ├── controller/
│   │   └── TicketController.java
│   ├── dto/
│   │   ├── TicketRequestDTO.java
│   │   └── TicketResponseDTO.java
│   ├── model/
│   │   ├── Priority.java
│   │   ├── Ticket.java
│   │   └── TicketStatus.java
│   ├── repository/
│   │   └── TicketRepository.java
│   └── service/
│       └── TicketService.java
└── src/test/java/com/example/ticketapi/
    ├── controller/
    │   └── TicketControllerTest.java
    └── service/
        └── TicketServiceTest.java
```

## Schnellstart

```bash
mvn test
mvn spring-boot:run
```

Die API läuft anschließend standardmäßig unter:

```text
http://localhost:8080
```

## Tests

```bash
mvn test
```

Die Tests prüfen die zentrale Service-Logik und mehrere REST-Endpunkte mit MockMvc.

## Beispiel-Request

```bash
curl -X POST http://localhost:8080/api/tickets \
  -H "Content-Type: application/json" \
  -d '{"title":"VPN login failed","description":"User cannot connect to the company VPN.","priority":"HIGH"}'
```

## Beispielausgabe

```json
{
  "id": 1,
  "title": "VPN login failed",
  "description": "User cannot connect to the company VPN.",
  "status": "OPEN",
  "priority": "HIGH",
  "createdAt": "2026-05-15T12:00:00"
}
```

## Hinweis auf synthetische Daten

Dieses Repository ist ein Bewerberprojekt mit synthetischen Beispieldaten. Es bildet kein echtes Ticketsystem und keine produktive Unternehmensinfrastruktur ab.

## English Summary

This repository is an applicant project for dual study applications in computer science and business information systems. It demonstrates a simple Java REST API with Spring Boot, JPA, validation, a small database layer and automated tests. The project uses synthetic examples and is intentionally scoped for a beginner-to-intermediate applicant portfolio.
