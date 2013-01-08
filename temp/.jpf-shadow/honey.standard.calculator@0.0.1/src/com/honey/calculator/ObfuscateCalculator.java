package com.honey.calculator;


/**
 * 字符串混淆计算器
 * @author Administrator
 *
 */
class ObfuscateCalculator  implements StandardCalculator{
	
	private static final char SPECIAL_SUFFIX = '_';
	
	private static final char []ALPHABET = {
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
		'0','1','2','3','4','5','6','7','8','9','_'
	};
	
	private static final String []MAPPING = {
		"D","n","g","W","5","P","o","_","J","oOo","w","v","x","q","l","d","H","X","4","z","m","I","O","Y","A","V",
		"6","y","Q","k","t","h","F","L","S","9","c","p","K","U","i","8","a","7","Z","M","f","1","N","3","s","R",
		"r","G","u","b","C","2","B","T","e","j","E",
		"Sf","2I","f2","1K","PW","Lj","I_","NC","BA","Rc","Ht","Gx","1e","zK","fq","g1","hH","U2","Xi","Cl","KB",
		"mR","jr","Tv","LI","cG","Hj","7y","XZ","sp","Gs","LR","S1","je","wy","fs","fv"
	};
	
	private HashArithmetic hash=null; 

	public ObfuscateCalculator(){
		this.hash=HashArithmetic.FNVHash; 
	}
	
	public ObfuscateCalculator(HashArithmetic hash){
		this.hash = hash;
	}
	
	/**
	 * 字符串混淆
	 * @param str 字符串
	 * @return
	 */
	public String calculator (boolean underline,String ...str){
		if( str != null && str.length > 0 ){
			String s="";
			for(String tmp : str ){
				s += tmp ;
			}
			StringBuilder answer = new StringBuilder();
			int hash = this.hash.getHash().code(s, 0);
			answer.append(s.charAt(0));
			int nHash =  str.hashCode();
			nHash = (nHash>0?nHash:0-nHash)%5;
			for(int i=0;i<nHash;i++ ){
				answer.append(SPECIAL_SUFFIX);
			}
			if(hash<0){
				answer.append(SPECIAL_SUFFIX).append("_");
				hash = 0-hash;
			}
			int num = hash ;
			StringBuilder sb = new StringBuilder();
			do{
				sb.insert(0, MAPPING[num%100]);
				num = num/100;
			}while(num>=100);
			sb.insert(0, MAPPING[num%100]);
			answer.append(sb);
			answer.append(SPECIAL_SUFFIX).append(ALPHABET[ hash%62 ]);
			return answer.toString();
		}else{
			return null;
		}
	}

	public enum HashArithmetic{
		
		/**
		 * java 提供hash算法
		 */
		NativeHash(){
			Hash getHash(){
				return new Hash(){
					@Override
					public int code(String str,long solt) {
						int  answer =(int) (str.hashCode() + (int)solt);
						return answer;
					}
				};
			}
		} ,

		/**
		 * 加盐后的BKDR Hash算法(初始值是盐值,如果盐值是0那么就变成标准的BKDR Hash算法)
		 * @author Administrator
		 *
		 */
		BKDRHash(){
			Hash getHash(){
				return new Hash(){
					@Override
					public int code(String str,long solt) {
						//long seed = 131; // 31 131 1313 13131 131313 etc..
						long seed = 21313 ;// 2113 is prime number 
						long hash = solt;
						char []c = str.toCharArray();
						for (int i = 0,size=c.length; i < size; i++) {
							hash = (hash * seed) + c[i];
						}
						return (int)(hash & 0x7FFFFFFF);
					}
				};
			}
		} ,
		
		/**
		 * FNV_1a hash算法<br />
		 * 
		 * @see http://www.isthe.com/chongo/tech/comp/fnv/ 
		 * @see http://en.wikipedia.org/wiki/Fowler_Noll_Vo_hash 
		 * @author Administrator
		 *
		 */
		FNVHash(){
			Hash getHash(){
				return new Hash(){
					private static final long FNV_32_INIT = 2166136261L;
				    
				    private static final long FNV_32_PRIME = 16777619;  //16777619 is prime number  
				    
					@Override
					public int code(String str,long solt) {
						long hash = FNV_32_INIT;
						hash = hash+solt;
			            char c[] = str.toCharArray();
			            for (int i = 0,size=c.length ; i < size; i++) {  
			                hash ^= c[i];  
			                hash *= FNV_32_PRIME;
			                //
			                hash += hash << 13;
			                hash ^= hash >> 7;
			                hash += hash << 3;
			                hash ^= hash >> 17;
			                hash += hash << 5;
			            }
			            //hash = hash+solt;
			            return (int)hash;
					}
				};
			}
		} ,
		;
		private Hash hash;
		
		abstract  Hash getHash() ;		
	}
	
	/**
	 * hash 算法
	 * @author Administrator
	 *
	 */
	interface Hash{
		
		/**
		 * 计算字符串的hash值
		 * @param str 字符串
		 * @param solt 盐
		 * @return
		 */
		int code(String str,long solt) ;
	}
}
