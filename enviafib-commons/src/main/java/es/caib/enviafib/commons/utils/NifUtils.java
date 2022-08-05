package es.caib.enviafib.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author anadal (u80067)
 * 
 * TODO XYZ ZZZ Moure a una llibreria d'utilitats
 *
 */
public class NifUtils {

    public static final String PATTERN_NIF_PERSONA_FISICA = "[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]";

    public static final String PATTERN_NIE = "[XYZ][0-9]{7}[0-9A-Z]";

    public static final String PATTERN_NIF_PERSONA_JURIDICA = "[A-W][0-9]{7}[0-9A-J]";

    public static final String PATTERN_ANY = "^(" + PATTERN_NIF_PERSONA_FISICA + ")|("
            + PATTERN_NIE + ")|(" + PATTERN_NIF_PERSONA_JURIDICA + ")$";

    private static final Pattern PATTERN = Pattern.compile(PATTERN_ANY);
    // "^([0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]|[A-Z][0-9]{7}[0-9A-J]|[XYZ][0-9]{7}[0-9A-Z])(,([0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]|[A-Z][0-9]{7}[0-9A-J]|[XYZ][0-9]{7}[0-9A-Z]))*$");

    public enum TipusNif {
        PERSONA_FISICA, PERSONA_JURIDICA, NIE;
    };

    /**
     * Validate NIF: DNI, NIE and CIF
     * 
     * @param niflist
     * @return null if all OK. Otherwise, list of invalid nif
     */
    public static CheckNifResult validateNifsSeparatedByCommas(String niflist) {

        if (niflist == null || niflist.trim().length() == 0) {
            return new CheckNifResult(false, null, null);
        }
        
        niflist = cleanNif(niflist);
        

        String[] nifsSplit = niflist.split(",");

        List<NifInfo> nifsOK = new ArrayList<NifInfo>();
        List<NifInfo> nifsErrors = new ArrayList<NifInfo>();

        for (String nif : nifsSplit) {

            NifInfo ni = validateNif(nif);

            if (ni.isValid()) {
                nifsOK.add(ni);
            } else {
                // Error
                nifsErrors.add(ni);                
            }
        }

        if (nifsErrors.isEmpty()) {
            return new CheckNifResult(true, niflist, nifsOK);
        } else {
            return new CheckNifResult(false, niflist, nifsErrors);
        }

    }

    /**
     * Valida NIFs (DNI, NIE i CIF)
     * 
     * @param nif
     * @return null if error o el NIF normalitzat i netejat
     */
    public static NifInfo validateNif(String nif) {

        // Neteja ' ', -, . / \n \r uppercase
        String n = cleanNif(nif);

        n = n.toUpperCase();

        final Matcher matcher = PATTERN.matcher(n);
        NifInfo ni;
        if (matcher.find()) {

            if (matcher.groupCount() != 3) {
                final String error = "El nif " + n + " no té 3 groups com seria d'esperar ("
                        + matcher.groupCount() + ") segons el RegEx " + PATTERN_ANY;
                ni = new NifInfo(nif, n, error);
            } else {

                if (matcher.group(1) != null) {
                    // NIF persona Fisica
                    if (validaNifPersonaFisica(n)) {
                        ni = new NifInfo(nif, n, TipusNif.PERSONA_FISICA);
                    } else {
                        ni = new NifInfo(nif, n, TipusNif.PERSONA_FISICA,
                                "Aquest NIF no és vàlid");
                    }

                } else if (matcher.group(2) != null) {
                    // NIE
                    char c = n.charAt(0);

                    char valorLLetra;
                    if (c == 'X') {
                        valorLLetra = '0';
                    } else if (c == 'Y') {
                        valorLLetra = '1';
                    } else { // if (c == 'Z') {
                        valorLLetra = '2';
                    }

                    String nie = valorLLetra + n.substring(1);

                    if (validaNifPersonaFisica(nie)) {
                        ni = new NifInfo(nif, n, TipusNif.PERSONA_FISICA);
                    } else {
                        ni = new NifInfo(nif, n, TipusNif.PERSONA_FISICA,
                                "Aquest NIF no és vàlid");
                    }

                } else if (matcher.group(3) != null) {
                    // NIF persona juridica
                    if (Cif.validCIF(nif)) {
                        ni = new NifInfo(nif, n, TipusNif.PERSONA_JURIDICA);
                    } else {
                        ni = new NifInfo(nif, n, TipusNif.PERSONA_JURIDICA,
                                "Aquest NIF no és vàlid");
                    }

                } else {
                    
                    System.err.println("matcher.group(0) => " + matcher.group(0));
                    System.err.println("matcher.group(1) => " + matcher.group(1));
                    System.err.println("matcher.group(2) => " + matcher.group(2));
                    System.err.println("matcher.group(3) => " + matcher.group(3));

                    ni = new NifInfo(nif, n,
                            "Aquest NIF no s'ajusta a cap dels grups definits a RegEx "
                                    + PATTERN_ANY);
                }
            }

        } else {
            ni = new NifInfo(nif, n, "No existeix cap NIF (DNI, NIE o CIF) amb aquest format");
        }

        return ni;

    }

