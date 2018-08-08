package logunov.maxim.domain.entity;

public class Door implements DomainModel {

    private int descriptionId;
    private String title;
    private String doorUrl;
    private String glazedDoorUrl;

    public Door(String title, int description, String doorUrl, String glazedDoorUrl) {
        this.descriptionId = description;
        this.title = title;
        this.doorUrl = doorUrl;
        this.glazedDoorUrl = glazedDoorUrl;
    }


    public String getGlazedDoorUrl() {
        return glazedDoorUrl;
    }

    public void setGlazedDoorUrl(String glazedDoorUrl) {
        this.glazedDoorUrl = glazedDoorUrl;
    }

    public String getDoorUrl() {
        return doorUrl;
    }

    public void setDoorUrl(String doorUrl) {
        this.doorUrl = doorUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public void setDescriptionId(int descriptionId) {
        this.descriptionId = descriptionId;
    }
}
