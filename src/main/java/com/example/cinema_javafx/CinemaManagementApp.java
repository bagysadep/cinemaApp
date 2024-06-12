package com.example.cinema_javafx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;

class Cinema {
    private String name;
    private String address;
    private String category;
    private int seatCount;
    private int hallCount;
    private String status;

    public Cinema(String name, String address, String category, int seatCount, int hallCount, String status) {
        this.name = name;
        this.address = address;
        this.category = category;
        this.seatCount = seatCount;
        this.hallCount = hallCount;
        this.status = status;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getSeatCount() { return seatCount; }
    public void setSeatCount(int seatCount) { this.seatCount = seatCount; }

    public int getHallCount() { return hallCount; }
    public void setHallCount(int hallCount) { this.hallCount = hallCount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return name;
    }
}

class Movie {
    private String title;
    private String director;
    private String operator;
    private String actors;
    private String genre;
    private String studio;

    public Movie(String title, String director, String operator, String actors, String genre, String studio) {
        this.title = title;
        this.director = director;
        this.operator = operator;
        this.actors = actors;
        this.genre = genre;
        this.studio = studio;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getStudio() { return studio; }
    public void setStudio(String studio) { this.studio = studio; }

    @Override
    public String toString() {
        return title;
    }
}

class Showtime {
    private Cinema cinema;
    private Movie movie;
    private LocalDateTime dateTime;
    private double price;
    private int availableSeats;

    public Showtime(Cinema cinema, Movie movie, LocalDateTime dateTime, double price, int availableSeats) {
        this.cinema = cinema;
        this.movie = movie;
        this.dateTime = dateTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    // Getters and setters
    public Cinema getCinema() { return cinema; }
    public void setCinema(Cinema cinema) { this.cinema = cinema; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    @Override
    public String toString() {
        return movie.getTitle() + " (" + dateTime.toString() + ")";
    }
}

public class CinemaManagementApp extends Application {

    private ObservableList<Cinema> cinemas = FXCollections.observableArrayList();
    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<Showtime> showtimes = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Справочная служба кинотеатров");

        // Таблицы
        TableView<Cinema> cinemaTable = new TableView<>(cinemas);
        TableColumn<Cinema, String> cinemaNameCol = new TableColumn<>("Кинотеатр");
        cinemaNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
        cinemaTable.getColumns().add(cinemaNameCol);

        TableView<Movie> movieTable = new TableView<>(movies);
        TableColumn<Movie, String> movieTitleCol = new TableColumn<>("Фильм");
        movieTitleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));
        movieTable.getColumns().add(movieTitleCol);

        TableView<Showtime> showtimeTable = new TableView<>(showtimes);
        TableColumn<Showtime, String> showtimeMovieCol = new TableColumn<>("Сеанс");
        showtimeMovieCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().toString()));
        showtimeTable.getColumns().add(showtimeMovieCol);

        // Кнопки для добавления кинотеатра, фильма и сеанса
        Button addCinemaButton = new Button("Добавить кинотеатр");
        addCinemaButton.setOnAction(e -> {
            Cinema cinema = new Cinema("Кинотеатр 1", "Адрес 1", "Категория 1", 100, 2, "Работает");
            cinemas.add(cinema);
        });

        Button addMovieButton = new Button("Добавить фильм");
        addMovieButton.setOnAction(e -> {
            Movie movie = new Movie("Фильм 1", "Режиссер 1", "Оператор 1", "Актер 1, Актер 2", "Жанр 1", "Студия 1");
            movies.add(movie);
        });

        Button addShowtimeButton = new Button("Добавить сеанс");
        addShowtimeButton.setOnAction(e -> {
            if (!cinemas.isEmpty() && !movies.isEmpty()) {
                Showtime showtime = new Showtime(cinemas.get(0), movies.get(0), LocalDateTime.now(), 300, 50);
                showtimes.add(showtime);
            }
        });

        // Кнопки для удаления выбранного кинотеатра, фильма и сеанса
        Button removeCinemaButton = new Button("Удалить выбранный кинотеатр");
        removeCinemaButton.setOnAction(e -> {
            Cinema selectedCinema = cinemaTable.getSelectionModel().getSelectedItem();
            cinemas.remove(selectedCinema);
        });

        Button removeMovieButton = new Button("Удалить выбранный фильм");
        removeMovieButton.setOnAction(e -> {
            Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
            movies.remove(selectedMovie);
        });

        Button removeShowtimeButton = new Button("Удалить выбранный сеанс");
        removeShowtimeButton.setOnAction(e -> {
            Showtime selectedShowtime = showtimeTable.getSelectionModel().getSelectedItem();
            showtimes.remove(selectedShowtime);
        });

        // Layout
        HBox buttonBox = new HBox(10, addCinemaButton, addMovieButton, addShowtimeButton, removeCinemaButton, removeMovieButton, removeShowtimeButton);
        buttonBox.setPadding(new Insets(10));
        VBox layout = new VBox(10, buttonBox, cinemaTable, movieTable, showtimeTable);
        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


