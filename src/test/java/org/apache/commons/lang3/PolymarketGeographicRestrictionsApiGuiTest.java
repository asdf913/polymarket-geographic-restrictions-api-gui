package org.apache.commons.lang3;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Predicates;
import com.google.common.reflect.Reflection;

import io.github.toolfactory.narcissus.Narcissus;

public class PolymarketGeographicRestrictionsApiGuiTest {

	private static Method METHOD_MAP_TO_INT, METHOD_TEST, METHOD_GET_CLASS, METHOD_TO_STRING, METHOD_CAST,
			METHOD_ENDS_WITH, METHOD_GROUP, METHOD_MATCHER, METHOD_FIND, METHOD_COLLECT, METHOD_ADD_ROW = null;

	@BeforeClass
	static void beforeClass() throws NoSuchMethodException {
		//
		final Class<?> clz = PolymarketGeographicRestrictionsApiGui.class;
		//
		(METHOD_MAP_TO_INT = clz.getDeclaredMethod("mapToInt", Stream.class, ToIntFunction.class)).setAccessible(true);
		//
		(METHOD_TEST = clz.getDeclaredMethod("test", Predicate.class, Object.class)).setAccessible(true);
		//
		(METHOD_GET_CLASS = clz.getDeclaredMethod("getClass", Object.class)).setAccessible(true);
		//
		(METHOD_TO_STRING = clz.getDeclaredMethod("toString", Object.class)).setAccessible(true);
		//
		(METHOD_CAST = clz.getDeclaredMethod("cast", Class.class, Object.class)).setAccessible(true);
		//
		(METHOD_ENDS_WITH = clz.getDeclaredMethod("endsWith", String.class, String.class)).setAccessible(true);
		//
		(METHOD_GROUP = clz.getDeclaredMethod("group", MatchResult.class)).setAccessible(true);
		//
		(METHOD_MATCHER = clz.getDeclaredMethod("matcher", Pattern.class, CharSequence.class)).setAccessible(true);
		//
		(METHOD_FIND = clz.getDeclaredMethod("find", Matcher.class)).setAccessible(true);
		//
		(METHOD_COLLECT = clz.getDeclaredMethod("collect", Stream.class, Collector.class)).setAccessible(true);
		//
		(METHOD_ADD_ROW = clz.getDeclaredMethod("addRow", DefaultTableModel.class, Object[].class)).setAccessible(true);
		//
	}

	private static class IH implements InvocationHandler {

		private Boolean test;

		private Integer rowCount;

