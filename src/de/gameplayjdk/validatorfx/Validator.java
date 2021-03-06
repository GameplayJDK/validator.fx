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
