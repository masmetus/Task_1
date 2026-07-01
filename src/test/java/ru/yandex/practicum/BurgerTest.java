package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    @Test
    public void shouldReturnBurgerPriceWithBunAndIngredients() {
        Burger burger = new Burger();

        Mockito.when(bunMock.getPrice()).thenReturn(15f);
        Mockito.when(sauceMock.getPrice()).thenReturn(5f);
        Mockito.when(fillingMock.getPrice()).thenReturn(125f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        Float expectedPrice = 160f;
        Float actual = burger.getPrice();

        assertEquals(expectedPrice, actual, "Цены не сходятся.");
    }

    @Test
    public void shouldReturnBurgerReceipt() {
        Burger burger = new Burger();

        Mockito.when(bunMock.getName()).thenReturn("Каменная булка");
        Mockito.when(bunMock.getPrice()).thenReturn(15f);

        Mockito.when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauceMock.getName()).thenReturn("Табаско");
        Mockito.when(sauceMock.getPrice()).thenReturn(5f);

        Mockito.when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(fillingMock.getName()).thenReturn("Мясо тролля");
        Mockito.when(fillingMock.getPrice()).thenReturn(125f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        String expected = "(==== Каменная булка ====)\n"
                + "= sauce Табаско =\n"
                + "= filling Мясо тролля =\n"
                + "(==== Каменная булка ====)\n"
                + "\n"
                + String.format("Price: %f\n", 160f);


        String actual = burger.getReceipt();

        assertEquals(expected.replace("\r\n", "\n"),
                actual.replace("\r\n", "\n"),
                "Рецепт не сходится");
    }

    @Test
    void shouldDecreasePriceAfterRemovingIngredient() {
        Burger burger = new Burger();

        Mockito.when(bunMock.getPrice()).thenReturn(10f);
        Mockito.when(sauceMock.getPrice()).thenReturn(5f);
        Mockito.when(fillingMock.getPrice()).thenReturn(20f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        float priceBefore = burger.getPrice();

        burger.removeIngredient(0);

        float priceAfter = burger.getPrice();

        assertNotEquals(priceBefore, priceAfter);
        assertEquals(2 * 10f + 20f, priceAfter, "Цена после удаления неверна");
    }

    @Test
    void shouldChangeIngredientOrderInReceipt() {
        Burger burger = new Burger();

        Mockito.when(bunMock.getName()).thenReturn("Булка из водорослей");
        Mockito.when(bunMock.getPrice()).thenReturn(10f);

        Mockito.when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauceMock.getName()).thenReturn("Кисло-сладкий");
        Mockito.when(sauceMock.getPrice()).thenReturn(5f);

        Mockito.when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(fillingMock.getName()).thenReturn("Мясо жмыха");
        Mockito.when(fillingMock.getPrice()).thenReturn(20f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        burger.moveIngredient(0, 1);

        String receipt = burger.getReceipt();

        int sauceIndex = receipt.indexOf("Кисло-сладкий");
        int fillingIndex = receipt.indexOf("Мясо жмыха");

        assertTrue(fillingIndex < sauceIndex, "Ингредиенты не поменялись местами");
    }
}
