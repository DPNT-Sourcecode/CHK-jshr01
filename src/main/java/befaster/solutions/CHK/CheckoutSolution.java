package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    private final Map<String, Integer> pricesMap = new HashMap<>();

    public Integer checkout(String skus) {
        if(skus == null || skus.isEmpty()){
            return -1;
        }

        //item / quantity
        Map<String, Integer> shoppingCartMap = new HashMap<>();

        populatePricesMap();

        if(!parsedSkus(skus, shoppingCartMap)){
            return -1;
        }

        return -1;
    }

    /**
     * Parse sku string
     * @param skus, string with skus to parse
     * @param shoppingCartMap, map with items on cart
     * @return true is string is totally parsed, and false if there's any invalid sku code in it
     */
    private boolean parsedSkus(String skus, Map<String, Integer> shoppingCartMap){
        for(int i = 0; i < skus.length(); i++){
            if(pricesMap.get(skus.charAt(i)+"") != null){
                pricesMap.get(skus.charAt(i)+"");
            }else{
                return false;
            }
        }

        return true;
    }


    /**
     * initialize with item prices
     */
    private void populatePricesMap(){
        pricesMap.put("A", 50);
        pricesMap.put("B", 30);
        pricesMap.put("C", 20);
        pricesMap.put("D", 15);
    }
}



