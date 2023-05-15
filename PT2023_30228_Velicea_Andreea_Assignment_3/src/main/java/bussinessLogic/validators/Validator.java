package bussinessLogic.validators;
/**
 * Interfata pentru clasele care vor implementa validatorii
 *
 * @author Velicea Andreea - Ioana
 */
public interface Validator<T>{
    /**
     * Metoda care trebuie implementata
     * @param t obiect client/ product/ order
     */
    public void validate(T t);
}
