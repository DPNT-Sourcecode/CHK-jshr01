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

        populatePricesMap();

        Map<String, Integer> shoppingCartMap = parsedSkus(skus);

        if(shoppingCartMap == null){
            return -1;
        }

        for (Map.Entry<String, Integer> entry : shoppingCartMap.entrySet()) {
            String item = entry.getKey();
            Integer quantity = entry.getValue();
        }


        return -1;
    }

    /**
     * Parse sku string
     * @param skus, string with skus to parse
     * @return shopping cart map if string is totally parsed, and null if there's any invalid sku code in it
     */
    private Map<String, Integer> parsedSkus(String skus){
        //item / quantity
        Map<String, Integer> shoppingCartMap = new HashMap<>();

        for(int i = 0; i < skus.length(); i++){
            if(pricesMap.get(skus.charAt(i)+"") != null){
                //for more readability
                String item = skus.charAt(i)+"";
                //increase quantity
                shoppingCartMap.put(item, shoppingCartMap.getOrDefault(item, 0) + 1);
            }else{
                return null;
            }
        }

        return shoppingCartMap;
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

