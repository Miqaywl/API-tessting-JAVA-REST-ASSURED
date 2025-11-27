package tests;

import base.TestBase;
import models.Pet;
import org.testng.annotations.Test;

public class PetTests extends TestBase {

    @Test
    public void createPetTest() {
        Pet pet = new Pet(1001, "Doggie", "available");

        var response = petService.addPet(pet);

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetCreated(response, pet);
    }

    @Test
    public void getPetByIdTest() {
        Pet pet = new Pet(1002, "Kitty", "available");

        petService.addPet(pet);

        var response = petService.getPet(pet.getId());

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetReturned(response, pet);
    }

    @Test
    public void updatePetTest() {
        Pet pet = new Pet(1003, "Parrot", "available");
        petService.addPet(pet);

        pet.setName("GreenParrot");
        pet.setStatus("sold");

        var response = petService.updatePet(pet);

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetUpdated(response, pet);
    }

    @Test
    public void deletePetTest() {
        Pet pet = new Pet(1004, "Rabbit", "available");
        petService.addPet(pet);

        var deleteResponse = petService.deletePet(pet.getId());
        petAssertions.assertStatusCode(deleteResponse, 200);

        var getResponse = petService.getPet(pet.getId());
        petAssertions.assertStatusCode(getResponse, 404);
    }

    @Test
    public void getNonExistingPetTest() {
        long invalidId = 9999999;

        var response = petService.getPet(invalidId);

        petAssertions.assertStatusCode(response, 404);
    }

    @Test
    public void updateNonExistingPetTest() {
        Pet pet = new Pet(888888, "GhostPet", "unknown");

        var response = petService.updatePet(pet);

        petAssertions.assertStatusCode(response, 404);
    }

    @Test
    public void deleteNonExistingPetTest() {
        long invalidId = 1111111;

        var response = petService.deletePet(invalidId);

        petAssertions.assertStatusCode(response, 404);
    }
}