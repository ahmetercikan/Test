package API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    public Object result;
    public Object Response;
    public String error;
    public String id;
}