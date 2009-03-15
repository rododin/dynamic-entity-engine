/*
 * EntityDescriptor.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

import java.util.Set;

/**
 * Represents a definitor for an <code>{@link Entity}</code>.
 * The definitor introduces the entity <code>{@link #getName() name}</code>
 * and describes the entity <code>{@link #getPropertyDescriptors() properties}</code>.
 *
 * @author Rod Odin
 */
public interface EntityDescriptor
{
  /**
   * Returns a textual name of the entity.
   * @return non-<code>null</code>, empty or non-empty string
   */
  String getName();

  /**
   * Returns a set of <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>s
   * describing the entity properties.
   * @return <code>null</code>, empty or non-empty ordered set of the property descriptors in the order
   *         the properties (or descriptors) have been registered for the entity
   */
  Set<PropertyDescriptor> getPropertyDescriptors();

  EntityListener getDefaultListener();
}
