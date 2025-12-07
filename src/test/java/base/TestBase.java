package base;

import assertions.PetAssertions;
import org.testng.annotations.BeforeMethod;
import services.PetService;

public class TestBase {

    protected PetService petService;
    protected PetAssertions petAssertions;

    @BeforeMethod
    public void setUp() {
        petService = new PetService();
        petAssertions = new PetAssertions();
    }
}