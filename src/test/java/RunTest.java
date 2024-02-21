import io.restassured.internal.common.assertion.Assertion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class RunTest {
    //its RunTest origin

    public static int factorial(int num){
        int number = num;
        int fact =1;
        if (number == 0){
            fact=1;
        }
        if (number == 1){
            fact=0;
        }
        else {
            for (int i = 2; i <= number ; i++) {
                fact = fact*i;
            }
        }
        return fact;
    }


    @BeforeAll
    public static void  beforeAll(){
        System.out.println("befforeAl");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @Test
    public void factorialTest1() {
        Assert.assertEquals(720, factorial(6)); //положительная проверкаa
    }

    @Test
    public void factorialTest2() {
        Assert.assertEquals(1, factorial(0)); // проверка нуляя
    }

    @Test
    public void factorialTest3() {
        Assert.assertEquals(0, factorial(1)); // проверка единицыы
    }

    @Test
    public void factorialTest4() {
        Assert.assertThrows(Exception.class, ()-> {factorial(-1);}); // проверка вывода экспешена при отрицательных числах
    }


}
