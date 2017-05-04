package xue.myapp.utils;

import java.util.List;

public class ArrayUtil {

	public static boolean isEmptyList(List<?> obj) {
		return (null == obj || obj.isEmpty() || obj.size() == 0) ? true : false;
	}
	
	public static boolean isEmptyArray(byte[] obj) {
        return (null == obj || obj.length == 0) ? true : false;
    }

}
