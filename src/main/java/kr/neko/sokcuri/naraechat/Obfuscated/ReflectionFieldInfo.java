package kr.neko.sokcuri.naraechat.Obfuscated;

public class ReflectionFieldInfo<O, T> {
    private final String name;

    ReflectionFieldInfo(String name, Class<T> type, Class<O> owner, int depth) {
        this.name = name;
        this.type = type;
        this.owner = owner;
        this.depth = depth;
    }

    private final Class<T> type;
    private final Class<O> owner;
    private final int depth;

    static public <O, T> ReflectionFieldInfo<O, T> create(String name, Class<T> type, Class<O> owner, int depth) {
        return new ReflectionFieldInfo<>(name, type, owner, depth);
    }

    public String getName() {
        return this.name;
    }

    public Class<T> getTypeClass() {
        return this.type;
    }

    public Class<O> getOwnerClass() {
        return this.owner;
    }

    public int getDepth() {
        return this.depth;
    }
}