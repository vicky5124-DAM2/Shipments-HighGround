package cat.institutmarianao.shipmentsws.exception.model;

import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindingResult;

import java.util.*;

public class ErrorResponse {

    private Map<String, Object> body;

    public ErrorResponse(HttpStatusCode status, String error) {
        List<String> errors = new ArrayList<>();
        errors.add(error);
        setBody(status, errors);
    }

    public ErrorResponse(HttpStatusCode status, BindingResult bindingResult) {
        List<String> errors = bindingResult.getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage()).toList();
        setBody(status, errors);
    }

    public Map<String, Object> getBody() {
        return body;
    }

    private void setBody(HttpStatusCode status, List<String> errors) {
        body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("errors", errors);
    }
}