package org.apache.commons.lang3;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Proxy;
import java.nio.file.FileSystems;
import java.util.Collection;
import java.util.EventObject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.tuple.Pair;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32Util;

import io.github.toolfactory.narcissus.Narcissus;
import net.miginfocom.swing.MigLayout;
import tools.jackson.databind.ObjectMapper;

public class PolymarketGeographicRestrictionsApiGui extends JPanel implements ActionListener {

	private static final long serialVersionUID = 6305772484741534948L;

	private static final String VALUE = "value";

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

	private AbstractButton btnExecute = null;

	private DefaultTableModel dtm = null;

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
		boolean gui = Boolean.parseBoolean(get(toMap(args), "gui"));
		//
		if (!gui) {
			//
			final String name = getName(getClass(FileSystems.getDefault()));
			//
			if (Objects.equals(name, "sun.nio.fs.MacOSXFileSystem")) {
				//
				gui = System.console() == null;
				//
			} // if
				//
			if (Boolean.logicalAnd(!gui, Objects.equals(name, "sun.nio.fs.WindowsFileSystem"))) {
				//
				final Matcher matcher = matcher(Pattern.compile("\\d+"), getName(ManagementFactory.getRuntimeMXBean()));
				//
				if (find(matcher)) {
					//
					gui = BooleanUtils.toBooleanDefaultIfNull(testAndApply(NumberUtils::isDigits, group(matcher),
							x -> endsWith(Kernel32Util.QueryFullProcessImageName(NumberUtils.toInt(x), 0), "javaw.exe"),
							null), false);
					//
				} // if
					//
			} // if
				//
		} // if
			//
		if (gui) {
			//
			final PolymarketGeographicRestrictionsApiGui instance = new PolymarketGeographicRestrictionsApiGui();
			//
			instance.setLayout(new MigLayout());
			//
			instance.add(instance.btnExecute = new JButton("Execute"), "wrap");
			//
			instance.btnExecute.addActionListener(instance);
			//
			final JTable jTable = new JTable(instance.dtm = new DefaultTableModel(new Object[] { "Key", VALUE }, 0));
			//
			jTable.getColumn("Key").setMaxWidth(46);
			//
			jTable.getColumn(VALUE).setMaxWidth(96);
			//
			instance.add(new JScrollPane(jTable), "wmax 145,hmax 88");
			//
			final JFrame jFrame = Boolean.logicalAnd(!GraphicsEnvironment.isHeadless(), !isTestMode()) ? new JFrame()
					: null;
			//
			if (jFrame != null) {
				//
				jFrame.add(instance);
				//
				jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				//
				jFrame.pack();
				//
				jFrame.setVisible(true);
				//
			} // if
				//
			return;
			//
		} // if
			//
		final Map<?, ?> map = toMap("https://polymarket.com/api/geoblock");
		//
		final int maxKeyLength = orElse(max(mapToInt(stream(keySet(map)), x -> StringUtils.length(toString(x)))), 0);
		//
		if (map != null && map.entrySet() != null) {
			//
			for (final Entry<?, ?> entry : map.entrySet()) {
				//
				System.out.println(StringUtils.rightPad(toString(getKey(entry)), maxKeyLength) + " " + getValue(entry));
				//
			} // for
				//
		} // if
			//
	}

	private static boolean isTestMode() {
		try {
			return Class.forName("org.testng.annotations.Test") != null;
		} catch (final ClassNotFoundException e) {
			return false;
		}
	}

	private static boolean endsWith(final String instance, final String suffix) {
		//
		if (instance == null || suffix == null) {
			//
			return false;
			//
		} // if
			//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						x -> Objects.equals(getName(x), VALUE)), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		return (field == null || Boolean.logicalAnd(Narcissus.getField(instance, field) != null,
				Narcissus.getField(suffix, field) != null)) && instance.endsWith(suffix);
		//
	}

	private static String group(final MatchResult instance) {
		//
		if (instance == null) {
			//
			return null;
			//
		} // if
			//
		final Field first = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), "first")), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		if (first != null && Objects.equals(first.getType(), Integer.TYPE)) {
			//
			return Narcissus.getIntField(instance, first) >= 0 ? instance.group() : null;
			//
		} // if
			//
		return instance.group();
		//
	}

	private static <V> V get(final Map<?, V> instance, final Object key) {
		return instance != null ? instance.get(key) : null;
	}

	private static Matcher matcher(final Pattern instance, final CharSequence input) {
		//
		if (instance == null || input == null) {
			//
			return null;
			//
		} // if
			//
		final Field normalizedPattern = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), "normalizedPattern")), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		final Field value = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(input), FieldUtils::getAllFieldsList, null)),
						x -> Objects.equals(getName(x), VALUE)), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		return (normalizedPattern == null || Narcissus.getField(instance, normalizedPattern) != null)
				&& (value == null || Narcissus.getField(input, value) != null) ? instance.matcher(input) : null;
		//
	}

	private static boolean find(final Matcher instance) {
		//
		if (instance == null) {
			//
			return false;
			//
		} // if
			//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), "groups")), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		return (field == null || Narcissus.getField(instance, field) != null) && instance.find();
		//
	}

	private static Map<?, ?> toMap(final String url) throws IOException {
		//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(url), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), VALUE)), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		if (field != null && Narcissus.getField(url, field) == null) {
			//
			return null;
			//
		} // if
			//
		try (final InputStream is = openStream(testAndApply(Objects::nonNull, url, java.net.URL::new, null))) {
			//
			return cast(Map.class,
					testAndApply(Objects::nonNull, is, x -> new ObjectMapper().readValue(x, Object.class), null));
			//
		} // try
			//
	}

	private static InputStream openStream(final java.net.URL instance) throws IOException {
		//
		if (instance == null) {
			//
			return null;
			//
		} // if
			//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), "handler")), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		return (field == null || Narcissus.getField(instance, field) != null) ? instance.openStream() : null;
		//
	}

	private static Map<String, String> toMap(final String... ss) {
		//
		Map<String, String> map = null;
		//
		Entry<String, String> entry = null;
		//
		for (int i = 0; i < length(ss); i++) {
			//
			if ((entry = toEntry(ArrayUtils.get(ss, i))) == null) {
				//
				continue;
				//
			} // if
				//
			put(map = ObjectUtils.getIfNull(map, LinkedHashMap::new), getKey(entry), getValue(entry));
			//
		} // for
			//
		return map;
		//
	}

	private static <K, V> void put(final Map<K, V> instance, final K key, final V value) {
		if (instance != null) {
			instance.put(key, value);
		}
	}

	private static Entry<String, String> toEntry(final String string) {
		//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(string), FieldUtils::getAllFieldsList, null)),
						f -> Objects.equals(getName(f), VALUE)), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		if (string != null && field != null && Narcissus.getField(string, field) == null) {
			//
			return null;
			//
		} // if
			//
		if (Objects.equals(string, "=")) {
			//
			return Pair.of("", "");
			//
		} else if (string != null && string.length() == 2 && string.charAt(0) == '=') {
			//
			return Pair.of("", string.substring(1, string.length()));
			//
		} else if (string != null && string.length() == 2 && string.charAt(string.length() - 1) == '=') {
			//
			return Pair.of(string.substring(0, string.length() - 1), "");
			//
		} else if (string != null && string.indexOf('=') >= 0 && string.indexOf('=') == string.lastIndexOf('=')) {
			//
			return Pair.of(StringUtils.substringBefore(string, '='), StringUtils.substringAfter(string, '='));
			//
		} else if (string != null && string.length() > 2 && string.indexOf('=') != string.lastIndexOf('=')) {
			//
			return Pair.of(StringUtils.substring(string, 0, string.indexOf('=')),
					StringUtils.substring(string, string.indexOf('=') + 1));
			//
		} // if
			//
		return null;
		//
	}

	private static int length(final Object[] instance) {
		return instance != null ? instance.length : 0;
	}

	@Override
	public void actionPerformed(final ActionEvent evt) {
		//
		if (Objects.equals(getSource(evt), btnExecute)) {
			//
			try {
				//
				final Map<?, ?> map = toMap("https://polymarket.com/api/geoblock");
				//
				for (int i = (dtm != null ? dtm.getRowCount() : 0) - 1; dtm != null && i >= 0; i--) {
					//
					dtm.removeRow(i);
					//
				} // for
					//
				if (map != null && map.entrySet() != null) {
					//
					for (final Entry<?, ?> entry : map.entrySet()) {
						//
						addRow(dtm, new Object[] { getKey(entry), getValue(entry) });
						//
					} // for
						//
				} // if
					//
			} catch (final IOException e) {
				//
				if (e != null) {
					//
					e.printStackTrace();
					//
				} // if
					//
			} // try
				//
		} // if
			//
	}

	private static Object getSource(final EventObject instance) {
		return instance != null ? instance.getSource() : null;
	}

	private static void addRow(final DefaultTableModel instance, final Object[] rowData) {
		//
		if (instance == null) {
			//
			return;
			//
		} // if
			//
		final Field field = testAndApply(x -> IterableUtils.size(x) == 1,
				collect(filter(
						stream(testAndApply(Objects::nonNull, getClass(instance), FieldUtils::getAllFieldsList, null)),
						x -> Objects.equals(getName(x), "dataVector")), Collectors.toList()),
				x -> IterableUtils.get(x, 0), null);
		//
		if (field == null || Narcissus.getField(instance, field) != null) {
			//
			instance.addRow(rowData);
			//
		} // if
			//
	}

	private static <T, R, A> R collect(final Stream<T> instance, final Collector<? super T, A, R> collector) {
		return instance != null && (collector != null || Proxy.isProxyClass(getClass(instance)))
				? instance.collect(collector)
				: null;
	}

	private static <T> Stream<T> filter(final Stream<T> instance, final Predicate<? super T> predicate) {
		return instance != null ? instance.filter(predicate) : instance;
	}

	private static String getName(final RuntimeMXBean instance) {
		return instance != null ? instance.getName() : null;
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