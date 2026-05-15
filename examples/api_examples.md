# API Examples – Java Service Ticket API

Diese Beispiele zeigen die wichtigsten Endpunkte der Java Service Ticket API. Die Anwendung muss vorher lokal gestartet werden:

```bash
mvn spring-boot:run
```

## Ticket erstellen

```bash
curl -X POST http://localhost:8080/api/tickets \
  -H "Content-Type: application/json" \
  -d '{"title":"VPN login failed","description":"User cannot connect to the company VPN.","priority":"HIGH"}'
```

## Alle Tickets anzeigen

```bash
curl http://localhost:8080/api/tickets
```

## Einzelnes Ticket anzeigen

```bash
curl http://localhost:8080/api/tickets/1
```

## Status ändern

```bash
curl -X PATCH "http://localhost:8080/api/tickets/1/status?status=IN_PROGRESS"
```

Mögliche Statuswerte:

```text
OPEN
IN_PROGRESS
CLOSED
```

## Ticket löschen

```bash
curl -X DELETE http://localhost:8080/api/tickets/1
```

## Beispielantwort

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

## Hinweis

Alle Beispiele nutzen synthetische Daten und dienen nur zur Demonstration im Bewerberprojekt.
