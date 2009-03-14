/*
 * Property.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Represents a single abstract property (parameter) of an
 * <code>{@link ru.rododin.dynamic_entity_engine.entity.Entity}</code>.
 * Any property is defined by its <code>{@link #getDescriptor() descriptor}</code>
 * and has a <code>{@link #setValue(Object) modifiable}</code>
 * <code>{@link #getValue() value}</code>.
 * @param <Value> the value class generic parameter
 * @author Rod Odin
 */
public interface Property <Value>
  extends Cloneable
{
  /**
   * Returns the property descriptor.
   * @return non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>
   *         instance
   */
  PropertyDescriptor<Value> getDescriptor();

  /**
   * Returns the current property value.
   * @return <code>null</code> or non-<code>null</code> property value
   * @see #setValue(Object)
   * @see PropertyDescriptor#getDefaultValue()
   */
  Value getValue();

  /**
   * Replaces the current value with the new given one.
   * @param value the new value to be set
   * @see #getValue()
   */
  void setValue(Value value);

  /**
   * Redefines the standard <code>{@link Object#clone()}</code> method
   * and obligates implementors to implement it. It is useful to avoid
   * sharing of the properties and implicit changes of their values.
   * @return a new instance of the appropriate <code>Property</code> class
   *         with all the data copied from this property to the new one
   * @throws CloneNotSupportedException if the operation cannot be executed by the implementing class
   *                                    due to any reasons
   */
  Property<Value> clone() throws CloneNotSupportedException;
}
