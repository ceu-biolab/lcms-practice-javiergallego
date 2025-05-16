package adduct;

public class MassTransformation {

// !! TODO create functions to transform the mass of the mzs to monoisotopic masses and vice versa.

    public static double toMonoisotopicMass(double mz, int charge, double adductMass) {
        return (mz * charge) - adductMass;
    }

    public static double toMz(double monoMass, int charge, double adductMass) {
        return (monoMass + adductMass) / charge;
    }
}
