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
public interface Listener <EventData>
{
  void beforeActionPerformed(Event<EventData> event) throws ListenerException;
  void afterActionPerformed (Event<EventData> event) throws ListenerException;
}

