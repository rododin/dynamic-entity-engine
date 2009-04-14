/*
 * EntityListenerManager.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.impl;

import java.util.Set;

import ru.rododin.dynamic_entity_engine.entity.EntityEvent;
import ru.rododin.dynamic_entity_engine.entity.EntityListener;
import ru.rododin.dynamic_entity_engine.util.ListenerManager;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class EntityListenerManager
  extends ListenerManager<EntityListener>
  implements EntityListener
{
  public void entityAccessed(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entityAccessed(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void entityCreated(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entityCreated(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void entityLoaded(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entityLoaded(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void entitySaving(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entitySaving(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void entitySaved(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entitySaved(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void entityRemoving(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entityRemoving(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void entityRemoved(EntityEvent event)
  {
    Set<EntityListener> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(EntityListener entityListener : listenerSet)
      {
        entityListener.entityRemoved(event);
        if(event.getAbortAction())
          break;
      }
    }
  }
}

