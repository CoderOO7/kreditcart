package com.kreditcart.productCatalogue.TestExamples;

import org.junit.jupiter.api.Test;
import org.w3c.dom.css.Counter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConcurrencyTest {
    private int count = 1;

    void increamentCount() {
        this.count = this.count + 1;
    }

    @Test
    public void testConcurrency() {
        Thread t1 = new Thread(() -> increamentCount());
        Thread t2 = new Thread(() -> increamentCount());

        t1.start();
        t2.start();

        assertEquals(2, this.count);
    }
}
