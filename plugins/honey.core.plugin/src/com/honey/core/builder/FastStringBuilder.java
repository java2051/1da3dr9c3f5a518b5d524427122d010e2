package com.honey.core.builder;

/**
 * 优化StringBuilder,实现StringBuilder大部分方法.
 * 
 * @author Administrator
 * 
 */
public final class FastStringBuilder implements CharacterBuilder , CharSequence, Appendable,
		java.io.Serializable {

	private static final long serialVersionUID = -3281631510453736993L;

	/**
	 * 默认的缓冲区组为4;
	 */
	private static final int DEFAULT_INITIAL_GROUP = 4;

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
	 * 缓冲区组
	 * 
	 */
	private char[][] buffers = null;

	/**
	 * 当前缓冲区组的数量
	 */
	private int buffersCount;

	/**
	 * 当前活动换区在缓冲区组的位置
	 */
	private int currentBufferIndex = -1;

	/**
	 * 缓冲区
	 */
	private char[] currentBuffer;

	/**
	 * 缓冲区偏移量
	 */
	private int offset;

	/**
	 * 缓冲区字符大小
	 */
	private int count;

	/**
	 * 
	 * @param group
	 *            缓冲区组数量
	 * @param capacity
	 *            初始容量
	 * @param loadFactor
	 *            容量扩充因子,取值范围:<br />
	 *            小于等于0 : 使用默认值<br />
	 *            大于0 小于1 : 容量按照capacity*(1+loadFactor) * (1+loadFactor)扩充 大于等于1 :
	 *            容量按照buffer.length*2扩从
	 * 
	 */
	public FastStringBuilder(int group, int capacity, float loadFactor) {

		buffers = new char[group > 0 ? group : DEFAULT_INITIAL_GROUP][];

		capacity = capacity < 0 ? DEFAULT_INITIAL_CAPACITY : capacity;

		this.loadFactor = loadFactor <= 0 ? DEFAULT_LOAD_FACTOR
				: (loadFactor >= 1 ? 2 : loadFactor + 1);

		// Initialization buffer
		currentBufferIndex++;
		currentBuffer = new char[capacity];
		offset = 0;
		buffers[currentBufferIndex] = currentBuffer;
		buffersCount++;
	}

	/**
	 * 使用默认的参数
	 */
	public FastStringBuilder() {
		this(DEFAULT_INITIAL_CAPACITY);
	}

	/**
	 * 指定初始的容量
	 * 
	 * @param capacity
	 */
	public FastStringBuilder(int capacity) {
		this(-1, capacity, -1);
	}

	/**
	 * 指定初始容量和和扩从因子
	 * 
	 * @param capacity
	 * @param loadFactor
	 */
	public FastStringBuilder(int capacity, int loadFactor) {
		this(-1, capacity, loadFactor);
	}

	/**
	 * 扩容
	 * 
	 * @param capacity
	 *            扩从容量
	 */
	private void expandCapacity(int capacity) {
		if (currentBufferIndex < buffersCount - 1) { // recycling old buffer
			offset = 0;
			currentBufferIndex++;
			currentBuffer = buffers[currentBufferIndex];
		} else { // 创建新数组
			int newBufferSize = currentBuffer.length >= (capacity - count) ? currentBuffer.length
					: capacity - count;

			newBufferSize = loadFactor >= 2 ? newBufferSize << 1
					: (int) (newBufferSize * loadFactor);

			currentBufferIndex++;
			currentBuffer = new char[newBufferSize];
			offset = 0;

			// 平移数组
			if (currentBufferIndex >= buffers.length) {
				int newLen = buffers.length << 1;
				char[][] newBuffers = new char[newLen][];
				System.arraycopy(buffers, 0, newBuffers, 0, buffers.length);
				buffers = newBuffers;
			}
			buffers[currentBufferIndex] = currentBuffer;
			buffersCount++;
		}
	}

	/**
	 * 追加数组
	 * 
	 * @param array
	 * @param off
	 * @param len
	 * @return
	 */
	private FastStringBuilder append(char[] array, int off, int len) {
		int end = off + len;
		if (len == 0) {
			return this;
		}
		int newCount = count + len;
		int remaining = len;
		while (remaining > 0) {
			int part = Math.min(remaining, currentBuffer.length - offset);
			System.arraycopy(array, end - remaining, currentBuffer, offset,
					part);
			remaining -= part;
			offset += part;
			count += part;
			if (remaining > 0) {
				expandCapacity(newCount);
			}
		}
		return this;
	}

	/**
	 * 追加字符数组
	 * 
	 * @param array
	 * @return
	 */
	public FastStringBuilder append(char[] array) {
		return append(array, 0, array.length);
	}

	/**
	 * 追加字符
	 */
	public FastStringBuilder append(char element) {
		if (offset == currentBuffer.length) {
			expandCapacity(count + 1);
		}

		currentBuffer[offset] = element;
		offset++;
		count++;

		return this;
	}

	/**
	 * 追加自己
	 * 
	 * @param buff
	 *            自己
	 * @return
	 */
	public FastStringBuilder append(FastStringBuilder buff) {
		for (int i = 0; i < buff.currentBufferIndex; i++) {
			append(buff.buffers[i]);
		}
		append(buff.currentBuffer, 0, buff.offset);
		return this;
	}

	/**
	 * 追加字符串
	 * 
	 * @param string
	 *            字符串
	 * @return
	 */
	public FastStringBuilder append(String string) {
		if (string == null)
			string = "null";
		int len = string.length();
		if (len == 0) {
			return this;
		}
		int newCount = count + len/* 扩容长度 */, beginPoint = 0 /* 起点 */, endPoint = 0/* 终点 */;
		while (endPoint < len) {
			// 计算起点, 当前起点就是上次的终点.
			beginPoint = endPoint;

			// 计算当前容量的最小值 + 起点 = 当前终点
			endPoint = Math.min(len - endPoint, currentBuffer.length - offset)
					+ beginPoint;

			// 复制字符串
			string.getChars(beginPoint, endPoint, currentBuffer, offset);

			// 终点 - 起点 就是当前的偏移量
			offset += endPoint - beginPoint;

			if (endPoint < len) {
				expandCapacity(newCount);
			}
		}
		count += len;
		return this;
	}

	/**
	 * 追加 StringBuffer
	 * 
	 * @param sb
	 * @return
	 */
	public FastStringBuilder append(StringBuffer sb) {
		if (sb == null)
			return append("null");
		int len = sb.length();
		if (len == 0) {
			return this;
		}
		int newCount = count + len/* 扩容长度 */, beginPoint = 0 /* 起点 */, endPoint = 0/* 终点 */;
		while (endPoint < len) {
			// 计算起点, 当前起点就是上次的终点.
			beginPoint = endPoint;

			// 计算当前容量的最小值 + 起点 = 当前终点
			endPoint = Math.min(len - endPoint, currentBuffer.length - offset)
					+ beginPoint;

			// 复制字符串
			sb.getChars(beginPoint, endPoint, currentBuffer, offset);

			// 终点 - 起点 就是当前的偏移量
			offset += endPoint - beginPoint;

			if (endPoint < len) {
				expandCapacity(newCount);
			}
		}
		count += len;
		return this;
	}

	/**
	 * 追加 StringBuilder
	 * 
	 * @param sb
	 * @return
	 */
	public FastStringBuilder append(java.lang.StringBuilder sb) {
		if (sb == null)
			return append("null");
		int len = sb.length();
		if (len == 0) {
			return this;
		}
		int newCount = count + len/* 扩容长度 */, beginPoint = 0 /* 起点 */, endPoint = 0/* 终点 */;
		while (endPoint < len) {
			// 计算起点, 当前起点就是上次的终点.
			beginPoint = endPoint;

			// 计算当前容量的最小值 + 起点 = 当前终点
			endPoint = Math.min(len - endPoint, currentBuffer.length - offset)
					+ beginPoint;

			// 复制字符串
			sb.getChars(beginPoint, endPoint, currentBuffer, offset);

			// 终点 - 起点 就是当前的偏移量
			offset += endPoint - beginPoint;

			if (endPoint < len) {
				expandCapacity(newCount);
			}
		}
		count += len;
		return this;
	}

	/**
	 * 追加int类型的数
	 * 
	 * @param i
	 * @return
	 */
	public FastStringBuilder append(int i) {
		return append(Integer.toString(i));
	}

	/**
	 * 追加long类型的数
	 * 
	 * @param l
	 * @return
	 */
	public FastStringBuilder append(long l) {
		return append(Long.toString(l));
	}

	/**
	 * 追加short类型的数
	 * 
	 * @param s
	 * @return
	 */
	public FastStringBuilder append(short s) {
		return append(Short.toString(s));
	}

	/**
	 * 追加double类型的数
	 * 
	 * @param d
	 * @return
	 */
	public FastStringBuilder append(double d) {
		return append(Double.toString(d));
	}

	/**
	 * 追加float类型的数
	 * 
	 * @param f
	 * @return
	 */
	public FastStringBuilder append(float f) {
		return append(Float.toString(f));
	}

	/**
	 * 追加boolean类型的数
	 * 
	 * @param s
	 * @return
	 */
	public FastStringBuilder append(boolean b) {
		if (b) {
			append(new char[] { 't', 'r', 'u', 'e' });
		} else {
			append(new char[] { 'f', 'a', 'l', 's', 'e' });
		}

		return this;
	}

	/**
	 * 追加Object
	 * 
	 * @param obj
	 * @return
	 */
	public FastStringBuilder append(Object obj) {
		return append(String.valueOf(obj));
	}

	/**
	 * 重置缓冲区,缓冲区中的内容没有清空,只是把缓冲区的偏移量置0;
	 */
	public FastStringBuilder clear() {
		count = offset = currentBufferIndex = 0;
		currentBuffer = buffers[0];
		buffersCount = 1;
		
		return this;
	}

	/**
	 * 返回字符串数组
	 * 
	 * @return
	 */
	public char[] toArray() {
		int remaining = count, pos = 0, c = 0;
		char[] array = new char[count];
		for (char[] buf : buffers) {
			c = buf.length <= remaining ? buf.length : remaining;
			System.arraycopy(buf, 0, array, pos, c);
			pos += c;
			remaining -= c;
			if (remaining == 0) {
				break;
			}
		}
		return array;
	}

	/**
	 * 返回字符串数组
	 * 
	 * @param start
	 *            起始位置
	 * @param len
	 *            长度
	 * @return
	 */
	public char[] toArray(int start, int len) {
		int remaining = len, pos = 0;
		char[] array = new char[len];
		if (len == 0)
			return array;

		int i = 0;
		while (start >= buffers[i].length) {
			start -= buffers[i].length;
			i++;
		}

		while (i < buffersCount) {
			char[] buf = buffers[i];
			int c = Math.min(buf.length - start, remaining);
			System.arraycopy(buf, start, array, pos, c);
			pos += c;
			remaining -= c;
			if (remaining == 0) {
				break;
			}
			start = 0;
			i++;
		}
		return array;
	}

	/**
	 * 获取指定位置的字符
	 * 
	 * @param index
	 * @return
	 */
	public char get(int index) {
		if (index >= count) {
			throw new IndexOutOfBoundsException();
		}
		int ndx = 0;
		while (true) {
			char[] b = buffers[ndx];
			if (index < b.length) {
				return b[index];
			}
			ndx++;
			index -= b.length;
		}
	}

	/**
	 * 删除指定位置的字符
	 * 
	 * @param index
	 * @return
	 */
	public FastStringBuilder delete(int index) {
		if (index >= count)
			throw new IndexOutOfBoundsException();
		int ndx = 0;
		char[] b = null, nc = null;
		while (true) {
			b = buffers[ndx];
			if (index < b.length) {
				nc = new char[b.length - 1];
				if (index > 0)
					System.arraycopy(b, 0, nc, 0, index);
				System.arraycopy(b, index + 1, nc, index, b.length - index - 1);
				buffers[ndx] = nc;
				count--;
				if (ndx == currentBufferIndex) {
					currentBuffer = nc;
					offset--;
				}
				return this;
			}
			ndx++;
			index -= b.length;
		}
	}

	public FastStringBuilder deleteCharAt(int index) {
		return delete(index);
	}

//	public FastStringBuilder delete(int index, int length) {
//		if (index >= count) {
//			throw new IndexOutOfBoundsException();
//		}
//		int ndx = 0;
//		while (true) {
//			char[] b = buffers[ndx];
//			if (index < b.length) {
//				if(length <= b.length-index ){
//					//不跨域
//					
//					
//				}else{
//					//跨域;
//				}
//					
//				
//				
//				
//				
//			}
//			ndx++;
//			index -= b.length;
//		}
//
//	}
//	
	
	public static void main(String[] args) {
		FastStringBuilder sb = new FastStringBuilder(5, 3, .4f);
		sb.append("ABCDEFGHIJKTE#G%#D#FDFEERE");
		for(int i =0 ;i< 10;i++){
			sb.delete(0);
			System.out.println(sb);
		}
		sb.append("123123123123123123123123123123123123123123123123");
		System.out.println(sb);
		
		java.lang.StringBuilder bu = new java.lang.StringBuilder();
		bu.append("ABCDEFGHIJKTE#G%#D#FDFEERE");
		System.out.println(bu.delete(1, 3));
		
	}

	
	
	/**
	 * 插入数组
	 */
	public FastStringBuilder insert(int index, char[] c) {
		if (index >= count)
			return append(c);
		int ndx = 0;
		char[] b = null, nc = null;
		while (true) {
			b = buffers[ndx];
			if (index < b.length) {
				nc = new char[b.length + c.length];
				if (index > 0)
					System.arraycopy(b, 0, nc, 0, index);
				System.arraycopy(c, 0, nc, index, c.length);
				System.arraycopy(b, index, nc, index + c.length, b.length
						- index);
				buffers[ndx] = nc;
				count += c.length;
				if (ndx == currentBufferIndex) {
					currentBuffer = nc;
					offset += c.length;
				}
				return this;
			}
			ndx++;
			index -= b.length;
		}
	}

	/**
	 * 插入字符串
	 * 
	 * @param index
	 * @param string
	 * @return
	 */
	public FastStringBuilder insert(int index, String string) {
		if (index >= count)
			return append(string);
		int ndx = 0, len = string.length();
		char[] b = null, nc = null;
		while (true) {
			b = buffers[ndx];
			if (index < b.length) {
				nc = new char[b.length + len];
				if (index > 0)
					System.arraycopy(b, 0, nc, 0, index);
				string.getChars(0, len, nc, index);
				System.arraycopy(b, index, nc, index + len, b.length - index);
				buffers[ndx] = nc;
				count += len;
				if (ndx == currentBufferIndex) {
					currentBuffer = nc;
					offset += len;
				}
				return this;
			}
			ndx++;
			index -= b.length;
		}
	}

	/**
	 * 插入 StringBuilder
	 * 
	 * @param index
	 * @param builder
	 * @return
	 */
	public FastStringBuilder insert(int index, java.lang.StringBuilder builder) {
		if (index >= count)
			return append(builder);
		int ndx = 0, len = builder.length();
		char[] b = null, nc = null;
		while (true) {
			b = buffers[ndx];
			if (index < b.length) {
				nc = new char[b.length + len];
				if (index > 0)
					System.arraycopy(b, 0, nc, 0, index);
				builder.getChars(0, len, nc, index);
				System.arraycopy(b, index, nc, index + len, b.length - index);
				buffers[ndx] = nc;
				count += len;
				if (ndx == currentBufferIndex) {
					currentBuffer = nc;
					offset += len;
				}
				return this;
			}
			ndx++;
			index -= b.length;
		}
	}

	/**
	 * 
	 * 插入StringBuffer
	 * 
	 * @param index
	 * @param buffer
	 * @return
	 */
	public FastStringBuilder insert(int index, StringBuffer buffer) {
		if (index >= count)
			return append(buffer);
		int ndx = 0, len = buffer.length();
		char[] b = null, nc = null;
		while (true) {
			b = buffers[ndx];
			if (index < b.length) {
				nc = new char[b.length + len];
				if (index > 0)
					System.arraycopy(b, 0, nc, 0, index);
				buffer.getChars(0, len, nc, index);
				System.arraycopy(b, index, nc, index + len, b.length - index);
				buffers[ndx] = nc;
				count += len;
				if (ndx == currentBufferIndex) {
					currentBuffer = nc;
					offset += len;
				}
				return this;
			}
			ndx++;
			index -= b.length;
		}
	}

	/**
	 * 插入 int
	 * 
	 * @param index
	 * @param i
	 * @return
	 */
	public FastStringBuilder insert(int index, int i) {
		return insert(index, Integer.toString(i));
	}

	/**
	 * 插入 long
	 * 
	 * @param index
	 * @param l
	 * @return
	 */
	public FastStringBuilder insert(int index, long l) {
		return insert(index, Long.toString(l));
	}

	/**
	 * 插入 float
	 * 
	 * @param index
	 * @param f
	 * @return
	 */
	public FastStringBuilder insert(int index, float f) {
		return insert(index, Float.toString(f));
	}

	/**
	 * 插入 double
	 * 
	 * @param index
	 * @param d
	 * @return
	 */
	public FastStringBuilder insert(int index, double d) {
		return insert(index, Double.toString(d));
	}

	/**
	 * 插入 object
	 * 
	 * @param index
	 * @param obj
	 * @return
	 */
	public FastStringBuilder insert(int index, Object obj) {
		return insert(index, String.valueOf(obj));
	}

	/**
	 * 插入boolean
	 * 
	 * @param index
	 * @param b
	 * @return
	 */
	public FastStringBuilder insert(int index, boolean b) {
		char[] c = null;
		if (b)
			c = new char[] { 't', 'r', 'u', 'e' };
		else
			c = new char[] { 'f', 'a', 'l', 's', 'e' };
		return insert(index, c);
	}

	/**
	 * 插入 char
	 * 
	 * @param index
	 * @param c
	 * @return
	 */
	public FastStringBuilder insert(int index, char c) {
		return insert(index, new char[] { c });
	}

	/**
	 * 插入自己
	 * 
	 * @param buff
	 *            自己
	 * @return
	 */
	public FastStringBuilder insert(int index, FastStringBuilder buff) {
		insert(index, buff.toArray());
		return this;
	}

	/**
	 * 长度
	 */
	public int length() {
		return count;
	}

	/**
	 * 长度
	 * 
	 * @return
	 */
	public int size() {
		return count;
	}

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * 字符串首次出现位置
	 * 
	 * @param str
	 * @return
	 */
	public int indexOf(String str) {
		return indexOf(str, 0);
	}

	/**
	 * 字符串首次出现位置
	 * 
	 * @param str
	 * @param fromIndex
	 * @return
	 */
	public int indexOf(String str, int fromIndex) {
		return indexOf(this.toArray(), 0, count, str.toCharArray(), 0, str
				.length(), fromIndex);

	}

	/**
	 * 字符串最后一次出现位置
	 * 
	 * @param str
	 * @return
	 */
	public int lastIndexOf(String str) {
		return lastIndexOf(str, count);
	}

	/**
	 * 字符串最后一次出现位置
	 * 
	 * @param str
	 * @param fromIndex
	 * @return
	 */
	public int lastIndexOf(String str, int fromIndex) {
		return lastIndexOf(this.toArray(), 0, count, str.toCharArray(), 0, str
				.length(), fromIndex);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new String(toArray());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.CharSequence#charAt(int)
	 */
	@Override
	public char charAt(int index) {
		return get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.CharSequence#subSequence(int, int)
	 */
	@Override
	public CharSequence subSequence(int start, int end) {
		int len = end - start;
		return new java.lang.StringBuilder(len).append(toArray(start, len));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Appendable#append(java.lang.CharSequence)
	 */
	@Override
	public FastStringBuilder append(CharSequence csq) {
		append(csq, 0, csq.length());
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Appendable#append(java.lang.CharSequence, int, int)
	 */
	@Override
	public FastStringBuilder append(CharSequence csq, int start, int end) {
		for (int i = start; i < end; i++) {
			append(csq.charAt(i));
		}
		return this;
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
	public int capacity() {
		
		return 0;
	}

	@Override
	public CharacterBuilder delete(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CharacterBuilder append(CharacterBuilder sb) {
		
		return append(sb.toArray());
	}

	
}
