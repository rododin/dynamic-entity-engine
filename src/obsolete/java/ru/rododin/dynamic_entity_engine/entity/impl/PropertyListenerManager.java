/*
 * PropertyListenerManager.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity.impl;

import java.util.Set;

import ru.rododin.dynamic_entity_engine.entity.PropertyEvent;
import ru.rododin.dynamic_entity_engine.entity.PropertyListener;
import ru.rododin.dynamic_entity_engine.util.ListenerManager;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class PropertyListenerManager<Value>
  extends ListenerManager<PropertyListener<Value>>
  implements PropertyListener<Value>
{
  public void propertyAccessed(PropertyEvent<Value> event)
  {
    Set<PropertyListener<Value>> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(PropertyListener<Value> propertyListener : listenerSet)
      {
        propertyListener.propertyAccessed(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void propertyChanging(PropertyEvent<Value> event)
  {
    Set<PropertyListener<Value>> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(PropertyListener<Value> propertyListener : listenerSet)
      {
        propertyListener.propertyChanging(event);
        if(event.getAbortAction())
          break;
      }
    }
  }

  public void propertyChanged(PropertyEvent<Value> event)
  {
    Set<PropertyListener<Value>> listenerSet = getListenerSet();
    if(listenerSet != null && !listenerSet.isEmpty())
    {
      for(PropertyListener<Value> propertyListener : listenerSet)
      {
        propertyListener.propertyChanged(event);
        if(event.getAbortAction())
          break;
      }
    }
  }
}
