package assertions;

import io.restassured.response.Response;
import models.Pet;
import org.testng.Assert;

public class PetAssertions {

    public void assertStatusCode(Response response, int expectedCode) {
        Assert.assertEquals(response.getStatusCode(), expectedCode,
                "Status code mismatch");
    }


    public void assertPetCreated(Response response, Pet expectedPet) {
        Pet actual = response.as(Pet.class);

        Assert.assertEquals(actual.getId(), expectedPet.getId(), "Pet ID mismatch");
        Assert.assertEquals(actual.getName(), expectedPet.getName(), "Pet name mismatch");
        Assert.assertEquals(actual.getStatus(), expectedPet.getStatus(), "Pet status mismatch");
    }


    public void assertPetReturned(Response response, Pet expectedPet) {
        Pet actual = response.as(Pet.class);

        Assert.assertEquals(actual.getId(), expectedPet.getId(), "Pet ID mismatch after GET");
        Assert.assertEquals(actual.getName(), expectedPet.getName(), "Pet name mismatch after GET");
        Assert.assertEquals(actual.getStatus(), expectedPet.getStatus(), "Pet status mismatch after GET");
    }


    public void assertPetUpdated(Response response, Pet expectedPet) {
        Pet actual = response.as(Pet.class);

        Assert.assertEquals(actual.getId(), expectedPet.getId(), "Pet ID mismatch after update");
        Assert.assertEquals(actual.getName(), expectedPet.getName(), "Pet name mismatch after update");
        Assert.assertEquals(actual.getStatus(), expectedPet.getStatus(), "Pet status mismatch after update");
    }
}