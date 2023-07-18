package some.dima.calc;

class Number {
    private int arabicFormat;

    private boolean isArabicFormat;

    private String romanFormat;

    public Number(String input) {
        try {
            arabicFormat = Integer.parseInt(input);

            if (arabicFormat < 1 || arabicFormat > 10)
                throw new NumberException("число " + arabicFormat + " не входит в отрезок [1, 10]");

            romanFormat = RomanNumeral.arabicToRoman(arabicFormat);
            isArabicFormat = true;
        } catch (NumberFormatException e) {
            arabicFormat = RomanNumeral.romanToArabic(input);

            if (arabicFormat < 1 || arabicFormat > 10)
                throw new NumberException("число " + input + " не входит в отрезок [I, X]");

            romanFormat = input;
            isArabicFormat = false;
        }
    }

    public Number(int arabicFormat, boolean isArabicFormat) {
        if (!isArabicFormat) {
            if (arabicFormat < 0)
                throw new NumberException("в римской системе нет отрицательных чисел");
            else if (arabicFormat == 0)
                throw new NumberException("в римской системе нет нуля");

            romanFormat = RomanNumeral.arabicToRoman(arabicFormat);
        }
        this.arabicFormat = arabicFormat;
        this.isArabicFormat = isArabicFormat;
    }

    public Number plus(Number that) {
        checkFormats(that);
        return new Number(this.arabicFormat + that.arabicFormat, that.isArabicFormat);
    }

    public Number minus(Number that) {
        checkFormats(that);
        return new Number(this.arabicFormat - that.arabicFormat, that.isArabicFormat);
    }

    public Number divide(Number that) {
        checkFormats(that);
        return new Number(this.arabicFormat / that.arabicFormat, that.isArabicFormat);
    }

    public Number multiply(Number that) {
        checkFormats(that);
        return new Number(this.arabicFormat * that.arabicFormat, that.isArabicFormat);
    }

    private void checkFormats(Number that) {
        if (this.isArabicFormat != that.isArabicFormat)
            throw new NumberException("используются одновременно разные системы счисления");
    }

    @Override
    public String toString() {
        if (isArabicFormat)
            return String.valueOf(arabicFormat);
        else
            return romanFormat;
    }

}
