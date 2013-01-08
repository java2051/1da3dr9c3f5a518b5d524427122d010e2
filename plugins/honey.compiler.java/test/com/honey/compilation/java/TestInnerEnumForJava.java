package com.honey.compilation.java;

import java.util.Date;

import com.sun.istack.internal.NotNull;

public class TestInnerEnumForJava  {

    /**
     * 默认的构造函数
     * 
     */
    public TestInnerEnumForJava(  ) {
        // TODO Auto-generated method stub;
    }

    /**
     * 内部枚举类的内部类
     */
    @Deprecated
    private static final class InnerClasstest  {
        int filed1;

        public static final long filed2 = 0L;

        /**内部枚举类的内部类的字段 */
        public static final long filed3 = 0L;
    }

    /**
     * 测试内部枚举类,注释1
     * 测试内部枚举类,注释2
     * 测试内部枚举类,注释3
     */
    public enum TestEnum {

        /**
         * 枚举字段1
         */
        @Deprecated
        @NotNull
        ENUM_FIELD_1( "ABC", 12, new Date(), (short)1, 1D ), 

        /**
         * 枚举字段2
         */
        ENUM_FIELD_2( "ABC", 12, new Date(), (short)2, 1D );
        /**测试属性1 */
        int value;

        private String emf0;

        private int emf1;

        private Date emf2;

        private short emf3;

        private double emf4;


        static{
            System.out.println(2);
            System.out.println(2);
        }


        static{
            System.out.println(3);
            System.out.println(3);
        }

        /**
         * @return
         */
        public synchronized void testMethod(  ) {
            synchronized (this) {
                int i=0;
            }
        }

        /**
         * @param emf0 {java.lang.String} 
         * @param emf1 {int} 
         * @param emf2 {java.util.Date} 
         * @param emf3 {short} 
         * @param emf4 {double} 
         * 
         */
        private TestEnum( String emf0, int emf1, Date emf2, short emf3, double emf4 ) {
            this.emf0 = emf0;
            this.emf1 = emf1;
            this.emf2 = emf2;
            this.emf3 = emf3;
            this.emf4 = emf4;
        }

        /**
         * 内部枚举类的内部类
         */
        @Deprecated
        public class InnerClass  {
            int filed1;

            public static final long filed2 = 0L;

            /**内部枚举类的内部类的字段 */
            public static final long filed3 = 0L;
        }
    }
}
