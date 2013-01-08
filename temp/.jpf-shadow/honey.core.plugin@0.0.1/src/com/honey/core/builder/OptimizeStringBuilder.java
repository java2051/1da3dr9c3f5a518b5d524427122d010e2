package com.honey.core.builder;


/**
 * 优化jdk的StringBuilder
 * 
 * @author Administrator
 * 
 */
public final class OptimizeStringBuilder implements CharacterBuilder {

	/**
	 * 默认缓冲区容量大小16
	 */
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * 默认的扩容因子
	 */
	private static final float DEFAULT_LOAD_FACTOR = 1.75f;

	/**
	 * 扩容因子
	 */
	private final float loadFactor;

	/**
	 * 缓冲区
	 */
	private char buffer[];

	/**
	 * 缓冲区数量
	 */
	private int count;
	
	/**
	 * 默认构造函数
	 */
	public OptimizeStringBuilder() {
		this(DEFAULT_INITIAL_CAPACITY, -1);
	}

	/**
	 * 构造函数努
	 * @param capacity 初始容量
	 */
	public OptimizeStringBuilder(int capacity) {
		this(capacity, -1);
	}

	/**
	 * 构造函数
	 * @param capacity 初始容量
	 * @param loadFactor 扩容因子
	 */
	public OptimizeStringBuilder(int capacity, float loadFactor) {
		capacity = capacity < 0 ? DEFAULT_INITIAL_CAPACITY : capacity;

		this.loadFactor = loadFactor <= 0 ? DEFAULT_LOAD_FACTOR
				: (loadFactor >= 1 ? 2 : loadFactor + 1);

		buffer = new char[capacity];
	}

	
	@Override
	public int length() {
		return count;
	}
	
	@Override
	public int capacity() {
		return buffer.length;
	}

	/**
	 * 扩充容量,按照扩充因子进行容量扩充
	 * @param minimumCapacity
	 */
	private void expandCapacity(int minimumCapacity) {
		int newCapacity = minimumCapacity > buffer.length ? minimumCapacity
				: buffer.length;
		newCapacity = loadFactor >= 2 ? newCapacity << 1
				: (int) (newCapacity * loadFactor);
		char[] newBuffer = new char[newCapacity];
		System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
		buffer = newBuffer;
	}

	
	public char charAt(int index) {
		if ((index < 0) || (index >= count))
			throw new StringIndexOutOfBoundsException(index);
		return buffer[index];
	}
	
	@Override
	public OptimizeStringBuilder append(Object obj) {
		return append(String.valueOf(obj));
	}

	@Override
	public OptimizeStringBuilder append(String str) {
		if (str == null)
			str = "null";
		int len = str.length();
		if (len == 0)
			return this;
		int newCount = count + len;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		str.getChars(0, len, buffer, count);
		count = newCount;
		return this;
	}

	@Override
	public OptimizeStringBuilder append(java.lang.StringBuffer sb) {
		if (sb == null)
			return append("null");
		int len = sb.length();
		int newCount = count + len;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		sb.getChars(0, len, buffer, count);
		count = newCount;
		return this;
	}

	@Override
	public OptimizeStringBuilder append(java.lang.StringBuilder sb) {
		if (sb == null)
			return append("null");
		int len = sb.length();
		int newCount = count + len;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		sb.getChars(0, len, buffer, count);
		count = newCount;
		return this;
	}

	@Override
	public CharacterBuilder append(CharacterBuilder sb){
		return append(sb.toArray()) ;
	}

	@Override
	public OptimizeStringBuilder append(char str[]) {
		int newCount = count + str.length;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		System.arraycopy(str, 0, buffer, count, str.length);
		count = newCount;
		return this;
	}

