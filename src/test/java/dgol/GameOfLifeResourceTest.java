package dgol;

import dgol.model.Grid;
import dgol.service.GameOfLifeService;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@QuarkusTest
public class GameOfLifeResourceTest {

    //TODO: Add test cases to check body content. Some issue with JSON-B object mapping..

    private static GameOfLifeService serviceMock;

    @BeforeAll
    public static void setup() {
        serviceMock = mock(GameOfLifeService.class);
        QuarkusMock.installMockForType(serviceMock, GameOfLifeService.class);
    }


    @Test
    public void when_initializeEndpointIsCalled_should_return_http200() {
        Grid mockGrid = createMockGrid();
        when(serviceMock.initGame(10,10, 0.35)).thenReturn(mockGrid);
        given()
                .contentType(ContentType.JSON)
                .body("{\"columns\": \"10\", \"rows\": \"10\", \"aliveProbability\": \"0.35\"}")
                .when()
                .post("/gameoflife/initialize")
                .then()
                .statusCode(200);

    }

    @Test
    public void when_nextEndpointIsCalled_should_return_http200() {
        // Arrange
        Grid mockNextGrid = createMockGrid();
        when(serviceMock.next()).thenReturn(mockNextGrid);

        // Act
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/gameoflife/next")
                .then()
                .statusCode(200);

    }

    private Grid createMockGrid() {
        return new Grid(10, 10);
    }
}

