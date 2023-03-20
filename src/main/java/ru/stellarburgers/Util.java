package ru.stellarburgers;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import ru.stellarburgers.model.User;
import ru.stellarburgers.model.UserLogin;

import javax.annotation.Nullable;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class Util {

    public static User randomUser() {
        return randomUser("1234567");
    }

    public static User randomUser(String password) {
        Random random = new Random();
        String email = "khalturin" + random.nextInt(10000000) + "@yandex.ru";
        return new User(email, password, "Mike");
    }

    public static void createUser(User user) {
        System.out.println("Trying to create a user. Email: " + user.getEmail() + ", Password: " + user.getPassword() + ", Name: " + user.getName());
        Response response = given()
                .header("Content-type", "application/json")
                .body(user)
                .post("/api/auth/register");
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            System.out.println("User successfully created");
        } else {
            System.out.println("Response code after creating user is " + response.getStatusCode());
        }
    }

    public static void deleteUser(User user) {
        String authToken = getToken(user);
        if (authToken != null) {
            Response response = given()
                    .header("Authorization", authToken)
                    .delete("/api/auth/user");
            if (response.getStatusCode() == HttpStatus.SC_ACCEPTED) {
                System.out.println("User " + user.getEmail() + " deleted");
            }
        }
    }

    @Nullable
    public static String getToken(User user) {
        UserLogin userLogin = new UserLogin(user.getEmail(), user.getPassword());

        Response response = given()
                .body(userLogin)
                .header("Content-type", "application/json")
                .post("/api/auth/login");
        if (response.getStatusCode() == HttpStatus.SC_OK) {
            return response.jsonPath().getString("accessToken");
        } else {
            return null;
        }

    }
}
