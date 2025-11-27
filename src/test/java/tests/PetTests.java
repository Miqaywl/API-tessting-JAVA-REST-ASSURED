package tests;

import base.TestBase;
import models.Pet;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class PetTests extends TestBase {

    @Test
    public void createPetTest() {
        Pet pet = buildPet("Doggie", "available");

        var response = petService.addPet(pet);

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetCreated(response, pet);

        petService.deletePet(pet.getId());
    }

    @Test
    public void getPetByIdTest() {
        Pet pet = buildPet("Kitty", "available");

        petService.addPet(pet);

        var response = petService.getPet(pet.getId());

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetReturned(response, pet);

        petService.deletePet(pet.getId());
    }

    @Test
    public void updatePetTest() {
        Pet pet = buildPet("Parrot", "available");
        petService.addPet(pet);

        pet.setName("GreenParrot");
        pet.setStatus("sold");

        var response = petService.updatePet(pet);

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetUpdated(response, pet);

        petService.deletePet(pet.getId());
    }

    @Test
    public void deletePetTest() {
        Pet pet = buildPet("Rabbit", "available");
        petService.addPet(pet);

        var deleteResponse = petService.deletePet(pet.getId());
        petAssertions.assertStatusCode(deleteResponse, 200);

        var getResponse = petService.getPet(pet.getId());
        petAssertions.assertStatusCode(getResponse, 404);
    }

    @Test
    public void getNonExistingPetTest() {
        long invalidId = generatePetId();

        var response = petService.getPet(invalidId);

        petAssertions.assertStatusCode(response, 404);
    }

    @Test
    public void updateMissingPetCreatesNewEntryTest() {
        Pet pet = buildPet("GhostPet", "unknown");

        var response = petService.updatePet(pet);

        petAssertions.assertStatusCode(response, 200);
        petAssertions.assertPetUpdated(response, pet);

        var getResponse = petService.getPet(pet.getId());
        petAssertions.assertStatusCode(getResponse, 200);
        petAssertions.assertPetReturned(getResponse, pet);

        petService.deletePet(pet.getId());
    }

    @Test
    public void deleteNonExistingPetTest() {
        long invalidId = generatePetId();

        var response = petService.deletePet(invalidId);

        petAssertions.assertStatusCode(response, 404);
    }

    private Pet buildPet(String namePrefix, String status) {
        long id = generatePetId();
        return new Pet(id, namePrefix + "-" + id, status);
    }

    private long generatePetId() {
        return ThreadLocalRandom.current().nextLong(1_000_000_000L, 9_999_999_999L);
    }
}