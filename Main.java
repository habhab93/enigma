package enigma;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/** Enigma simulator.
 *  @author Brian Ha
 */
public final class Main {

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */

    /** rotors is an array of Rotors. */
    private static Rotor[] rotors = new Rotor[5];

    /** This is the main method. */
    public static void main(String[] unused) {
        Machine M;
        BufferedReader input =
            new BufferedReader(new InputStreamReader(System.in));
        boolean check = false;
        buildRotors();
        M = null;
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine(rotors);
                    configure(M, line);
                    check = true;
                } else {
                    if (!check) {
                        System.exit(1);
                    }
                    if (!line.equals("")) {
                        printMessageLine(M.convert(standardize(line)));
                    } else {
                        System.out.println("");
                    }
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        String[] sgmts = line.split("\\s+");
        String choices = "I II III IV V VI VII VIII";
        if (sgmts.length != 7) {
            return false;
        }
        if (sgmts[3].equals(sgmts[4]) || sgmts[4].equals(sgmts[5])
            || sgmts[3].equals(sgmts[5])) {
            return false;
        }
        if (!(sgmts[0].equals("*"))) {
            return false;
        }
        if (!(sgmts[1].equals("B")) && !(sgmts[1].equals("C"))) {
            return false;
        }
        if (!(sgmts[2].equals("BETA")) && !(sgmts[2].equals("GAMMA"))) {
            return false;
        }
        if (!(choices.contains(sgmts[3]))) {
            return false;
        }
        if (!(choices.contains(sgmts[4]))) {
            return false;
        }
        if (!(choices.contains(sgmts[5]))) {
            return false;
        }
        if (!(sgmts[6].matches("[A-Z]+")) || sgmts[6].length() != 4) {
            return false;
        }
        return true;
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        String[] sgmts = config.split("\\s+");
        M.setRotorTypeVal(0, sgmts[1]);
        M.setRotorTypeVal(1, sgmts[2]);
        M.setRotorTypeVal(2, sgmts[3]);
        M.setRotorTypeVal(3, sgmts[4]);
        M.setRotorTypeVal(4, sgmts[5]);
        M.setRotors(sgmts[6]);
    }

    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        String noSpace = line.replaceAll("\\s+", "");
        if (!(noSpace.matches("[a-zA-Z ]+"))) {
            System.exit(1);
        } else {
            return noSpace.toUpperCase();
        }
        return null;
    }

    /** Print MSG in groups of five (except that the last group may
     *  have fewer letters). */
    private static void printMessageLine(String msg) {
        String outStr = "";
        for (int i = 0; i < msg.length(); i++) {
            outStr += msg.charAt(i);
            if ((i + 1) % 5 == 0) {
                outStr += " ";
            }
        }
        System.out.println(outStr);
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        Reflector ref = new Reflector();
        FixedRotor fix = new FixedRotor();
        Rotor rot3 = new Rotor();
        Rotor rot4 = new Rotor();
        Rotor rot5 = new Rotor();
        rotors[0] = ref;
        rotors[1] = fix;
        rotors[2] = rot3;
        rotors[3] = rot4;
        rotors[4] = rot5;
    }

}

