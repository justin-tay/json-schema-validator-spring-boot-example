# JSON Schema Validator Spring Boot Example

## Quick Start

### Native

```shell
mvn -Pnative native:compile
```

```shell
target\demo.exe
```

### Schema Validation

```shell
curl -X POST http://localhost:8080/schema/validate -H 'Content-Type: application/json' -d '{"childprop": "something","group": {"parentprop":"something","notallowed":false}}'
```

### Meta Schema Validation

```shell
curl -X POST http://localhost:8080/meta-schema/2020-12/validate -H 'Content-Type: application/json' -d '{"definitions":"hello","childprop": "something","group": {"parentprop":"something","notallowed":false}}'
```

```shell
curl -X POST http://localhost:8080/meta-schema/2019-09/validate -H 'Content-Type: application/json' -d '{"definitions":"hello","childprop": "something","group": {"parentprop":"something","notallowed":false}}'
```

```shell
curl -X POST http://localhost:8080/meta-schema/07/validate -H 'Content-Type: application/json' -d '{"definitions":"hello","childprop": "something","group": {"parentprop":"something","notallowed":false}}'
```

```shell
curl -X POST http://localhost:8080/meta-schema/06/validate -H 'Content-Type: application/json' -d '{"definitions":"hello","childprop": "something","group": {"parentprop":"something","notallowed":false}}'
```