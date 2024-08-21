package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Profiles {
    private String name;
    private int monthlyBudget;
    private List<Expenses> expensesList;

    public void addExpenses(Expenses expenses) {
        if (expenses.getAmount()<0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        expensesList.add(expenses);
    }

    public void getExpensesByCategory(Category category){
        /*for (Expenses expenses : expensesList) {
            if (expenses.getCategory().equals(category)) {
                System.out.println(expenses);
            }
        }*/
        expensesList.stream().filter((expenses)->expenses.getCategory().equals(category)).forEach(System.out::println);
    }

    public int getTotalSpentThisMonth(){
        int expensesTotal = 0;
        for (Expenses expenses : expensesList) {
            int month = expenses.getDate().getMonthValue();
            if (month == LocalDate.now().getMonthValue()){
                expensesTotal += expenses.getAmount();
            };
        }
        return expensesTotal;
    }

    public int getRemainingBudget(){
       int remaining = monthlyBudget - getTotalSpentThisMonth();
       if (remaining < 0) {
           throw new IllegalArgumentException("Expenses is too expensive for your budget");
       }
       return remaining;
    }

    public void getTopCategories(){
        int FoodAndCatering = 0;
        int Transport = 0;
        int Entertainment = 0;
        int PublicService = 0;
        int Others = 0;
        for (Expenses expenses : expensesList) {
            if (expenses.getCategory()==Category.FoodAndCatering){
                FoodAndCatering++;
            }
            if (expenses.getCategory()==Category.Transport){
                Transport++;
            }
            if (expenses.getCategory()==Category.Entertainment){
                Entertainment++;
            }
            if (expenses.getCategory()==Category.PublicService){
                PublicService++;
            }
            if (expenses.getCategory()==Category.Others){
                Others++;
            }
        }
        List<Integer> numberChoiceCategory = new ArrayList<>();
        numberChoiceCategory.add(FoodAndCatering);
        numberChoiceCategory.add(Transport);
        numberChoiceCategory.add(Entertainment);
        numberChoiceCategory.add(PublicService);
        numberChoiceCategory.add(Others);
        numberChoiceCategory.sort(Comparator.comparingInt(a -> (int) a).reversed());
        System.out.println("The first best category is " + numberChoiceCategory.get(0));
        System.out.println("The second best category is " + numberChoiceCategory.get(1));
        System.out.println("The third best category is " + numberChoiceCategory.get(2));
    }

    public void calculateAverageSpendingPerCategory(){
        int totalExpensesFoodAndCatering = 0;
        int totalExpensesTransport = 0;
        int totalExpensesEntertainment = 0;
        int totalExpensesPublicService = 0;
        int totalExpensesOthers = 0;
        for (Expenses expenses : expensesList) {
            if (expenses.getCategory()==Category.FoodAndCatering){
                totalExpensesFoodAndCatering+=expenses.getAmount();
            }
            if (expenses.getCategory()==Category.Transport){
                totalExpensesTransport+=expenses.getAmount();
            }
            if (expenses.getCategory()==Category.Entertainment){
                totalExpensesEntertainment+=expenses.getAmount();
            }
            if (expenses.getCategory()==Category.PublicService){
                totalExpensesPublicService+=expenses.getAmount();
            }
            if (expenses.getCategory()==Category.Others){
                totalExpensesOthers+=expenses.getAmount();
            }
        }
        System.out.println("Total expenses for FoodAndCatering is " + totalExpensesFoodAndCatering);
        System.out.println("Total expenses for Transport is " + totalExpensesTransport);
        System.out.println("Total expenses for Entertainment is " + totalExpensesEntertainment);
        System.out.println("Total expenses for PublicService is " + totalExpensesPublicService);
        System.out.println("Total expenses for Others is " + totalExpensesOthers);
    }
}
