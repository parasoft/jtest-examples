package examples.flowanalysis;

/**
 * Class that demonstrates application of "Division by zero" rule.
 */
public class DivisionByZero {

    public static final int DISCOUNT = 0;

    public static final int PERSONAL_DISCOUNT = 0;

    public static final int SPECIAL_OFFER = 0;

    public static float calculateDiscountedSum(int code, float initialSum, Consumer person) {
        float discountedSum = calculateCommonDiscountedSum(initialSum);
        if (code == PERSONAL_DISCOUNT) {
        } else if (code == DISCOUNT) {
        } else if (code == SPECIAL_OFFER) {
            discountedSum *= getSpecialOfferDiscountCoefficient();
        }
        if (code == PERSONAL_DISCOUNT) {
            float progressionCoef = person.getOverallSum() / discountedSum; // VIOLATION
            discountedSum *= person.getPersonalDiscountCoefficient();
            float nextPersounalDiscount = // NaN
                    person.getPersonalDiscountCoefficient() + progressionCoef;
            person.setPersonalDiscountCoefficient(nextPersounalDiscount);
        }
        person.setOverallSum(discountedSum + person.getOverallSum());
        return discountedSum;
    }

    private static float calculateCommonDiscountedSum(float initialSum) {
        float discountedSum = 0.0f;
        if (initialSum > 5.0f) {
            discountedSum = initialSum * 0.95f;
        }
        if (initialSum < 5.0f) {
            discountedSum = initialSum * 0.98f;
        }
        return discountedSum;
    }

    private static float getSpecialOfferDiscountCoefficient() {
        return 0.75f;
    }

    class Consumer {

        private float _overallSum = 0.0f;

        private float _personalDiscountCoefficient = 0.0f;

        public void setOverallSum(float f) {
            _overallSum = f;
        }

        public float getOverallSum() {
            return _overallSum;
        }

        public void setPersonalDiscountCoefficient(float f) {
            _personalDiscountCoefficient = f;
        }

        public float getPersonalDiscountCoefficient() {
            return _personalDiscountCoefficient;
        }
    }
}
