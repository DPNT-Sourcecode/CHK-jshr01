package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    private final Map<String, Integer> pricesMap = new HashMap<>();


    public Integer checkout(String skus) {
        Map<String, Integer> shoppingCartMap = new HashMap<>();

        
    }


    private void populatePricesMap(){
        pricesMap.put("A", 50);
        pricesMap.put("B", 30);
        pricesMap.put("C", 20);
        pricesMap.put("D", 15);
    }
}


