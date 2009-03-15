/*
 * PropertyDescriptor.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Represents a definitor for a <code>{@link ru.rododin.dynamic_entity_engine.entity.Property}</code>.
 * The definitor introduces the property <code>{@link #getName() name}</code>,
 * its <code>{@link #getValueClass() value class}</code> and defines the
 * property <code>{@link #getDefaultValue() default value}</code>.
 * @param <Value> the value class generic parameter
 * @author Rod Odin
 */
public interface PropertyDescriptor <Value>
{
  /**
   * Returns the property name.
   * @return non-<code>null</code> textual property identifier
   */
  String getName();

  /**
   * Returns the <code>{@link Class}</code> object representing the <code>Value</code>
   * generic parameter.
   * @return non-<code>null</code> <code>{@link Class}</code> instance
   */
  Class<Value> getValueClass();

  /**
   * Returns the property default value.
   * @return <code>null</code> or non-<code>null</code> <code>Value</code> instance
   */
  Value getDefaultValue();

  PropertyListener<Value> getDefaultListener();
}
