package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.*;

public class CheckoutSolution {
    private final Map<String, Integer> pricesMap = new HashMap<>();

    private final Map<String, Map<Integer, Integer>> discountsMap = new HashMap<>();

    private final Map<String, Map<Integer, Integer>> freeItemsMap = new HashMap<>();

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
                Map<Integer, Integer> itemDiscountsMap = discountsMap.get(item);
                totalCost += calculateDiscounts(quantity, price, itemDiscountsMap);
            }else{
                totalCost += (price * quantity);
            }
        }


        return totalCost;
    }

    /**
     * calculate quantity discounts
     * @param quantity
     * @param price
     * @param itemDiscountsMap
     * @return
     */
    private Integer calculateDiscounts(Integer quantity, Integer price, Map<Integer, Integer> itemDiscountsMap){
        Integer totalCost = 0;
        List<Integer> sortedQuantities = new ArrayList(itemDiscountsMap.keySet());
        //more quantities first
        Collections.sort(sortedQuantities, Collections.reverseOrder());

        for (Integer discountQtt : sortedQuantities) {
            Integer priceDiscount = itemDiscountsMap.get(discountQtt);
            Integer discounts = quantity / discountQtt;
            totalCost += discounts * priceDiscount;
            quantity %= discountQtt;
        }

        totalCost += quantity * price;

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
        pricesMap.put("E", 40);
    }

    /**
     * initialize with discounts
     */
    private void populateDiscountsMap(){
        //List with following structure: quantity, price. Note: This should all be done with external classes,
        //but lets do that in the same file for simplicity and prevent errors on test platform

        Map<Integer, Integer> firstDiscount = new HashMap<>();
        firstDiscount.put(3, 130);
        firstDiscount.put(5, 200);
        discountsMap.put("A", firstDiscount);

        Map<Integer, Integer> secondDiscount = new HashMap<>();
        secondDiscount.put(2, 45);
        discountsMap.put("B", secondDiscount);
    }
}
