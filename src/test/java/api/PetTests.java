package api;

import api.models.Category;
import api.models.Pet;
import api.models.Tag;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetTests {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = BASE_URL;

    }
    @Test
    @DisplayName("Create pet")
    public void petCreatedTest() {
        Specification.installSpecification(Specification.requestSpec);

        Category category = Category.builder().id(10).name("Dog").build();
        Pet pet = Pet.builder()
                .id(521)
                .name("Roy")
                .status("available")
                .category(category)
                .photoUrls(new ArrayList<>(Collections.singletonList("String")))  // Добавление хотя бы одного URL фото
                .tags(new ArrayList<>(Collections.singletonList(new Tag(1, "tagName"))))  // Добавление хотя бы одного тега
                .build();

        Pet createPet = (Pet) given().
                body(pet)
                .when()
                .post("/pet")
                .then()
                .spec(Specification.responseSpec)
                .log().body()
                .extract().as(Pet.class);

        Assertions.assertEquals(pet.getId(), createPet.getId());
        Assertions.assertEquals(pet.getName(), createPet.getName());

    }

    @Test
    @DisplayName("Read pet")
    public void petReadTest() {
        Specification.installSpecification(Specification.requestSpec);

        int id = 521;

        Pet getPet = given()
                .when()
                .get("/pet/" + id)
                .then()
                .spec(Specification.responseSpec)
                .log().body()
                .extract().as(Pet.class);

        Assertions.assertEquals(id, getPet.getId());

    }

    @Test
    @DisplayName("Update pet")
    public void petUpdatedTest() {
        Specification.installSpecification(Specification.requestSpec);

        Category category = Category.builder().id(9).name("Dog").build();
        Pet pet = Pet.builder()
                .id(521)
                .name("Jack")
                .status("available")
                .category(category)
                .photoUrls(new ArrayList<>(Collections.singletonList("String")))  // Добавление хотя бы одного URL фото
                .tags(new ArrayList<>(Collections.singletonList(new Tag(2, "Name"))))  // Добавление хотя бы одного тега
                .build();

        Pet updatePet = (Pet) given().
                body(pet)
                .when()
                .put("/pet")
                .then()
                .spec(Specification.responseSpec)
                .log().body()
                .extract().as(Pet.class);

        Assertions.assertEquals(pet.getId(), updatePet.getId());
        Assertions.assertEquals(pet.getName(), updatePet.getName());

    }

    @Test
    @DisplayName("Delete pet")
    public void deletePetTest() {
        Specification.installSpecification(Specification.requestSpec);

        int id = 521;

         given()
                .when()
                .delete("/pet/" + id)
                .then()
                .spec(Specification.responseSpec)
                .log().body()
                .body("message" ,equalTo(String.valueOf(id)));


    }

}
