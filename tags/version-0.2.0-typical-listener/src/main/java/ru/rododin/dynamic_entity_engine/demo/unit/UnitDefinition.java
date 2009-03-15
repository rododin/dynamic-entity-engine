/*
 * UnitDefinition.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.demo.unit;

import ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor;
import ru.rododin.dynamic_entity_engine.entity.impl.StandardPropertyDescriptor;

/**
 * Introduces definitions for all the units supported by the system.
 * Each unit may be defined by a set of unique and non-unique properties.
 * Some units may contain the same-named properties, but with different default values.
 * <p/>
 * Please note, the currently defined units are just an example.
 * Please don't take care of any game-play balance here.
 * @author Rod Odin
 */
public enum UnitDefinition
{
  /**
   * The weaker melee unit with a club.
   */
  Warrior (new PropertyDescriptor[]
  {
    createPropertyDescriptor(UnitPropertyDefinition.Health       ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.Strength     ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.MeleeAtack   ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.MeleeDefence ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.RangedDefence,    10),
    createPropertyDescriptor(UnitPropertyDefinition.Experience   ,     0),
    createPropertyDescriptor(UnitPropertyDefinition.DoubleAtack  , false),
    createPropertyDescriptor(UnitPropertyDefinition.Price        , 10.00),
  }),
  /**
   * The weaker ranged-atack unit with a bow.
   */
  Archer (new PropertyDescriptor[]
  {
    createPropertyDescriptor(UnitPropertyDefinition.Health       ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.Strength     ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.MeleeDefence ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.RangedAtack  ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.RangedDefence,    10),
    createPropertyDescriptor(UnitPropertyDefinition.Shots        ,    10),
    createPropertyDescriptor(UnitPropertyDefinition.Experience   ,     0),
    createPropertyDescriptor(UnitPropertyDefinition.DoubleAtack  , false),
    createPropertyDescriptor(UnitPropertyDefinition.Price        , 12.50),
  }),
  /**
   * A second-level melee unit with a sword.
   */
  Swordsman (new PropertyDescriptor[]
  {
    createPropertyDescriptor(UnitPropertyDefinition.Health       ,    20),
    createPropertyDescriptor(UnitPropertyDefinition.Strength     ,    20),
    createPropertyDescriptor(UnitPropertyDefinition.MeleeAtack   ,    20),
    createPropertyDescriptor(UnitPropertyDefinition.MeleeDefence ,    20),
    createPropertyDescriptor(UnitPropertyDefinition.RangedDefence,    15),
    createPropertyDescriptor(UnitPropertyDefinition.Experience   ,     0),
    createPropertyDescriptor(UnitPropertyDefinition.DoubleAtack  ,  true),
    createPropertyDescriptor(UnitPropertyDefinition.Price        , 20.00),
  }),
  /**
   * A second-level archer unit with a long bow.
   */
  LongArcher (new PropertyDescriptor[]
  {
    createPropertyDescriptor(UnitPropertyDefinition.Health       ,    15),
    createPropertyDescriptor(UnitPropertyDefinition.Strength     ,    15),
    createPropertyDescriptor(UnitPropertyDefinition.MeleeDefence ,    15),
    createPropertyDescriptor(UnitPropertyDefinition.RangedAtack  ,    25),
    createPropertyDescriptor(UnitPropertyDefinition.RangedDefence,    15),
    createPropertyDescriptor(UnitPropertyDefinition.Shots        ,    15),
    createPropertyDescriptor(UnitPropertyDefinition.Experience   ,     0),
    createPropertyDescriptor(UnitPropertyDefinition.DoubleAtack  ,  true),
    createPropertyDescriptor(UnitPropertyDefinition.Price        , 22.75),
  });

  private UnitDefinition(PropertyDescriptor[] propertyDescriptors)
  {
    this.propertyDescriptors = propertyDescriptors;
  }

  /**
   * Returns the <code>{@link ru.rododin.dynamic_entity_engine.entity.PropertyDescriptor}</code>s describing
   * this unit definition.
   * @return non-<code>null</code> and non-empty array of property descriptors
   */
  public PropertyDescriptor[] getPropertyDescriptors()
  {
    return propertyDescriptors;
  }

  /**
   * Please use this method to create a new <code>{@link ru.rododin.dynamic_entity_engine.demo.unit.Unit}</code>
   * instance specified by this unit definition.
   * @return a non-<code>null</code> <code>{@link ru.rododin.dynamic_entity_engine.demo.unit.Unit}</code> instance
   */
  public Unit create()
  {
    return new Unit(this);
  }

  private PropertyDescriptor[] propertyDescriptors;

  @SuppressWarnings("unchecked")
  private static <Value> PropertyDescriptor<Value>
  createPropertyDescriptor
    ( UnitPropertyDefinition unitPropertyDefinition
    , Value                  defaultValue
    )
  {
    return
      new StandardPropertyDescriptor<Value>
        ( unitPropertyDefinition.name()
        , unitPropertyDefinition.getPropertyValueClass()
        , defaultValue
        )
      {
      };
  }
}
