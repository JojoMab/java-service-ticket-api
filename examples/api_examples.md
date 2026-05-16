# API Examples

## Ticket erstellen

```bash
curl -X POST http://localhost:8080/api/tickets   -H "Content-Type: application/json"   -d '{"title":"VPN Login schlägt fehl","description":"Ein Nutzer kann sich nicht anmelden.","priority":"HIGH"}'
```

## Alle Tickets abrufen

```bash
curl http://localhost:8080/api/tickets
```

## Ein Ticket abrufen

```bash
curl http://localhost:8080/api/tickets/1
```

## Status ändern

```bash
curl -X PATCH "http://localhost:8080/api/tickets/1/status?status=IN_PROGRESS"
```

## Ticket löschen

```bash
curl -X DELETE http://localhost:8080/api/tickets/1
```
