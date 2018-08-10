package logunov.maxim.domain.entity;

public class Door implements DomainModel {

    private String description;
    private String title;
    private String doorUrl;

    public Door(String title, String description, String doorUrl) {
        this.description = description;
        this.title = title;
        this.doorUrl = doorUrl;
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
}
