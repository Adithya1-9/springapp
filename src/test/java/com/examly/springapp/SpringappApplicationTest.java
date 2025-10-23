package com.examly.springapp;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import java.io.File;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappShowTests {

    @Autowired
    private MockMvc mockMvc;

    // ---------- Core API Tests ----------

    @Order(1)
    @Test
    void AddShowReturns200() throws Exception {
        String showData = """
                {
                    "title": "Test Show",
                    "genre": "Action",
                    "releaseYear": 2025,
                    "rating": 9,
                    "duration": 120
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shows/addShow")
                        .with(jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(showData)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Order(2)
    @Test
    void GetAllShowsReturnsArray() throws Exception {
        mockMvc.perform(get("/api/shows/allShows")
                        .with(jwt())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andReturn();
    }

   

  

    @Order(5)
    @Test
    void DeleteShowReturns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/shows/1")
                        .with(jwt())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    // ---------- Project Structure Tests ----------

    @Test
    void ControllerDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/controller";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void ShowControllerFileExists() {
        String filePath = "src/main/java/com/examly/springapp/controller/ShowController.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    void ModelDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/model";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void ShowModelFileExists() {
        String filePath = "src/main/java/com/examly/springapp/model/Show.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    void RepositoryDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/repository";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void ServiceDirectoryExists() {
        String directoryPath = "src/main/java/com/examly/springapp/service";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    void ShowServiceClassExists() {
        checkClassExists("com.examly.springapp.service.ShowService");
    }

    @Test
    void ShowModelClassExists() {
        checkClassExists("com.examly.springapp.model.Show");
    }

    @Test
    void ShowModelHasTitleField() {
        checkFieldExists("com.examly.springapp.model.Show", "title");
    }

    @Test
    void ShowModelHasGenreField() {
        checkFieldExists("com.examly.springapp.model.Show", "genre");
    }

    @Test
    void ShowModelHasReleaseYearField() {
        checkFieldExists("com.examly.springapp.model.Show", "releaseYear");
    }

    @Test
    void ShowModelHasRatingField() {
        checkFieldExists("com.examly.springapp.model.Show", "rating");
    }

    @Test
    void ShowModelHasDurationField() {
        checkFieldExists("com.examly.springapp.model.Show", "duration");
    }

    @Test
    void ShowRepoExtendsJpaRepository() {
        checkClassImplementsInterface("com.examly.springapp.repository.ShowRepository", "org.springframework.data.jpa.repository.JpaRepository");
    }

    @Test
    void CorsConfigurationClassExists() {
        checkClassExists("com.examly.springapp.configuration.CorsConfiguration");
    }

    @Test
    void CorsConfigurationHasConfigurationAnnotation() {
        checkClassHasAnnotation("com.examly.springapp.configuration.CorsConfiguration", "org.springframework.context.annotation.Configuration");
    }

    // ---------- Helpers ----------

    private void checkClassExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " does not exist.");
        }
    }

    private void checkFieldExists(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            clazz.getDeclaredField(fieldName);
        } catch (ClassNotFoundException | NoSuchFieldException e) {
            fail("Field " + fieldName + " in class " + className + " does not exist.");
        }
    }

    private void checkClassImplementsInterface(String className, String interfaceName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> interfaceClazz = Class.forName(interfaceName);
            assertTrue(interfaceClazz.isAssignableFrom(clazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or interface " + interfaceName + " does not exist.");
        }
    }

    private void checkClassHasAnnotation(String className, String annotationName) {
        try {
            Class<?> clazz = Class.forName(className);
            Class<?> annotationClazz = Class.forName(annotationName);
            assertTrue(clazz.isAnnotationPresent((Class<? extends java.lang.annotation.Annotation>) annotationClazz));
        } catch (ClassNotFoundException e) {
            fail("Class " + className + " or annotation " + annotationName + " does not exist.");
        }
    }
}
