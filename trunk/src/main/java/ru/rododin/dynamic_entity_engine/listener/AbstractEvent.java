/*
 * AbstractEvent.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public abstract class AbstractEvent <EventData>
  implements Event <EventData>
{
  protected AbstractEvent(EventData data, boolean actionAborted)
  {
    this.data = data;
    this.actionAborted = actionAborted;
  }

  public EventData getData()
  {
    return data;
  }

  public void setData(EventData data)
  {
    this.data = data;
  }

  public boolean isActionAborted()
  {
    return actionAborted;
  }

  public void setActionAborted(boolean actionAborted)
  {
    this.actionAborted = actionAborted;
  }

  private EventData data;
  private boolean actionAborted;
}

