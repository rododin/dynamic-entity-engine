/*
 * Collections.java
 * (C) 2006-2009 Rod Odin.
 */

package ru.rododin.dynamic_entity_engine.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Defines a set of static methods useful for work with miscellaneous
 * <code>{@link java.util.Collection}</code>s and <code>{@link java.util.Map}</code>s.
 *
 * @author Rod Odin
 */
public abstract class Collections
{
  /**
   * Converts the given <code>source</code> collection into the <code>{@link java.util.List}</code>
   * representation. It does nothing if the given <code>source</code> collection is already a
   * <code>{@link java.util.List}</code>, otherwise it creates a new
   * <code>{@link java.util.ArrayList}</code> using its
   * <code>{@link ArrayList#ArrayList(java.util.Collection)}</code> constructor.
   * @param source the collection to be converted
   * @param <T> the collection element type
   * @return <code>null</code> if the given <code>source</code> is also <code>null</code>,
   *         empty or non-empty <code>{@link java.util.List}</code> instance otherwise
   * @see #toList(Object[])
   */
  public static <T> List<T> toList(Collection<T> source)
  {
    if(source == null)
      return null;
    else if(source instanceof List)
      return (List<T>)source;
    else
      return new ArrayList<T>(source);
  }

  /**
   * Converts the given <code>source</code> array into the <code>{@link java.util.List}</code>
   * representation. It is just a synonim for the <code>{@link java.util.Arrays#asList(Object[])}
   * method, but accepts also <code>null</code>s for the given <code>source</code> parameter.
   * @param source the array to be converted
   * @param <T> the array element type
   * @return <code>null</code> if the given <code>source</code> is also <code>null</code>,
   *         empty or non-empty <code>{@link java.util.List}</code> instance otherwise
   * @see #toList(java.util.Collection)
   */
  public static <T> List<T> toList(T[] source)
  {
    if(source == null)
      return null;
    else
      return Arrays.asList(source);
  }

  /**
   * Converts the given <code>source</code> collection into the <code>{@link java.util.Set}</code>
   * representation. It does nothing if the given <code>source</code> collection is already a
   * <code>{@link java.util.Set}</code>, otherwise it creates a new
   * <code>{@link java.util.HashSet}</code> using its
   * <code>{@link HashSet#HashSet(java.util.Collection)}</code> constructor.
   * If the given <code>source</code> contains duplicated elements, the only one will be included
   * into the result set. Please note, the order of the elements in the result set is not
   * saved, see <code>{@link #toOrderedSet(java.util.Collection)}</code> method if you need
   * the order to be saved.
   * @param source the collection to be converted
   * @param <T> the collection element type
   * @return <code>null</code> if the given <code>source</code> is also <code>null</code>,
   *         empty or non-empty <code>{@link java.util.Set}</code> instance otherwise
   * @see #toSet(Object[])
   * @see #toOrderedSet (java.util.Collection)
   */
  public static <T> Set<T> toSet(Collection<T> source)
  {
    if(source == null)
      return null;
    else if(source instanceof Set)
      return (Set<T>)source;
    else
      return new HashSet<T>(source);
  }

  /**
   * Converts the given <code>source</code> array into the <code>{@link java.util.Set}</code>
   * representation.
   * If the given <code>source</code> contains duplicated elements, the only one will be included
   * into the result set. Please note, the order of the elements in the result set is not
   * saved, see <code>{@link #toOrderedSet(Object[])}</code> method if you need
   * the order to be saved.
   * @param source the collection to be converted
   * @param <T> the array element type
   * @return <code>null</code> if the given <code>source</code> is also <code>null</code>,
   *         empty or non-empty <code>{@link java.util.Set}</code> instance otherwise
   * @see #toSet(java.util.Collection)
   * @see #toOrderedSet (Object[])
   */
  @SuppressWarnings("ManualArrayToCollectionCopy")
  public static <T> Set<T> toSet(T[] source)
  {
    Set<T> rv;
    if(source == null)
      rv = null;
    else
    {
      rv = new HashSet<T>(Math.max((int)(source.length/0.75f) + 1, 16));
      for(T t : source)
        rv.add(t);
    }
    return rv;
  }

  /**
   * Converts the given <code>source</code> collection into the <code>{@link java.util.Set}</code>
   * representation. It does nothing if the given <code>source</code> collection is already a
   * <code>{@link java.util.Set}</code>, otherwise it creates a new
   * <code>{@link java.util.LinkedHashSet}</code> using its
   * <code>{@link LinkedHashSet#LinkedHashSet(java.util.Collection)}</code> constructor.
   * If the given <code>source</code> contains duplicated elements, the only one will be included
   * into the result set. The order of the elements in the result set is
   * saved.
   * @param source the collection to be converted
   * @param <T> the collection element type
   * @return <code>null</code> if the given <code>source</code> is also <code>null</code>,
   *         empty or non-empty <code>{@link java.util.SortedSet}</code> instance otherwise
   * @see #toOrderedSet(Object[])
   * @see #toSet(java.util.Collection)
   */
  public static <T> Set<T> toOrderedSet(Collection<T> source)
  {
    if(source == null)
      return null;
    else if(source instanceof Set)
      return (Set<T>)source;
    else
      return new LinkedHashSet<T>(source);
  }

  /**
   * Converts the given <code>source</code> array into the <code>{@link java.util.Set}</code>
   * representation.
   * If the given <code>source</code> contains duplicated elements, the only one will be included
   * into the result set. The order of the elements in the result set is saved.
   * @param source the array to be converted
   * @param <T> the collection element type
   * @return <code>null</code> if the given <code>source</code> is also <code>null</code>,
   *         empty or non-empty <code>{@link java.util.SortedSet}</code> instance otherwise
   * @see #toOrderedSet(Object[])
   * @see #toSet(java.util.Collection)
   */
  @SuppressWarnings("ManualArrayToCollectionCopy")
  public static <T> Set<T> toOrderedSet(T[] source)
  {
    Set<T> rv;
    if(source == null)
      rv = null;
    else
    {
      rv = new LinkedHashSet<T>(Math.max((int)(source.length/0.75f) + 1, 16));
      for(T t : source)
        rv.add(t);
    }
    return rv;
  }
}
