package com.ust.sdet.tests;

import com.ust.sdet.builder.OrderBuilder;
import com.ust.sdet.factory.OrderFactory;
import com.ust.sdet.model.Order;
import com.ust.sdet.repository.OrderRepository;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {

    private static final OrderRepository repo = new OrderRepository();
    private static final OrderFactory factory = new OrderFactory(repo);

    @BeforeAll
    static void migrateDatabase() {

        Flyway flyway = Flyway.configure()
                .dataSource(
                        System.getProperty("db.url", "jdbc:postgresql://localhost:5432/testdb"),
                        System.getProperty("db.user", "postgres"),
                        System.getProperty("db.password", "postgres")
                )
                .load();

        flyway.migrate();
    }

    @BeforeEach
    void setup() {
        repo.reset();
    }

    @Test
    void createsOrder() {

        factory.persisted(OrderBuilder.anOrder()
                        .withQty(3)
                        .build()
        );

        assertEquals(1, repo.count());
    }

    @Test
    void countsOrders() {

        factory.persisted(OrderBuilder.anOrder()
                        .build()
        );

        factory.persisted(OrderBuilder.anOrder()
                        .build()
        );

        assertEquals(2, repo.count());
    }

    @Test
    void findsOrderBySku() {

        factory.persisted(
                OrderBuilder.anOrder()
                        .withSku("SKU-100")
                        .build()
        );

        Order order = repo.findBySku("SKU-100");

        assertEquals("SKU-100", order.sku());
        assertEquals(1, order.qty());
    }

    @Test
    void countsOnlyShippedOrders() {

        factory.persisted(
                OrderBuilder.anOrder()
                        .withShipped(true)
                        .build()
        );

        factory.persisted(
                OrderBuilder.anOrder()
                        .withSku("SKU-2")
                        .build()
        );

        assertEquals(2, repo.count());
        assertEquals(1, repo.countShipped());
    }
}