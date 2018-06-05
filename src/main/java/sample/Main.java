package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }


    public static void main(String[] args) {
        //System.out.println(Utils.makeRequest("http://localhost:8080/book"));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        BookEntity bookEntity = restTemplate.getForObject("http://localhost:8080/book/2", BookEntity.class);
        System.out.println(bookEntity.getTitle());

        ////////////////////////////////////////
        BookEntity bookEntity1 = new BookEntity();
        bookEntity1.setAuthor("Ksiazka Oskara");
        bookEntity1.setPages(5324);
        bookEntity1.setTitle("Z aplikacji");


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("key", "22142121o2oasfADa25noisafrj00uAIODJOSf241");

        HttpEntity<BookEntity> entity = new HttpEntity<BookEntity>(bookEntity1, httpHeaders);
        restTemplate.postForObject("http://localhost:8080/book", entity, String.class);
        ////////////////////////////////////////

       // launch(args);
    }
}
