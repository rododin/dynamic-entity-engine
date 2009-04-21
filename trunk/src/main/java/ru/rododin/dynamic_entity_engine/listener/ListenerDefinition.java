/*
 * ListenerDefinition.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public interface ListenerDefinition <EventDefinition extends Enum<EventDefinition>>
{
  <EventData> Listener<EventData> getListener(EventDefinition eventDefinition);
}

