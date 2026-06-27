package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    private Ingredient ingredient;


    @Test
    public void shouldReturnIngredientType() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Мясо человека", 4.45f);

        IngredientType actual = ingredient.getType();

        assertEquals(IngredientType.SAUCE, actual, "Типы ингредиентов не совпадают");
    }

    @Test
    public void shouldReturnBunName() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Мясо человека", 4.45f);

        String expected = "Мясо человека";
        String actual = ingredient.getName();

        assertEquals(expected, actual, "Название не совпадает");
    }

    @Test
    public void shouldReturnBunPrise() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Мясо человека", -4.45f);

        Float expected = -4.45f;
        Float actual = ingredient.getPrice();

        assertEquals(expected, actual, "Цена не совпадает");
    }
}
