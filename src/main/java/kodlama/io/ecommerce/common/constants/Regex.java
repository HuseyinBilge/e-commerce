package kodlama.io.ecommerce.common.constants;

public class Regex {
    public final static String Password ="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-={}\\[\\]\\|:;\"'<>,.?\\/])(?!.*\s).{8,20}$";
    public final static String EMail ="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public final static String CreditCardNumber ="\\b(?:\\d[ -]*?){13,16}\\b";
    public final static String CreditCardCVV = "\\b\\d{3,3}\\b";

}
