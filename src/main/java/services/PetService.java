package services;

import core.ApiClient;
import io.restassured.response.Response;
import models.Pet;

public class PetService {
    private static final String PET = "/pet";

    public Response addPet(Pet pet) {
        return ApiClient.getRequest()
                .body(pet)
                .post(PET);
    }

    public Response getPet(long id) {
        return ApiClient.getRequest()
                .get(PET + "/" + id);
    }

    public Response updatePet(Pet pet) {
        return ApiClient.getRequest()
                .body(pet)
                .put(PET);
    }

    public Response deletePet(long id) {
        return ApiClient.getRequest()
                .delete(PET + "/" + id);
    }
}