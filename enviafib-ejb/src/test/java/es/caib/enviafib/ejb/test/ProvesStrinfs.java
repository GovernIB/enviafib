package es.caib.enviafib.ejb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fun.mike.dmp.Diff;
import fun.mike.dmp.DiffMatchPatch;

public class ProvesStrinfs {

    public static void main(String[] args) {

        String error1 = "Error creant petició de fi a PortaFIB: No existeix cap usuari amb NIF 45186149P i l'aplicació no té permís per crear-ne";
        //        String error2 = "No s'ha creat ni enviat cap peticio a PortaFIB";
        String error2 = "Error creant petició de fi a PortaFIB: No existeix cap usuari amb NIF 12313245L o 65498745G i l'aplicació no té permís per crear-ne";

        error1 = "No s'ha pogut crear el fitxer Fitxer Proves 1 a l'aplicació PortaFIB";
        error2 = "No s'ha pogut crear el fitxer buit-copia a l'aplicació PortaFIB";

        String fileName1 = "Fitxer Proves 1";
        String fileName2 = "buit-copia";

        error1 = error1.replaceAll(fileName1, "FILENAME");
        error2 = error2.replaceAll(fileName2, "FILENAME");

        System.out.println(error1);
        System.out.println(error2);

        System.out.println(error1.length());
        System.out.println(error2.length());

        DiffMatchPatch dmp = new DiffMatchPatch();
        LinkedList<Diff> diffs = dmp.diff_main(error1, error2);
        System.out.println(diffs);

        int contadorEqu = 0;
        int contadorDel = 0;
        int contadorIns = 0;

        for (Diff diff : diffs) {
            switch (diff.operation) {
                case EQUAL:
                    contadorEqu += diff.text.length();
                break;
                case DELETE:
                    contadorDel += diff.text.length();
                break;
                case INSERT:
                    contadorIns += diff.text.length();
                break;

            }
        }

        System.out.println(contadorEqu);
        System.out.println(contadorDel);
        System.out.println(contadorIns);

        //        List<String[]> diferents = new ArrayList<String[]>();
        //        compararPalabras2(error1, error2, diferents);
        //
        //        for (String[] strings : diferents) {
        //            System.out.println(strings[0] + " " + strings[1]);
        //        }

        //        Pair<String> par = diff(error1, error2);
        //        System.out.println(par);
        //        int par1 = par.first.length();
        //        int par2 = par.second.length();
        //
        //        System.out.println(par1 + " - " + error1.length() + " = " + (error1.length() - par1));
        //        System.out.println(par2 + " - " + error2.length() + " = " + (error2.length() - par2));
        //
        //        System.out.println(par1 + " + " + par2 + " = " + (par1 + par2) + " > " + error1.length());
        //
        //        if (par.first.length() + par.second.length() > error1.length()) {
        //            System.out.println("Son errores distintos");
        //        } else {
        //            System.out.println("Son el mismo error");
        //        }

    }

    public void compararPalabras(String error1, String error2) {

        String[] palabras1 = error1.split(" ");
        String[] palabras2 = error2.split(" ");

        List<String> diferencies1 = new ArrayList<String>();
        List<String> diferencies2 = new ArrayList<String>();
        List<String> comunes = new ArrayList<String>();

        for (int i1 = 0; i1 < palabras1.length; i1++) {
            for (int i2 = 0; i2 < palabras2.length; i2++) {

                String palabra1 = palabras1[i1];
                String palabra2 = palabras2[i2];

                if (palabra1.equals(palabra2)) {
                    comunes.add(palabra1);
                } else {
                    diferencies1.add(palabra1);
                    diferencies2.add(palabra2);
                }

            }
        }
    }

    public static List<String[]> compararPalabras2(String error1, String error2, List<String[]> diferents) {

        if (error1 == null || error2 == null) {
            return diferents;
        }

        int pipe1 = error1.indexOf(" ") + 1;
        int pipe2 = error2.indexOf(" ") + 1;

        String palabra1;
        String palabra2;

        String newError1;
        String newError2;

        if (pipe1 == 0) {
            palabra1 = error1;
            newError1 = null;
        } else {
            palabra1 = error1.substring(0, pipe1);
            newError1 = error1.substring(pipe1);
        }

        if (pipe2 == 0) {
            palabra2 = error2;
            newError2 = null;
        } else {
            palabra2 = error2.substring(0, pipe2);
            newError2 = error2.substring(pipe2);
        }

        if (palabra1.equals(palabra2)) {
            System.out.println("equi");
            diferents = compararPalabras2(newError1, newError2, diferents);
        } else {
            System.out.println("dif");
            String[] d = { palabra1, palabra2 };
            diferents.add(d);
            //            diferents = compararPalabras2(error1, newError2, diferents);
            //            diferents = compararPalabras2(newError1, error2, diferents);
        }
        return diferents;

    }

    public static Pair<String> diff(String a, String b) {
        return diffHelper(a, b, new HashMap<>());
    }

    private static Pair<String> diffHelper(String a, String b, Map<Long, Pair<String>> lookup) {
        long key = ((long) a.length()) << 32 | b.length();
        if (!lookup.containsKey(key)) {
            Pair<String> value;
            if (a.isEmpty() || b.isEmpty()) {
                value = new Pair<>(a, b);
            } else if (a.charAt(0) == b.charAt(0)) {
                value = diffHelper(a.substring(1), b.substring(1), lookup);
            } else {
                Pair<String> aa = diffHelper(a.substring(1), b, lookup);
                Pair<String> bb = diffHelper(a, b.substring(1), lookup);
                if (aa.first.length() + aa.second.length() < bb.first.length() + bb.second.length()) {
                    value = new Pair<>(a.charAt(0) + aa.first, aa.second);
                } else {
                    value = new Pair<>(bb.first, b.charAt(0) + bb.second);
                }
            }
            lookup.put(key, value);
        }
        return lookup.get(key);
    }

    public static class Pair<T> {
        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public final T first, second;

        public String toString() {
            return "(" + first + "," + second + ")";
        }
    }
}
