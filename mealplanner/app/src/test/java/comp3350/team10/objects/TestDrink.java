package comp3350.team10.objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestDrink {

    @Nested
    @DisplayName("Simple tests")
    class Test_Simple {
        private Drink testEdible;
        private String testString;

        @BeforeEach
        void setUp() {
            testEdible = new Drink();
            testString = "testString";
        }

        @Test
        @DisplayName("Tests an Drink's contents upon new creation")
        void testInitialValues() {
            assertEquals(testEdible.getDbkey(), -1);
            assertNull(testEdible.getName());
            assertNull(testEdible.getDescription());
            assertEquals(testEdible.getQuantity(), -1);
            assertNull(testEdible.getUnit());

            assertEquals(testEdible.getCalories(), 0);
            assertEquals(testEdible.getProtein(), 0);
            assertEquals(testEdible.getCarbs(), 0);
            assertEquals(testEdible.getFat(), 0);

            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());

            assertFalse(testEdible.getIsCustom());
            assertNull(testEdible.getPhoto());

            assertNotNull(testEdible.getIngredients());
            assertEquals(testEdible.getIngredients().size(), 0);
        }

        @Test
        @DisplayName("Tests setting a simple DB key for a drink")
        void testSetDBKey() {
            testEdible.setDBKey(5);
            assertEquals(testEdible.getDbkey(), 5);

            testEdible.setDBKey(10);
            assertEquals(testEdible.getDbkey(), 10);

            testEdible.initDetails(5, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 5);

            testEdible.initDetails(10, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple name for a drink")
        void testSetName() {
            testEdible.setName(testString);
            assertEquals(testEdible.getName(), testString);

            testEdible.setName("A different name");
            assertEquals(testEdible.getName(), "A different name");

            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), testString);

            testEdible.initDetails(5, "A different name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), "A different name");
        }

        @Test
        @DisplayName("Tests setting a simple description for a drink")
        void testSetDescription() {
            testEdible.setDescription(testString);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.setDescription("A different description");
            assertEquals(testEdible.getDescription(), "A different description");

            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.initDetails(5, "name", "A different description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), "A different description");
        }

        @Test
        @DisplayName("Tests setting a simple quantity for a drink")
        void testSetQuantity() {
            testEdible.setBaseQuantity(5);
            assertEquals(testEdible.getQuantity(), 5);

            testEdible.setBaseQuantity(10);
            assertEquals(testEdible.getQuantity(), 10);

            testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 5);

            testEdible.initDetails(10, "name", "description", 10, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 10);
        }

        @Test
        @DisplayName("Tests setting a drinks unit")
        void testSetUnit() {
            testEdible.setBaseUnit(Edible.Unit.cups);
            assertEquals(testEdible.getUnit(), Edible.Unit.cups);

            testEdible.setBaseUnit(Edible.Unit.g);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);

            testEdible.setBaseUnit(Edible.Unit.ml);
            assertEquals(testEdible.getUnit(), Edible.Unit.ml);

            testEdible.setBaseUnit(Edible.Unit.oz);
            assertEquals(testEdible.getUnit(), Edible.Unit.oz);

            testEdible.setBaseUnit(Edible.Unit.liter);
            assertEquals(testEdible.getUnit(), Edible.Unit.liter);

            testEdible.setBaseUnit(Edible.Unit.serving);
            assertEquals(testEdible.getUnit(), Edible.Unit.serving);

            testEdible.setBaseUnit(Edible.Unit.tbsp);
            assertEquals(testEdible.getUnit(), Edible.Unit.tbsp);

            testEdible.setBaseUnit(Edible.Unit.tsp);
            assertEquals(testEdible.getUnit(), Edible.Unit.tsp);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            assertEquals(testEdible.getUnit(), Edible.Unit.cups);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.g);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.ml);
            assertEquals(testEdible.getUnit(), Edible.Unit.ml);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.oz);
            assertEquals(testEdible.getUnit(), Edible.Unit.oz);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.liter);
            assertEquals(testEdible.getUnit(), Edible.Unit.liter);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.serving);
            assertEquals(testEdible.getUnit(), Edible.Unit.serving);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.tbsp);
            assertEquals(testEdible.getUnit(), Edible.Unit.tbsp);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.tsp);
            assertEquals(testEdible.getUnit(), Edible.Unit.tsp);
        }

        @Test
        @DisplayName("Tests setting a simple calorie count for a drink")
        void testSetCalories() {
            testEdible.setCalories(5);
            assertEquals(testEdible.getCalories(), 5);

            testEdible.setCalories(10);
            assertEquals(testEdible.getCalories(), 10);

            testEdible.initNutrition(5, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 5);

            testEdible.initNutrition(10, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple protein count for a drink")
        void testSetProtein() {
            testEdible.setProtein(5);
            assertEquals(testEdible.getProtein(), 5);

            testEdible.setProtein(10);
            assertEquals(testEdible.getProtein(), 10);

            testEdible.initNutrition(1, 5, 1, 1);
            assertEquals(testEdible.getProtein(), 5);

            testEdible.initNutrition(1, 10, 1, 1);
            assertEquals(testEdible.getProtein(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple carb count for a drink")
        void testSetCarbs() {
            testEdible.setCarbs(5);
            assertEquals(testEdible.getCarbs(), 5);

            testEdible.setCarbs(10);
            assertEquals(testEdible.getCarbs(), 10);

            testEdible.initNutrition(1, 1, 5, 1);
            assertEquals(testEdible.getCarbs(), 5);

            testEdible.initNutrition(1, 1, 10, 1);
            assertEquals(testEdible.getCarbs(), 10);
        }

        @Test
        @DisplayName("Tests setting a simple fat count for a drink")
        void testSetFat() {
            testEdible.setFat(5);
            assertEquals(testEdible.getFat(), 5);

            testEdible.setFat(10);
            assertEquals(testEdible.getFat(), 10);

            testEdible.initNutrition(1, 1, 1, 5);
            assertEquals(testEdible.getFat(), 5);

            testEdible.initNutrition(1, 1, 1, 10);
            assertEquals(testEdible.getFat(), 10);
        }

        @Test
        @DisplayName("Tests setting an drinks alcoholic flag")
        void testSetIsAlcoholic() {
            testEdible.setAlcoholic(false);
            assertFalse(testEdible.getIsAlcoholic());

            testEdible.setAlcoholic(true);
            assertTrue(testEdible.getIsAlcoholic());

            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsAlcoholic());

            testEdible.initCategories(true, false, false, false, false);
            assertTrue(testEdible.getIsAlcoholic());
        }

        @Test
        @DisplayName("Tests setting an drinks spicy flag")
        void testSetIsSpicy() {
            testEdible.setSpicy(false);
            assertFalse(testEdible.getIsSpicy());

            testEdible.setSpicy(true);
            assertTrue(testEdible.getIsSpicy());

            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsSpicy());

            testEdible.initCategories(false, true, false, false, false);
            assertTrue(testEdible.getIsSpicy());
        }

        @Test
        @DisplayName("Tests setting an drinks vegan flag")
        void testSetIsVegan() {
            testEdible.setVegan(false);
            assertFalse(testEdible.getIsVegan());

            testEdible.setVegan(true);
            assertTrue(testEdible.getIsVegan());

            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsVegan());

            testEdible.initCategories(false, false, true, false, false);
            assertTrue(testEdible.getIsVegan());
        }

        @Test
        @DisplayName("Tests setting an drinks vegetarian flag")
        void testSetVegetarian() {
            testEdible.setVegetarian(false);
            assertFalse(testEdible.getIsVegetarian());

            testEdible.setVegetarian(true);
            assertTrue(testEdible.getIsVegetarian());

            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsVegetarian());

            testEdible.initCategories(false, false, false, true, false);
            assertTrue(testEdible.getIsVegetarian());
        }

        @Test
        @DisplayName("Tests setting an drinks gluten free flag")
        void testSetGlutenFree() {
            testEdible.setGlutenFree(false);
            assertFalse(testEdible.getIsGlutenFree());

            testEdible.setGlutenFree(true);
            assertTrue(testEdible.getIsGlutenFree());

            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsGlutenFree());

            testEdible.initCategories(false, false, false, false, true);
            assertTrue(testEdible.getIsGlutenFree());
        }

        @Test
        @DisplayName("Tests setting an drinks custom flag")
        void testSetIsCustom() {
            testEdible.setCustom(false);
            assertFalse(testEdible.getIsCustom());

            testEdible.setCustom(true);
            assertTrue(testEdible.getIsCustom());

            testEdible.initMetadata(false, "photo");
            assertFalse(testEdible.getIsCustom());

            testEdible.initMetadata(true, "photo");
            assertTrue(testEdible.getIsCustom());
        }

        @Test
        @DisplayName("Tests setting a simple photo for a drink")
        void testSetPhoto() {
            testEdible.setPhoto(testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.setPhoto("A different photo");
            assertEquals(testEdible.getPhoto(), "A different photo");

            testEdible.initMetadata(false, testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.initMetadata(false, "A different photo");
            assertEquals(testEdible.getPhoto(), "A different photo");
        }

        @Test
        @DisplayName("Tests cloning a simple drink with all false flags")
        void testCloneDrinkWithAllFalse() {
            Edible newEdible;

            testEdible.initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, testString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 1);
            assertEquals(newEdible.getName(), "name");
            assertEquals(newEdible.getDescription(), "description");
            assertEquals(newEdible.getQuantity(), 1);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 1);
            assertEquals(newEdible.getProtein(), 1);
            assertEquals(newEdible.getCarbs(), 1);
            assertEquals(newEdible.getFat(), 1);
            assertFalse(newEdible.getIsAlcoholic());
            assertFalse(newEdible.getIsSpicy());
            assertFalse(newEdible.getIsVegan());
            assertFalse(newEdible.getIsVegetarian());
            assertFalse(newEdible.getIsGlutenFree());
            assertFalse(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), testString);
        }

        @Test
        @DisplayName("Tests cloning a simple drink with all true flags")
        void testCloneDrinkWithAllTrue() {
            Edible newEdible;
            testEdible.initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, testString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 1);
            assertEquals(newEdible.getName(), "name");
            assertEquals(newEdible.getDescription(), "description");
            assertEquals(newEdible.getQuantity(), 1);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 1);
            assertEquals(newEdible.getProtein(), 1);
            assertEquals(newEdible.getCarbs(), 1);
            assertEquals(newEdible.getFat(), 1);
            assertTrue(newEdible.getIsAlcoholic());
            assertTrue(newEdible.getIsSpicy());
            assertTrue(newEdible.getIsVegan());
            assertTrue(newEdible.getIsVegetarian());
            assertTrue(newEdible.getIsGlutenFree());
            assertTrue(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), testString);
        }

        @Test
        @DisplayName("Tests setting a single ingredient to a drink")
        void testSetIngredients() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, "photo");
            testEdible.initDetails(1, "drink name", "drink description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "drink photo");
            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);
            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 1);
        }

        @Test
        @DisplayName("Tests setting a single ingredients to an edible and updating the drink based on the ingredient")
        void testReadIngredients() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, "photo");
            testEdible.initDetails(1, "drink name", "drink description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "drink photo");
            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);
            testEdible.setIngredients(ingredients);
            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 1);
            assertEquals(testEdible.getProtein(), 1);
            assertEquals(testEdible.getCarbs(), 1);
            assertEquals(testEdible.getFat(), 1);
            assertTrue(testEdible.getIsAlcoholic());
            assertTrue(testEdible.getIsSpicy());
            assertTrue(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertTrue(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 1);
        }
    }

    @Nested
    @DisplayName("Complex tests")
    class Test_Complex {
        private Drink testEdible;
        private String testString;
        private String numberTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Drink();
            numberTestString = "666";
            testString = "String test_instruction=\"very long instructions sdakjlfh&sl@$jfkhldsakjhfiuweasdhyfuiklewahearewrw\" +\n" +
                    "                        \"adsjfkghbewakjdshfljkaewhdflkaewj\\njewifhewl\\r isdfauhjljkewf\\n\\\\wieosuhjrfiol;ewk\" +\n" +
                    "                        \"53465687-/34324o90ukljo&$^#$^@#$%@#^%$*#$#%@@$#@$@!$@#\";";

            testEdible.initDetails(1, "drink name", "drink description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "drink photo");
        }

        @Test
        @DisplayName("Tests setting a complex DB key for a drink")
        void testSetDBKey() {
            testEdible.setDBKey(500);
            assertEquals(testEdible.getDbkey(), 500);

            testEdible.setDBKey(1000);
            assertEquals(testEdible.getDbkey(), 1000);

            testEdible.initDetails(500, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 500);

            testEdible.initDetails(1000, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex name for a drink")
        void testSetName() {
            testEdible.setName(testString);
            assertEquals(testEdible.getName(), testString);

            testEdible.setName(numberTestString);
            assertEquals(testEdible.getName(), numberTestString);

            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), testString);

            testEdible.initDetails(5, numberTestString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), numberTestString);
        }

        @Test
        @DisplayName("Tests setting a complex description for a drink")
        void testSetDescription() {
            testEdible.setDescription(testString);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.setDescription(numberTestString);
            assertEquals(testEdible.getDescription(), numberTestString);

            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.initDetails(5, "name", numberTestString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), numberTestString);
        }

        @Test
        @DisplayName("Tests setting a complex quantity for a drink")
        void testSetQuantity() {
            testEdible.setBaseQuantity(500);
            assertEquals(testEdible.getQuantity(), 500);

            testEdible.setBaseQuantity(1000);
            assertEquals(testEdible.getQuantity(), 1000);

            testEdible.initDetails(5, "name", "description", 500, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 500);

            testEdible.initDetails(10, "name", "description", 1000, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex calorie count for a drink")
        void testSetCalories() {
            testEdible.setCalories(500);
            assertEquals(testEdible.getCalories(), 500);

            testEdible.setCalories(1000);
            assertEquals(testEdible.getCalories(), 1000);

            testEdible.initNutrition(500, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 500);

            testEdible.initNutrition(1000, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex protein count for a drink")
        void testSetProtein() {
            testEdible.setProtein(500);
            assertEquals(testEdible.getProtein(), 500);

            testEdible.setProtein(1000);
            assertEquals(testEdible.getProtein(), 1000);

            testEdible.initNutrition(1, 500, 1, 1);
            assertEquals(testEdible.getProtein(), 500);

            testEdible.initNutrition(1, 1000, 1, 1);
            assertEquals(testEdible.getProtein(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex carb count for a drink")
        void testSetCarbs() {
            testEdible.setCarbs(500);
            assertEquals(testEdible.getCarbs(), 500);

            testEdible.setCarbs(1000);
            assertEquals(testEdible.getCarbs(), 1000);

            testEdible.initNutrition(1, 1, 500, 1);
            assertEquals(testEdible.getCarbs(), 500);

            testEdible.initNutrition(1, 1, 1000, 1);
            assertEquals(testEdible.getCarbs(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex fat count for a drink")
        void testSetFat() {
            testEdible.setFat(500);
            assertEquals(testEdible.getFat(), 500);

            testEdible.setFat(1000);
            assertEquals(testEdible.getFat(), 1000);

            testEdible.initNutrition(1, 1, 1, 500);
            assertEquals(testEdible.getFat(), 500);

            testEdible.initNutrition(1, 1, 1, 1000);
            assertEquals(testEdible.getFat(), 1000);
        }

        @Test
        @DisplayName("Tests setting a complex photo for a drink")
        void testSetPhoto() {
            testEdible.setPhoto(testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.setPhoto(numberTestString);
            assertEquals(testEdible.getPhoto(), numberTestString);

            testEdible.initMetadata(false, testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.initMetadata(false, numberTestString);
            assertEquals(testEdible.getPhoto(), numberTestString);
        }

        @Test
        @DisplayName("Tests cloning a complex drink with all true or false flags")
        void testCloneDrink() {
            Edible newEdible;

            testEdible.initDetails(0, testString, testString, 1, Edible.Unit.g)
                    .initNutrition(0, 0, 0, 0)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, testString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 0);
            assertEquals(newEdible.getName(), testString);
            assertEquals(newEdible.getDescription(), testString);
            assertEquals(newEdible.getQuantity(), 1);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 0);
            assertEquals(newEdible.getProtein(), 0);
            assertEquals(newEdible.getCarbs(), 0);
            assertEquals(newEdible.getFat(), 0);
            assertFalse(newEdible.getIsAlcoholic());
            assertFalse(newEdible.getIsSpicy());
            assertFalse(newEdible.getIsVegan());
            assertFalse(newEdible.getIsVegetarian());
            assertFalse(newEdible.getIsGlutenFree());
            assertFalse(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), testString);

            testEdible.initDetails(500, numberTestString, numberTestString, 500, Edible.Unit.g)
                    .initNutrition(500, 500, 500, 500)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, numberTestString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 500);
            assertEquals(newEdible.getName(), numberTestString);
            assertEquals(newEdible.getDescription(), numberTestString);
            assertEquals(newEdible.getQuantity(), 500);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 500);
            assertEquals(newEdible.getProtein(), 500);
            assertEquals(newEdible.getCarbs(), 500);
            assertEquals(newEdible.getFat(), 500);
            assertTrue(newEdible.getIsAlcoholic());
            assertTrue(newEdible.getIsSpicy());
            assertTrue(newEdible.getIsVegan());
            assertTrue(newEdible.getIsVegetarian());
            assertTrue(newEdible.getIsGlutenFree());
            assertTrue(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), numberTestString);
        }

        @Test
        @DisplayName("Tests setting multiple ingredients to a drink")
        void testSetIngredients() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(true, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, true, true, true)
                    .initMetadata(true, "photo");
            Edible smallDrink = new Drink().initDetails(1, "drink", "description", 5, Edible.Unit.g)
                    .initNutrition(10, 10, 10, 10)
                    .initCategories(false, true, false, true, false)
                    .initMetadata(true, "photo");
            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            currIngredient.init(smallDrink, 1, Edible.Unit.liter);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 3);
        }

        @Test
        @DisplayName("Tests setting multiple ingredients to an drink and updating its details based on the ingredients")
        void testReadIngredients() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(true, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(1, 1, 1, 1)
                    .initCategories(false, false, true, true, true)
                    .initMetadata(true, "photo");
            Edible smallDrink = new Drink().initDetails(1, "drink", "description", 5, Edible.Unit.g)
                    .initNutrition(10, 10, 10, 10)
                    .initCategories(false, true, false, true, false)
                    .initMetadata(true, "photo");
            DrinkIngredient currIngredient = new DrinkIngredient();

            testEdible.initDetails(1, "drink name", "drink description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "drink photo");

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(smallDrink, 1, Edible.Unit.liter);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);
            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 12);
            assertEquals(testEdible.getProtein(), 12);
            assertEquals(testEdible.getCarbs(), 12);
            assertEquals(testEdible.getFat(), 12);
            assertTrue(testEdible.getIsAlcoholic());
            assertTrue(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 3);
        }

        @Test
        @DisplayName("Tests setting multiple ingredients to an drink and updating its details based on the ingredients but with different details")
        void testReadMoreIngredients() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            DrinkIngredient currIngredient = new DrinkIngredient();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(10, 10, 10, 10)
                    .initCategories(false, false, false, true, true)
                    .initMetadata(true, "photo");
            Edible smallDrink = new Drink().initDetails(1, "drink", "description", 5, Edible.Unit.g)
                    .initNutrition(15, 15, 15, 15)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(smallDrink, 1, Edible.Unit.liter);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);
            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 30);
            assertEquals(testEdible.getProtein(), 30);
            assertEquals(testEdible.getCarbs(), 30);
            assertEquals(testEdible.getFat(), 30);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 3);
        }
    }

    @Nested
    @DisplayName("Empty tests")
    class Test_Empty {
        private Drink testEdible;
        private String emptyTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Drink();
            emptyTestString = "";

            testEdible.initDetails(1, "drink name", "drink description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "drink photo");
        }

        @Test
        @DisplayName("Tests setting a empty or null name for a drink")
        void testSetName() {
            try {
                testEdible.setName(null);
                fail("Name cannot be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.setName(emptyTestString);
                fail("Name cannot be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setName(" ");
            assertEquals(testEdible.getName(), " ");

            try {
                testEdible.initDetails(1, null, "description", 5, Edible.Unit.cups);
                fail("Name cannot be null, should throw IllegalArgumentExceptionn");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, emptyTestString, "description", 5, Edible.Unit.cups);
                fail("Name cannot be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.initDetails(1, " ", "description", 5, Edible.Unit.cups);
            assertEquals(testEdible.getName(), " ");
        }

        @Test
        @DisplayName("Tests setting a empty or null description for a drink")
        void testSetDescription() {
            try {
                testEdible.setDescription(null);
                fail("Description should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setDescription(emptyTestString);
            assertEquals(testEdible.getDescription(), emptyTestString);

            testEdible.setDescription(" ");
            assertEquals(testEdible.getDescription(), " ");

            try {
                testEdible.initDetails(1, "name", null, 5, Edible.Unit.cups);
                fail("Description should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.initDetails(1, "name", emptyTestString, 5, Edible.Unit.cups);
            assertEquals(testEdible.getDescription(), emptyTestString);

            testEdible.initDetails(1, "name", " ", 5, Edible.Unit.cups);
            assertEquals(testEdible.getDescription(), " ");
        }

        @Test
        @DisplayName("Tests setting a null unit for a drink")
        void testSetUnit() {
            try {
                testEdible.setBaseUnit(null);
                fail("Unit should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initDetails(1, "name", "description", 5, null);
                fail("Unit should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting a empty or null photo for a drink")
        void testSetPhoto() {
            try {
                testEdible.setPhoto(null);
                fail("Photo should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.setPhoto(emptyTestString);
                fail("Photo should not be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setPhoto(" ");
            assertEquals(testEdible.getPhoto(), " ");

            testEdible.initMetadata(false, " ");
            assertEquals(testEdible.getPhoto(), " ");

            try {
                testEdible.initMetadata(false, null);
                fail("Photo should not be null, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            try {
                testEdible.initMetadata(false, emptyTestString);
                fail("Photo should not be empty, should throw IllegalArgumentException");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }
        }

        @Test
        @DisplayName("Tests setting empty or null ingredient lists for a drink")
        void testSetIngredients() {
            try {
                testEdible.setIngredients(null);
                fail("Ingredient list cannot be null");
            } catch (Exception e) {
                assertTrue(e instanceof IllegalArgumentException);
            }

            testEdible.setIngredients(new ArrayList<DrinkIngredient>());
            assertEquals(testEdible.getIngredients().size(), 0);
            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
        }

        @Test
        @DisplayName("Tests reading ingredient data on an empty ingredient list for a drink")
        void testReadIngredients() {
            assertEquals(testEdible.getIngredients().size(), 0);
            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");

            testEdible.setIngredients(new ArrayList<DrinkIngredient>());
            testEdible.readIngredientData();

            assertEquals(testEdible.getIngredients().size(), 0);
            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
        }
    }

    @Nested
    @DisplayName("Edge case tests")
    class Test_EdgeCases {
        private Drink testEdible;
        private String testString;
        private String largeTestString;

        @BeforeEach
        void setUp() {
            testEdible = new Drink();
            testString = "testString";
            largeTestString = "";

            for (int i = 0; i < 9999; i++) {
                largeTestString = largeTestString + "a";
            }

            testEdible.initDetails(1, "drink name", "drink description", 5, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, "drink photo");
        }

        @Test
        @DisplayName("Tests setting the smallest DB key for a drink")
        void testSetDBKey() {
            testEdible.setDBKey(0);
            assertEquals(testEdible.getDbkey(), 0);

            testEdible.initDetails(0, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 0);
        }

        @Test
        @DisplayName("Tests setting the same DB key that a drink already has")
        void testSetDuplicateDBKey() {
            testEdible.setDBKey(5);
            testEdible.setDBKey(5);
            assertEquals(testEdible.getDbkey(), 5);

            testEdible.initDetails(5, "name", "description", 1, Edible.Unit.g);
            testEdible.initDetails(5, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getDbkey(), 5);
        }

        @Test
        @DisplayName("Tests setting the longest name for a drink")
        void testSetName() {
            testEdible.setName(largeTestString);
            assertEquals(testEdible.getName(), largeTestString);

            testEdible.initDetails(5, largeTestString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same name a drink already has")
        void testSetDuplicateName() {
            testEdible.setName(testString);
            testEdible.setName(testString);
            assertEquals(testEdible.getName(), testString);

            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            testEdible.initDetails(5, testString, "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getName(), testString);
        }

        @Test
        @DisplayName("Tests setting the longest description for a drink")
        void testSetDescription() {
            testEdible.setDescription(largeTestString);
            assertEquals(testEdible.getDescription(), largeTestString);

            testEdible.initDetails(5, "name", largeTestString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same description a drink already has")
        void testSetDuplicateDescription() {
            testEdible.setDescription(testString);
            testEdible.setDescription(testString);
            assertEquals(testEdible.getDescription(), testString);

            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            testEdible.initDetails(5, "name", testString, 1, Edible.Unit.g);
            assertEquals(testEdible.getDescription(), testString);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest quantities for a drink")
        void testSetQuantity() {
            testEdible.setBaseQuantity(1);
            assertEquals(testEdible.getQuantity(), 1);

            testEdible.setBaseQuantity(9999);
            assertEquals(testEdible.getQuantity(), 9999);

            testEdible.initDetails(1, "name", "description", 1, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 1);

            testEdible.initDetails(1, "name", "description", 9999, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same quantity a drink already has")
        void testSetDuplicateQuantity() {
            testEdible.setBaseQuantity(5);
            testEdible.setBaseQuantity(5);
            assertEquals(testEdible.getQuantity(), 5);

            testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
            testEdible.initDetails(5, "name", "description", 5, Edible.Unit.g);
            assertEquals(testEdible.getQuantity(), 5);
        }

        @Test
        @DisplayName("Tests setting the same unit a drink already has")
        void testSetUnit() {
            testEdible.setBaseUnit(Edible.Unit.cups);
            testEdible.setBaseUnit(Edible.Unit.cups);
            assertEquals(testEdible.getUnit(), Edible.Unit.cups);

            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            testEdible.initDetails(5, "name", "description", 10, Edible.Unit.cups);
            assertEquals(testEdible.getUnit(), Edible.Unit.cups);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest calorie counts for a drink")
        void testSetCalories() {
            testEdible.setCalories(0);
            assertEquals(testEdible.getCalories(), 0);

            testEdible.setCalories(9999);
            assertEquals(testEdible.getCalories(), 9999);

            testEdible.initNutrition(0, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 0);

            testEdible.initNutrition(9999, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same calorie count a drink already has")
        void testSetDuplicateCalories() {
            testEdible.setCalories(5);
            testEdible.setCalories(5);
            assertEquals(testEdible.getCalories(), 5);

            testEdible.initNutrition(5, 1, 1, 1);
            testEdible.initNutrition(5, 1, 1, 1);
            assertEquals(testEdible.getCalories(), 5);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest protein counts for a drink")
        void testSetProtein() {
            testEdible.setProtein(0);
            assertEquals(testEdible.getProtein(), 0);

            testEdible.setProtein(9999);
            assertEquals(testEdible.getProtein(), 9999);

            testEdible.initNutrition(1, 0, 1, 1);
            assertEquals(testEdible.getProtein(), 0);

            testEdible.initNutrition(1, 9999, 1, 1);
            assertEquals(testEdible.getProtein(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same protein count a drink already has")
        void testSetDuplicateProtein() {
            testEdible.setProtein(5);
            testEdible.setProtein(5);
            assertEquals(testEdible.getProtein(), 5);

            testEdible.initNutrition(1, 5, 1, 1);
            testEdible.initNutrition(1, 5, 1, 1);
            assertEquals(testEdible.getProtein(), 5);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest carb counts for a drink")
        void testSetCarbs() {
            testEdible.setCarbs(0);
            assertEquals(testEdible.getCarbs(), 0);

            testEdible.setCarbs(9999);
            assertEquals(testEdible.getCarbs(), 9999);

            testEdible.initNutrition(1, 1, 0, 1);
            assertEquals(testEdible.getCarbs(), 0);

            testEdible.initNutrition(1, 1, 9999, 1);
            assertEquals(testEdible.getCarbs(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same carbs count a drink already has")
        void testSetDuplicateCarbs() {
            testEdible.setCarbs(5);
            testEdible.setCarbs(5);
            assertEquals(testEdible.getCarbs(), 5);

            testEdible.initNutrition(1, 1, 5, 1);
            testEdible.initNutrition(1, 1, 5, 1);
            assertEquals(testEdible.getCarbs(), 5);
        }

        @Test
        @DisplayName("Tests setting the largest and smallest fat count for a drink")
        void testSetFat() {
            testEdible.setFat(0);
            assertEquals(testEdible.getFat(), 0);

            testEdible.setFat(9999);
            assertEquals(testEdible.getFat(), 9999);

            testEdible.initNutrition(1, 1, 1, 0);
            assertEquals(testEdible.getFat(), 0);

            testEdible.initNutrition(1, 1, 1, 9999);
            assertEquals(testEdible.getFat(), 9999);
        }

        @Test
        @DisplayName("Tests setting the same fat count a drink already has")
        void testSetDuplicateFat() {
            testEdible.setFat(5);
            testEdible.setFat(5);
            assertEquals(testEdible.getFat(), 5);

            testEdible.initNutrition(1, 1, 1, 5);
            testEdible.initNutrition(1, 1, 1, 5);
            assertEquals(testEdible.getFat(), 5);
        }

        @Test
        @DisplayName("Tests setting the same alcoholic flag a drink already has")
        void testSetIsAlcoholic() {
            testEdible.setAlcoholic(false);
            testEdible.setAlcoholic(false);
            assertFalse(testEdible.getIsAlcoholic());

            testEdible.setAlcoholic(true);
            testEdible.setAlcoholic(true);
            assertTrue(testEdible.getIsAlcoholic());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsAlcoholic());

            testEdible.initCategories(true, false, false, false, false);
            testEdible.initCategories(true, false, false, false, false);
            assertTrue(testEdible.getIsAlcoholic());
        }

        @Test
        @DisplayName("Tests setting the same spicy flag a drink already has")
        void testSetIsSpicy() {
            testEdible.setSpicy(false);
            testEdible.setSpicy(false);
            assertFalse(testEdible.getIsSpicy());

            testEdible.setSpicy(true);
            testEdible.setSpicy(true);
            assertTrue(testEdible.getIsSpicy());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsSpicy());

            testEdible.initCategories(false, true, false, false, false);
            testEdible.initCategories(false, true, false, false, false);
            assertTrue(testEdible.getIsSpicy());
        }

        @Test
        @DisplayName("Tests setting the same vegan flag a drink already has")
        void testSetIsVegan() {
            testEdible.setVegan(false);
            testEdible.setVegan(false);
            assertFalse(testEdible.getIsVegan());

            testEdible.setVegan(true);
            testEdible.setVegan(true);
            assertTrue(testEdible.getIsVegan());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsVegan());

            testEdible.initCategories(false, false, true, false, false);
            testEdible.initCategories(false, false, true, false, false);
            assertTrue(testEdible.getIsVegan());
        }

        @Test
        @DisplayName("Tests setting the same vegetarian flag a drink already has")
        void testSetVegetarian() {
            testEdible.setVegetarian(false);
            testEdible.setVegetarian(false);
            assertFalse(testEdible.getIsVegetarian());

            testEdible.setVegetarian(true);
            testEdible.setVegetarian(true);
            assertTrue(testEdible.getIsVegetarian());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsVegetarian());

            testEdible.initCategories(false, false, false, true, false);
            testEdible.initCategories(false, false, false, true, false);
            assertTrue(testEdible.getIsVegetarian());
        }

        @Test
        @DisplayName("Tests setting the same gluten free flag a drink already has")
        void testSetGlutenFree() {
            testEdible.setGlutenFree(false);
            testEdible.setGlutenFree(false);
            assertFalse(testEdible.getIsGlutenFree());

            testEdible.setGlutenFree(true);
            testEdible.setGlutenFree(true);
            assertTrue(testEdible.getIsGlutenFree());

            testEdible.initCategories(false, false, false, false, false);
            testEdible.initCategories(false, false, false, false, false);
            assertFalse(testEdible.getIsGlutenFree());

            testEdible.initCategories(false, false, false, false, true);
            testEdible.initCategories(false, false, false, false, true);
            assertTrue(testEdible.getIsGlutenFree());
        }

        @Test
        @DisplayName("Tests setting the same is custom flag a drink already has")
        void testSetIsCustom() {
            testEdible.setCustom(false);
            testEdible.setCustom(false);
            assertFalse(testEdible.getIsCustom());

            testEdible.setCustom(true);
            testEdible.setCustom(true);
            assertTrue(testEdible.getIsCustom());

            testEdible.initMetadata(false, "photo");
            testEdible.initMetadata(false, "photo");
            assertFalse(testEdible.getIsCustom());

            testEdible.initMetadata(true, "photo");
            testEdible.initMetadata(true, "photo");
            assertTrue(testEdible.getIsCustom());
        }

        @Test
        @DisplayName("Tests setting the longest photo for a drink")
        void testSetPhoto() {
            testEdible.setPhoto(largeTestString);
            assertEquals(testEdible.getPhoto(), largeTestString);

            testEdible.initMetadata(false, largeTestString);
            assertEquals(testEdible.getPhoto(), largeTestString);
        }

        @Test
        @DisplayName("Tests setting the same photo a drink already has")
        void testSetDuplicatePhoto() {
            testEdible.setPhoto(testString);
            testEdible.setPhoto(testString);
            assertEquals(testEdible.getPhoto(), testString);

            testEdible.initMetadata(false, testString);
            testEdible.initMetadata(false, testString);
            assertEquals(testEdible.getPhoto(), testString);
        }

        @Test
        @DisplayName("Tests cloning a complex drink with all true or false flags")
        void testCloneDrink() {
            Edible newEdible;

            testEdible.initDetails(0, largeTestString, largeTestString, 1, Edible.Unit.g)
                    .initNutrition(0, 0, 0, 0)
                    .initCategories(false, false, false, false, false)
                    .initMetadata(false, largeTestString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 0);
            assertEquals(newEdible.getName(), largeTestString);
            assertEquals(newEdible.getDescription(), largeTestString);
            assertEquals(newEdible.getQuantity(), 1);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 0);
            assertEquals(newEdible.getProtein(), 0);
            assertEquals(newEdible.getCarbs(), 0);
            assertEquals(newEdible.getFat(), 0);
            assertFalse(newEdible.getIsAlcoholic());
            assertFalse(newEdible.getIsSpicy());
            assertFalse(newEdible.getIsVegan());
            assertFalse(newEdible.getIsVegetarian());
            assertFalse(newEdible.getIsGlutenFree());
            assertFalse(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), largeTestString);

            testEdible.initDetails(1, largeTestString, largeTestString, 9999, Edible.Unit.g)
                    .initNutrition(9999, 9999, 9999, 9999)
                    .initCategories(true, true, true, true, true)
                    .initMetadata(true, largeTestString);

            newEdible = testEdible.clone();

            assertEquals(newEdible.getDbkey(), 1);
            assertEquals(newEdible.getName(), largeTestString);
            assertEquals(newEdible.getDescription(), largeTestString);
            assertEquals(newEdible.getQuantity(), 9999);
            assertEquals(newEdible.getUnit(), Edible.Unit.g);
            assertEquals(newEdible.getCalories(), 9999);
            assertEquals(newEdible.getProtein(), 9999);
            assertEquals(newEdible.getCarbs(), 9999);
            assertEquals(newEdible.getFat(), 9999);
            assertTrue(newEdible.getIsAlcoholic());
            assertTrue(newEdible.getIsSpicy());
            assertTrue(newEdible.getIsVegan());
            assertTrue(newEdible.getIsVegetarian());
            assertTrue(newEdible.getIsGlutenFree());
            assertTrue(newEdible.getIsCustom());
            assertEquals(newEdible.getPhoto(), largeTestString);
        }

        @Test
        @DisplayName("Tests adding duplicate ingredients to a drink")
        void testAddDuplicateIngredients() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(2, 2, 2, 2)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");
            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(food, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);

            testEdible.readIngredientData();
            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 4);
            assertEquals(testEdible.getProtein(), 4);
            assertEquals(testEdible.getCarbs(), 4);
            assertEquals(testEdible.getFat(), 4);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);
        }

        @Test
        @DisplayName("Tests adding and calculating ingredients when there is excess calories for a drink")
        void testIngredientsExcessiveCalories() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(9999, 0, 0, 0)
                    .initCategories(false, false, false, true, true)
                    .initMetadata(true, "photo");

            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);

            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 9999);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);
        }

        @Test
        @DisplayName("Tests adding and calculating ingredients when there is excess protein for a drink")
        void testIngredientsExcessiveProtein() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(0, 9999, 0, 0)
                    .initCategories(false, false, false, true, true)
                    .initMetadata(true, "photo");

            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);

            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 9999);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);
        }

        @Test
        @DisplayName("Tests adding and calculating ingredients when there is excess carbs for a drink")
        void testIngredientsExcessiveCarbs() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(0, 0, 9999, 0)
                    .initCategories(false, false, false, true, true)
                    .initMetadata(true, "photo");

            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);

            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 9999);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);
        }

        @Test
        @DisplayName("Tests adding and calculating ingredients when there is excess fat for a drink")
        void testIngredientsExcessiveFat() {
            ArrayList<DrinkIngredient> ingredients = new ArrayList<DrinkIngredient>();
            Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(5, 5, 5, 5)
                    .initCategories(false, false, false, true, false)
                    .initMetadata(true, "photo");
            Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                    .initNutrition(0, 0, 0, 9999)
                    .initCategories(false, false, false, true, true)
                    .initMetadata(true, "photo");

            DrinkIngredient currIngredient = new DrinkIngredient();

            currIngredient.init(food, 1, Edible.Unit.cups);
            ingredients.add(currIngredient);

            currIngredient = new DrinkIngredient();
            currIngredient.init(secondFood, 1, Edible.Unit.ml);
            ingredients.add(currIngredient);

            testEdible.setIngredients(ingredients);

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 5);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertFalse(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);

            testEdible.readIngredientData();

            assertEquals(testEdible.getDbkey(), 1);
            assertEquals(testEdible.getName(), "drink name");
            assertEquals(testEdible.getDescription(), "drink description");
            assertEquals(testEdible.getQuantity(), 5);
            assertEquals(testEdible.getUnit(), Edible.Unit.g);
            assertEquals(testEdible.getCalories(), 5);
            assertEquals(testEdible.getProtein(), 5);
            assertEquals(testEdible.getCarbs(), 5);
            assertEquals(testEdible.getFat(), 9999);
            assertFalse(testEdible.getIsAlcoholic());
            assertFalse(testEdible.getIsSpicy());
            assertFalse(testEdible.getIsVegan());
            assertTrue(testEdible.getIsVegetarian());
            assertFalse(testEdible.getIsGlutenFree());
            assertFalse(testEdible.getIsCustom());
            assertEquals(testEdible.getPhoto(), "drink photo");
            assertEquals(testEdible.getIngredients().size(), 2);
        }
    }
}

@Nested
@DisplayName("Invalid tests")
class Test_Invalid {
    private Drink testEdible;
    private String longTestString;

    @BeforeEach
    void setUp() {
        testEdible = new Drink();
        longTestString = "";

        for (int i = 0; i < 10000; i++) {
            longTestString = longTestString + "a";
        }
    }

    @Test
    @DisplayName("Tests setting an invalid db key for a drink")
    void testSetDBkey() {
        try {
            testEdible.setDBKey(-1);
            fail("Should throw an exception, db key is less than 0");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.initDetails(-1, "name", "description", 5, Edible.Unit.cups);
            fail("Should throw an exception, db key is less than 0");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    @DisplayName("Tests setting an invalid name for a drink")
    void testSetName() {
        try {
            testEdible.setName(longTestString);
            fail("Should throw an exception, this name is too long");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.initDetails(1, longTestString, "description", 5, Edible.Unit.cups);
            fail("Should throw an exception, this name is too long");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    @DisplayName("Tests setting an invalid description for a drink")
    void testSetDescription() {
        try {
            testEdible.setDescription(longTestString);
            fail("Should throw an exception, this description is too long");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.initDetails(1, "name", longTestString, 5, Edible.Unit.cups);
            fail("Should throw an exception, this description is too long");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    @DisplayName("Tests setting an invalid quantity for a drink")
    void testSetQuantity() {
        try {
            testEdible.setBaseQuantity(0);
            fail("Should throw an exception, this quantity is too low");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.setBaseQuantity(10000);
            fail("Should throw an exception, this quantity is too big");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.initDetails(1, "name", "description", 0, Edible.Unit.cups);
            fail("Should throw an exception, this quantity is too low");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.initDetails(1, "name", "description", 10000, Edible.Unit.cups);
            fail("Should throw an exception, this quantity is too big");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    @DisplayName("Tests setting an invalid photo for a drink")
    void testSetPhoto() {
        try {
            testEdible.setPhoto(longTestString);
            fail("Should throw an exception, this photo is too long");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            testEdible.initMetadata(false, longTestString);
            fail("Should throw an exception, this photo is too long");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    @DisplayName("Tests cloning an incomplete or invalid drink")
    void testCloneDrink() {
        testEdible.setDBKey(1);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setName("name");

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setDescription("description");

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setBaseQuantity(5);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setBaseUnit(Edible.Unit.g);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setCalories(5);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setProtein(5);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setCarbs(5);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setFat(5);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setAlcoholic(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setSpicy(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setVegan(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setVegetarian(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setVegan(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setVegetarian(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setGlutenFree(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setCustom(true);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.setPhoto("photo");

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.initDetails(1, "name", "description", 5, Edible.Unit.g);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.initNutrition(5, 5, 5, 5);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.initCategories(false, false, false, false, false);

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        testEdible = new Drink();
        testEdible.initMetadata(false, "photo");

        try {
            testEdible.clone();
            fail("Should throw an exception, this is an incomplete drink");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    @DisplayName("Tests adding ingredient lists with null entries where null appears fisrt, then second, then last")
    void testIngredientList() {
        DrinkIngredient foodIngredient = new DrinkIngredient();
        DrinkIngredient secondFoodIngredient = new DrinkIngredient();
        ArrayList<DrinkIngredient> ingredients;
        Edible food = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                .initNutrition(5, 5, 5, 5)
                .initCategories(false, false, false, true, false)
                .initMetadata(true, "photo");
        Edible secondFood = new Edible().initDetails(1, "name", "description", 1, Edible.Unit.g)
                .initNutrition(0, 0, 0, 9999)
                .initCategories(false, false, false, true, true)
                .initMetadata(true, "photo");

        foodIngredient.init(food, 1, Edible.Unit.cups);
        secondFoodIngredient.init(secondFood, 1, Edible.Unit.cups);

        try {
            ingredients = new ArrayList<DrinkIngredient>();
            ingredients.add(null);
            ingredients.add(foodIngredient);
            ingredients.add(secondFoodIngredient);
            testEdible.setIngredients(ingredients);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            ingredients = new ArrayList<DrinkIngredient>();
            ingredients.add(foodIngredient);
            ingredients.add(null);
            ingredients.add(secondFoodIngredient);
            testEdible.setIngredients(ingredients);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        try {
            ingredients = new ArrayList<DrinkIngredient>();
            ingredients.add(foodIngredient);
            ingredients.add(secondFoodIngredient);
            ingredients.add(null);
            testEdible.setIngredients(ingredients);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
}