/*
 * Event.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Description.
 *
 * @author Rod Odin
 */
public abstract class Event
{
// Constructing ------------------------------------------------------------------------------------

  protected Event()
  {
    this(DEFAULT_ABORT_ACTION);
  }

  protected Event(boolean abortAction)
  {
    this.abortAction = abortAction;
  }

// Interface Constants -----------------------------------------------------------------------------

  public static final boolean DEFAULT_ABORT_ACTION = false;

// Interface Methods -------------------------------------------------------------------------------

  public boolean getAbortAction()
  {
    return abortAction;
  }

  public void setAbortAction(boolean abortAction)
  {
    this.abortAction = abortAction;
  }

// Internal Logic ----------------------------------------------------------------------------------

  private boolean abortAction;
}
