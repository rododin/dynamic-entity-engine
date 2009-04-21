/*
 * PropertyListenerDefinition.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener.demo;

import ru.rododin.dynamic_entity_engine.listener.ListenerDefinition;
import ru.rododin.dynamic_entity_engine.listener.Listener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public enum PropertyListenerDefinition
  implements ListenerDefinition<PropertyEventDefinition>
{
  DUMMY; // TODO: complete the enum

  public <EventData> Listener<EventData> getListener(PropertyEventDefinition propertyEventDefinition)
  {
    return null;
  }
}

