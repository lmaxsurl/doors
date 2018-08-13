package logunov.maxim.domain.entity;

public class Door implements DomainModel {

    private String description;
    private String title;
    private String doorUrl;
    private String highQualityDoorUrl;

    public Door(String title, String description, String doorUrl, String highQualityDoorUrl) {
        this.description = description;
        this.title = title;
        this.doorUrl = doorUrl;
        this.highQualityDoorUrl = highQualityDoorUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHighQualityDoorUrl() {
        return highQualityDoorUrl;
    }

    public void setHighQualityDoorUrl(String highQualityDoorUrl) {
        this.highQualityDoorUrl = highQualityDoorUrl;
    }
}