	@Override
	public OptimizeStringBuilder append(boolean b) {
		if (b) {
			int newCount = count + 4;
			if (newCount > buffer.length)
				expandCapacity(newCount);
			buffer[count++] = 't';
			buffer[count++] = 'r';
			buffer[count++] = 'u';
			buffer[count++] = 'e';
		} else {
			int newCount = count + 5;
			if (newCount > buffer.length)
				expandCapacity(newCount);
			buffer[count++] = 'f';
			buffer[count++] = 'a';
			buffer[count++] = 'l';
			buffer[count++] = 's';
			buffer[count++] = 'e';
		}
		return this;
	}

	@Override
	public OptimizeStringBuilder append(char c) {
		int newCount = count + 1;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		buffer[count++] = c;
		return this;
	}

	@Override
	public OptimizeStringBuilder append(int i) {
		return append(Integer.toString(i));
	}

	@Override
	public OptimizeStringBuilder append(long l) {
		return append(Long.toString(l));
	}

	@Override
	public OptimizeStringBuilder append(float f) {
		return append(Float.toString(f));

	}

	@Override
	public OptimizeStringBuilder append(double d) {
		return append(Double.toString(d));
	}

	@Override
	public OptimizeStringBuilder delete(int start, int end) {
		if (start < 0)
			throw new StringIndexOutOfBoundsException(start);
		if (end > count)
			end = count;
		if (start > end)
			throw new StringIndexOutOfBoundsException();
		int len = end - start;
		if (len > 0) {
			System.arraycopy(buffer, start + len, buffer, start, count - end);
			count -= len;
		}
		return this;
	}

	
	public OptimizeStringBuilder deleteCharAt(int index) {
		if ((index < 0) || (index >= count))
			throw new StringIndexOutOfBoundsException(index);
		System.arraycopy(buffer, index + 1, buffer, index, count - index - 1);
		count--;
		return this;
	}

	public OptimizeStringBuilder insert(int index, char str[], int offset,
			int len) {
		if ((index < 0) || (index > length()))
			throw new StringIndexOutOfBoundsException(index);
		if ((offset < 0) || (len < 0) || (offset > str.length - len))
			throw new StringIndexOutOfBoundsException("offset " + offset
					+ ", len " + len + ", str.length " + str.length);
		int newCount = count + len;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		System.arraycopy(buffer, index, buffer, index + len, count - index);
		System.arraycopy(str, offset, buffer, index, len);
		count = newCount;
		return this;
	}

	@Override
	public OptimizeStringBuilder insert(int offset, Object obj) {
		return insert(offset, String.valueOf(obj));
	}

	@Override
	public OptimizeStringBuilder insert(int offset, String str) {
		if ((offset < 0) || (offset > length()))
			throw new StringIndexOutOfBoundsException(offset);
		return insert(offset, str.toCharArray());
	}

	@Override
	public OptimizeStringBuilder insert(int offset, char str[]) {
		if ((offset < 0) || (offset > length()))
			throw new StringIndexOutOfBoundsException(offset);
		int len = str.length;
		int newCount = count + len;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		System.arraycopy(buffer, offset, buffer, offset + len, count - offset);
		System.arraycopy(str, 0, buffer, offset, len);
		count = newCount;
		return this;
	}

	@Override
	public OptimizeStringBuilder insert(int offset, boolean b) {
		return insert(offset, String.valueOf(b));
	}

	@Override
	public OptimizeStringBuilder insert(int offset, char c) {
		int newCount = count + 1;
		if (newCount > buffer.length)
			expandCapacity(newCount);
		System.arraycopy(buffer, offset, buffer, offset + 1, count - offset);
		buffer[offset] = c;
		count = newCount;
		return this;
	}

	@Override
	public OptimizeStringBuilder insert(int offset, int i) {
		return insert(offset, String.valueOf(i));
	}

	@Override
	public OptimizeStringBuilder insert(int offset, long l) {
		return insert(offset, String.valueOf(l));
	}

	@Override
	public OptimizeStringBuilder insert(int offset, float f) {
		return insert(offset, String.valueOf(f));
	}

	@Override
	public OptimizeStringBuilder insert(int offset, double d) {
		return insert(offset, String.valueOf(d));
	}

	
	public int indexOf(String str) {
		return indexOf(str, 0);
	}

