/*
 * Unit.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.demo.unit;

import ru.rododin.dynamic_entity_engine.entity.Property;
import ru.rododin.dynamic_entity_engine.entity.impl.AbstractEntity;
import ru.rododin.dynamic_entity_engine.entity.impl.StandardEntityDescriptor;
import ru.rododin.dynamic_entity_engine.util.Collections;

/**
 * Extends the <code>{@link ru.rododin.dynamic_entity_engine.entity.Entity}</code> class to represent
 * a unit defined using a <code>{@link ru.rododin.dynamic_entity_engine.demo.unit.UnitDefinition}</code> constant.
 *
 * @author Rod Odin
 */
public class Unit
  extends AbstractEntity
{
  /**
   * Creates a unit defined by the given <code>{@link ru.rododin.dynamic_entity_engine.demo.unit.UnitDefinition}</code>.
   * @param unitDefinition the unit definitor
   */
  public Unit(UnitDefinition unitDefinition)
  {
    super(new StandardEntityDescriptor
      ( unitDefinition.name()
      , Collections.toOrderedSet(unitDefinition.getPropertyDescriptors())
      ));
  }

  /**
   * Returns a property defined by the given <code>{@link ru.rododin.dynamic_entity_engine.demo.unit.UnitPropertyDefinition}</code>.
   * @param unitPropertyDefinition the unit property definitor
   * @return a non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.entity.Property}</code> instance
   *         if there is a property defined by the given <code>unitPropertyDefinition</code>,
   *         or <code>null</code> otherwise
   */
  public Property getProperty(UnitPropertyDefinition unitPropertyDefinition)
  {
    return getProperty(unitPropertyDefinition.name());
  }
}
