package zad3;

import java.beans.*;

public class AccountLimitator implements VetoableChangeListener{
    int limitator;

    public AccountLimitator(int limitator) {
        this.limitator = limitator;
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        int value = (int) evt.getNewValue();
        if(value < limitator) { //bo np. -201 < -200
            throw new PropertyVetoException(evt.getPropertyName() + "Unacceptable value change: " + (double) value, evt); //proba bez "(double)>
        }
    }
}
