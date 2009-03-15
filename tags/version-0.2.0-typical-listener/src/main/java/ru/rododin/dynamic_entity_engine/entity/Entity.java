/*
 * Entity.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

import java.util.Iterator;

/**
 * Represents an abstract entity defined by its
 * <code>{@link #getDescriptor() descriptor}</code>.
 * Any <code>Entity</code> contains 0 or more named
 * <code>{@link ru.rododin.dynamic_entity_engine.entity.Property properties}</code>.
 *
 * @author Rod Odin
 */
public interface Entity
{
  /**
   * Returns the entity descriptor.
   * @return non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.entity.EntityDescriptor}</code>
   *         instance
   */
  EntityDescriptor getDescriptor();

  /**
   * Returns the property unique defined by the given <code>propertyName</code>.
   * @param propertyName unique (for this entity) string identifier for the property to be found
   * @return a non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.entity.Property}</code>
   *         instance if the property has been found, or <code>null</code> otherwise
   * @see #getPropertyIterator()
   */
  Property getProperty(String propertyName);

  /**
   * Returns an <code>{@link java.util.Iterator iterator}</code> over the set of all properties
   *         in the order the properties have been added to the entity.
   * @return a non-<code>null</code> <code>{@link java.util.Iterator}</code> instance,
   *         or <code>null</code> if no properties have been added
   * @see #getProperty(String)
   */
  Iterator<Property> getPropertyIterator();
}
