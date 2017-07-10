package de.gameplayjdk.validatorfx;

import javafx.beans.property.Property;

/**
 * Created by GameplayJDK on 07.06.2017.
 */
public interface Requirement<P extends Property> {

    boolean check(P property);
}
