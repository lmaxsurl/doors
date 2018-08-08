package logunov.maxim.domain.entity;

public class Type implements DomainModel {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Type(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Type && ((Type) obj).getType().equalsIgnoreCase(this.type);
    }
}
