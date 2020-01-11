package fr.umontpellier.iut.conquest;

import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {
    final Deque<Memento> mementos = new ArrayDeque<>();

    public Memento getMemento()

    {

        Memento Memento= mementos.pop();

        return Memento;

    }

    public void addMemento(Memento memento)

    {

        mementos.push(memento);

    }
}
