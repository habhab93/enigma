package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @author Brian Ha
 */
class Rotor {

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;
    /** True if the rotor has a ratchet; false otherwise. */
    private boolean hasRatchet;
    /** True if the rotor has an inverse; false otherwise. */
    private boolean hasInv;
    /** Description of the rotor type. */
    private String typeVal;

    /** Rotor constructor. */
    public Rotor() {
    }

    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) (p + 'A');
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return c - 'A';
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return hasRatchet;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        return hasInv;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Return hasRatchet. */
    boolean getHasRatchet() {
        return hasRatchet;
    }

    /** Return hasInv. */
    boolean getHasInv() {
        return hasInv;
    }

    /** Return typeVal. */
    String typeVal() {
        return typeVal;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        for (int i = 0; i < PermutationData.ROTOR_SPECS.length; i++) {
            if (typeVal.equals(PermutationData.ROTOR_SPECS[i][0])) {
                return toIndex(PermutationData.ROTOR_SPECS[i][1].charAt(p));
            }
        }
        return -1;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        for (int i = 0; i < PermutationData.ROTOR_SPECS.length; i++) {
            if (typeVal.equals(PermutationData.ROTOR_SPECS[i][0])) {
                return toIndex(PermutationData.ROTOR_SPECS[i][2].charAt(e));
            }
        }
        return -1;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {
        for (int i = 0; i < PermutationData.ROTOR_SPECS.length; i++) {
            if (typeVal.equals(PermutationData.ROTOR_SPECS[i][0])) {
                String tempStr = PermutationData.ROTOR_SPECS[i][3];
                for (int j = 0; j < tempStr.length(); j++) {
                    if (toLetter(_setting) == (tempStr.charAt(j))) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    /** Advance me one position. */
    void advance() {
        if (_setting >= ALPHABET_SIZE - 1) {
            set(0);
        } else {
            set(_setting + 1);
        }
    }

    /** Set typeVal variable.
     * @param inStr is what typeVal will be set to.
     */
    void setTypeVal(String inStr) {
        typeVal = inStr;
    }

    /** Set hasRatchet variable.
     * @param hasRat is what hasRatchet will be set to.
     */
    void setHasRatchet(boolean hasRat) {
        hasRatchet = hasRat;
    }

    /** Set hasInv variable.
     * @param hasIn is what hasInv will be set to.
     */
    void setHasInv(boolean hasIn) {
        hasInv = hasIn;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;

}
