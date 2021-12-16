package populator;

import categories.CategoryNames;
import com.github.javafaker.Faker;

public class RandomStorePopulator {
    Faker faker = new Faker();

    public String getProductName(CategoryNames category) {
        switch (category) {
            case FRUIT:
                return faker.food().fruit();
            case SPICE:
                return faker.food().spice();
            case SUSHI:
                return faker.food().sushi();
            case VEGETABLE:
                return faker.food().vegetable();
            default:
                return null;
        }
    }

    public Double getProductPrice() {
        return faker.number().randomDouble(2, 1, 100);
    }

    public Double getProductRate() {
        return faker.number().randomDouble(2, 1, 10);
    }
}
