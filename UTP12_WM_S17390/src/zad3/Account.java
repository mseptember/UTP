package zad3;

import java.beans.*;

public class Account {
    private double balance;
    private int id;
    private static int counter = 1;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private VetoableChangeSupport vcs = new VetoableChangeSupport(this);

    public Account(double balance) {
        this.balance  = balance;
        id = counter++;
    }

    public Account() {
        this.balance  = 0;
        id = counter++;
    }

    public double deposit(double cash) throws PropertyVetoException{
        setBalance(this.balance + cash);
        return this.balance;
    }

    public double withdraw(double cash) throws PropertyVetoException{
        setBalance(this.balance - cash);
        return this.balance;
    }

    public double transfer(Account acc, double cash) throws PropertyVetoException{
        setTransfer(acc, cash);
        return this.balance;
    }

    @Override
    public String toString() {
        return "Acc " + id + ": " + balance;
    }

    public synchronized void setTransfer(Account account, double balance) throws PropertyVetoException {
        setBalance(this.balance - balance);
        account.setBalance(account.balance + balance);
    }

    public void setBalance(double balance) throws PropertyVetoException{
        int oldValue = (int) this.balance;
        vcs.fireVetoableChange(id + ": ", new Integer(oldValue), new Integer((int) balance));
        this.balance = balance;
        pcs.firePropertyChange(id + ": ", new Double(oldValue), new Double((int) balance));
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /*public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }*/

    public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
        vcs.addVetoableChangeListener(listener);
    }

    /*public synchronized void removeVetoableChangeListener(VetoableChangeListener listener) {
        vcs.removeVetoableChangeListener(listener);
    }*/
}
