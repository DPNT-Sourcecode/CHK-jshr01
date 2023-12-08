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
        assertThat(chk.checkout("ABCDE"), equalTo(155));
    }

    @Test
    public void calculate_cost_with_free_items() {
        assertThat(chk.checkout("ABCDEE"), equalTo(165));
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

    @Test
    public void calculate_cost_with_free_items_2() {
        assertThat(chk.checkout("ABBCDEE"), equalTo(195));
    }

    @Test
    public void calculate_cost_with_free_items_3() {
        assertThat(chk.checkout("ABBCDEEB"), equalTo(210));
    }

    @Test
    public void calculate_cost_with_E() {
        assertThat(chk.checkout("EEB"), equalTo(80));
    }

    @Test
    public void calculate_cost_with_one_E() {
        assertThat(chk.checkout("BE"), equalTo(70));
    }

    @Test
    public void calculate_cost_with_E_and_B() {
        assertThat(chk.checkout("EEEEBB"), equalTo(160));
    }

    @Test
    public void calculate_cost_with_E_and_B_2() {
        assertThat(chk.checkout("BEBEEE"), equalTo(160));
    }

    @Test
    public void calculate_cost_with_all_chars() {
        assertThat(chk.checkout("ABCDEABCDE"), equalTo(280));
    }

    @Test
    public void calculate_cost_with_F() {
        assertThat(chk.checkout("AFFF"), equalTo(70));
    }

    @Test
    public void calculate_cost_with_F_and_E() {
        assertThat(chk.checkout("AFFFEEB"), equalTo(150));
    }

    @Test
    public void calculate_cost_with_one_F() {
        assertThat(chk.checkout("AF"), equalTo(60));
    }

    @Test
    public void calculate_cost_with_20_products() {
        assertThat(chk.checkout("ABCDEFGHIJKLMNOPQRST"), equalTo(670));
    }
}
