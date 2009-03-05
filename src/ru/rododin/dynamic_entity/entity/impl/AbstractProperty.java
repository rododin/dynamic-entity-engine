/*
 * AbstractProperty.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity.entity.impl;

import java.util.HashMap;
import java.util.Map;

import ru.rododin.dynamic_entity.entity.Property;
import ru.rododin.dynamic_entity.entity.PropertyDescriptor;

/**
 * Represents a default useful implementation of the
 * <code>{@link ru.rododin.dynamic_entity.entity.Property}</code> interface.
 * The class implements all the methods and defined as an abstract just because
 * it doesn't represent a concrete <code>{@link ru.rododin.dynamic_entity.entity.Property}</code>
 * and the <code>Value</code> generic parameter is still undefined here.
 * @param <Value> the value generic type
 * @author Rod Odin
 */
public abstract class AbstractProperty <Value>
  implements Property<Value>
{
// Constructing ------------------------------------------------------------------------------------

  /**
   * Creates a new <code>{@link ru.rododin.dynamic_entity.entity.Property}</code>
   * instance using the given
   * <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor descriptor}</code>.
   * <p/>
   * Please note, the <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor descriptor}</code>s
   * are saved in an internal map and if a descriptor is already in the map, it will be used
   * instead of the given one. Such behavior allows usage of the same descriptors for all the properties
   * based on the same <code>name</code>, <code>valueClass</code> and <code>defaultValue</code>.
   * @param descriptor the <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor}</code>
   * @throws NullPointerException if the given <code>descriptor</code> is <code>null</code>
   */
  @SuppressWarnings("unchecked")
  protected AbstractProperty(PropertyDescriptor<Value> descriptor)
    throws NullPointerException
  {
    if(descriptor == null)
      throw new NullPointerException("Property descriptor must not be null");
    PropertyDescriptor d = DESCRIPTOR_MAP.get(descriptor);
    if(d == null)
    {
      DESCRIPTOR_MAP.put(descriptor, descriptor);
      this.descriptor = descriptor;
    }
    else
      this.descriptor = d;
    this.value = this.descriptor.getDefaultValue();
  }

  /**
   * Creates a new <code>{@link ru.rododin.dynamic_entity.entity.Property}</code>
   * instance using the given <code>name</code>, <code>valueClass</code> and <code>defaultValue</code>.
   * @see #AbstractProperty(ru.rododin.dynamic_entity.entity.PropertyDescriptor)
   * @see ru.rododin.dynamic_entity.entity.PropertyDescriptor
   * @param name the property name
   * @param valueClass the property value class
   * @param defaultValue the property default value
   * @throws NullPointerException if the given <code>name</code> or <code>valueClass</code>
   *         is <code>null</code>
   */
  protected AbstractProperty(String name, Class<Value> valueClass, Value defaultValue)
    throws NullPointerException
  {
    this(new StandardPropertyDescriptor<Value>(name, valueClass, defaultValue));
  }

// Interface ---------------------------------------------------------------------------------------

  /**
   * Returns the property descriptor.
   * @return non-<code>null</code> <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor}</code>
   *         instance
   */
  public PropertyDescriptor<Value> getDescriptor()
  {
    return descriptor;
  }

  /**
   * Returns the current property value.
   * @return <code>null</code> or non-<code>null</code> property value
   * @see #setValue(Object)
   * @see PropertyDescriptor#getDefaultValue()
   */
  public Value getValue()
  {
    return value;
  }

  /**
   * Replaces the current value with the new given one.
   * @param value the new value to be set
   * @see #getValue()
   */
  public void setValue(Value value)
  {
    this.value = value;
  }

  /**
   * Overrides the standard <code>{@link Object#clone()}</code> method.
   * It provides a possibility to avoid properties sharing causing implicit changes of their values.
   * @return a new instance of the <code>AbstractProperty</code> class
   *         with all the data copied from this property to the new one
   * @throws CloneNotSupportedException never
   */
  @Override
  @SuppressWarnings({"unchecked", "CloneDoesntCallSuperClone"})
  public Property<Value> clone() throws CloneNotSupportedException
  {
    Property<Value> rv = new AbstractProperty(getDescriptor()){};
    rv.setValue(getValue());
    return rv;
  }

  /**
   * Overrides the standard <code>{@link Object#toString()}</code> method
   * to provide user-friendly textual output of a
   * <code>{@link ru.rododin.dynamic_entity.entity.Property}</code>.
   * @return non-<code>null</code> and non-empty string
   */
  @Override
  public String toString()
  {
    PropertyDescriptor<Value> d = getDescriptor();
    return d.getName() + " (" + d.getValueClass().getSimpleName() + "): " +
           getValue() + " (" + d.getDefaultValue() + ")";
  }

// Internal Logic ----------------------------------------------------------------------------------

  /**
   * The map avoids existence of 2 equal
   * <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor}</code>s.
   */
  private static final Map<PropertyDescriptor, PropertyDescriptor>
    DESCRIPTOR_MAP = new HashMap<PropertyDescriptor, PropertyDescriptor>();

  private PropertyDescriptor<Value> descriptor;
  private Value value;
}
