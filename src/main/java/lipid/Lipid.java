package lipid;

import java.util.Objects;

public class Lipid {
    private final int compoundId;
    private final String name;
    private final String formula;
    private final LipidType lipidType;
    private final int carbonCount;
    private final int doubleBondsCount;


    /**
     * @param compoundId compoundId
     * @param name name
     * @param formula formula
     * @param lipidType lipidType
     * @param carbonCount carbonCount
     * @param doubleBondCount doubleBondCount
     */
    public Lipid(int compoundId, String name, String formula, LipidType lipidType, int carbonCount, int doubleBondCount) {
        this.compoundId = compoundId;
        this.name = name;
        this.formula = formula;
        this.lipidType = lipidType;
        this.carbonCount = carbonCount;
        this.doubleBondsCount = doubleBondCount;
    }

    public int getCompoundId() {
        return compoundId;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public LipidType getLipidType() {
        return this.lipidType;
    }

    public int getCarbonCount() {
        return carbonCount;
    }

    public int getDoubleBondsCount() {
        return doubleBondsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lipid)) return false;
        Lipid lipid = (Lipid) o;
        return compoundId == lipid.compoundId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(compoundId);
    }

    @Override
    public String toString() {
        return "Lipid{" +
                "compoundId=" + compoundId +
                ", name='" + name + '\'' +
                ", formula='" + formula + '\'' +
                ", lipidType='" + lipidType + '\'' +
                ", carbonCount=" + carbonCount +
                ", doubleBondCount=" + doubleBondsCount +
                '}';
    }

    // Function to determine type order (PG < PE < PI < PA < PS < PC)
    /*public int getTypeOrder(LipidType type) {
        switch (type) {
            case PG: return 1;
            case PE: return 2;
            case PI: return 3;
            case PA: return 4;
            case PS: return 5;
            case PC: return 6;
            case TG: return 7;
            case FA: return 8;
            default: return 9;
        }
    }*/
}
