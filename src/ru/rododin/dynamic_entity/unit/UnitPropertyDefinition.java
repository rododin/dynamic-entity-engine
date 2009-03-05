/*
 * UnitPropertyDefinition.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity.unit;

/**
 * Introduces a set of <code>{@link ru.rododin.dynamic_entity.entity.Property}</code> definitions to be used
 * together with <code>{@link ru.rododin.dynamic_entity.unit.Unit}</code>s and
 * <code>{@link ru.rododin.dynamic_entity.unit.UnitDefinition}</code>s.
 * <p/>
 * Each <code>UnitPropertyDefinition</code> defines the property name by its own enum constant name
 * and defines the property
 * <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor#getValueClass() value class}</code>
 * <p/>
 * Please note, it doesn't define the property
 * <code>{@link ru.rododin.dynamic_entity.entity.PropertyDescriptor#getDefaultValue() default value}</code>.
 * <p/>
 * Please note, it is just an example. Please don't think whether the enumeration lists all the
 * possible unit properties or not. 
 *
 * @author Rod Odin
 */
public enum UnitPropertyDefinition
{
  Health       (Integer.class),
  Strength     (Integer.class),
  MeleeAtack   (Integer.class),
  MeleeDefence (Integer.class),
  RangedAtack  (Integer.class),
  RangedDefence(Integer.class),
  Shots        (Integer.class),
  DoubleAtack  (Boolean.class),
  Experience   (Long   .class),
  Price        (Float  .class);

  private UnitPropertyDefinition(Class propertyValueClass)
  {
    this.propertyValueClass = propertyValueClass;
  }

  /**
   * Returns the <code>{@link Class}</code> object representing the unit property value.
   * @see ru.rododin.dynamic_entity.entity.PropertyDescriptor#getValueClass()
   * @return non-<code>null</code> <code>{@link Class}</code> instance
   */
  public Class getPropertyValueClass()
  {
    return propertyValueClass;
  }

  private Class propertyValueClass;
}
