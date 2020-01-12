package fr.umontpellier.iut.conquest;

import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {
    final Deque<Memento> mementos = new ArrayDeque<>();

    public Memento getMemento() {
        Memento memento= mementos.pop();
        return memento;
    }

    public void addMemento(Memento memento)   {
        mementos.push(memento);
    }

    public boolean isEmpty(){
        return mementos.isEmpty();
    }
}
