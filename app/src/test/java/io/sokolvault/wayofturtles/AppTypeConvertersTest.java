package io.sokolvault.wayofturtles;

import org.junit.Assert;
import org.junit.Test;

import io.sokolvault.wayofturtles.domain.model.GoalCategory;
import io.sokolvault.wayofturtles.utils.AppTypeConverters;


public class AppTypeConvertersTest {
    private AppTypeConverters appTypeConverters = new AppTypeConverters();

    @Test
    public void testFromEnumToString() throws Exception {
        String result = appTypeConverters.fromEnumToString(null);
        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testFromStringToEnum() throws Exception {
        Enum<GoalCategory> result = appTypeConverters.fromStringToEnum("string");
        Assert.assertEquals(null, result);
    }
}
