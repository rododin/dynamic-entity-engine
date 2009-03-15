/*
 * PropertyAdapter.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.impl;

import ru.rododin.dynamic_entity_engine.entity.PropertyEvent;
import ru.rododin.dynamic_entity_engine.entity.PropertyListener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class PropertyAdapter <Value>
  implements PropertyListener<Value>
{
  public void propertyAccessed(PropertyEvent<Value> valuePropertyEvent)
  {
  }

  public void propertyChanging(PropertyEvent<Value> valuePropertyEvent)
  {
  }

  public void propertyChanged(PropertyEvent<Value> valuePropertyEvent)
  {
  }
}
