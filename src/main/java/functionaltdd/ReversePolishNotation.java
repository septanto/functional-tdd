package functionaltdd;

public class ReversePolishNotation {
    int compute(String expression) {
        String[] elems = expression.trim().split(" ");
        if (elems.length != 1 && elems.length != 3)
            throw new NotReversePolishNotationError();
        if (elems.length == 1) {
            return parseInt(elems[0]);
        } else {
            if ("+".equals(elems[2]))
                return parseInt(elems[0]) + parseInt(elems[1]);
            else if ("-".equals(elems[2]))
                return parseInt(elems[0]) - parseInt(elems[1]);
            else if ("*".equals(elems[2]))
                return parseInt(elems[0]) * parseInt(elems[1]);
            else if ("/".equals(elems[2]))
                return parseInt(elems[0]) / parseInt(elems[1]);
            else
                throw new NotReversePolishNotationError();
        }
    }

    private int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NotReversePolishNotationError();
        }
    }
}
