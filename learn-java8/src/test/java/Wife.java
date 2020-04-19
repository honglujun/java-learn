public class Wife {
    private int husbandName;
    private String whoseWife;
    private String familyId;

    public Wife() {
    }

    public Wife(int husbandName, String whoseWife, String familyId) {
        this.husbandName = husbandName;
        this.whoseWife = whoseWife;
        this.familyId = familyId;
    }

    public int getHusbandName() {
        return husbandName;
    }

    public void setHusbandName(int husbandName) {
        this.husbandName = husbandName;
    }

    public String getWhoseWife() {
        return whoseWife;
    }

    public void setWhoseWife(String whoseWife) {
        this.whoseWife = whoseWife;
    }

    public String getFamilyId() {
        return familyId;
    }

    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }
}
