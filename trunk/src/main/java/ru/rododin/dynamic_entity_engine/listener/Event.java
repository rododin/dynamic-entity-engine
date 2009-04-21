/*
 * Event.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.listener;

/**
 * Description.
 *
 * @author Rod Odin
 */
public interface Event <EventData>
{
  String getName();
  EventData getData();
  boolean isActionAborted();
  void setActionAborted(boolean actionAborted);
}

