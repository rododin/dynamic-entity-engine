# Dynamic Entity Engine – Обзор #

Автор: [Род Один](mailto:rododin@gmail.com) <sub>(Николай Чеботарёв)</sub>

Опубликовано: Март 2009

English edition is provided [here](http://code.google.com/p/dynamic-entity-engine).

## Мотивация ##

Знакомы ли Вы с игрой [Герои Меча и Магии](http://ru.wikipedia.org/wiki/Heroes_of_Might_and_Magic) или с [Цивилизацией](http://ru.wikipedia.org/wiki/Civilization_IV)? Или с любой другой игрой, где имеется множество различных юнитов, наподобие Драконов, Рыцарей, Лучников, или, быть может, Танков, Сверхзвуковых Истребителей и Ракетных Крейсеров? Или Вы разрабатываете бизнес-приложение с десятками различных бизнес-сущностей, обрабатываемых Вашей бизнес-логикой?

Если Вы действительно разрабатываете что-то такое, как бы Вы поступили?
Создадите ли Вы десятки и сотни классов, по одному на каждую сущность, с десятками `get`/`set`-методов, представляющих их свойства (properties)? Конечно же, Вы – один из опытнейших объектно-ориентированных разработчиков и Вы построите прекрасно-продуманную иерархию сущностей (или игровых юнитов). И если Ваши сущности должны сохраняться долговременно (в БД, например), то Вы пропишите их отображения на реляционные таблицы с помощью JPA-аннотаций. После чего Вы напишете тонны бизнес-логики (десятки сессионных бинов), чтобы как-то управлять и обрабатывать эти плоские сущности – сущности, просто состоящие из наборов свойств, задаваемых парами `get`/`set`-методов.

А что бы Вы сделали, если Вашей бизнес-логике требуется перехватывать события наподобие `propertyChanged` или `propertyAccessed`? Например, Вы имеете 50-60 различных сущностей, содержащих в среднем по 10 свойств каждая. Но лишь в небольшом ряде случаев Вам требуется перехватывать события типа `propertyChanged` или `entitySaved`. Станете ли Вы добавлять поддержку _слушателя событий_ в каждый `get`/`set`-метод, где это потребуется? Или Вы добавите поддержку _слушателей_ во все `get`/`set`-методы, т.к. Вы не знаете заранее, где она может оказаться полезной?

А давайте снова вернёмся к объектно-ориентированной иерархии… К примеру, все Ваши сущности имеют свойство `id`, выступающее в роли первичного ключа. В большинстве случаев этот `id` – целое число, но в редких исключениях это строка. Многие Ваши сущности имеют свойство `name`, например `User`, `Client` или `Organization`. При этом, `User` и `Client` являются людьми, поэтому их общие свойства (`name`, `surname`, `birthDate` и `taxNumber`) Вы можете задать в общем базовом классе `Person`. Однако свойство `name` должно перейти в ещё более абстрактный класс (назовём его `NamedEntity`), поскольку `Organization` также имеет имя. Поле `taxNumber` должно перейти в класс `Client`, поскольку оно касается только клиентов и никак не касается, например, `User`-а. Но `Organization` также может играть роль клиента, поэтому `Client` не есть `Person`. В такой ситуации `Client` представляет просто абстрактного клиента, который расширяется до конкретных `ClientPerson` и `ClientOrganization`. Соответственно, `ClientPerson` должен наследовать два класса: `Person` и `Client`. Аналогично `ClientOrganization` – два класса: `Organization` и `Client`. Окончательная иерархия выглядит, как показано на рисунке:

http://dynamic-entity-engine.googlecode.com/files/ExampleHierarchy_small.PNG

В Java, однако, Вы не имеете множественного наследования, а интерфейсы не работают, т.к. Вам необходимо прописать JPA-аннотации к свойствам. Но даже если бы интерфейсы работали, Вам было бы необходимо повторять код, реализующий одинаковые свойства, или, как минимум, Вам  пришлось бы агрегировать единственную реализацию многократно, при этом код доступа к агрегациям, опять-таки, должен повторяться. Иными словами, многократная агрегация является так же повторением кода.

Я надеюсь, всего этого достаточно, чтобы спросить Вас, действительно ли Вы хотите делать всю эту работу из раза в раз? Действительно ли Вы хотите писать вручную десятки классов, объявляя сотни свойств, даже если `get`/`set`-методы генерируются средой разработки? Действительно ли Вы хотите постоянно заботится о поддержке слушателей событий и строить хорошо продуманные иерархии? И снова, хотите ли Вы постоянно делать одно и то же?

Я надеюсь, Вам необходима прагматическая и оптимальная реализация Вашей задачи с минимумом повторяющегося кода и минимум повторяющейся рутинной работы с Вашей стороны. Если так, то позвольте спросить Вас, действительно ли Вам необходимо точное реляционное отображение некоторой сущности Вашей бизнес-модели на Java-класс? Если нет, позвольте предложить решение, реализованное в данном фреймворке.

## Решение ##

Идея данного решения основана на мета-видении системы. Каждая сущность есть просто сущность (`Entity`), а свойство сущности есть просто свойство (`Property`). Таким образом, всё, что Вам необходимо, это иметь класс `Property` для представления любого именованного свойства, и класс `Entity` для представления любой именованной сущности, обладающей набором свойств.

Разумеется, каждое `Property` имеет атрибуты `name` и `value`. Конечно же, было бы очень неплохо расширить `Property` также атрибутами `valueClass` и `defaultValue`. Ещё далее Вы можете расширить `Property` поддержкой `PropertyListener`-ов и любыми другими необходимыми вещами.

Аналогично, каждая `Entity` имеет, как минимум, `name` и набор дочерних `Property`. Далее она может быть расширена поддержкой `EntityListener`-ов и другими вещами.

Конечно, Вы можете сказать, что такое решение имеет большие накладные расходы, т.к. каждое `Property`, помимо собственно `value`, должно хранить также `name`, `valueClass` и `defaultValue`. И, аналогично, каждая `Entity` также должна «знать» своё `name` и, возможно, что-то ещё. Т.е., например, если мы имеем тысячу различных экземпляров сущности `Ware`, то каждый экземпляр будет хранить копию строки «Ware» в качестве имени. Сущность `Ware` имеет, например, три свойства: `id`, `name` и `price`. Таким образом, эти три строки «id», «name» и «price» также будут дублироваться тысячу раз каждая. Кроме этого по тысяче раз будут одни и те же храниться значения для `valueClass` и `defaultValue`. Конечно же, это очень неэффективное использование памяти.

Для устранения такой ситуации мы вводим понятие `PropertyDescriptor`-а и `EntityDescriptor`-а соответственно. Эти классы включают любую общую информации о свойствах и сущностях соответственно (`name`, `valueClass`, `defaultValue` для `PropertyDescriptor`-а; и, как минимум, `name` для `EntityDescriptor`-а). Таким образом, каждая `Entity` и каждое `Property` должны знать только о своих дескрипторах, а это, всего лишь, одна ссылка на объект. При этом, конечно же, возможно устранить дублирование самих дескрипторов. Внутренний механизм сохраняет каждый уникальный дескриптор во внутренней карте и если Вы создаёте ещё одно `Property` (возможно даже для другой `Entity`) с одинаковым набором базовых параметров, то существующий дескриптор будет использован вместо сохранения дубликата. Всё это сделано прозрачно для конечного разработчика, т.е. для Вас.

## Пример ##

Пакет `ru.rododin.dynamic_entity_engine.demo.unit` содержит простой пример использования данной идеи. Пример выводит понятие `Unit`-а, определяемого посредством перечисления `UnitDefinition`. Конечно, совсем несложно предложить другую реализацию, где все `Unit`-ы будут задаваться посредством `.properties`-файлов или посредством XML-конфигурации.

Я бы предложил смотреть исходники в следующем порядке:
  * [UnitDefinition](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/demo/unit/UnitDefinition.java)
  * [UnitPropertyDefinition](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/demo/unit/UnitPropertyDefinition.java)
  * [Unit](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/demo/unit/Unit.java)
  * [Entity](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/Entity.java)
  * [Property](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/Property.java)
  * [EntityDescriptor](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/EntityDescriptor.java)
  * [PropertyDescriptor](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/PropertyDescriptor.java)
  * [AbstractEntity](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/impl/AbstractEntity.java)
  * [AbstractProperty](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/impl/AbstractProperty.java)
  * [StandardEntityDescriptor](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/impl/StandardEntityDescriptor.java)
  * [StandardPropertyDescriptor](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.1.1-base-idea/src/main/java/ru/rododin/dynamic_entity_engine/entity/impl/StandardPropertyDescriptor.java)

Результирующая диаграмма классов выглядит следующим образом: <br />
![http://dynamic-entity-engine.googlecode.com/files/BaseIdeaClassDiagram.png](http://dynamic-entity-engine.googlecode.com/files/BaseIdeaClassDiagram.png)

## Исходные коды ##

Исходные коды находятся [здесь](http://code.google.com/p/dynamic-entity-engine/source/checkout).

«Голая» реализация базовой идеи доступна в этом тэге: <br />
http://dynamic-entity-engine.googlecode.com/svn/tags/version-0.1.1-base-idea

Тэг `version-0.2.0-typical-listener` содержит типичную реализацию поддержки для `Property`- и `EntityListener`-ов: <br />
http://dynamic-entity-engine.googlecode.com/svn/tags/version-0.2.0-typical-listener <br />
Данная реализация не является оптимальной, поскольку в ней имеется повторяющейся код. См., например, [EntityListenerManager](http://code.google.com/p/dynamic-entity-engine/source/browse/tags/version-0.2.0-typical-listener/src/main/java/ru/rododin/dynamic_entity_engine/entity/impl/EntityListenerManager.java). Как можете видеть, методы обработки каждого события `entityXxx` практически полностью идентичны. Конечно, можно написать единую реализацию таких методов с использованием механизма рефлексии, т.е. по строковому идентификатору находить нужный метод и вызывать его через рефлексию. Такой подход, однако, представляется неоптимальным, т.к. в реальной системе это может привести к вызову тысяч слушателей таким образом. Сейчас мною ведётся работа над альтернативной реализацией поддержки для слушателей событий (TODO 3), идея которой аналогична базовой идее данного фреймворка, когда набор событий и их слушателей определяется перечислением.

Головная ветка содержит рабочую копию исходников: <br />
http://dynamic-entity-engine.googlecode.com/svn/trunk <br />

Проект основан на [Maven](http://maven.apache.org)-е и содержит стандартный POM-файл. Помимо этого, имеется проект для [IntelliJ IDEA](http://www.jetbrains.com/idea). Надеюсь, Вы сумеете выгрузить исходники из [SVN](http://ru.wikipedia.org/wiki/Subversion) (см. также: http://tortoisesvn.tigris.org), собрать и запустить их с помощью Maven-а или IDE-и.

## TODO ##

  1. ~~Предоставить диаграмму классов (полученную реверс-инженирингом) для данного механизма~~ – сделано.
  1. ~~Добавить поддержку _слушателей событий_ в механизм (`PropertyListener` и `EntityListener`)~~ – типичная реализация завершена в версии 0.2.
  1. Переделать поддержку слушателей событий, используя базовую идею данного фреймворка (реализация мета-слушателя).
  1. Добавить поддержку для `UnitRenderer` и `UnitController` в сущность `Unit` для демонстрации расширяемости данного подхода.
  1. БОЛЬШОЙ TODO: Продумать и предоставить концепцию ORM-фреймворка для данного механизма. Исследовать вопрос, насколько Hibernate или Top-Link (или что-то ещё) могут быть полезны и/или применимы для разработки такого специализированного ORM-фреймворка. Основная задача здесь - найти простейший и быстрейший способ маппинга именованных сущностей и свойств на таблицы и поля базы данных.