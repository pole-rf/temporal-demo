# Temporal Demo Docker Compose


## Запускаем Temporal

### Версия docker-compose-full

В этой версии вместе с темпорал будет запущена Grafana/Loki/OpenTelemetry - т.е. полный набор в котором можно не только тестировать разработку, но и изучить логирование, трасировку и метрики.

Для корректной работы логирования, перед запуском нужно установить (делается один раз) докер плагин для Loki.
```bash
docker plugin install grafana/loki-docker-driver:latest --alias loki --grant-all-permissions
```

После чего можно многократно запускать Temporal

```bash
./start-temporal-full.sh
```

После запуска кроме Temporal Web UI будут дотсупны дополнительные инструменты:
- http://localhost:8090 - Temporal Web UI
- http://localhost:8085 - Grafana dashboards
- http://localhost:9090 - Prometheus UI
- http://localhost:9090/targets - Prometheus targets
- http://localhost:8000/metrics - Server metrics



### Версия docker-compose-mini

В этой версии будет минимальнор необходимый набор для изучения разработки с Temporal

```bash
./start-temporal-full.sh
```

## Temporal Web UI

После запуска любым из способов будет доступен Temporal Web UI: http://localhost:8080

## Temporal CLI tool

В Temporal есть CLI инструмент для базовых административных задач. Называется `tctl`.

### При локальном запуке через docker-compose

Можно создать алиас `tctl`:

```bash
alias tctl="docker exec temporal-admin-tools tctl"
tctl <tctl CLI arguments>
```

Можно запускать без алиаса, напрямую используя docker команду
```bash
docker exec temporal-admin-tools tctl <tctl CLI arguments>
```

Пример
```bash
tctl --ns default workflow list

docker exec temporal-admin-tools tctl --ns default workflow list
```

Документация `tctl`: [docs.temporal.io](https://docs.temporal.io/docs/system-tools/tctl/).





https://github.com/temporalio/dashboards
https://github.com/tsurdilo/my-temporal-dockercompose
https://github.com/temporalio/background-checks

