package Views;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import Server.Config;
import Utils.Request;

public abstract class View{
	public Object resolve(Request request) {
		try {
			Method method = this.getClass().getMethod(request.getOp(), request.getClass());
			return method.invoke(this, request);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			Config.getConfig().getLogger().exception(e);
			return null;
		}
	}
}