    public static String cleanNif(String nif) {

        if (nif == null) {
            return null;
        }

        String n = nif.toUpperCase().replace(" ", "").replace("-", "").replace(".", "")
                .replace("/", "").replace("\n", "").replace("\r", "");
        return n;
    }

    private static boolean validaNifPersonaFisica(String dni) {

        final String juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKE";

        int dniNumber;

        try {
            dniNumber = Integer.parseInt(dni.substring(0, dni.length() - 1));

        } catch (java.lang.NumberFormatException nfe) {
            return false;
        }

        int modulo = dniNumber % 23;

        char lletaCalculada = juegoCaracteres.charAt(modulo);

        char lletraActual = dni.charAt(dni.length() - 1);

        if (lletaCalculada != lletraActual) {
            return false;
        }
        return true;

    }

    /**
     * Clase que utilizamos para validar el CIF de una empresa.
     *
     */
    public static class Cif {

        // private static final String LETRAS_CIF = "ABCDEFGHJKLMNPQRSUVW"; // Caracteres
        // validos pra el CIF.

        /* Guarda los caracteres control por lo que puede comenzar un CIF. */
        private static final char[] LETRA_CONTROL = new char[] { 'J', 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I' };

        private static final String PRIMER_CARACTER_LETRA = "NPQRSW";

        /**
         * Devuelve el número sobre el que se va a calcular el dígito control
         * 
         * @return Devuelve el un número de 7 cifras en formato texto.
         */
        private static String getDigitsCif(String cif) {
            return cif.substring(1, cif.length() - 1);
        }

        /**
         * Suma los números de las posiciones pares del número de 7 dígitos.
         * 
         * @return Devuelve el resultado en formato de número entero.
         */
        private static int getSumaPares(String cif) {

            int sumaPares = 0; // Suma de números de las posiciones pares.

            String dc = getDigitsCif(cif);

            // Creamos un bucle para recorrer las posiciones pares.
            for (int pos = 1; pos < dc.length(); pos += 2) {
                /*
                 * A la variable sumaPares le sumamos lo que tenga la variable en ese momento,
                 * más el número convertido en dato numérico
                 */
                sumaPares = sumaPares + Integer.parseInt(dc.substring(pos, pos + 1));
            }
            return sumaPares;
        }

        /**
         * Multiplica por dos los números de las posiciones impares y devuelve la suma, si el
         * resultado es de dos cifras, suma ambas cifras.
         * 
         * @return Devuelve el resultado de la suma en formato número entero.
         */
        protected static int getSumaImpares(String cif) {

            int sumaImpares = 0; // Suma de números de las posiciones impares.

            String dc = getDigitsCif(cif);

            Integer longitudCifraImpar;
            for (int pos = 0; pos < dc.length(); pos += 2) {
                /*
                 * A la variable sumaImpares le sumamos lo que tenga la variable en ese
                 * momento, más el número convertido en dato numérico multiplicado por dos
                 */
                
                String number = dc.substring(pos, pos + 1);
                if (!Character.isDigit(number.charAt(0))) {
                  continue;
                }
                longitudCifraImpar = 2 * Integer.parseInt(number);
                

                /*
                 * Si al multiplicar por dos un numero de unaposicion impar el restultado tiene
                 * una longitud de más de 1 cifra..
                 */
                if (longitudCifraImpar.toString().length() > 1) {
                    /* Sumamos las dos cifras */
                    longitudCifraImpar = Integer
                            .parseInt(longitudCifraImpar.toString().substring(0, 1))
                            + Integer.parseInt(longitudCifraImpar.toString().substring(1, 2));
                }
                // Sino, sumamos el resultado a la suma impar
                sumaImpares = sumaImpares + longitudCifraImpar;
            }
            return sumaImpares;
        }

        /**
         * Obtiene el dígito control valido para el CIF que el usuario introduce por teclado.
         * 
         * @return Devuelve un valor de tipo entero con el dígito control siempre que el valor
         *         de las unidades sea mayor que cero.
         */
        protected static int getObtenerDigitoControl(String cif) {

            int resultadoResta = 0;

            int sumaTotal;
            // Sumamos el resultado de los pares y los impares
            sumaTotal = getSumaPares(cif) + getSumaImpares(cif);

            // Nos quedamos con las unidades de dicha suma
            int unidades = sumaTotal % 10;

            // Si el valor de las unidades es 0
            if (unidades > 0) {
                // Restamos de 10 las unidades obtenidas.
                resultadoResta = 10 - unidades;
            } else { // En caso contrario
                resultadoResta = 0;
            }

            // Devolvemos el resultado.
            return resultadoResta;
        }

        /**
         * Comprueba si el CIF introducido por el usuario es correcto.
         * 
         * @return Devuelve verdaddero el si CIF introducido es correcto.
         */
        public static boolean validCIF(String cif) {

            char letraCif = cif.substring(0, 1).toUpperCase().charAt(0);

            // Guardamos el dígito control en una variable tipo texto.
            String digitoControl = String.valueOf(getObtenerDigitoControl(cif));

            /*
             * Si los dos primeros dígitos de la cadena que se utiliza para calcular el digito
             * control son dos ceros o bien, si la letra del CIF esta contenida en la cadena
             * primerCaracterLetra, el dígito control del CIF será una letra
             */

            String cifValido;
            if (getDigitsCif(cif).substring(1, 2).equals("00")
                    || PRIMER_CARACTER_LETRA.indexOf(letraCif) != -1) {

                // Almacenamos un caracter de control tipo letra
                String caracterControl;
                caracterControl = Character
                        .toString(LETRA_CONTROL[getObtenerDigitoControl(cif)]);

                // Armamos el CIF con la letra.
                cifValido = letraCif + getDigitsCif(cif) + caracterControl;

            } else { // En caso de no comenzar por una letra, almacenamos un número.

                // Almacenamos un caracter control de tipo numérico
                cifValido = letraCif + getDigitsCif(cif) + digitoControl;
            }

            // Si el CIF válido coincide con la cadena introducida por el usuario
            // el método equals de la clase String, devuelve verdadero.
            // Por lo tanto podemos crear el return de la siguiente forma:
            return cif.equals(cifValido);
        }
    }

