package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.*;


public class BurgerTest {

    private Burger burger;

    @Test
    public void shouldReturnBurgerPriceWithBunAndIngredients() {
        burger = new Burger();

        Bun bun = new Bun("Каменная булка", 15f);
        Ingredient sause = new Ingredient(SAUCE, "Табаско", 5f);
        Ingredient filling = new Ingredient(FILLING, "Мясо тролля", 125f);

        burger.setBuns(bun);
        burger.addIngredient(sause);
        burger.addIngredient(filling);

        Float expectedPrice = 160f;
        Float actual = burger.getPrice();

        assertEquals(expectedPrice, actual, "Цены не сходятся.");
    }

    @Test
    public void shouldReturnBurgerReceipt() {
        burger = new Burger();

        Bun bun = new Bun("Каменная булка", 15f);
        Ingredient sause = new Ingredient(SAUCE, "Табаско", 5f);
        Ingredient filling = new Ingredient(FILLING, "Мясо тролля", 125f);

        burger.setBuns(bun);
        burger.addIngredient(sause);
        burger.addIngredient(filling);

        String expected = "(==== Каменная булка ====)\n"
                + "= sauce Табаско =\n"
                + "= filling Мясо тролля =\n"
                + "(==== Каменная булка ====)\n"
                + "\n"
                + "Price: 160,000000\n";


        String actual = burger.getReceipt();

        assertEquals(expected.replace("\r\n", "\n"),
                actual.replace("\r\n", "\n"),
                "Рецепт не сходится");
    }

    @Test
    void shouldDecreasePriceAfterRemovingIngredient() {
        Burger burger = new Burger();

        Bun bun = new Bun("Булка из водорослей", 10f);
        Ingredient sauce = new Ingredient(SAUCE, "Кисло-сладкий", 5f);
        Ingredient filling = new Ingredient(FILLING, "Мясо жмыха", 20f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float priceBefore = burger.getPrice();

        burger.removeIngredient(0);

        float priceAfter = burger.getPrice();

        assertNotEquals(priceBefore, priceAfter);
        assertEquals(2 * 10f + 20f, priceAfter);
    }

    @Test
    void shouldChangeIngredientOrderInReceipt() {
        Burger burger = new Burger();

        Bun bun = new Bun("Булка из водорослей", 10f);
        Ingredient sauce = new Ingredient(SAUCE, "Кисло-сладкий", 5f);
        Ingredient filling = new Ingredient(FILLING, "Мясо жмыха", 20f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        String receipt = burger.getReceipt();

        int sauceIndex = receipt.indexOf("Кисло-сладкий");
        int fillingIndex = receipt.indexOf("Мясо жмыха");

        assertTrue(fillingIndex < sauceIndex);
    }
}
