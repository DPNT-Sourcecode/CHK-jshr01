package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.*;

public class CheckoutSolution {
    private final Map<String, Integer> pricesMap = new HashMap<>();

    private final Map<String, Map<Integer, Integer>> discountsMap = new HashMap<>();

    private final Map<String, Map<Integer, String>> freeItemsMap = new HashMap<>();

    public Integer checkout(String skus) {
        // - For any illegal input return -1
        if(skus == null){
            return -1;
        }else if(skus.isEmpty()){
            return 0;
        }

        //populate data
        populatePricesMap();
        populateDiscountsMap();
        populateFreeItemsMap();

        Map<String, Integer> shoppingCartMap = parsedSkus(skus);

        if(shoppingCartMap == null){
            return -1;
        }


        shoppingCartMap = calculateFreeItems(shoppingCartMap);


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
     * @return totalCost
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
     * calculate free items
     * @param shoppingCartMap
     * @return Map<String, Integer> with removed free items
     */
    private Map<String, Integer> calculateFreeItems(Map<String, Integer> shoppingCartMap){
        Map<String, Integer> shoppingCartMapTemp = shoppingCartMap;

        for (Map.Entry<String, Map<Integer, String>> entry: freeItemsMap.entrySet()) {
            String freeItem = entry.getKey();
            Map<Integer, String> freeItemsDetailMap = entry.getValue();

            for(Map.Entry<Integer, String> subEntry : freeItemsDetailMap.entrySet()){
                if(shoppingCartMapTemp.get(freeItem) >= subEntry.getKey()){
                    if(shoppingCartMap.get(subEntry.getValue()) != null){
                        shoppingCartMap.put(subEntry.getValue(), shoppingCartMap.getOrDefault(subEntry.getValue(), 0) - subEntry.getKey());
                    }
                    shoppingCartMapTemp.put(freeItem, shoppingCartMapTemp.get(freeItem) - subEntry.getKey());
                }
            }
        }

        return shoppingCartMap;
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
        Map<Integer, Integer> firstDiscount = new HashMap<>();
        firstDiscount.put(3, 130);
        firstDiscount.put(5, 200);
        discountsMap.put("A", firstDiscount);

        Map<Integer, Integer> secondDiscount = new HashMap<>();
        secondDiscount.put(2, 45);
        discountsMap.put("B", secondDiscount);
    }

    /**
     * initialize with free items
     */
    private void populateFreeItemsMap(){
        //For 2 E, get one B free
        Map<Integer, String> firstDiscount = new HashMap<>();
        firstDiscount.put(2, "B");
        freeItemsMap.put("E", firstDiscount);
    }
}



