package api;

import api.models.Category;
import api.models.Pet;
import api.models.Tag1;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;



    @Tag("API")
    public class ApiTests {
        private static final String BASE_URL = "https://petstore.swagger.io/v2/";

        @BeforeEach
        public void setUp() {
            RestAssured.baseURI = BASE_URL;

        }
        @Test
        @Order(1)
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
                    .tags(new ArrayList<>(List.of(new Tag1(1, "tagName"))))  // Добавление хотя бы одного тега
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
        @Order(2)
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
        @Order(3)
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
                    .tags(new ArrayList<>(Collections.singletonList(new Tag1(2, "Name"))))  // Добавление хотя бы одного тега
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
        @Order(4)
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

