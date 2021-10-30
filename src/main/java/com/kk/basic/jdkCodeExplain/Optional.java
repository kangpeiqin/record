package com.kk.basic.jdkCodeExplain;


import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 解决空值
 * JDK 源码分析  Optional
 */
public final class Optional<T> {

    /**
     * 静态实例常量 <?> 通配符，全局实例(饿汉式)
     */
    private static final Optional<?> EMPTY = new Optional<>();

    private final T value;

    /**
     * 私有构造函数：创建一个空实例
     */
    private Optional() {
        this.value = null;
    }

    /**
     * 单例，直接返回实例
     *
     * @param <T>
     * @return
     */
    public static <T> Optional<T> empty() {
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }

    /**
     * 构造非空值，如果为空值则抛出异常
     *
     * @param value
     */
    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    public <X extends Throwable> T orElseGet(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 对象比较，重写 equals 方法。默认比较是否为同一个对象
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional<?> other = (Optional<?>) obj;
        return Objects.equals(value, other.value);
    }

}
