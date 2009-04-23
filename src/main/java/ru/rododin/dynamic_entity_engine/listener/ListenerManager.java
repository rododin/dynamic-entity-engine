/*
 * ListenerManager.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class ListenerManager
{
// Constructors ------------------------------------------------------------------------------------

// Interface ---------------------------------------------------------------------------------------

  public boolean addListenerForEvent(Class<? extends Event> eventClass, Listener<Event> listener)
    throws NullPointerException
  {
    if(eventClass == null)
      throw new NullPointerException(ERR_MSG_EVENT_CLASS_IS_NULL);
    if(listener == null)
      throw new NullPointerException(ERR_MSG_LISTENER_IS_NULL);
    return checkListenerSet(eventClass).add(listener);
  }

  public boolean addListenersForEvent(Class<? extends Event> eventClass, Listener<Event>[] listeners)
    throws NullPointerException
  {
    if(eventClass == null)
      throw new NullPointerException(ERR_MSG_EVENT_CLASS_IS_NULL);
    if(listeners == null)
      throw new NullPointerException(ERR_MSG_LISTENERS_IS_NULL);
    boolean rv = false;
    for(Listener<Event> listener : listeners)
    {
      if(listener == null)
        throw new NullPointerException(ERR_MSG_LISTENER_IS_NULL);
      rv |= checkListenerSet(eventClass).add(listener);
    }
    return rv;
  }

  public boolean addListenersForEvent(Class<? extends Event> eventClass, Collection<Listener<Event>> listeners)
  {
    if(eventClass == null)
      throw new NullPointerException(ERR_MSG_EVENT_CLASS_IS_NULL);
    if(listeners == null)
      throw new NullPointerException(ERR_MSG_LISTENERS_IS_NULL);
    boolean rv = false;
    for(Listener<Event> listener : listeners)
    {
      if(listener == null)
        throw new NullPointerException(ERR_MSG_LISTENER_IS_NULL);
      rv |= checkListenerSet(eventClass).add(listener);
    }
    return rv;
  }

  public void eventOccured(Event event)
    throws ListenerException
  {
    if(event == null)
      throw new NullPointerException(ERR_MSG_EVENT_IS_NULL);
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = getListenerMap();
    if(listenerMap != null)
    {
      Set<Listener<Event>> targetListenerSet = listenerMap.get(event.getClass());
      if(targetListenerSet != null)
      {
        for(Listener<Event> listener : targetListenerSet)
          listener.eventOccured(event);
      }
    }
  }

  public List<Listener<Event>> getAllListeners()
  {
    List<Listener<Event>> rv = null;
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = getListenerMap();
    if(listenerMap != null && !listenerMap.isEmpty())
    {
      rv = new ArrayList<Listener<Event>>();
      Collection<Set<Listener<Event>>> listenerSets = listenerMap.values();
      for(Set<Listener<Event>> listenerSet : listenerSets)
      {
        for(Listener<Event> listener : listenerSet)
          rv.add(listener);
      }
      if(rv.isEmpty())
        rv = null;
    }
    return rv;
  }

  public Set<Listener<Event>> getListenersForEvent(Class<? extends Event> eventClass)
  {
    if(eventClass == null)
      throw new NullPointerException(ERR_MSG_EVENT_CLASS_IS_NULL);
    Set<Listener<Event>> rv = null;
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = getListenerMap();
    if(listenerMap != null)
      rv = listenerMap.get(eventClass);
    return rv;
  }

  public boolean removeAllListeners()
  {
    boolean rv = false;
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = getListenerMap();
    if(listenerMap != null && !listenerMap.isEmpty())
      rv = true;
    setListenerMap(null);
    return rv;
  }

  public boolean removeListener(Listener<Event> listener)
  {
    if(listener == null)
      throw new NullPointerException(ERR_MSG_LISTENER_IS_NULL);
    boolean rv = false;
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = getListenerMap();
    if(listenerMap != null && !listenerMap.isEmpty())
    {
      Collection<Set<Listener<Event>>> listenerSets = listenerMap.values();
      for(Set<Listener<Event>> listenerSet : listenerSets)
      {
        rv |= listenerSet.remove(listener);
      }
    }
    return rv;
  }

  public boolean removeListenersForEvent(Class<? extends Event> eventClass)
  {
    if(eventClass == null)
      throw new NullPointerException(ERR_MSG_EVENT_CLASS_IS_NULL);
    boolean rv = false;
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = getListenerMap();
    if(listenerMap != null)
      rv = listenerMap.remove(eventClass) != null;
    return rv;
  }

// Protected Interface -----------------------------------------------------------------------------

  protected Map<Class<? extends Event>, Set<Listener<Event>>> checkListenerMap()
  {
    return (listenerMap == null)
           ? listenerMap = new HashMap<Class<? extends Event>, Set<Listener<Event>>>()
           : listenerMap;
  }

  protected Set<Listener<Event>> checkListenerSet(Class<? extends Event> eventClass)
  {
    Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap = checkListenerMap();
    Set<Listener<Event>> rv = listenerMap.get(eventClass);
    if(rv == null)
    {
      rv = new HashSet<Listener<Event>>();
      listenerMap.put(eventClass, rv);
    }
    return rv;
  }

  protected Map<Class<? extends Event>, Set<Listener<Event>>> getListenerMap()
  {
    return listenerMap;
  }

  protected void setListenerMap(Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap)
  {
    this.listenerMap = listenerMap;
  }

// Internal Logic ----------------------------------------------------------------------------------

  @SuppressWarnings("HardCodedStringLiteral")
  private static final String ERR_MSG_EVENT_CLASS_IS_NULL = "Null isn't acceptable for eventClass";
  @SuppressWarnings("HardCodedStringLiteral")
  private static final String ERR_MSG_EVENT_IS_NULL = "Null isn't acceptable for event";
  @SuppressWarnings("HardCodedStringLiteral")
  private static final String ERR_MSG_LISTENER_IS_NULL = "Null isn't acceptable for listener";
  @SuppressWarnings("HardCodedStringLiteral")
  private static final String ERR_MSG_LISTENERS_IS_NULL = "Null isn't acceptable for listeners array/collection";

  private Map<Class<? extends Event>, Set<Listener<Event>>> listenerMap;
}
