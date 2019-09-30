package sheridan.araujope.nhlinfo.beans;

public class Team {
    private String name = "";
    private String location = "";
    private String division = "";

    public Team(String name, String location, String division) {
        this.name = name;
        this.location = location;
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.location + " (" + this.division + " division)";
    }
}
