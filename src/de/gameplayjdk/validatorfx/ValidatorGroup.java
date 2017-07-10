package de.gameplayjdk.validatorfx;

import javafx.beans.property.MapProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;

/**
 * Created by GameplayJDK on 07.06.2017.
 */
public class ValidatorGroup<P extends Property> extends Validator<MapProperty<String, Validator<P>>> implements Requirement<MapProperty<String, Validator<P>>> {

    private MapProperty<String, Validator<P>> mapProperty;

    public ValidatorGroup() {
        super();

        this.mapProperty = new SimpleMapProperty<String, Validator<P>>();
        this.mapProperty.setValue(FXCollections.observableHashMap());

        super.setProperty(this.mapProperty);
        super.setRequirement(this);
    }

    @Override
    public boolean check(MapProperty<String, Validator<P>> property) {
        boolean valid = true;

        for (Validator<P> validator : this.mapProperty.values()) {
            if (validator == this) {
                continue;
            }

            valid = valid && validator.validate();
        }

        return valid;
    }

    public void addValidator(String id, Validator<P> validator) {
        this.mapProperty.put(id, validator);
    }

    public void removeValidator(String id) {
        this.mapProperty.remove(id);
    }
}
