package bussinessLogic.validators;

import model.Order;


/**
 * Clasa care implementeaza metoda din interfata Validator pentru validarea cantitatii la operatia de insert order
 *
 * @author Velicea Andreea - Ioana
 */
public class QuantityValidator implements Validator<Order>{
    private static  final int MIN_Q=0;
    private static final int MAX_Q=1000;

    public void validate(Order or)
    {
        if(or.getQuantity() < MIN_Q || or.getQuantity()> MAX_Q)
        {
            throw new IllegalArgumentException("The quantity is not respected!");
        }
    }
}
