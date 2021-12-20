package API;

import UI.pages.BaseClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.thucydides.core.annotations.Title;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import static org.testng.Assert.assertEquals;

public class Services extends BaseClass {

    public static io.restassured.response.Response response = null;
    Fairy fairy = Fairy.create();
    Person person = fairy.person();
    String errorValue;
    String idValue;
    public Services(){}
    public static Integer getResponseCode(String baseUri, String endPoint)
    {
        RestAssured.baseURI = baseUri;
        response = RestAssured.given().contentType(ContentType.JSON).get(endPoint);
        assertEquals(200, response.getStatusCode());
        return Integer.valueOf(response.getStatusCode());
    }
    public void getServices(String baseUri,String endPoint)
    {
        RestAssured.baseURI = baseUri;
        response = RestAssured.given().contentType(ContentType.JSON).get(endPoint);
        assertEquals(200, response.getStatusCode());
    }
    public void putServices(String baseUri, String endPoint, String servicesName, java.util.Map<String,Object> jsonAsMap)
    {
        RestAssured.baseURI = baseUri;
        response = RestAssured.given().contentType(ContentType.JSON).body(jsonAsMap).put(endPoint);
        System.out.println(" --- " + servicesName + " --- ");
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals(200, response.getStatusCode());
    }
    public void postServices(String baseUri, String endPoint, String servicesName, java.util.Map<String,Object> jsonAsMap)
    {
        RestAssured.baseURI = baseUri;
        response = RestAssured.given().contentType(ContentType.JSON).body(jsonAsMap).post(endPoint);
        System.out.println(" --- " + servicesName + " --- ");
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals(200, response.getStatusCode());
    }
    public void deleteServices(String baseUri,String endPoint, String servicesName,String request)
    {
        RestAssured.baseURI = baseUri;
        response = RestAssured.given().contentType(ContentType.JSON).body(request).delete(endPoint);
        System.out.println(" --- " + servicesName + " --- ");
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals(200, response.getStatusCode());
    }
    public void deleteServicesNonReq(String baseUri,String endPoint, String servicesName)
    {
        RestAssured.baseURI = baseUri;
        response = RestAssured.given().contentType(ContentType.JSON).delete(endPoint);
        System.out.println(" --- " + servicesName + " --- ");
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        assertEquals(200, response.getStatusCode());
    }
    @Test
    @Title("Book Get Request")
    public void bookGetRequest()
    {
        logger = extent.startTest("Book Get Request","Book Get Request");
        getServices(odevApiURL,"/api/books/");
        System.out.println("Response :" + response.asString());
    }
    @Test
    @Title("Book Get Request By ID")
    public void bookGetRequestById()
    {
        logger = extent.startTest("Book Get Request By ID","Book Get Request By ID");
        getServices(odevApiURL,"/api/books/1");
        System.out.println("Response :" + response.asString());
    }
    @Test
    @Title("Book Put Request Empty")
    public void bookPutRequestEmpty() throws JsonProcessingException
    {
        logger = extent.startTest("Book Put Request mpty","Book Put Request Empty");
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("", "");
        jsonAsMap.put("", "");
        putServices(odevApiURL, "/api/books", "Book Put Request", jsonAsMap);
        ObjectMapper objectMapper = new ObjectMapper();
        Response responseobj = objectMapper.readValue(response.asString(), Response.class);
        errorValue = responseobj.error;
        System.out.println("Error : " + errorValue);
    }
    @Test
    @Title("Book Put Request")
    public void bookPutRequest(String author,String title) throws JsonProcessingException
    {
        logger = extent.startTest("Book Put Request","Book Put Request");
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("author", author);
        jsonAsMap.put("title", title + " Book");
        putServices(odevApiURL, "/api/books", "Book Put Request", jsonAsMap);
        ObjectMapper objectMapper = new ObjectMapper();
        Response responseobj = objectMapper.readValue(response.asString(), Response.class);
        idValue = responseobj.id;
        System.out.println("Id : " + idValue);
    }
    @Test
    @Title("Create Book Get Request By ID")
    public void createBookGetRequestById() throws JsonProcessingException
    {
        logger = extent.startTest("Create Book Get Request By ID","Create Book Get Request By ID");
        bookPutRequest(person.getFirstName(), person.getUsername());
        getServices(odevApiURL,"/api/books/"+idValue);
        System.out.println("Response :" + response.asString());
    }
    @Test
    @Title("Already Book Get Request")
    public void alreadyBookgetRequest() throws JsonProcessingException
    {
        logger = extent.startTest("Already Book Get Request","Already Book Get Request");
        bookPutRequest("Ahmet Erçıkan", "Java");
        getServices(odevApiURL,"/api/books/"+idValue);
        System.out.println("Response :" + response.asString());
        bookPutRequest("Ahmet Erçıkan","Java");
        ObjectMapper objectMapper = new ObjectMapper();
        Response responseobj = objectMapper.readValue(response.asString(), Response.class);
        errorValue = responseobj.error;
        System.out.println("Error : " + errorValue);
    }
}