package domain;

public interface EntityConverter <T extends Entity>{
    String toString(T object);

    String toString(Agent oject);

    T fromString(String line);
}
