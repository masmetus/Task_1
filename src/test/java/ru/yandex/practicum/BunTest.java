package ru.yandex.practicum;

import org.junit.jupiter.api.Test;
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
}
