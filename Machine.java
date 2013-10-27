package enigma;

/** Class that represents a complete enigma machine.
 *  @author Brian Ha
 */
class Machine {

    /** rotors holds all the Rotor objects. */
    private Rotor[] rotors;

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;

    /** Machine constructor.
     * @param arr is an array of Rotor objects.
     */
    public Machine(Rotor[] arr) {
        rotors = arr;
    }

    /** Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     *  settings are all 'A'.
     * @param arr is an array of Rotor objects.
     */
    void replaceRotors(Rotor[] arr) {
    }

    /** Sets typeVal variable of a single Rotor object.
     * @param i is the index of the Rotor to be set
     * @param typeValStr is the new String value of the Rotor object's typeVal
     */
    void setRotorTypeVal(int i, String typeValStr) {
        rotors[i].setTypeVal(typeValStr);
    }

    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        rotors[1].set(setting.charAt(0) - 'A');
        rotors[2].set(setting.charAt(1) - 'A');
        rotors[3].set(setting.charAt(2) - 'A');
        rotors[4].set(setting.charAt(3) - 'A');
    }

    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        String finStr = "";
        for (int i = 0; i < msg.length(); i++) {
            char input = msg.charAt(i);
            int thruVal = input - 'A';
            if (rotors[3].atNotch()) {
                rotors[2].advance();
                rotors[3].advance();
            } else if (rotors[4].atNotch()) {
                rotors[3].advance();
            }
            rotors[4].advance();
            for (int j = 4; j >= 1; j--) {
                int temp1 = thruVal + rotors[j].getSetting();
                thruVal = (temp1 % ALPHABET_SIZE + ALPHABET_SIZE)
                    % ALPHABET_SIZE;
                thruVal = rotors[j].convertForward(thruVal);
                int temp2 = thruVal - rotors[j].getSetting();
                thruVal = (temp2 % ALPHABET_SIZE + ALPHABET_SIZE)
                    % ALPHABET_SIZE;
            }
            thruVal = rotors[0].convertForward(thruVal);
            for (int k = 1; k <= 4; k++) {
                int temp1 = thruVal + rotors[k].getSetting();
                thruVal = (temp1 % ALPHABET_SIZE + ALPHABET_SIZE)
                    % ALPHABET_SIZE;
                thruVal = rotors[k].convertBackward(thruVal);
                int temp2 = thruVal - rotors[k].getSetting();
                thruVal = (temp2 % ALPHABET_SIZE + ALPHABET_SIZE)
                    % ALPHABET_SIZE;
            }
            char chAdd = (char) (thruVal + 'A');
            finStr += chAdd;
        }
        return finStr;
    }
}
