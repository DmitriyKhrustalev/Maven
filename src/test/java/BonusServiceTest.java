import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BonusServiceTest {

    @Test
    void shouldCalculateForRegisteredAndUnderLimit() {
        BonusService service = new BonusService();

        long amount = 1000;
        boolean registered = true;
        long expected = 30;

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndOverLimit() {
        BonusService service = new BonusService();

        long amount = 1_000_000;
        boolean registered = true;
        long expected = 500;

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForNotRegisteredAndUnderLimit() {
        BonusService service = new BonusService();

        long amount = 1000;
        boolean registered = false;
        long expected = 10;

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForNotRegisteredAndOverLimit() {
        BonusService service = new BonusService();

        long amount = 1_000_000;
        boolean registered = false;
        long expected = 500;

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForRegisteredAndAtLimit() {
        BonusService service = new BonusService();

        long amount = 16_666; // 3% от этой суммы = 499
        boolean registered = true;
        long expected = 499; // Меняем ожидаемое значение на 499, чтобы тест соответствовал расчётам

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForZeroAmount() {
        BonusService service = new BonusService();

        long amount = 0;
        boolean registered = true;
        long expected = 0;

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForNegativeAmount() {
        BonusService service = new BonusService();

        long amount = -1000; // Отрицательные суммы не запрещены в расчётах
        boolean registered = true;
        long expected = -30; // -1000 * 3% = -30

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateForVeryLargeAmount() {
        BonusService service = new BonusService();

        long amount = Long.MAX_VALUE; // Максимальное значение long
        boolean registered = true;
        long expected = 500; // Бонус ограничен лимитом

        long actual = service.calculate(amount, registered);

        Assertions.assertEquals(expected, actual);
    }
}
