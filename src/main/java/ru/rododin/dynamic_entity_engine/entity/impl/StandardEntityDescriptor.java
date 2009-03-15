/*
 * StandardEntityDescriptor.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.impl;

import java.util.Set;

import ru.rododin.dynamic_entity_engine.entity.EntityDescriptor;
import ru.rododin.dynamic_entity_engine.entity.EntityListener;
import ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor;

/**
 * A standard (default) useful implementation of the
 * <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code> interface.
 * @author Rod Odin
 */
public class StandardEntityDescriptor
  implements EntityDescriptor
{
  /**
   * Creates a new <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>
   * with the given parameters.
   * @param name the name of the entity
   * @param propertyDescriptors the set of the
   *                            <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>s
   *                            introducing the entity properties
   * @throws NullPointerException if the given name is <code>null</code>
   */
  public StandardEntityDescriptor
    ( String                  name
    , Set<PropertyDescriptor> propertyDescriptors
    )
    throws NullPointerException
  {
    this(name, propertyDescriptors, null);
  }

  /**
   * Creates a new <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>
   * with the given parameters.
   * @param name the name of the entity
   * @param propertyDescriptors the set of the
   *                            <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>s
   *                            introducing the entity properties
   * @param defaultListener the default entity listener to be used with all entities based on this descriptor
   * @throws NullPointerException if the given name is <code>null</code>
   */
  public StandardEntityDescriptor
    ( String                  name
    , Set<PropertyDescriptor> propertyDescriptors
    , EntityListener          defaultListener
    )
    throws NullPointerException
  {
    if(name == null)
      throw new NullPointerException("Entity name must not be null");
    this.name = name;
    this.propertyDescriptors = propertyDescriptors;
    this.defaultListener = defaultListener;
  }

  /**
   * Returns a textual name of the entity.
   * @return non-<code>null</code>, empty or non-empty string
   */
  public String getName()
  {
    return name;
  }

  /**
   * Returns a set of <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>s
   * describing the entity properties.
   * @return <code>null</code>, empty or non-empty ordered set of the property descriptors in the order
   *         the properties (or descriptors) have been registered for the entity
   */
  public Set<PropertyDescriptor> getPropertyDescriptors()
  {
    return propertyDescriptors;
  }

  public EntityListener getDefaultListener()
  {
    return defaultListener;
  }

  /**
   * Auto-generated overriding implementation of the <code>{@link Object#equals(Object)}</code> method.
   * It accounts all the fields: <code>name</code>, <code>propertyDescriptors</code> and
   * <code>defaultListener</code>.
   * @param o the object to be compared with
   * @return <code>true</code> if the objects have the same
   *         <code>name</code> and <code>propertyDescriptors</code>;
   *         <code>false</code> otherwise
   * @see #hashCode()
   */
  @Override
  public boolean equals(Object o)
  {
    if(this == o) return true;
    if(o == null || getClass() != o.getClass()) return false;
    StandardEntityDescriptor that = (StandardEntityDescriptor) o;
    return !(defaultListener != null ? !defaultListener.equals(that.defaultListener) : that.defaultListener != null) &&
           name.equals(that.name) &&
           !(propertyDescriptors != null ? !propertyDescriptors.equals(that.propertyDescriptors)
                                         : that.propertyDescriptors != null);
  }

  /**
   * Auto-generated overriding implementation of the <code>{@link Object#hashCode()}</code> method.
   * It accounts all the fields: <code>name</code>, <code>propertyDescriptors</code> and
   * <code>defaultListener</code>.
   * @return the hash-code computed according to the Java Language Specification
   * @see #equals(Object)
   */
  @Override
  public int hashCode()
  {
    int result = name.hashCode();
    result = 31 * result + (propertyDescriptors != null ? propertyDescriptors.hashCode() : 0);
    result = 31 * result + (defaultListener != null ? defaultListener.hashCode() : 0);
    return result;
  }

  private String name;
  private Set<PropertyDescriptor> propertyDescriptors;
  private EntityListener defaultListener;
}

