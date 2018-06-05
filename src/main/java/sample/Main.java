package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

        System.out.print("Podaj klucz: ");
        String key = scanner.nextLine();
        System.out.println();

        System.out.print("Podaj ID: ");
        int id = Integer.parseInt(scanner.nextLine());



        BookEntity bookEntity = restTemplate.getForObject("http://localhost:8080/book/" + id, BookEntity.class);


        ////////////////////////////////////////
        BookEntity newBookEntity = new BookEntity();

        System.out.print("Podaj autora: ");
        String author = scanner.nextLine();
        newBookEntity.setAuthor(author.equals("") ? bookEntity.getAuthor() : author);

        System.out.print("Podaj ilość stron: ");
        int pages = Integer.parseInt(scanner.nextLine());
        newBookEntity.setPages(pages);

        System.out.print("Podaj tytuł: ");
        String title = scanner.nextLine();
        newBookEntity.setTitle(title.equals("") ? bookEntity.getTitle() : title);

        newBookEntity.setId(id);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("key", key);

        HttpEntity<BookEntity> entity = new HttpEntity<>(newBookEntity, httpHeaders);
        restTemplate.exchange("http://localhost:8080/book", HttpMethod.PUT, entity, String.class);
        ////////////////////////////////////////

    }
}
