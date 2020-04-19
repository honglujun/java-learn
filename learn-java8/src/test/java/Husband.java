public class Husband {
    private int husbandName;
    private String husband;
    private String familyId;

    public Husband(int husbandName, String husband, String familyId) {
        this.husbandName = husbandName;
        this.husband = husband;
        this.familyId = familyId;
    }

    public int getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(int husbandName) {
        this.husbandName = husbandName;
    }

    public String getHusband() {
        return husband;
    }

    public void setHusband(String husband) {
        this.husband = husband;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
