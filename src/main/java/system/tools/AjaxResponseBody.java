package system.tools;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AjaxResponseBody {

    String msg;
    boolean result = true;
    List<?> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

}