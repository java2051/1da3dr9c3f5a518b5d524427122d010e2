/**
 * 奥斯丁发生的发生的非
 * sdasdfasdf
 */
import java.io.IOException;

public class TestObjectFactory  {
    /**asdfsd */
    public static final int bean1 = 0;

    private String bean2;





    /**
     * 默认的构造函数
     */
    public TestObjectFactory(  ) {
        // TODO Auto-generated method stub;
    }

    /**
     * 带有参数的构造函数
     * @param bean2 {java.lang.String} []
     */
    public TestObjectFactory( final String bean2 ) {
        this . bean2 = bean2;
    }

    /**
     * JUnit 启动测试方法
     * @return
     */
    public static final void startup(  )throws IOException, Exception {
        int i = 0 ;
        String tmp = null ;
        String tmp2 = null ;
        if (true){
            i=9;
        }
        switch(i){
            case 1: break;
            case 2: break;
            case 3: break;
        }
        {
            i=4;
        }
        String b= "asdfwe";
        Integer ii = new Integer(3);

        // 代码注释1
        // 代码注释2
        {
        ii=4;}
        ii=4;
        ii = new Integer(3);

       
    }

    enum DisableEnum {

        /**启用*/
        ENABLE (0,"启用") ,

        /**禁用*/
        DISABLE (1,"禁用") ;

        private  int value;

        private final String show;

        private DisableEnum(int value, String show) {
            this.value =  value ;
            this.show =  show ;
        }

        
    }
}
