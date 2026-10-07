package org.apache.commons.lang3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Proxy;
import java.nio.file.FileSystems;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.function.FailableFunction;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import tools.jackson.databind.ObjectMapper;

public class PolymarketGeographicRestrictionsApiGui {

	private interface LibNM extends Library {

		Pointer nm_client_new(final Pointer cancellable, final Pointer error);

		@Target(ElementType.METHOD)
		@Retention(RetentionPolicy.RUNTIME)
		@interface URL {

			String value();

		}

		@URL("https://networkmanager.dev/docs/libnm/latest/NMClient.html#nm-client-get-connectivity")
		int nm_client_get_connectivity(final Pointer client);

	}

	private static LibNM libnm = null;

	public static void main(final String[] args) throws IOException {
		//
		if (Objects.equals(getName(getClass(FileSystems.getDefault())), "sun.nio.fs.LinuxFileSystem")
				&& new File("/usr/bin/nmcli").isFile()
				&& (libnm = ObjectUtils.getIfNull(libnm, () -> Native.load("nm", LibNM.class))) != null
				&& libnm.nm_client_get_connectivity(libnm.nm_client_new(null, null)) != 4) {
			//
			return;
			//
		} // if
			//
		try (final InputStream is = new java.net.URL("https://polymarket.com/api/geoblock").openStream()) {
			//
			final Map<?, ?> map = cast(Map.class,
					testAndApply(Objects::nonNull, is, x -> new ObjectMapper().readValue(x, Object.class), null));
			//
			final int maxKeyLength = orElse(max(mapToInt(stream(keySet(map)), x -> StringUtils.length(toString(x)))),
					0);
			//
			if (map != null && map.entrySet() != null) {
				//
				for (final Entry<?, ?> entry : map.entrySet()) {
					//
					System.out.println(
							StringUtils.rightPad(toString(getKey(entry)), maxKeyLength) + " " + getValue(entry));
					//
				} // for
					//
			} // if
				//
		} // try
			//
	}

	private static String getName(final Class<?> instance) {
		return instance != null ? instance.getName() : null;
	}

	private static <K> K getKey(final Entry<K, ?> instance) {
		return instance != null ? instance.getKey() : null;
	}

	private static <V> V getValue(final Entry<?, V> instance) {
		return instance != null ? instance.getValue() : null;
	}

	private static String toString(final Object instance) {
		return instance != null ? instance.toString() : null;
	}

	private static int orElse(final OptionalInt instance, final int other) {
		return instance != null ? instance.orElse(other) : other;
	}

	private static OptionalInt max(final IntStream instance) {
		return instance != null ? instance.max() : null;
	}

	private static <T> IntStream mapToInt(final Stream<T> instance, final ToIntFunction<? super T> mapper) {
		//
		return instance != null && (Proxy.isProxyClass(getClass(instance)) || mapper != null)
				? instance.mapToInt(mapper)
				: null;
		//
	}

	private static Class<?> getClass(final Object instance) {
		return instance != null ? instance.getClass() : null;
	}

	private static <T> Stream<T> stream(final Collection<T> instance) {
		return instance != null ? instance.stream() : null;
	}

	private static <K> Set<K> keySet(final Map<K, ?> instance) {
		return instance != null ? instance.keySet() : null;
	}

	private static <T> T cast(final Class<T> clz, final Object instance) {
		return clz != null && clz.isInstance(instance) ? clz.cast(instance) : null;
	}

	private static <T, R, E extends Throwable> R testAndApply(final Predicate<T> predicate, final T value,
			final FailableFunction<T, R, E> functionTrue, final FailableFunction<T, R, E> functionFalse) throws E {
		return test(predicate, value) ? apply(functionTrue, value) : apply(functionFalse, value);
	}

	private static <T> boolean test(final Predicate<T> instance, final T value) {
		return instance != null && instance.test(value);
	}

	private static <T, R, E extends Throwable> R apply(final FailableFunction<T, R, E> instance, final T value)
			throws E {
		return instance != null ? instance.apply(value) : null;
	}

}