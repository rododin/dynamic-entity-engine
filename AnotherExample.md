# Yet an example of classical OO-hierarchy difficulty #

Author: [Rod Odin](mailto:rododin@gmail.com) <sub>(Nikolay Chebotaryov)</sub>

## Introduction ##

You design a game like the well known “Heroes of Might and Magic”. You have dozens or probably hundreds of miscellaneous units like Knights, Archers, Orcs, Vampires, Mages, Dragons, etc. I would classify the units from the two following view-points:
  * The unit nature:
    * Living units (People, Elves, Dwarfs, etc.)
    * Undead units (summoned dead units like Zombies or Vampires)
    * Daemons (summoned from hell, like Imps or Devils)
    * Mechanized (like Ballistas, Golems, etc.)
    * Elemental (Fire, Air, Water, Earth Elementals and maybe some others)
  * Unit action way:
    * Melee (any infantry and other units attacking enemies from short distance)
    * Ranged (shooting/archery units attacking the enemies from long distance)
    * Magic (units attacking enemies with spells and casting lots of other miscellaneous spells)
    * Air (any flying units usually attacking enemies from air)
    * Driven (units driven by riders, or in other words consisting from two units – the driven unit and its rider/driver)
Of course, in the next version of your game you will introduce some other classes, e.g. Water Units (swimming and attacking from water only) and Underground Units (creeping under ground and attacking from the earth). But ok, it is the future, right now you have the only listed above.

So, now see the figure, it represents the attempt of building a classical object-oriented hierarchy:

![http://dynamic-entity-engine.googlecode.com/files/AnotherExample.jpg](http://dynamic-entity-engine.googlecode.com/files/AnotherExample.jpg)

There are two problems with this example:

### Problem 1 – Further extension of the `Unit` hierarchy ###

Really all of your units don’t represent a pure Melee, Ranged, or another class. Mostly of them combine two or more classes. E.g., what to do if you want to get a unit like `DragonRider`? This unit represents a mage – the master of dragons. It is a `MagicUnit`, because may cast a lot of spells and use magic in other ways. But also it is a flying `AirUnit`, because it is a really flying Black Dragon. As well as it is a `DrivenUnit`, because the Dragon is being driven by the mage-rider. Other examples are the lots of miscellaneous cavalry units introduced in “Heroes of Might and Magic”. Also the archery units may act as melee units, of course with reduced attack power using daggers or something like that, but any way. And finally you may want to create a mixed `MeleeRangedMagicUnit`, e.g. the well known `Titan` – good in the both attack types: a) attacking enemies from long distance with lightning arrows (the arrows powered with magic) and b) attacking from short distance with his terrible thunder blade (also powered with magic). Additionally you may give him an ability to cast some lightning-related spells.

I believe you see now it would be extremely hard to extend and manage the `Unit` hierarchy, if you will even have a complete multiple inheritance support provided in C++ or CLOS.

But ok, would you probably exchange the hierarchy of units using an enumeration like the `UnitNature` one? M-m… What would you do with the properties like `meleeAttack`, `shots` or `magicWeapon`? Probably you would say we must introduce and abstract `weapon` property in the parent `Unit` class? As well as we must move other properties into the `Unit` class too. Would you really like to do so? I don’t think whether it’s a good idea. Of course it is possible to do so, but it destroys the consistency of your business model. Each specific `Unit` subclass has a specific `attack`. The units mixing a few subclasses have a few appropriate `attack`s. `Melee`- or `AirUnit`s don’t have any `shot`s. And finally about the abstract `weapon`… I wouldn’t place the `weapon` property of the abstract `Weapon` class into the parent `Unit`. Why? Just because it is a logical constraint of the business model. It is strongly specified that the `MeleeUnit`s may act with the only `MeleeWeapon`, as well as the `RangedUnit`s act with the only `RangedWeapon`, and so on. If you put the abstract `Weapon` into the `Unit` class you lose control over the constraint. And of course, any way, the _mixed_ units must have several weapons.
So, it is impossible to use an enumerated `Unit` class without losing of the business model consistence. What to do?
Would you probably exchange the hierarchy using the classical (again classical) aggregation technique? Do you really want to do that? Let’s see…

### Problem 2 – `Weapon`, `Armor` and `Skill` hierarchies ###

## NOTES – some points to discuss ##
  * The business entity model is usually a plain model. In other words, the entity beans are just primitive classes without any behavior inside consisting of just primitive getters/setters. In general polymorphism is not used in the entity beans. Business logic must use often the `instanceof` operators to switch behavior for different subtypes. So, there are no reasons to have exact mapping between DB tables and Java classes.
  * Currently I don’t know how the ORMs like Hibernate process the _lazy-loading_ functionality. But in our engine the _lazy-loading_ has to be based on listeners defined for the properties representing sub-entities of collections of sub-entities.

## TODO ##

  * **Complete the document**