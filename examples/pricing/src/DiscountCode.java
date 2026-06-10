import java.math.BigDecimal;

/**
 * Represents a percentage discount code.
 *
 * <p>In simple terms, this is intentionally mutable so the pricing example can demonstrate a
 * realistic accidental state-mutation bug.</p>
 */
public final class DiscountCode {
    private final String code;
    private BigDecimal percentageOff;

    public DiscountCode(String code, BigDecimal percentageOff) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("code must not be blank");
        }
        if (percentageOff == null) {
            throw new IllegalArgumentException("percentageOff must not be null");
        }
        if (percentageOff.compareTo(BigDecimal.ZERO) < 0 || percentageOff.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("percentageOff must be between 0 and 100");
        }
        this.code = code;
        this.percentageOff = percentageOff;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getPercentageOff() {
        return percentageOff;
    }

    public void setPercentageOff(BigDecimal percentageOff) {
        if (percentageOff == null) {
            throw new IllegalArgumentException("percentageOff must not be null");
        }
        this.percentageOff = percentageOff;
    }
}
