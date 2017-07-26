/*
 * MIT License
 *
 * Copyright (c) 2017  GameplayJDK
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
