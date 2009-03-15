/*
 * StandardPropertyDescriptor.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.impl;

import ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor;
import ru.rododin.dynamic_entity_engine.entity.PropertyListener;

/**
 * A standard (default) useful implementation of the
 * <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code> interface.
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
  public StandardPropertyDescriptor
    ( String                  name
    , Class<Value>            valueClass
    , Value                   defaultValue
    )
    throws NullPointerException
  {
    this(name, valueClass, defaultValue, null);
  }

  /**
   * Creates a new <code>PropertyDescriptor</code> with the given parameters.
   * @param name the name of the property
   * @param valueClass the <code>{@link Class}</code> object representing the <code>Value</code> generic parameter
   * @param defaultValue the property default value
   * @param defaultListener the default property listener to be associated with all properties based on this descriptor
   * @throws NullPointerException if <code>name</code> or <code>valueClass</code> is <code>null</code>
   */
  public StandardPropertyDescriptor
    ( String                  name
    , Class<Value>            valueClass
    , Value                   defaultValue
    , PropertyListener<Value> defaultListener
    )
    throws NullPointerException
  {
    if(name == null)
      throw new NullPointerException("Property name must not be null");
    if(valueClass == null)
      throw new NullPointerException("Property value class must not be null");
    this.name         = name;
    this.valueClass   = valueClass;
    this.defaultValue = defaultValue;
    this.defaultListener = defaultListener;
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

  public PropertyListener<Value> getDefaultListener()
  {
    return defaultListener;
  }

  /**
   * Auto-generated overriding implementation of the <code>{@link Object#equals(Object)}</code> method.
   * It accounts all the fields: <code>name</code>, <code>valueClass</code>, <code>defaultValue</code> and
   * <code>defaultListener</code>.
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
    return !(defaultListener != null ? !defaultListener.equals(that.defaultListener) : that.defaultListener != null) &&
           !(defaultValue != null ? !defaultValue.equals(that.defaultValue) : that.defaultValue != null) &&
           name.equals(that.name) && valueClass.equals(that.valueClass);
  }

  /**
   * Auto-generated overriding implementation of the <code>{@link Object#hashCode()}</code> method.
   * It accounts all the fields: <code>name</code>, <code>valueClass</code>, <code>defaultValue</code> and
   * <code>defaultListener</code>.
   * @return the hash-code computed according to the Java Language Specification
   * @see #equals(Object)
   */
  @Override
  public int hashCode()
  {
    int result = name.hashCode();
    result = 31 * result + valueClass.hashCode();
    result = 31 * result + (defaultValue != null ? defaultValue.hashCode() : 0);
    result = 31 * result + (defaultListener != null ? defaultListener.hashCode() : 0);
    return result;
  }

  private String       name;
  private Class<Value> valueClass;
  private Value        defaultValue;
  private PropertyListener<Value> defaultListener;
}
