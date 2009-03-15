/*
 * AbstractEntity.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ru.rododin.dynamic_entity_engine.entity.Entity;
import ru.rododin.dynamic_entity_engine.entity.EntityDescriptor;
import ru.rododin.dynamic_entity_engine.entity.EntityEvent;
import ru.rododin.dynamic_entity_engine.entity.EntityListener;
import ru.rododin.dynamic_entity_engine.entity.Property;
import ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor;

/**
 * Represents a default useful implementation of the
 * <code>{@link ru.rododin.dynamic_entity_engine.entity.Entity}</code> interface.
 * The class implements all the methods and defined as an abstract just because
 * it doesn't represent a concrete <code>{@link ru.rododin.dynamic_entity_engine.entity.Entity}</code>.
 *
 * @author Rod Odin
 */
public abstract class AbstractEntity
  extends EntityListenerManager
  implements Entity
{
// Constructing ------------------------------------------------------------------------------------

  /**
   * Creates an entity using the given <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>.
   * <p/>
   * Please note, the <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor descriptor}</code>s
   * are saved in an internal map and if a descriptor is already in the map, it will be used
   * instead of the given one. Such behavior allows usage of the same descriptors for all the entities
   * based on the same <code>name</code>, <code>propertyDescriptor</code>s set.
   * <p/>
   * Please note, the properties defined by the
   * <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor#getPropertyDescriptors()}</code>
   * will be created and included into the entity automatically.
   * @see #AbstractEntity(String, ru.rododin.dynamic_entity_engine.entity.Property[])
   * @see #AbstractEntity(String, java.util.List)
   * @param descriptor the <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>
   * @throws NullPointerException if the given <code>descriptor</code> is <code>null</code>
   */
  @SuppressWarnings("unchecked")
  protected AbstractEntity(EntityDescriptor descriptor)
    throws NullPointerException
  {
    if(descriptor == null)
      throw new NullPointerException("Entity descriptor must not be null");
    EntityDescriptor d = DESCRIPTOR_MAP.get(descriptor);
    if(d == null)
    {
      DESCRIPTOR_MAP.put(descriptor, descriptor);
      this.descriptor = descriptor;
    }
    else
      this.descriptor = d;
    Set<PropertyDescriptor> propertyDescriptors = this.descriptor.getPropertyDescriptors();
    if(propertyDescriptors != null && !propertyDescriptors.isEmpty())
    {
      propertyMap = new LinkedHashMap<String, Property>();
      for(PropertyDescriptor propertyDescriptor : propertyDescriptors)
        propertyMap.put(propertyDescriptor.getName(), new AbstractProperty(propertyDescriptor){});
    }
    EntityListener defaultListener = this.descriptor.getDefaultListener();
    if(defaultListener != null)
      addListener(defaultListener);
  }

  /**
   * Creates an entity using the given <code>name</code> and <code>properties</code> array.
   * This constructor doesn't use the
   * <code>{@link #AbstractEntity(ru.rododin.dynamic_entity_engine.entity.EntityDescriptor)}</code> one, but
   * an <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code> is still required to
   * create the entity, so it is created from the given <code>name</code> and
   * <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>s of the given
   * <code>properties</code>.
   * <p/>
   * Please note, the given <code>properties</code> will be cloned while including them into the entity.
   * @see #AbstractEntity(ru.rododin.dynamic_entity_engine.entity.EntityDescriptor)
   * @see #AbstractEntity(String, java.util.List)
   * @param name the name of the entity to be created
   * @param properties <code>null</code>, empty or non-empty array of the properties to be included into the
   *                   entity
   * @throws NullPointerException if the given <code>name</code> is <code>null</code>
   * @throws CloneNotSupportedException if there is at least one property in the given <code>properties</code>
   *                                    array which doesn't support the <code>{@link Object#clone()}</code> operation
   */
  protected AbstractEntity(String name, Property[] properties)
    throws NullPointerException, CloneNotSupportedException
  {
    if(properties != null && properties.length > 0)
    {
      propertyMap = new LinkedHashMap<String, Property>();
      Set<PropertyDescriptor> propertyDescriptors = new LinkedHashSet<PropertyDescriptor>();
      for(Property property : properties)
      {
        PropertyDescriptor propertyDescriptor = property.getDescriptor();
        propertyMap.put(propertyDescriptor.getName(), property.clone());
        propertyDescriptors.add(propertyDescriptor);
      }
      descriptor = new StandardEntityDescriptor(name, propertyDescriptors);
      EntityDescriptor d = DESCRIPTOR_MAP.get(descriptor);
      if(d == null)
        DESCRIPTOR_MAP.put(descriptor, descriptor);
      else
        this.descriptor = d;
    }
    EntityListener defaultListener = this.descriptor.getDefaultListener();
    if(defaultListener != null)
      addListener(defaultListener);
  }

  /**
   * Creates an entity using the given <code>name</code> and <code>properties</code> list.
   * It is just a <code>{@link java.util.List}</code> version of the
   * <code>{@link #AbstractEntity(String, ru.rododin.dynamic_entity_engine.entity.Property[])}</code> constructor.
   * @see #AbstractEntity(ru.rododin.dynamic_entity_engine.entity.EntityDescriptor)
   * @see #AbstractEntity(String, ru.rododin.dynamic_entity_engine.entity.Property[])
   * @param name the name of the entity to be created
   * @param properties <code>null</code>, empty or non-empty list of the properties to be included into the
   *                   entity
   * @throws NullPointerException if the given <code>name</code> is <code>null</code>
   * @throws CloneNotSupportedException if there is at least one property in the given <code>properties</code>
   *                                    list which doesn't support the <code>{@link Object#clone()}</code> operation
   */
  protected AbstractEntity(String name, List<Property> properties)
    throws NullPointerException, CloneNotSupportedException
  {
    this(name, (Property[])properties.toArray());
  }

// Interface ---------------------------------------------------------------------------------------

  /**
   * Returns the entity descriptor.
   * @return non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>
   *         instance
   */
  public EntityDescriptor getDescriptor()
  {
    return descriptor;
  }

  /**
   * Returns the property unique defined by the given <code>propertyName</code>.
   * @param propertyName unique (for this entity) string identifier for the property to be found
   * @return a non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.entity.Property}</code>
   *         instance if the property has been found, or <code>null</code> otherwise
   * @see #getPropertyIterator()
   */
  public Property getProperty(String propertyName)
  {
    Property rv = propertyMap != null ? propertyMap.get(propertyName) : null;
    if(getListenerSet() != null)
      entityAccessed(new EntityEvent(this, rv));
    return rv;
  }

  /**
   * Returns an <code>{@link java.util.Iterator iterator}</code> over the set of all properties
   *         in the order the properties have been added to the entity.
   * @return a non-<code>null</code> <code>{@link java.util.Iterator}</code> instance,
   *         or <code>null</code> if no properties have been added
   * @see #getProperty(String)
   */
  public Iterator<Property> getPropertyIterator()
  {
    Iterator<Property> rv = propertyMap != null ? propertyMap.values().iterator() : null;
    if(getListenerSet() != null)
      entityAccessed(new EntityEvent(this));
    return rv;
  }

  /**
   * Overrides the standard <code>{@link Object#toString()}</code> method
   * to provide user-friendly textual output of an
   * <code>{@link ru.rododin.dynamic_entity_engine.entity.Entity}</code>.
   * @return non-<code>null</code> and non-empty string
   */
  @Override
  public String toString()
  {
    String rv = getDescriptor().getName() + " {\n";
    if(propertyMap.values() != null && !propertyMap.values().isEmpty())
      for(Property property : propertyMap.values())
        rv += "  " + property.toString() + "\n";
    rv += "}";
    return rv;
  }

// Internal Logic ----------------------------------------------------------------------------------

  /**
   * The map avoids existence of 2 equal
   * <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>s.
   */
  private static final Map<EntityDescriptor, EntityDescriptor>
    DESCRIPTOR_MAP = new HashMap<EntityDescriptor, EntityDescriptor>();

  private EntityDescriptor      descriptor;
  private Map<String, Property> propertyMap;
}
