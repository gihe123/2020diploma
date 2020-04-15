
public class A {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String s ="B：123:1,1";
		
		String [] s1=s.split("：|:|,");
		for(String a:s1)
			System.out.println(a);
	}

}
