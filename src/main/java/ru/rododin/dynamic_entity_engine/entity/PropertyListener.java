/*
 * PropertyListener.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Description.
 *
 * @author Rod Odin
 */
public interface PropertyListener <Value>
{
  void propertyAccessed(PropertyEvent<Value> event);
  void propertyChanging(PropertyEvent<Value> event);
  void propertyChanged(PropertyEvent<Value> event);
}

