package system.service;

import java.io.Serializable;

public interface GenericService<T, ID extends Serializable> {
	<S extends T> S save(S entity);
}
