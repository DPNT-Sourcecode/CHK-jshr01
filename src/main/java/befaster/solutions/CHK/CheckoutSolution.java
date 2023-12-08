package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {
    private final Map<String, Integer> pricesMap = new HashMap<>();

    private final Map<String, List<Integer>> discountsMap = new HashMap<>();

    public Integer checkout(String skus) {
        // - For any illegal input return -1
        if(skus == null){
            return -1;
        }else if(skus.isEmpty()){
            return 0;
        }

        populatePricesMap();
        populateDiscountsMap();

        Map<String, Integer> shoppingCartMap = parsedSkus(skus);

        if(shoppingCartMap == null){
            return -1;
        }

        Integer totalCost = 0;

        for (Map.Entry<String, Integer> entry : shoppingCartMap.entrySet()) {
            String item = entry.getKey();
            Integer quantity = entry.getValue();

            Integer price = pricesMap.get(item) == null ? 0 : pricesMap.get(item);

            if(discountsMap.containsKey(item)){
                List<Integer> discount = discountsMap.get(item);
                Integer quantityDiscount = discount.get(0);
                Integer priceDiscount = discount.get(1);

                totalCost += (quantity / quantityDiscount) * priceDiscount;
                totalCost += (quantity % quantityDiscount) * price;
            }else{
                totalCost += (price * quantity);
            }
        }


        return totalCost;
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

    private void populateDiscountsMap(){
        //List with following structure: quantity, price. Note: This should all be done with external classes,
        //but lets do that in the same file for simplicity and prevent errors on test platform

        List<Integer> firstDiscount = new ArrayList<>();
        //quantity
        firstDiscount.add(3);
        //price
        firstDiscount.add(130);
        discountsMap.put("A", firstDiscount);

        List<Integer> secondDiscount = new ArrayList<>();
        //quantity
        secondDiscount.add(2);
        //price
        secondDiscount.add(45);
        discountsMap.put("B", secondDiscount);
    }
}
