/*
 * EntityEvent.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class EntityEvent
  extends Event
{
// Constructing ------------------------------------------------------------------------------------

  public EntityEvent(Entity entity)
    throws NullPointerException
  {
    this(entity, DEFAULT_ABORT_ACTION);
  }

  public EntityEvent(Entity entity, boolean abortAction)
    throws NullPointerException
  {
    this(entity, DEFAULT_ACCESSED_PROPERTY, abortAction);
  }

  public EntityEvent(Entity entity, Property accessedProperty)
    throws NullPointerException
  {
    this(entity, accessedProperty, DEFAULT_ABORT_ACTION);
  }

  public EntityEvent(Entity entity, Property accessedProperty, boolean abortAction)
    throws NullPointerException
  {
    super(abortAction);
    if(entity == null)
      throw new NullPointerException("Entity must not be null");
    this.entity = entity;
    this.accessedProperty = accessedProperty;
  }

// Interface Constants -----------------------------------------------------------------------------

  public static final Property DEFAULT_ACCESSED_PROPERTY = null;

// Interface Methods -------------------------------------------------------------------------------

  public Entity getEntity()
  {
    return entity;
  }

  public Property getAccessedProperty()
  {
    return accessedProperty;
  }

  public void setAccessedProperty(Property accessedProperty)
  {
    this.accessedProperty = accessedProperty;
  }

// Internal Logic ----------------------------------------------------------------------------------

  private Entity entity;
  private Property accessedProperty;
}