	public int indexOf(String str, int fromIndex) {
		return indexOf(buffer, 0, count, str.toCharArray(), 0, str.length(),
				fromIndex);
	}

	public int lastIndexOf(String str) {
		return lastIndexOf(str, count);
	}

	public int lastIndexOf(String str, int fromIndex) {
		return lastIndexOf(buffer, 0, count, str.toCharArray(), 0,
				str.length(), fromIndex);
	}

	@Override
	public char[] toArray() {
		char []c= new char [count];
		System.arraycopy(buffer, 0, c, 0, count);
		return c;
	}

	/**
	 * 计算字符输在在另外一个字符数组中首次出现的位置
	 * 
	 * @param source
	 * @param sourceOffset
	 * @param sourceCount
	 * @param target
	 * @param targetOffset
	 * @param targetCount
	 * @param fromIndex
	 * @return
	 */
	private static int indexOf(char[] source, int sourceOffset,
			int sourceCount, char[] target, int targetOffset, int targetCount,
			int fromIndex) {
		if (fromIndex >= sourceCount) {
			return (targetCount == 0 ? sourceCount : -1);
		}
		if (fromIndex < 0) {
			fromIndex = 0;
		}
		if (targetCount == 0) {
			return fromIndex;
		}

		char first = target[targetOffset];
		int max = sourceOffset + (sourceCount - targetCount);

		for (int i = sourceOffset + fromIndex; i <= max; i++) {
			/* Look for first character. */
			if (source[i] != first) {
				while (++i <= max && source[i] != first)
					;
			}

			/* Found first character, now look at the rest of v2 */
			if (i <= max) {
				int j = i + 1;
				int end = j + targetCount - 1;
				for (int k = targetOffset + 1; j < end
						&& source[j] == target[k]; j++, k++)
					;

				if (j == end) {
					/* Found whole string. */
					return i - sourceOffset;
				}
			}
		}
		return -1;
	}

	/**
	 * 计算字符输在在另外一个字符数组中最后一次出现的位置
	 * 
	 * @param source
	 * @param sourceOffset
	 * @param sourceCount
	 * @param target
	 * @param targetOffset
	 * @param targetCount
	 * @param fromIndex
	 * @return
	 */
	private static int lastIndexOf(char[] source, int sourceOffset,
			int sourceCount, char[] target, int targetOffset, int targetCount,
			int fromIndex) {
		/*
		 * Check arguments; return immediately where possible. For consistency,
		 * don't check for null str.
		 */
		int rightIndex = sourceCount - targetCount;
		if (fromIndex < 0) {
			return -1;
		}
		if (fromIndex > rightIndex) {
			fromIndex = rightIndex;
		}
		/* Empty string always matches. */
		if (targetCount == 0) {
			return fromIndex;
		}

		int strLastIndex = targetOffset + targetCount - 1;
		char strLastChar = target[strLastIndex];
		int min = sourceOffset + targetCount - 1;
		int i = min + fromIndex;

		startSearchForLastChar: while (true) {
			while (i >= min && source[i] != strLastChar) {
				i--;
			}
			if (i < min) {
				return -1;
			}
			int j = i - 1;
			int start = j - (targetCount - 1);
			int k = strLastIndex - 1;

			while (j > start) {
				if (source[j--] != target[k--]) {
					i--;
					continue startSearchForLastChar;
				}
			}
			return start - sourceOffset + 1;
		}
	}
	
	@Override
	public String toString() {
		if(count >0){
			return new String(buffer, 0, count);
		}else{
			return "";
		}
	}

	@Override
	public CharacterBuilder clear() {
		count = 0;

		return this;
	}

	@Override
	public CharacterBuilder delete(int index) {
		return deleteCharAt(index);
	}

	@Override
	public CharacterBuilder insert(int index, java.lang.StringBuilder sb) {
		return insert(index, sb.toString());
	}

	@Override
	public CharacterBuilder insert(int index, StringBuffer sb) {
		return insert(index, sb.toString());
	}
}
