package ru.netology.javaqa.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void shouldRemoveExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Product 1", 100);
        Product product2 = new Product(2, "Product 2", 200);
        repository.add(product1);
        repository.add(product2);

        repository.removeById(1);
        Product[] products = repository.findAll();

        assertEquals(1, products.length);
        assertEquals(2, products[0].getId());
    }

    @Test
    void shouldThrowWhenRemovingNonExistingProduct() {
        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "Product 1", 100);
        repository.add(product);

        assertThrows(NotFoundException.class, () -> repository.removeById(2),
                "Ожидалось исключение NotFoundException при попытке удалить несуществующий товар");

        // Проверка сообщения исключения
        NotFoundException exception = assertThrows(NotFoundException.class, () -> repository.removeById(2));
        assertTrue(exception.getMessage().contains("Element with id: 2 not found"));
    }
}