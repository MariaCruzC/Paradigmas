package Model;

import java.util.Arrays;
import java.util.regex.*;

public class Parser {

    private int[] indexMapping;

    private LogicNode primero; // root node
    private LogicNode[] nodes; // store the variable
    private boolean[] values; // values on which the variable depends

    private char[] nombreVariable; // Almacena el nombre de cada varaible (P,Q,R,S,T,U,V,W)

    private String expresion;

    private static char[] letras = "PQRSTUVW".toCharArray();
    private static Pattern pattern = Pattern.compile("^[\t \\(\\)A-Z\\*\\+\\-']+$");

    Parser(String s) {
        expresion = Parser.evaluaExpresion(s);

        char[] sequence = expresion.toCharArray();

        int count = 0;
        for (int i = 0; i < sequence.length; i++)
            if (sequence[i] == '(')
                count++;
            else if (sequence[i] == ')')
                count--;
        if (count != 0)
            throw new RuntimeException("Desbalanceados ()");

        indexMapping = new int[letras.length];
        nombreVariable = new char[letras.length];

        for (int i = 0; i < letras.length; i++)
            indexMapping[i] = -1;

        int numberOfVariable = 0;

        for (int j = 0; j < letras.length; j++) {
            for (int i = 0; i < sequence.length; i++) {
                if (sequence[i] == letras[j] && indexMapping[(int) letras[j] - (int) letras[0]] == -1) {
                    indexMapping[(int) letras[j] - (int) letras[0]] = numberOfVariable++;
                    nombreVariable[numberOfVariable - 1] = letras[j];
                }
            }
        }

        // trim down end of array
        nombreVariable = Arrays.copyOf(nombreVariable, numberOfVariable);

        values = new boolean[numberOfVariable];
        nodes = new LogicNode[numberOfVariable];

        for (int i = 0; i < numberOfVariable; i++)
            nodes[i] = LogicNode.variable(values, i, nombreVariable[i]);

        primero = parse(sequence);
    }

    // getters

    LogicNode getPrimero() {
        return primero;
    }

    char[] getNames() {
        return nombreVariable;
    }

    boolean[] getValues() {
        return values;
    }

    String getCleanedQuery() {
        return expresion;
    }

    //Recibe un arreglo de chars que contiene la expresion infija a evaluar
    private LogicNode parse(char[] expresion) { 
        if (expresion.length < 1)
            throw new RuntimeException("Expresion vacia!");

        int count = 0;
        // search for OR in max scope
        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i] == '(')
                count++;
            else if (expresion[i] == ')')
                count--;
            else if (count == 0 && expresion[i] == '*')
                return LogicNode.or(parse(Arrays.copyOfRange(expresion, 0, i - 1 + 1)),
                        parse(Arrays.copyOfRange(expresion, i + 1, expresion.length)));

            if (count < 0) //Excepcion de parentesis desbalanceados, si count es menor que 0
                throw new RuntimeException("Parentesis desbalanceados");
        }

        if (count != 0) //Excepcion de parentesis desbalanceados, si count es diferente que 0
            throw new RuntimeException("Parentesis desbalanceados");

        // search for AND in max scope
        for (int i = 0; i < expresion.length; i++) {
            if (expresion[i] == '(')
                count++;
            else if (expresion[i] == ')')
                count--;
            else if (count == 0 && expresion[i] == '+')
                return LogicNode.and(parse(Arrays.copyOfRange(expresion, 0, i - 1 + 1)),
                        parse(Arrays.copyOfRange(expresion, i + 1, expresion.length)));
        }

        //revisa si llega un NOT al revisar la expresion
        if (expresion[0] == '-')
            return LogicNode.not(parse(Arrays.copyOfRange(expresion, 1, expresion.length)));

        // Comprueba de nuevo los PARENTESIS (que abra y que cierre)
        if ((expresion[0] == '(') != (expresion[expresion.length - 1] == ')'))
            throw new RuntimeException("not anticipated");
        if (expresion[0] == '(')
            return parse(Arrays.copyOfRange(expresion, 1, expresion.length - 1));

        // Si solo hay una sola variable
        if (expresion.length != 1)
            throw new RuntimeException("didn't expect that...");

        //VALIDA
        if (expresion[0] == '0')
            return LogicNode.falso();
        if (expresion[0] == '1')
            return LogicNode.verdadero();
        try {
            return nodes[indexMapping[(int) expresion[0] - (int) letras[0]]];
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new RuntimeException("Letra no válida: " + expresion[0]);
        }
    }

    private static String evaluaExpresion(String s) {
        s = s.toUpperCase();
        if (!pattern.matcher(s).matches())
            throw new RuntimeException("Letras invalidas!"); //HACER JOptionPane

        // Se encarga de eliminar espacios en blanco (da muchos ERRORES!)
        s = Pattern.compile("[ \t]").matcher(s).replaceAll("");

        // transform ' -> !
        s = Pattern.compile("([A-Z]){1}'").matcher(s).replaceAll("!$1"); // signle value
        s = Pattern.compile("(\\([\\(\\)A-Z\\*\\|!']+\\))'").matcher(s).replaceAll("!$1"); // expression

        // convierte el - en negacion
        s = Pattern.compile("NOT").matcher(s).replaceAll("-");

        // convierte el * en OR
        s = Pattern.compile("OR").matcher(s).replaceAll("*");

        // convierte el + en AND
        s = Pattern.compile("AND").matcher(s).replaceAll("+");

        // convierte -- en positivo (doble negacion da positivo)
        s = Pattern.compile("--").matcher(s).replaceAll("");

        // METER JOptionPane!!!!!
        s = Pattern.compile("\\[").matcher(s).replaceAll("(");
        s = Pattern.compile("\\]").matcher(s).replaceAll(")");


        // REVISAR SI LA EXPRESION ESTA MALA
        // operadores seguidos 
        if (Pattern.compile("^.*[\\|]{2,}.*$").matcher(s).matches())
            throw new RuntimeException("Dos operadores seguidos");

        // remove some un-necessary brackets

        // global brackets
        Matcher m = Pattern.compile("^\\(([^()]*)\\)$").matcher(s);
        while (m.matches()) {
            s = m.replaceAll("$1");
            m = Pattern.compile("^\\((.*)\\)$").matcher(s);
        }

        // parentesis repetitivos (validacion) --- LISTO
        s = Pattern.compile("[\\(]+([^\\(\\)]*)[\\)]+").matcher(s).replaceAll("($1)");

        // valida parentesis vacios y los elimina
        s = Pattern.compile("\\(\\)").matcher(s).replaceAll("");

        // consecutive variables
        if (Pattern.compile("^.*[A-Z\\)]{1}[-]?[A-Z0-1\\(]{1}.*$").matcher(s).matches()) {
            // AB -> twice bc othewhise ABCD -> A&BC&D
            s = Pattern.compile("([A-Z]{1})([-]?[A-Z]{1})").matcher(s).replaceAll("$1&$2");
            s = Pattern.compile("([A-Z]{1})([-]?[A-Z]{1})").matcher(s).replaceAll("$1&$2");
            // A(
            s = Pattern.compile("([A-Z]{1})([-]?[\\(]{1})").matcher(s).replaceAll("$1&$2");
            // )A
            s = Pattern.compile("([\\)]{1})([-]?[A-Z]{1})").matcher(s).replaceAll("$1&$2");
        }

        return s;
    }
}