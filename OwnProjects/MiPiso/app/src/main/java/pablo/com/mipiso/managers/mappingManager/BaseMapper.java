package pablo.com.mipiso.managers.mappingManager;

public abstract class BaseMapper<T, R> {

    public abstract R map(T t);

}
