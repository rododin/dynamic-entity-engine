/*
 * ListenerException.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public class ListenerException
  extends RuntimeException
{
  public ListenerException()
  {
  }

  public ListenerException(String message)
  {
    super(message);
  }

  public ListenerException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public ListenerException(Throwable cause)
  {
    super(cause);
  }
}

