import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.reflect.FieldUtils;

import io.github.toolfactory.narcissus.Narcissus;
import tools.jackson.databind.ObjectMapper;

public class PolymarketGeographicRestrictionsApiGui {

	public static void main(final String[] args) throws IOException {
		//
		if (Objects.equals(getName(getClass(FileSystems.getDefault())), "sun.nio.fs.LinuxFileSystem")) {
			//
			final Charset charset = StandardCharsets.UTF_8;
			//
			boolean nmcliExists = false;
			//
			try (final InputStream is = getInputStream(start(new ProcessBuilder(new String[] { "which", "nmcli" })))) {
				//
				nmcliExists = exists(testAndApply(Objects::nonNull, StringUtils.trim(testAndApply(Objects::nonNull,
						readAllBytes(is), x -> new String(x, StandardCharsets.UTF_8), null)), File::new, null));
				//
			} // try
				//
			if (nmcliExists) {
				//
				try (final InputStream is = getInputStream(
						start(new ProcessBuilder(new String[] { "nmcli", "-mode", "multiline", "general" })))) {
					//
					final Collection<String> collection = testAndApply(Objects::nonNull, is,
							x -> IOUtils.readLines(x, charset), null);
					//
					if (!Objects.equals(testAndApply(x -> IterableUtils.size(x) == 1,
							toList(map(filter(stream(collection), x -> startsWith(x, "CONNECTIVITY:")),
									x -> StringUtils.trim(StringUtils.substringAfter(x, ':')))),
							x -> IterableUtils.get(x, 0), null), "full")) {
						//
						return;
						//
					} // if
						//
				} // try
					//
			} // if
				//
		} // if
			//
		try (final InputStream is = new URL("https://polymarket.com/api/geoblock").openStream()) {
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

	private static byte[] readAllBytes(final InputStream instance) throws IOException {
		return instance != null ? instance.readAllBytes() : null;
	}

	private static boolean startsWith(final String instance, final String prefix) {
		//
		if (instance == null) {
			//
			return false;
			//
		} // if
			//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				toList(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), "value"))),
				x -> IterableUtils.get(x, 0), null);
		//
		return (field == null || Narcissus.getField(instance, field) != null) && instance.startsWith(prefix);
		//
	}

	private static <T> List<T> toList(final Stream<T> instance) {
		return instance != null ? instance.toList() : null;
	}

	private static <T, R> Stream<R> map(final Stream<T> instance, final Function<? super T, ? extends R> mapper) {
		//
		return instance != null && (Proxy.isProxyClass(getClass(instance)) || mapper != null) ? instance.map(mapper)
				: null;
		//
	}

	private static <T> Stream<T> filter(final Stream<T> instance, final Predicate<? super T> predicate) {
		//
		return instance != null && (predicate != null || Proxy.isProxyClass(getClass(instance)))
				? instance.filter(predicate)
				: null;
		//
	}

	private static boolean exists(final File instance) {
		return instance != null && instance.getPath() != null && instance.exists();
	}

	private static InputStream getInputStream(final Process instance) {
		return instance != null ? instance.getInputStream() : null;
	}

	private static Process start(final ProcessBuilder instance) throws IOException {
		//
		if (instance == null) {
			//
			return null;
			//
		} // if
			//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				toList(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), "command"))),
				x -> IterableUtils.get(x, 0), null);
		//
		return (field == null || Narcissus.getField(instance, field) != null) ? instance.start() : null;
		//
	}

	private static String getName(final Member instance) {
		return instance != null ? instance.getName() : null;
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