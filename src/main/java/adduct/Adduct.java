package adduct;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Adduct {

    /**
     * Calculate the mass to search depending on the adduct hypothesis
     *
     * @param observedMZ mz
     * @param adductName adduct name ([M+H]+, [2M+H]+, [M+2H]2+, etc..)
     * @return the monoisotopic mass of the experimental mass mz with the adduct @param adduct
     */
    // !! TODO Create the necessary regex to obtain the multimer (number before the M) and the charge (number before the + or - (if no number, the charge is 1).
    public static Double getMonoisotopicMassFromMZ(Double observedMZ, String adductName) {
        Double adductMass = null;

        if (AdductList.MAPMZPOSITIVEADDUCTS.containsKey(adductName)) {
            adductMass = AdductList.MAPMZPOSITIVEADDUCTS.get(adductName);
        } else if (AdductList.MAPMZNEGATIVEADDUCTS.containsKey(adductName)) {
            adductMass = AdductList.MAPMZNEGATIVEADDUCTS.get(adductName);
        }

        int multimer = 1;
        if (adductName.startsWith("[2M")) {
            multimer = 2;
        }

        int charge = 1;
        if (adductName.endsWith("2+") || adductName.endsWith("2-")) {
            charge = 2;
        }
        return ((observedMZ * charge) + adductMass) / multimer;
    }


    // !! TODO Create the necessary regex to obtain the multimer (number before the M) and the charge (number before the + or - (if no number, the charge is 1).
    public static Double getMzFromMonoisotopicMass(Double monoisotopicMass, String adductName) {
        if (monoisotopicMass == null || adductName == null) {
            return null;
        }

        Double adductMass = AdductList.MAPMZPOSITIVEADDUCTS.get(adductName);

        if (adductMass == null) {
            adductMass = AdductList.MAPMZNEGATIVEADDUCTS.get(adductName);
        }
        if (adductMass == null) {
            return null;
        }

        int multimer = 1;
        if (adductName.startsWith("[2M")) {
            multimer = 2;
        }

        int charge = 1;
        if (adductName.endsWith("2+") || adductName.endsWith("2-")) {
            charge = 2;
        }

        return ((monoisotopicMass * multimer) - adductMass) / charge;
    }

    /**
     * Returns the ppm difference between measured mass and theoretical mass
     *
     * @param experimentalMass Mass measured by MS
     * @param theoreticalMass  Theoretical mass of the compound
     */
    public static int calculatePPMIncrement(Double experimentalMass, Double theoreticalMass) {
        int ppmIncrement;
        ppmIncrement = (int) Math.round(Math.abs((experimentalMass - theoreticalMass) * 1000000
                / theoreticalMass));
        return ppmIncrement;
    }

    /**
     * Returns the ppm difference between measured mass and theoretical mass
     *
     * @param experimentalMass Mass measured by MS
     * @param ppm              ppm of tolerance
     */
    public static double calculateDeltaPPM(Double experimentalMass, int ppm) {
        double deltaPPM;
        deltaPPM = Math.round(Math.abs((experimentalMass * ppm) / 1000000));
        return deltaPPM;
    }
    /*
    int multimer = 1;
        Matcher multimerMatcher = Pattern.compile("\\[(\\d*)M").matcher(adductName);
        if (multimerMatcher.find() && !multimerMatcher.group(1).isEmpty()) {
            multimer = Integer.parseInt(multimerMatcher.group(1));
        }

        int charge = 1;
        Matcher chargeMatcher = Pattern.compile("([+-])?(\\d*)([+-])\\]").matcher(adductName);
        if (chargeMatcher.find()) {
            String chargeStr = chargeMatcher.group(2);
            charge = chargeStr.isEmpty() ? 1 : Integer.parseInt(chargeStr);
        }
     */
}