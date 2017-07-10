# validator.fx

Simple JavaFx library to handle field validation (or validation of properties in general).

The idea behind this library is to allow validation of all kinds of properties and structure said validation as object tree using only one `validate()` method to check all.

## What's in it?

The library only contains three classes which makes its size only about `4kb`.

 - The `Validator` is simply a base class that wraps an `ObjectProperty` and allows a check against a `Requirement`.
 - A `Requirement` allows definition of a method for checking any type of property and return the fulfillment as boolean.
 - With `ValidatorGroup` is itself an extended implementation of a `Validator` that allows to use multiple `Validators` at once.

## How to install it?

For that just download the latest release and add the jar to your project as a library.

Of course you are free to clone the repo and build it yourself.

## How to use it?

**Please also read the section with other important information below!**

(The following example use code from [simple.fx](https://github.com/GameplayJDK/simple.fx)).

To use the library simply instantiate a `Validator` and define a requirement method for it:

```java
// ...

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import de.gameplayjdk.simplefx.Controller;

public class MyController extends Controller<AnchorPane> implements Requirement<StringProperty> {
    
    @FXML
    private TextField textField;
    
    private Validator<StringProperty> stringValidator;
    
    public MyController() {
        super();
    }
    
    @FXML
    @Override
    protected void initialize() {
        this.stringValidator = new Validator<StringProperty>(this.textField.textProperty(), this);
        
        // ...
    }
    
    // The validation logic
    @Override
    public boolean check(StringProperty property) {
        return !property.isEmpty();
    }
    
    // The validation flow (call this before submitting, etc)
    public void validateTextField() {
        if (!this.stringValidator.validate()) {
            // Field is empty
            
            return;
        }
        
        // ...
    }
    
    // ...
}

// ...
```

You can avoid having too many validators in your controller by using the `validate(P property)` method, which accepts a property to check as argument.

The possibility to group validators is also an alternative:

```java
// ...

    public void initializeValidator() {
        ValidatorGroup group = new ValidatorGroup();
        
        Validator nameValidator = new Validator(this.textField.textProperty(), ...);
        Validator tocValidator = new Validator(this.checkBox.selectedProperty(), ...);
        
        group.addValidator("Name", nameValidator);
        group.addValidator("AcceptTOC", tocValidator);
        // Use `group.removeValidator(...)` to remove a validator from the group
        
        // ...
        
        // Returns the combined reslt of all containing validators
        group.validate();
    }

// ...
```

## And the license?

It's MIT.
