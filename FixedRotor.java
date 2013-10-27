package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author Brian Ha
 */
class FixedRotor extends Rotor {

    /** FixedRotor constructor. */
    public FixedRotor() {
    }

    @Override
    boolean advances() {
        return false;
    }

    @Override
    boolean atNotch() {
        return false;
    }

    /** Fixed rotors do not advance. */
    @Override
    void advance() {
    }

}
