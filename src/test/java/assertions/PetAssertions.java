package assertions;

import io.restassured.response.Response;
import models.Pet;
import org.testng.Assert;

public class PetAssertions {

    /**
     * Assert HTTP status code
     */
    public void assertStatusCode(Response response, int expectedCode) {
        Assert.assertEquals(response.getStatusCode(), expectedCode,
                "❌ Status code mismatch");
    }

    /**
     * Assert pet created successfully
     */
    public void assertPetCreated(Response response, Pet expectedPet) {
        Pet actual = response.as(Pet.class);

        Assert.assertEquals(actual.getId(), expectedPet.getId(), "❌ Pet ID mismatch");
        Assert.assertEquals(actual.getName(), expectedPet.getName(), "❌ Pet name mismatch");
        Assert.assertEquals(actual.getStatus(), expectedPet.getStatus(), "❌ Pet status mismatch");
    }

    /**
     * Assert the pet returned by GET /pet/{id}
     */
    public void assertPetReturned(Response response, Pet expectedPet) {
        Pet actual = response.as(Pet.class);

        Assert.assertEquals(actual.getId(), expectedPet.getId(), "❌ Pet ID mismatch after GET");
        Assert.assertEquals(actual.getName(), expectedPet.getName(), "❌ Pet name mismatch after GET");
        Assert.assertEquals(actual.getStatus(), expectedPet.getStatus(), "❌ Pet status mismatch after GET");
    }

    /**
     * Assert pet updated successfully
     */
    public void assertPetUpdated(Response response, Pet expectedPet) {
        Pet actual = response.as(Pet.class);

        Assert.assertEquals(actual.getId(), expectedPet.getId(), "❌ Pet ID mismatch after update");
        Assert.assertEquals(actual.getName(), expectedPet.getName(), "❌ Pet name mismatch after update");
        Assert.assertEquals(actual.getStatus(), expectedPet.getStatus(), "❌ Pet status mismatch after update");
    }
}