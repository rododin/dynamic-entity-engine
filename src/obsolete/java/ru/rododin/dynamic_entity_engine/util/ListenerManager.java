/*
 * ListenerManager.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Description.
 *
 * @author Rod Odin
 */
public abstract class ListenerManager <Listener>
{
// Interface Methods -------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public Listener[] getListeners()
  {
    Listener[] rv = null;
    Set<Listener> listenerSet = getListenerSet();
    if(listenerSet != null)
      rv = (Listener[])listenerSet.toArray();
    return rv;
  }

  public boolean setListener(Listener listener)
    throws NullPointerException
  {
    if(listener == null)
      throw new NullPointerException("Cannot set null listener");
    boolean rv = removeListener(listener);
    return addListener(listener) && rv;
  }

  public boolean addListener(Listener listener)
    throws NullPointerException
  {
    if(listener == null)
      throw new NullPointerException("Cannot add null listener");
    return initListenerSet().add(listener);
  }

  public boolean addListeners(Listener[] listeners)
    throws NullPointerException, IllegalArgumentException
  {
    if(listeners == null)
      throw new NullPointerException("Cannot add null listeners");
    if(listeners.length == 0)
      throw new IllegalArgumentException("Cannot add an empty array of listeners");
    return initListenerSet().addAll(Arrays.asList(listeners));
  }

  public boolean addListeners(Collection<Listener> listeners)
    throws NullPointerException, IllegalArgumentException
  {
    if(listeners == null)
      throw new NullPointerException("Cannot add null listeners");
    if(listeners.isEmpty())
      throw new IllegalArgumentException("Cannot add an empty collection of listeners");
    return initListenerSet().addAll(listeners);
  }

  public boolean removeListener(Listener listener)
    throws NullPointerException
  {
    boolean rv = false;
    if(listener == null)
      throw new NullPointerException("Cannot remove null listener");
    Set<Listener> listenerSet = getListenerSet();
    if(listenerSet != null)
      rv = listenerSet.remove(listener);
    return rv;
  }

  public boolean removeAllListeners()
  {
    Set<Listener> listenerSet = getListenerSet();
    boolean rv = listenerSet != null && !listenerSet.isEmpty();
    setListenerSet(null);
    return rv;
  }

// Protected Interface -----------------------------------------------------------------------------

  protected Set<Listener> getListenerSet()
  {
    return listenerSet;
  }

  protected void setListenerSet(Set<Listener> listenerSet)
  {
    this.listenerSet = listenerSet;
  }

  protected Set<Listener> initListenerSet()
  {
    if(listenerSet == null)
      listenerSet = new LinkedHashSet<Listener>();
    return listenerSet;
  }
// Internal Logic ----------------------------------------------------------------------------------

  private Set<Listener> listenerSet;
}
