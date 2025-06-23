package momospider.core.models;

public record ProductRecord(String name, String price, String url) {
    public static ProductRecord of(String name, String price, String imageUrl) {
        return new ProductRecord(name, price, imageUrl);
    }
}
