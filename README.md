# Java Service Ticket API

Einfache Java-REST-API zur Verwaltung von IT-Service-Tickets. Das Projekt zeigt Backend-Grundlagen wie Controller, Service-Schicht, Repository, Validierung, In-Memory-Datenhaltung und Tests. Es ist bewusst auf Bewerberniveau fuer duale Studiengaenge in Informatik und Wirtschaftsinformatik gehalten.

## Kurzprofil fuer Recruiter

Das Projekt macht Java, OOP, REST, Backend-Struktur und Testing sichtbar. Inhaltlich passt es zu IT-Service-Prozessen, Fehleranalyse, SLA-Risiken und digitalen Geschaeftsprozessen.

## Bewerbungsbezug

Passend fuer Atruvia, Allianz, Finanz Informatik, ConSol, CANCOM, Deutsche Telekom, Atos, Deloitte und EY.

## Tech Stack

- Java 21
- Spring Boot
- Maven
- REST API
- Bean Validation
- JUnit 5 / Spring Boot Test
- GitHub Actions CI

## API-Endpunkte

| Methode | Pfad | Zweck |
| --- | --- | --- |
| GET | `/tickets` | alle Tickets anzeigen |
| GET | `/tickets/{id}` | Ticket nach ID anzeigen |
| POST | `/tickets` | Ticket anlegen |
| PUT | `/tickets/{id}/status` | Status aendern |
| GET | `/tickets/priority` | Tickets nach Prioritaet sortieren |
| GET | `/reports/summary` | Zusammenfassung offener und kritischer Tickets |

## Schnellstart

```bash
mvn test
mvn spring-boot:run
```

## Beispiel-Request

```bash
curl -X POST http://localhost:8080/tickets \
  -H "Content-Type: application/json" \
  -d '{"title":"VPN login failed","category":"Security","priority":"HIGH"}'
```

## Beispielausgabe

Eine kurze Beispielausgabe liegt unter `examples/terminal_output.txt`.

## Hinweis

Bewerberprojekt mit synthetischen Beispieldaten. Kein Cloud-Betrieb und kein echtes Ticketsystem.
