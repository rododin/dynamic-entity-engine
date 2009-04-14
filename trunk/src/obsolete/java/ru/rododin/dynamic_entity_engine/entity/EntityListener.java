/*
 * EntityListener.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Description.
 *
 * @author Rod Odin
 */
public interface EntityListener
{
  void entityAccessed(EntityEvent event);
  void entityCreated(EntityEvent event);
  void entityLoaded(EntityEvent event);
  void entitySaving(EntityEvent event);
  void entitySaved(EntityEvent event);
  void entityRemoving(EntityEvent event);
  void entityRemoved(EntityEvent event);
}