    /**
     * 
     * @author anadal (u80067)
     *
     */
    public static class CheckNifResult {

        private final boolean valid;
        
        private final String nifListFormatted;

        private final List<NifInfo> nifs;

        public CheckNifResult(boolean valid, String nifListFormatted, List<NifInfo> nifs) {
            super();
            this.valid = valid;
            this.nifListFormatted = nifListFormatted;
            this.nifs = nifs == null ? new ArrayList<NifInfo>() : nifs;
        }

        public boolean isValid() {
            return valid;
        }

        public List<NifInfo> getNifs() {
            return nifs;
        }

        public String getNifListFormatted() {
            return nifListFormatted;
        }

    }

    /**
     * 
     * @author anadal (u80067)
     *
     */
    public static class NifInfo {

        private final String error;

        private final String nif;

        private final String nifNormalized;

        private final TipusNif tipusNif;

        public NifInfo(String nif, String nifNormalized, String error) {
            super();
            this.error = error;
            this.nif = nif;
            this.nifNormalized = nifNormalized;
            this.tipusNif = null;
        }

        public NifInfo(String nif, String nifNormalized, TipusNif tipusNif) {
            super();
            this.error = null;
            this.nif = nif;
            this.nifNormalized = nifNormalized;
            this.tipusNif = tipusNif;
        }

        public NifInfo(String nif, String nifNormalized, TipusNif tipusNif, String error) {
            super();
            this.error = error;
            this.nif = nif;
            this.nifNormalized = nifNormalized;
            this.tipusNif = tipusNif;
        }

        public boolean isValid() {
            return error == null;
        }

        public String getError() {
            return error;
        }

        public String getNif() {
            return nif;
        }

        public String getNifNormalized() {
            return nifNormalized;
        }

        public TipusNif getTipusNif() {
            return tipusNif;
        }
        
        @Override
        public String toString() {
            if (error == null) {
                return nifNormalized;
            } else {
                return nif;
            }
        }

    }

}
