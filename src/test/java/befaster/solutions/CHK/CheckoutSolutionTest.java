package befaster.solutions.CHK;

import befaster.solutions.SUM.SumSolution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutSolutionTest {
    private CheckoutSolution chk;

    @BeforeEach
    public void setUp() {
        chk = new CheckoutSolution();
    }

    @Test
    public void calculate_cost() {
        assertThat(chk.checkout("ABCD"), equalTo(115));
    }

    @Test
    public void calculate_cost_with_discount() {
        assertThat(chk.checkout("ABCDAA"), equalTo(195));
    }

    @Test
    public void calculate_cost_invalid() {
        assertThat(chk.checkout("ABCXD"), equalTo(-1));
    }

    @Test
    public void empty_sku_list() {
        assertThat(chk.checkout(""), equalTo(0));
    }
}

