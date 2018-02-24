import java.util.stream.IntStream;

class IsbnVerifier {

    boolean isValid(String isbn) {
        final char[] characters = isbn.replaceAll("-", "").toCharArray();

        if (characters.length != 10) {
            return false;
        } else {
            try {
                final int product = IntStream.range(0, 10)
                        .map(idx -> (10 - idx) * characterValue(characters[idx], idx == 9))
                        .sum();
                return product % 11 == 0;
            } catch (IllegalArgumentException e) {
                return false;
            }
        }
    }

    private static int characterValue(char c, boolean checkDigit) {
        if (Character.isDigit(c)) {
            return Character.getNumericValue(c);
        } else if (c == 'X' && checkDigit) {
            return 10;
        } else {
            throw new IllegalArgumentException("Wrong character for ISBN");
        }
    }
}
