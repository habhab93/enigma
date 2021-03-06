package enigma;

/** Class that represents a reflector in the enigma.
 *  @author Brian Ha
 */
class Reflector extends Rotor {

    /** Reflector constructor. */
    public Reflector() {
    }

    @Override
    boolean hasInverse() {
        return false;
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

}
