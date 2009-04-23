/*
 * Listener.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public interface Listener <E extends Event>
{
  void eventOccured(E event) throws ListenerException;
}

