package com.screening.sanctions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ScreeningControllerTest {

    private final ScreeningController sc = new ScreeningController();

    @Test
    public void getGreeting() {
        Assertions.assertEquals("Greetings!", sc.index());
    }

    @Test
    public void assertHit() {
        Assertions.assertEquals("Hit", sc.screenInput("Kristoph Doe"));
        Assertions.assertEquals("Hit", sc.screenInput("Iceland"));
        Assertions.assertEquals("Hit", sc.screenInput("Royal Arctic Line"));

        Assertions.assertEquals("Hit", sc.screenInput("Kristopher Toe"));
        Assertions.assertEquals("Hit", sc.screenInput("Kristophre Doe"));
        Assertions.assertEquals("Hit", sc.screenInput("Iveland"));
        Assertions.assertEquals("Hit", sc.screenInput("Icelandic"));
        Assertions.assertEquals("Hit", sc.screenInput("RoyalArcticLine"));
        Assertions.assertEquals("Hit", sc.screenInput("Royal Artic Lie"));

    }

    @Test
    public void assertNoHit() {
        Assertions.assertEquals("No Hit", sc.screenInput("Kris Doe"));
        Assertions.assertEquals("No Hit", sc.screenInput("Greenland"));
        Assertions.assertEquals("No Hit", sc.screenInput("Royal Caribbean Line"));
    }

}