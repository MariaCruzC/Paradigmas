package Model;

import java.util.Arrays;

public class LogicChain implements LogicFunction {

    private LogicNode first;
    private boolean[] values;
    private char[] names;
    private String query;

    public LogicChain(String description) {
        Parser p;
        // build the logic
        try {
            p = new Parser(description);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException("Letras invalidas!");
        }
        
        // get references
        values = p.getValues();
        names = p.getNames();
        first = p.getPrimero();
        query = p.getCleanedQuery();
    }

    public String[] getNombreValores() {
        String[] nms = new String[names.length];
        for (int i = 0; i < nms.length; i++)
            nms[i] = String.valueOf(names[i]);

        return nms;
    }

    public boolean call() {
        return first.call();
    }

    public boolean call(boolean... vs) {
        if (vs.length != values.length)
            throw new RuntimeException("not the right number of values");

        for (int i = 0; i < values.length; i++)
            values[i] = vs[i];

        return first.call();
    }

    public String getTruthTableRepresentation() {
        String res = "";

        resetValues();

        res += "----";
        for (int i = 0; i < names.length; i++)
            res += "--";
        res += "-------\n";

        res += "|  ";
        for (int i = 0; i < names.length; i++)
            res += names[i] + "  ";
        res += "| f |\n";

        res += "|-";
        for (int i = 0; i < (names.length*2)-1; i++)
            res += "--";
        res += "+---|";

        do {
            res += "\n| ";
            for (int i = 0; i < values.length; i++)
                res += (values[i] ? " V " : " F ");
            res += " | " + (call() ? "V" : "F") + " |";
        } while (incrementValues());

        res += "\n---";
        for (int i = 0; i < names.length; i++)
            res += "--";
        res += "-------";

        return res;
    }

    public boolean[] getTablaVerdad() {
        boolean[] table = new boolean[1 << values.length];

        int count = 0;
        do {
            table[count++] = call();
        } while (incrementValues());

        return table;
    }

    public String expressAsSumOfMinterms() {
        String s = "";
        resetValues();
        do {
            if (call()) {
                if (s != "")
                    s += "∨";
                s += "(";
                for (int i = 0; i < values.length; i++) {
                    if (i != 0)
                        s += "∧";
                    if (!values[i])
                        s += "¬";
                    s += String.valueOf(names[i]);
                }
                s += ")";
            }
        } while (incrementValues());

        return s;
    }

    public String expressAsProductOfMaxterms() {
        String s = "";

        resetValues();
        do {
            if (!call()) {
                if (s != "")
                    s += "∧";
                s += "(";
                for (int i = 0; i < values.length; i++) {
                    if (i != 0)
                        s += "∨";
                    if (values[i])
                        s += "¬";
                    s += String.valueOf(names[i]);
                }
                s += ")";
            }
        } while (incrementValues());

        return s;
    }

    private void resetValues() {
        for (int i = 0; i < values.length; i++)
            values[i] = false;
    }

    private boolean incrementValues() {
        if (values.length < 1)
            return false;

        if (values.length == 1) {
            if (values[0])
                return false;

            values[0] = !values[0];
            return true;
        }

        boolean reminder = values[values.length - 1];
        boolean temp;
        values[values.length - 1] = !values[values.length - 1];

        for (int k = values.length - 2; k >= 0 && reminder; k--) {
            temp = reminder && values[k];
            if (k == 0 && reminder && values[0])
                return false;
            if (reminder)
                values[k] = !values[k];
            reminder = temp;
        }
        return true;
    }
}
