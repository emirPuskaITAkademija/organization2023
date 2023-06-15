package ba.celebration.organization.country.town;

import java.io.Serializable;
import java.util.List;

public class TownPage implements Serializable {
    private int totalPages;
    private List<Town> towns;

    public TownPage(){

    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }
}
