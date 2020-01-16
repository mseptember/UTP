package zad3;

import java.beans.*;

public class AccountChange implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if((double) evt.getNewValue() > 0) {
            System.out.println(evt.getPropertyName() + "Value changed from " + evt.getOldValue() + " to " + evt.getNewValue());
        }
        else {
            System.out.println(evt.getPropertyName() + "Value changed from " + evt.getOldValue() + " to " + evt.getNewValue() + ", balance < 0!");
        }
    }
}
