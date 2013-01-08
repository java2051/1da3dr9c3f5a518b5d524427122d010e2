package com.honey.core.builder;



/**
 * 
 * 代码中大量使用StringBuilder,而FastStringBuilder针对StringBuilder的特殊优化
 * 
 * @author Administrator
 * 
 */
public final class CharacterBuilderFactory {

	/**
	 * 返回系统默认的StringBuilder.
	 * 
	 * @return
	 */
	public static CharacterBuilder createDefaultStringBuilder() {

		return Capacity.DEFAULT.createBuilder();
	}
	
	/**
	 * 初始容量8
	 * @return
	 */
	public static CharacterBuilder createC8StringBuilder() {
		return Capacity.C8.createBuilder();
	}
	
	/**
	 * 初始容量16
	 * @return
	 */
	public static CharacterBuilder createC16StringBuilder() {
		return Capacity.C16.createBuilder();
	}
	
	/**
	 * 初始容量32
	 * @return
	 */
	public static CharacterBuilder createC32StringBuilder() {
		return Capacity.C32.createBuilder();
	}


	/**
	 * 初始容量64
	 * @return
	 */
	public static CharacterBuilder createC64StringBuilder() {
		return Capacity.C64.createBuilder();
	}

	/**
	 * 初始容量128
	 * @return
	 */
	public static CharacterBuilder createC128StringBuilder() {
		return Capacity.C128.createBuilder();
	}

	/**
	 * 初始容量256
	 * @return
	 */
	public static CharacterBuilder createC256StringBuilder() {
		return Capacity.C256.createBuilder();
	}

	/**
	 * 初始容量512
	 * @return
	 */
	public static CharacterBuilder createC512StringBuilder() {
		return Capacity.C512.createBuilder();
	}

	/**
	 * 初始容量1024
	 * @return
	 */
	public static CharacterBuilder createC1024StringBuilder() {
		return Capacity.C1024.createBuilder();
	}

	/**
	 * 初始容量1K
	 * @return
	 */
	public static CharacterBuilder createC1KStringBuilder() {
		return Capacity.C1024.createBuilder();
	}

	/**
	 * 初始容量2048
	 * @return
	 */
	public static CharacterBuilder createC2048StringBuilder() {
		return Capacity.C2048.createBuilder();
	}

	/**
	 * 初始容量2K
	 * @return
	 */
	public static CharacterBuilder createC2KStringBuilder() {
		return Capacity.C2048.createBuilder();
	}

	/**
	 * 初始容量4096
	 * @return
	 */
	public static CharacterBuilder createC4096StringBuilder() {
		return Capacity.C4096.createBuilder();
	}
	
	/**
	 * 初始容量4k
	 * @return
	 */
	public static CharacterBuilder createC4KStringBuilder() {
		return Capacity.C4096.createBuilder();
	}

	/**
	 * 初始容量8192
	 * @return
	 */
	public static CharacterBuilder createC8192StringBuilder() {
		return Capacity.C8192.createBuilder();
	}

	/**
	 * 初始容量8k
	 * @return
	 */
	public static CharacterBuilder createC8KStringBuilder() {
		return Capacity.C8192.createBuilder();
	}

	/**
	 * 初始容量10240
	 * @return
	 */
	public static CharacterBuilder createC10240StringBuilder() {
		return Capacity.C10240.createBuilder();
	}
	
	/**
	 * 初始容量10K
	 * @return
	 */
	public static CharacterBuilder createC10KStringBuilder() {
		return Capacity.C10240.createBuilder();
	}

	/**
	 * 初始容量16384
	 * @return
	 */
	public static CharacterBuilder createC16384StringBuilder() {
		return Capacity.C16384.createBuilder();
	}

	/**
	 * 初始容量16K
	 * @return
	 */
	public static CharacterBuilder createC16KStringBuilder() {
		return Capacity.C16384.createBuilder();
	}
	
	/**
	 * 
	 * CharacterBuilder的容量扩充值,
	 * 
	 * @author Administrator
	 * 
	 */
	public enum Capacity {
		
		/**
		 * jdk默认长度是16,扩因子是2
		 */
		DEFAULT(8, 16 , 2),
		
		/**
		 * 8位长度
		 */
		C8(8, 8, 0.75f),
		
		/**
		 * 16位长度
		 */
		C16(8, 16, 0.75f),
		
		/**
		 * 32位长度
		 */
		C32(8, 32, 0.75f),
		
		/**
		 * 64位长度
		 */
		C64(8, 64, 0.75f),
		
		/**
		 * 128位长度
		 */
		C128(8, 128, 0.75f),
		
		/**
		 * 256位长度
		 */
		C256(8, 256, 0.75f),
		
		/**
		 * 512位长度
		 */
		C512(8, 512, 0.75f),
		
		/**
		 * 1024位长度
		 */
		C1024(4, 1024, 0.75f), //1K
		
		/**
		 * 2048位长度
		 */
		C2048(4, 2048, 0.75f),//2K
		
		/**
		 * 4096位长度
		 */
		C4096(4, 4096, 0.75f),//4K
		
		/**
		 * 8192位长度
		 */
		C8192(2, 8192, 0.75f),//8K
		
		/**
		 * 10240位长度
		 */
		C10240(2, 10240, 0.75f),//10K
		
		/**
		 * 16384位长度
		 */
		C16384(2, 16384, 0.75f),//16K
		
		;
		
		private final int group;
		/**
		 * 容量
		 */
		private final int capacity;
		
		/**
		 * 扩充因子
		 */
		private final float loadFactor ;
		
		
		
		private Capacity(int group,int capacity, float loadFactor) {
			this.group = group;
			this.capacity = capacity;
			this.loadFactor = loadFactor;
		}
		
		public CharacterBuilder createBuilder(){
			//return new FastStringBuilder(this.group,this.capacity,this.loadFactor );
			return new OptimizeStringBuilder(this.capacity,this.loadFactor );
		}
		
		public int getCapacity() {
			return capacity;
		}
		
	}
	
	
	public static void main(String []args){
		
		CharacterBuilder sb = CharacterBuilderFactory.createC16StringBuilder();
		System.out.println(sb.append("wqew"));
		
		sb = CharacterBuilderFactory.createC16StringBuilder();
		System.out.println(sb.append("234234"));

	}
}
