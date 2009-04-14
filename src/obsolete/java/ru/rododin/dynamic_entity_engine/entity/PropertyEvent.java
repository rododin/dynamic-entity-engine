/*
 * PropertyEvent.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.entity;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class PropertyEvent <Value>
  extends Event
{
// Constructing ------------------------------------------------------------------------------------

  public PropertyEvent(Property<Value> property)
    throws NullPointerException
  {
    this(property, DEFAULT_ABORT_ACTION);
  }

  @SuppressWarnings("unchecked")
  public PropertyEvent(Property<Value> property, boolean abortAction)
    throws NullPointerException
  {
    this(property, (Value)DEFAULT_NEW_VALUE, abortAction);
  }

  public PropertyEvent(Property<Value> property, Value newValye)
    throws NullPointerException
  {
    this(property, newValye, DEFAULT_ABORT_ACTION);
  }

  public PropertyEvent(Property<Value> property, Value newValye, boolean abortAction)
    throws NullPointerException
  {
    super(abortAction);
    if(property == null)
      throw new NullPointerException("Property must not be null");
    this.property = property;
    this.newValye = newValye;
  }

// Interface Constants -----------------------------------------------------------------------------

  public static final Object DEFAULT_NEW_VALUE = null;

// Interface Methods -------------------------------------------------------------------------------

  public Property<Value> getProperty()
  {
    return property;
  }

  public Value getNewValye()
  {
    return newValye;
  }

  public void setNewValye(Value newValye)
  {
    this.newValye = newValye;
  }

// Internal Logic ----------------------------------------------------------------------------------

  private Property<Value> property;
  private Value newValye;
}
