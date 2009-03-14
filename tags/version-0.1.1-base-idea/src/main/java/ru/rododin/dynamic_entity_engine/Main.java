/*
 * Main.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine;

import ru.rododin.dynamic_entity_engine.demo.unit.Unit;
import ru.rododin.dynamic_entity_engine.demo.unit.UnitDefinition;
import ru.rododin.dynamic_entity_engine.demo.unit.UnitPropertyDefinition;

/**
 * Description.
 *
 * @author Rod Odin
 */
@SuppressWarnings({"HardCodedStringLiteral", "unchecked"})
public class Main
{
  public static void main(String[] args)
  {
    System.out.println("Dynamic Entity. Version 1.0");
    Unit warrior1 = UnitDefinition.Warrior.create();
    Unit warrior2 = UnitDefinition.Warrior.create();
    Unit archer1 = UnitDefinition.Archer.create();
    Unit archer2 = UnitDefinition.Archer.create();
    Unit swordsman1 = UnitDefinition.Swordsman.create();
    Unit swordsman2 = UnitDefinition.Swordsman.create();
    Unit longArcher1 = UnitDefinition.LongArcher.create();
    Unit longArcher2 = UnitDefinition.LongArcher.create();

    System.out.println(warrior1.toString());
    System.out.println(warrior2.toString());
    System.out.println(archer1.toString());
    System.out.println(archer2.toString());
    System.out.println(swordsman1.toString());
    System.out.println(swordsman2.toString());
    System.out.println(longArcher1.toString());
    System.out.println(longArcher2.toString());
    System.out.println("----------");

    warrior1.getProperty(UnitPropertyDefinition.Health).setValue(22);
    warrior2.getProperty(UnitPropertyDefinition.Health).setValue(33);
    warrior1.getProperty(UnitPropertyDefinition.Strength).setValue(1122);
    warrior2.getProperty(UnitPropertyDefinition.Strength).setValue(2233);
    archer1.getProperty(UnitPropertyDefinition.Experience).setValue(222222);
    archer2.getProperty(UnitPropertyDefinition.Experience).setValue(111111);
    archer1.getProperty(UnitPropertyDefinition.RangedAtack).setValue(7777);
    archer2.getProperty(UnitPropertyDefinition.RangedAtack).setValue(8888);
    swordsman1.getProperty(UnitPropertyDefinition.Price).setValue(444.44);
    swordsman2.getProperty(UnitPropertyDefinition.Price).setValue(555.55);
    swordsman1.getProperty(UnitPropertyDefinition.RangedDefence).setValue(88);
    swordsman2.getProperty(UnitPropertyDefinition.RangedDefence).setValue(99);
    longArcher1.getProperty(UnitPropertyDefinition.DoubleAtack).setValue(false);
    longArcher2.getProperty(UnitPropertyDefinition.DoubleAtack).setValue(true);
    longArcher1.getProperty(UnitPropertyDefinition.Shots).setValue(333);
    longArcher2.getProperty(UnitPropertyDefinition.Shots).setValue(979);

    System.out.println(warrior1.toString());
    System.out.println(warrior2.toString());
    System.out.println(archer1.toString());
    System.out.println(archer2.toString());
    System.out.println(swordsman1.toString());
    System.out.println(swordsman2.toString());
    System.out.println(longArcher1.toString());
    System.out.println(longArcher2.toString());
  }
}