		@Override
		public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
			//
			if (Objects.equals(method != null ? method.getReturnType() : null, Void.TYPE)) {
				//
				return null;
				//
			} // if
				//
			final String name = getName(method);
			//
			if (Boolean.logicalAnd(proxy instanceof Member || proxy instanceof RuntimeMXBean,
					Objects.equals(name, "getName"))) {
				//
				return null;
				//
			} // if
				//
			if (Boolean.logicalAnd(proxy instanceof IntStream, Objects.equals(name, "max"))) {
				//
				return null;
				//
			} else if (Boolean.logicalAnd(proxy instanceof Predicate, Objects.equals(name, "test"))) {
				//
				return test;
				//
			} else if (Boolean.logicalAnd(proxy instanceof Entry,
					contains(Arrays.asList("getValue", "getKey"), name))) {
				//
				return null;
				//
			} else if (Boolean.logicalAnd(proxy instanceof FailableFunction, Objects.equals(name, "apply"))) {
				//
				return null;
				//
			} else if (Boolean.logicalAnd(proxy instanceof Collection, Objects.equals(name, "stream"))) {
				//
				return null;
				//
			} else if (Boolean.logicalAnd(proxy instanceof Map,
					contains(Arrays.asList("keySet", "get", "put"), name))) {
				//
				return null;
				//
			} else if (Boolean.logicalAnd(proxy instanceof MatchResult, Objects.equals(name, "group"))) {
				//
				return null;
				//
			} else if (Boolean.logicalAnd(proxy instanceof TableModel, Objects.equals(name, "getRowCount"))) {
				//
				return rowCount;
				//
			} else if (proxy instanceof Stream) {
				//
				if (contains(Arrays.asList("mapToInt", "collect"), name)) {
					//
					return null;
					//
				} else if (Objects.equals(name, "filter")) {
					//
					return proxy;
					//
				} // if
					//
			} // if
				//
			throw new Throwable(name);
			//
		}

	}

	private static String getName(final Member instance) {
		return instance != null ? instance.getName() : null;
	}

	private IH ih = null;

	private Pattern pattern = null;

	private PolymarketGeographicRestrictionsApiGui instance = null;

	@BeforeMethod
	void beforeMethod() throws Throwable {
		//
		ih = new IH();
		//
		pattern = Pattern.compile("\\d+");
		//
		instance = cast(PolymarketGeographicRestrictionsApiGui.class,
				Narcissus.allocateInstance(PolymarketGeographicRestrictionsApiGui.class));
		//
	}

	@Test

	void testNull() throws Throwable {
		//
		final Method[] ms = PolymarketGeographicRestrictionsApiGui.class.getDeclaredMethods();
		//
		Method m = null;
		//
		Class<?>[] parameterTypes = null;
		//
		Object result = null;
		//
		String toString = null;
		//
		Collection<Object> collection = null;
		//
		Object[] os = null;
		//
		for (int i = 0; ms != null && i < ms.length; i++) {
			//
			if ((m = ArrayUtils.get(ms, i)) == null || m.isSynthetic()
					|| (parameterTypes = m.getParameterTypes()) == null) {
				//
				continue;
				//
			} // if
				//
			clear(collection = ObjectUtils.getIfNull(collection, ArrayList::new));
			//
			for (int j = 0; j < parameterTypes.length; j++) {
				//
				if (Objects.equals(ArrayUtils.get(parameterTypes, j), Integer.TYPE)) {
					//
					add(collection, Integer.valueOf(0));
					//
				} else {
					//
					add(collection, null);
					//
				} // if
					//
			} // for
				//
			os = toArray(collection);
			//
			if (instance == null) {
				//
				instance = cast(PolymarketGeographicRestrictionsApiGui.class,
						Narcissus.allocateInstance(PolymarketGeographicRestrictionsApiGui.class));
				//
			} // if
				//
			result = Modifier.isStatic(m.getModifiers()) ? Narcissus.invokeStaticMethod(m, os)
					: Narcissus.invokeMethod(instance, m, os);
			//
			toString = toString(m);
			//
			if (contains(Arrays.asList(Boolean.TYPE, Integer.TYPE), m.getReturnType())) {
				//
				Assert.assertNotNull(result, toString);
				//
			} else {
				//
				Assert.assertNull(result, toString);
				//
			} // if
				//
		} // for
			//
	}

	private static <T> T cast(final Class<T> clz, final Object instance) throws Throwable {
		try {
			return (T) invoke(METHOD_CAST, null, clz, instance);
		} catch (final InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	private static String toString(final Object instance) throws Throwable {
		try {
			final Object obj = invoke(METHOD_TO_STRING, null, instance);
			if (obj == null) {
				return null;
			} else if (obj instanceof String) {
				return (String) obj;
			}
			throw new Throwable(toString(getClass(obj)));
		} catch (final InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	private static boolean contains(final Collection<?> instance, final Object item) {
		return instance != null && instance.contains(item);
	}

	private static Object[] toArray(final Collection<?> instance) {
		return instance != null ? instance.toArray() : null;
	}

	private static <E> void add(final Collection<E> instance, final E item) {
		if (instance != null) {
			instance.add(item);
		}
	}

	private static void clear(final Collection<?> instance) {
		if (instance != null) {
			instance.clear();
		}
	}

	@Test

	void testNotNull() throws Throwable {
		//
		final Method[] ms = PolymarketGeographicRestrictionsApiGui.class.getDeclaredMethods();
		//
		Method m = null;
		//
		Class<?>[] parameterTypes = null;
		//
		Class<?> parameterType = null;
		//
		Object result = null;
		//
		String toString, name = null;
		//
		Collection<Object> collection = null;
		//
		Object[] os = null;
		//
		for (int i = 0; ms != null && i < ms.length; i++) {
			//
			if ((m = ArrayUtils.get(ms, i)) == null || m.isSynthetic()
					|| (parameterTypes = m.getParameterTypes()) == null) {
				//
				continue;
				//
			} // if
				//
			clear(collection = ObjectUtils.getIfNull(collection, ArrayList::new));
			//
			for (int j = 0; j < parameterTypes.length; j++) {
				//
				if (Objects.equals(parameterType = ArrayUtils.get(parameterTypes, j), Integer.TYPE)) {
					//
					add(collection, Integer.valueOf(0));
					//
				} else if (parameterType != null && parameterType.isArray()) {
					//
					add(collection, Array.newInstance(parameterType.getComponentType(), 0));
					//
				} else if (parameterType != null && parameterType.isInterface()) {
					//
					if ((ih = ObjectUtils.getIfNull(ih, IH::new)) != null) {
						//
						final List<Field> fs = FieldUtils.getAllFieldsList(getClass(ih));
						//
						Field f = null;
						//
						Class<?> type = null;
						//
						for (int k = 0; k < IterableUtils.size(fs); k++) {
							//
							if (Objects.equals(type = getType(f = IterableUtils.get(fs, k)), Boolean.class)) {
								//
								Narcissus.setField(ih, f, Boolean.TRUE);
								//
							} else if (Objects.equals(type, Integer.class)) {
								//
								Narcissus.setField(ih, f, Integer.valueOf(0));
								//
							} // if
								//
						} // for
							//
					} // if
						//
					add(collection, Reflection.newProxy(parameterType, ih));
					//
				} else if (Objects.equals(parameterType, Class.class)) {
					//
					add(collection, Class.class);
					//
				} else if (Objects.equals(parameterType, AbstractButton.class)) {
					//
					add(collection, Narcissus.allocateInstance(JButton.class));
					//
				} else {
					//
					add(collection, Narcissus.allocateInstance(parameterType));
					//
				} // if
					//
			} // for
				//
			os = toArray(collection);
			//
			if (instance == null) {
				//
				instance = cast(PolymarketGeographicRestrictionsApiGui.class,
						Narcissus.allocateInstance(PolymarketGeographicRestrictionsApiGui.class));
				//
			} // if
				//
			result = Modifier.isStatic(m.getModifiers()) ? Narcissus.invokeStaticMethod(m, os)
					: Narcissus.invokeMethod(instance, m, os);
			//
			toString = toString(m);
			//
			if (Objects.equals(m.getReturnType(), Void.TYPE)
					|| Boolean.logicalAnd(Objects.equals(name = getName(m), "max"),
							Arrays.equals(parameterTypes, new Class<?>[] { IntStream.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "cast"),
							Arrays.equals(parameterTypes, new Class<?>[] { Class.class, Object.class }))
					|| Boolean.logicalAnd(contains(Arrays.asList("getValue", "getKey"), name),
							Arrays.equals(parameterTypes, new Class<?>[] { Entry.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "apply"),
							Arrays.equals(parameterTypes, new Class<?>[] { FailableFunction.class, Object.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "stream"),
							Arrays.equals(parameterTypes, new Class<?>[] { Collection.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "keySet"),
							Arrays.equals(parameterTypes, new Class<?>[] { Map.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "mapToInt"),
							Arrays.equals(parameterTypes, new Class<?>[] { Stream.class, ToIntFunction.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "testAndApply"),
							Arrays.equals(parameterTypes,
									new Class<?>[] { Predicate.class, Object.class, FailableFunction.class,
											FailableFunction.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "getName"),
							Boolean.logicalOr(Arrays.equals(parameterTypes, new Class<?>[] { Member.class }),
									Arrays.equals(parameterTypes, new Class<?>[] { RuntimeMXBean.class })))
					|| Boolean.logicalAnd(Objects.equals(name, "collect"),
							Arrays.equals(parameterTypes, new Class<?>[] { Stream.class, Collector.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "openStream"),
							Arrays.equals(parameterTypes, new Class<?>[] { URL.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "toMap"),
							Arrays.equals(parameterTypes, new Class<?>[] { String.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "group"),
							Arrays.equals(parameterTypes, new Class<?>[] { MatchResult.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "get"),
							Arrays.equals(parameterTypes, new Class<?>[] { Map.class, Object.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "matcher"),
							Arrays.equals(parameterTypes, new Class<?>[] { Pattern.class, CharSequence.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "toMap"),
							Arrays.equals(parameterTypes, new Class<?>[] { String[].class }))
					|| Boolean.logicalAnd(Objects.equals(name, "toEntry"),
							Arrays.equals(parameterTypes, new Class<?>[] { String.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "getSource"),
							Arrays.equals(parameterTypes, new Class<?>[] { EventObject.class }))) {
				//
				Assert.assertNull(result, toString);
				//
			} else {
				//
				Assert.assertNotNull(result, toString);
				//
			} // if
				//
		} // for
			//
	}

	private static Class<?> getType(final Field instance) {
		return instance != null ? instance.getType() : null;
	}

	private static Class<?> getClass(final Object instance) throws Throwable {
		try {
			final Object obj = invoke(METHOD_GET_CLASS, null, instance);
			if (obj == null) {
				return null;
			} else if (obj instanceof Class) {
				return (Class<?>) obj;
			}
			throw new Throwable(toString(getClass(obj)));
		} catch (final InvocationTargetException e) {
			throw e.getTargetException();
		}
	}

	@Test
	void testMapToInt() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertNull(invoke(METHOD_MAP_TO_INT, null, Stream.empty(), null));
		//
	}

	private static Object invoke(final Method method, final Object instance, final Object... args)
			throws IllegalAccessException, InvocationTargetException {
		return method != null ? method.invoke(instance, args) : null;
	}

	@Test
	void testTest() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertEquals(invoke(METHOD_TEST, null, Predicates.alwaysFalse(), null), Boolean.FALSE);
		//
	}

	@Test
	public void testMain() throws IOException {
		//
		PolymarketGeographicRestrictionsApiGui.main(new String[] { "=", "= ", " =", " = ", "== " });
		//
		PolymarketGeographicRestrictionsApiGui.main(new String[] { "gui=true" });
		//
	}

	@Test
	void testEndsWith() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertEquals(invoke(METHOD_ENDS_WITH, null, "", null), Boolean.FALSE);
		//
		Assert.assertEquals(invoke(METHOD_ENDS_WITH, null, "", Narcissus.allocateInstance(String.class)),
				Boolean.FALSE);
		//
		Assert.assertEquals(invoke(METHOD_ENDS_WITH, null, "", ""), Boolean.TRUE);
		//
		Assert.assertEquals(invoke(METHOD_ENDS_WITH, null, "", "a"), Boolean.FALSE);
		//
	}

	@Test
	void testGroup() throws IllegalAccessException, InvocationTargetException {
		//
		final String string = "1";
		//
		final Object matcher = invoke(METHOD_MATCHER, null, pattern, string);
		//
		Assert.assertNull(invoke(METHOD_GROUP, null, matcher));
		//
		Assert.assertEquals(invoke(METHOD_FIND, null, matcher), Boolean.TRUE);
		//
		Assert.assertEquals(invoke(METHOD_GROUP, null, matcher), string);
		//
	}

	@Test
	void testMatcher() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertNull(invoke(METHOD_MATCHER, null, pattern, null));
		//
		Assert.assertNull(invoke(METHOD_MATCHER, null, pattern, Narcissus.allocateInstance(String.class)));
		//
	}

	@Test
	void testFind() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertEquals(invoke(METHOD_FIND, null, invoke(METHOD_MATCHER, null, pattern, "")), Boolean.FALSE);
		//
	}

	@Test
	public void testActionPerformed() throws IllegalAccessException {
		//
		if (instance == null) {
			//
			return;
			//
		} // if
			//
		instance.actionPerformed(new ActionEvent("", 0, null));
		//
		// btnExecute
		//
		final AbstractButton btnExecute = new JButton();
		//
		FieldUtils.writeDeclaredField(instance, "btnExecute", btnExecute, true);
		//
		final DefaultTableModel dtm = new DefaultTableModel();
		//
		FieldUtils.writeDeclaredField(instance, "dtm", dtm, true);
		//
		final ActionEvent actionEvent = new ActionEvent(btnExecute, 0, null);
		//
		for (int i = 0; i < 2; i++) {
			//
			instance.actionPerformed(actionEvent);
			//
		} // for
			//
			// btnCopy
			//
		final AbstractButton btnCopy = new JButton();
		//
		FieldUtils.writeDeclaredField(instance, "btnCopy", btnCopy, true);
		//
		instance.actionPerformed(new ActionEvent(btnCopy, 0, null));
		//
	}

	@Test
	void testCollect() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertNull(invoke(METHOD_COLLECT, null, Stream.empty(), null));
		//
		Assert.assertNull(invoke(METHOD_COLLECT, null,
				Reflection.newProxy(Stream.class, ObjectUtils.getIfNull(ih, IH::new)), null));
		//
	}

	@Test
	void testAddRow() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertNull(invoke(METHOD_ADD_ROW, null, new DefaultTableModel(), null));
		//
	}

}