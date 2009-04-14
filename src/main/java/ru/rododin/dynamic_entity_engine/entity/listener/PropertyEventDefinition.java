/*
 * PropertyEventDefinition.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.listener;

import ru.rododin.dynamic_entity_engine.entity.Property;

/**
 * Description.
 *
 * @author Rod Odin
 */
public enum PropertyEventDefinition
{
  ABORT_ACTION(Boolean .class, true),
  PROPERTY    (Property.class, null),
  NEW_VALUE   (Object  .class, null);

  private PropertyEventDefinition(Class valueClass, Object defaultValue)
  {
    this.valueClass   = valueClass  ;
    this.defaultValue = defaultValue;
  }

  public static

  public Class getValueClass()
  {
    return valueClass;
  }

  public Object getDefaultValue()
  {
    return defaultValue;
  }

  private Class  valueClass  ;
  private Object defaultValue;
}

