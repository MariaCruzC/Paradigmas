package Model;

public interface LogicFunction extends CallableLogic {

    public String[] getNombreValores();

    public boolean[] getTablaVerdad();

    public String getTruthTableRepresentation();

    //Para que se realice la expresion canónica disyuntiva (V)
    public String expressAsSumOfMinterms();

    //Para que se realice la expresion canónica conjuntiva (F)
    public String expressAsProductOfMaxterms();
}

