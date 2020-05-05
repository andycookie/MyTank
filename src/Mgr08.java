/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/4
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
public enum  Mgr08 {
	INSTANCE;
	
	public static void main(String [] arr){
		Mgr08 instance = Mgr08.INSTANCE;
		Mgr08 instance2 = Mgr08.INSTANCE;
		System.out.println(instance == instance2);
	}
}
