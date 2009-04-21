/*
 * PropertyEventData.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener.demo;

import ru.rododin.dynamic_entity_engine.entity.Property;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class PropertyEventData <PropertyValue>
{
  public PropertyEventData(Property<PropertyValue> property, PropertyValue newValue)
  {
    this.property = property;
    this.newValue = newValue;
  }

  public Property<PropertyValue> getProperty()
  {
    return property;
  }

  public void setProperty(Property<PropertyValue> property)
  {
    this.property = property;
  }

  public PropertyValue getNewValue()
  {
    return newValue;
  }

  public void setNewValue(PropertyValue newValue)
  {
    this.newValue = newValue;
  }

  private Property<PropertyValue> property;
  private PropertyValue newValue;
}
