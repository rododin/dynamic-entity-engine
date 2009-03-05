/*
 * StandardPropertyDescriptor.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity.entity.impl;

import ru.rododin.dynamic_entity.entity.PropertyDescriptor;

/**
 * A standard (default) useful implementation of the
 * <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor}</code> interface.
 * @param <Value> the property value generic type
 * @author Rod Odin
 */
public class StandardPropertyDescriptor<Value>
  implements PropertyDescriptor<Value>
{
  /**
   * Creates a new <code>PropertyDescriptor</code> with the given parameters.
   * @param name the name of the property
   * @param valueClass the <code>{@link Class}</code> object representing the <code>Value</code> generic parameter
   * @param defaultValue the property default value
   * @throws NullPointerException if <code>name</code> or <code>valueClass</code> is <code>null</code>
   */
  public StandardPropertyDescriptor(String name, Class<Value> valueClass, Value defaultValue)
    throws NullPointerException
  {
    if(name == null)
      throw new NullPointerException("Property name must not be null");
    if(valueClass == null)
      throw new NullPointerException("Property value class must not be null");
    this.name         = name;
    this.valueClass   = valueClass;
    this.defaultValue = defaultValue;
  }

  /**
   * Returns the property name.
   * @return non-<code>null</code> textual property identifier
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns the <code>{@link Class}</code> object representing the <code>Value</code>
   * generic parameter.
   * @return non-<code>null</code> <code>{@link Class}</code> instance
   */
  public Class<Value> getValueClass()
  {
    return valueClass;
  }

  /**
   * Returns the property default value.
   * @return <code>null</code> or non-<code>null</code> <code>Value</code> instance
   */
  public Value getDefaultValue()
  {
    return defaultValue;
  }

  /**
   * Auto-generated overriding implementation of the <code>{@link Object#equals(Object)}</code> method.
   * It accounts all the fields: <code>name</code>, <code>valueClass</code> and <code>defaultValue</code>.
   * @param o the object to be compared with
   * @return <code>true</code> if the objects have the same
   *         <code>name</code>, <code>valueClass</code> and <code>defaultValue</code>;
   *         <code>false</code> otherwise
   * @see #hashCode()
   */
  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    StandardPropertyDescriptor that = (StandardPropertyDescriptor) o;
    return !(defaultValue != null ? !defaultValue.equals(that.defaultValue) : that.defaultValue != null) &&
           name.equals(that.name) && valueClass.equals(that.valueClass);
  }

  /**
   * Auto-generated overriding implementation of the <code>{@link Object#hashCode()}</code> method.
   * It accounts all the fields: <code>name</code>, <code>valueClass</code> and <code>defaultValue</code>.
   * @return the hash-code computed according to the Java Language Specification
   * @see #equals(Object)
   */
  @Override
  public int hashCode()
  {
    int result = name.hashCode();
    result = 31 * result + valueClass.hashCode();
    result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
    return result;
  }

  private String       name;
  private Class<Value> valueClass;
  private Value        defaultValue;
}
