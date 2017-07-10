package de.gameplayjdk.validatorfx;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Created by GameplayJDK on 07.06.2017.
 */
public class Validator<P extends Property> {

    private ObjectProperty<P> property;

    private ObjectProperty<Requirement<P>> requirement;

    public Validator() {
        this(null, null);
    }

    public Validator(P property) {
        this(property, null);
    }

    public Validator(P property, Requirement<P> requirement) {
        this.property = new SimpleObjectProperty<P>(property);

        this.requirement = new SimpleObjectProperty<Requirement<P>>(requirement);
    }

    public boolean validate(P property) {
        return this.getRequirement().check(property);
    }

    public boolean validate() {
        return this.validate(this.getProperty());
    }

    public P getProperty() {
        return this.property.get();
    }

    public ObjectProperty<P> propertyProperty() {
        return property;
    }

    public void setProperty(P property) {
        this.property.set(property);
    }

    public Requirement<P> getRequirement() {
        return this.requirement.get();
    }

    public ObjectProperty<Requirement<P>> requirementProperty() {
        return requirement;
    }

    public void setRequirement(Requirement<P> requirement) {
        this.requirement.set(requirement);
    }
}
