/*
 * PropertyEventDefinition.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener.demo;

import ru.rododin.dynamic_entity_engine.entity.Property;
import ru.rododin.dynamic_entity_engine.listener.AbstractEvent;
import ru.rododin.dynamic_entity_engine.listener.Event;

/**
 * Description.
 *
 * @author Rod Odin
 */
public enum PropertyEventDefinition
{
  PROPERTY_READING,
  PROPERTY_CHANGING,
  PROPERTY_CHANGED;

  public <PropertyValue> Event<PropertyEventData<PropertyValue>> createEvent(final Property<PropertyValue> property)
  {
    return createEvent(property, null);
  }

  public <PropertyValue> Event<PropertyEventData<PropertyValue>> createEvent(final Property<PropertyValue> property, final PropertyValue newValue)
  {
    return new AbstractEvent<PropertyEventData<PropertyValue>>(new PropertyEventData<PropertyValue>(property, newValue), false)
    {
      public String getName()
      {
        return name();
      }
    };
  }
}

