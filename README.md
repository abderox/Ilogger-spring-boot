# Ilogger

Ilogger is a library for customized logging and debugging in Spring Boot applications. It provides a simple way to intercept and log HTTP requests and responses, as well as custom log messages.

## Table of Contents

- [Getting Started](#getting-started)
- [Usage](#usage)
- [Configuration](#Configuration)
- [Demo](#Demo)
- [Authors](#Authors)
- [Contributing](#Contributing)
- [License](#license)

## Getting Started

### Prerequisites

- Java 17
- Maven 3.8.1
- jdk 18

### Usage

- To use the `@Ilog` annotation, simply add it to any method you want to log
    
```java
    @Ilog
    public void logMethod() {
        LoggingContext.put("key1", "value1");
        // do something
        LoggingContext.put("key2", 123);
    }
```

- By default, the `@Ilog` annotation logs the method call and return value at the INFO level. You can customize the logging level by specifying the level provided by `Levels` enum.
    
```java
    @Ilog(level = Levels.INFO)
    public void logMethod() {
        LoggingContext.put("key1", 123);
        // do something
        LoggingContext.put("key2", 123);
    }
```

- A cleaner way , is to use the `@Ilog` annotation with the `keys` parameter. This way, you can specify the keys used, so it could be suppressed from the `LoggingContext`.

```java
    @Ilog(level = Levels.SEVERE , keys ={"key1","key2"})
    public void logMethod() {
        LoggingContext.put("key1", 123);
        // do something
        LoggingContext.put("key2", 123);
    }
```

### Configuration

- To disable the `@Ilog` annotation, you can set the `ilogger.annotation.enabled` property to `false` in the `application.properties` file.

```properties
    ilogger.annotation.enabled = false
```

## Demo

- To run the demo, you can use the following command:

```bash
    mvn spring-boot:run
```

- Then, you can access the following endpoints:

```bash
    http://localhost:8000/hello-test
```

<p
    align="center"
>
    <img
        src="./log.png"
        alt="log"
        width="100%"
    />
</p>

## Authors

- [@Mouzafir](https://www.github.com/abderox)

## Contributing

Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. The process is clear.

## License

Distributed under the MIT License. See `LICENSE` for more information.





  


