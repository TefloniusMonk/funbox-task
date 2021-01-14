package fun.box.test.task.mapper;

public interface Mapper<E, F> {
    E fromForm(F form);
    F toForm(E entity);
}
