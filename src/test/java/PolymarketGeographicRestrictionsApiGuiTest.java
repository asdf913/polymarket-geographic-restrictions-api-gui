import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
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

	private static Method METHOD_MAP_TO_INT, METHOD_TEST, METHOD_GET_CLASS, METHOD_TO_STRING, METHOD_GET_NAME,
			METHOD_MAP, METHOD_FILTER, METHOD_EXISTS = null;

	@BeforeClass
	static void beforeClass() throws NoSuchMethodException, SecurityException {
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
		(METHOD_GET_NAME = clz.getDeclaredMethod("getName", Member.class)).setAccessible(true);
		//
		(METHOD_MAP = clz.getDeclaredMethod("map", Stream.class, Function.class)).setAccessible(true);
		//
		(METHOD_FILTER = clz.getDeclaredMethod("filter", Stream.class, Predicate.class)).setAccessible(true);
		//
		(METHOD_EXISTS = clz.getDeclaredMethod("exists", File.class)).setAccessible(true);
		//
	}

	private static class IH implements InvocationHandler {

		private Boolean test;

		@Override
		public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
			//
			final String name = getName(method);
			//
			if (Boolean.logicalAnd(proxy instanceof Member, Objects.equals(name, "getName"))) {
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
			} else if (Boolean.logicalAnd(proxy instanceof Map, Objects.equals(name, "keySet"))) {
				//
				return null;
				//
			} else if (proxy instanceof Stream) {
				//
				if (contains(Arrays.asList("mapToInt", "map", "toList"), name)) {
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

	private static String getName(final Member instance) throws Throwable {
		try {
			final Object obj = invoke(METHOD_GET_NAME, null, instance);
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

	private IH ih = null;

	@BeforeMethod
	void beforeMethod() {
		//
		ih = new IH();
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
			result = Narcissus.invokeStaticMethod(m, toArray(collection));
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
						for (int k = 0; k < IterableUtils.size(fs); k++) {
							//
							if ((f = IterableUtils.get(fs, k)) == null) {
								//
								continue;
								//
							} // if
								//
							if (Objects.equals(f.getType(), Boolean.class)) {
								//
								Narcissus.setField(ih, f, Boolean.TRUE);
								//
							} // if
								//
						} // for
							//
					} // if
						//
					add(collection, Reflection.newProxy(parameterType, ih = ObjectUtils.getIfNull(ih, IH::new)));
					//
				} else if (Objects.equals(parameterType, Class.class)) {
					//
					add(collection, Class.class);
					//
				} else if (Objects.equals(parameterType, Process.class)) {
					//
					add(collection, Narcissus.allocateInstance(Class.forName("java.lang.ProcessImpl")));
					//
				} else if (Objects.equals(parameterType, InputStream.class)) {
					//
					add(collection, new ByteArrayInputStream(new byte[] {}));
					//
				} else {
					//
					add(collection, Narcissus.allocateInstance(parameterType));
					//
				} // if
					//
			} // for
				//
			result = Narcissus.invokeStaticMethod(m, toArray(collection));
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
					|| Boolean.logicalAnd(Objects.equals(name, "map"),
							Arrays.equals(parameterTypes, new Class<?>[] { Stream.class, Function.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "toList"),
							Arrays.equals(parameterTypes, new Class<?>[] { Stream.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "start"),
							Arrays.equals(parameterTypes, new Class<?>[] { ProcessBuilder.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "getInputStream"),
							Arrays.equals(parameterTypes, new Class<?>[] { Process.class }))
					|| Boolean.logicalAnd(Objects.equals(name, "getName"),
							Arrays.equals(parameterTypes, new Class<?>[] { Member.class }))) {
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
	void testMap() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertNull(invoke(METHOD_MAP, null, Stream.empty(), null));
		//
	}

	@Test
	void testFilter() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertNull(invoke(METHOD_FILTER, null, Stream.empty(), null));
		//
		final Stream<?> stream = Reflection.newProxy(Stream.class, ObjectUtils.getIfNull(ih, IH::new));
		//
		Assert.assertSame(invoke(METHOD_FILTER, null, stream, null), stream);
		//
	}

	@Test
	void testExists() throws IllegalAccessException, InvocationTargetException {
		//
		Assert.assertEquals(invoke(METHOD_EXISTS, null, new File("")), Boolean.FALSE);
		//
	}

}