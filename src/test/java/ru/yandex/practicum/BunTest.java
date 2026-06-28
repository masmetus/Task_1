package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private Bun bun;


    @Test
    public void shouldReturnBunName() {
        bun = new Bun("Булка вкусная", 5.54f);

        String expected = "Булка вкусная";
        String actual = bun.getName();

        assertEquals(expected, actual, "Название не совпадает");
    }

    @Test
    public void shouldReturnBunPrice() {
        bun = new Bun("Булка вкусная", -5.54f);

        Float expected = -5.54f;
        Float actual = bun.getPrice();

        assertEquals(expected, actual, "Цена не совпадает");
    }

    @ParameterizedTest(name = "Тест {index}: название = ''{0}'', цена = {1}")
    @CsvSource({
            "Булка вкусная, 5.54",
            "Булка с минусом, -5.54",
            "Бесплатная, 0.0",
            "'', 100.0"
    })
    public void shouldReturnBunNameAndPrice(String expectedName, float expectedPrice) {
        Bun bun = new Bun(expectedName, expectedPrice);

        assertEquals(expectedName, bun.getName(), "Название не совпадает");
        assertEquals(expectedPrice, bun.getPrice(), "Цена не совпадает");
    }
}
