# 🔧 Mini Java Web Framework
A minimal custom Java web framework featuring annotation-based routing, manual dependency injection, a lightweight dispatcher, and JSON (de)serialization – inspired by Spring and Tomcat.


This project is a **completely dependency-free Java web framework** developed from scratch without using any external libraries or frameworks like Spring, Jackson, or Tomcat.

It is built entirely using **pure Java** and its **core capabilities** such as reflection, annotations, collections, and the built-in `HttpServer`.  
The goal was to understand how modern frameworks like **Spring MVC** and application servers like **Tomcat** work internally — and replicate their basic behaviors in a minimalistic, educational implementation.

---

## 🚀 Key Features & Architecture

### ✅ 1. Custom Annotation-Based MVC Framework
- Supports annotations similar to Spring:
  - `@MyController` → Marks a class as a controller.
  - `@GetMapping("/path")` & `@PostMapping("/path")` → Maps HTTP routes to methods.
  - `@MyAutowired` → For injecting dependencies.
  - `@MyComponent` → For marking services/components to be managed by our IoC container.

### 🧠 2. Manual Dependency Injection (IoC)
- A simplified version of Spring's `ApplicationContext` is implemented.
- During application startup, all `@MyComponent` annotated classes are instantiated and stored in a context.
- Dependencies marked with `@MyAutowired` are injected manually using reflection.

### 🌐 3. Mini Dispatcher (Custom Servlet-like Dispatcher)
- The framework includes a **dispatcher** that mimics how `DispatcherServlet` works in Spring.
- It:
  - Matches incoming HTTP requests to the right controller and method using `HandlerMapping`.
  - Determines if the request is `GET` or `POST`, and calls the corresponding method via reflection.
  - Builds and sends the HTTP response to the client.

### 🛣️ 4. Routing & Handler Resolution
- `HandlerMapping` maps the route path (`/api/user`, etc.) to the appropriate method.
- `HandlerAdapter` is responsible for invoking the method with required parameters (if any), and returning a response.

### 🧾 5. Custom JSON Serialization / Deserialization
- The framework includes its own basic `JsonParser` and `MyJsonMapper`:
  - Parses JSON strings into Java objects (`User`, `Product`, etc.).
  - Converts Java objects back into JSON for responses.
  - Uses reflection to call setter methods and resolve data types using a `TypeConverter`.

### 🖥️ 6. Embedded HTTP Server (No Tomcat)
- Uses Java’s built-in `com.sun.net.httpserver.HttpServer` to start a server and listen to a port (e.g., 8080).
- No need for Tomcat, Jetty, or any external servlet container.

---

## 🧪 Example Usage

```java
@MyController
public class HelloController {
    @GetMapping("/api/user")
    public User getUser() {
        User user = new User();
        user.setUsername("skywalker");
        user.setPassword("1234");
        user.setAge(20);
        return user;
    }
}
```
→ Automatically returns:
```
{
  "username": "skywalker",
  "password": "1234",
  "age": 20
}
```
