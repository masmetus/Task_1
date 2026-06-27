package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    public void shouldContainsAllEnumValues() {
        IngredientType[] ingredientTypes = IngredientType.values();

        assertEquals(2, ingredientTypes.length, "Кол-во типов ингредиентов различается. Ожидается 2");
        assertEquals(IngredientType.SAUCE, ingredientTypes[0], "Тип должен быть Соус");
        assertEquals(IngredientType.FILLING, ingredientTypes[1], "Тип должен быть Начинка");
    }
}
