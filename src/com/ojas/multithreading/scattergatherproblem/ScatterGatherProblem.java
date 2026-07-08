package com.ojas.multithreading.scattergatherproblem;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class ScatterGatherProblem {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ProductService productService = new ProductService();
//        Set<Integer> productPricesV1 = productService.fetchProductPricesV1();
        Set<Integer> productPricesV2 = productService.fetchProductPricesV2();

//        System.out.println("Product prices V1: " + productPricesV1);
        System.out.println("Product prices V2: " + productPricesV2);
    }

}
