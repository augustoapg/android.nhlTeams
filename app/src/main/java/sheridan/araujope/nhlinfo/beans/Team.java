/**
 * Project: NHL Teams Information
 * Author: Augusto A P Goncalez
 * Date: Sept. 30, 2019
 *
 * Project Description:
 * This app gets information from an API to display a list of all the teams in the NHL.
 * The list contains basic information, but when clicking on a team, a new Activity page opens with
 * additional information.
 */

package sheridan.araujope.nhlinfo.beans;

import java.io.Serializable;

public class Team implements Serializable {
    private String name = "";
    private String location = "";
    private String venue = "";
    private String firstYear = "";
    private String conference = "";
    private String division = "";

    public Team(String name, String location, String venue, String firstYear, String conference, String division) {
        this.name = name;
        this.location = location;
        this.venue = venue;
        this.firstYear = firstYear;
        this.conference = conference;
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

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getFirstYear() {
        return firstYear;
    }

    public void setFirstYear(String firstYear) {
        this.firstYear = firstYear;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.location + " (" + this.firstYear + ")";
    }
}
