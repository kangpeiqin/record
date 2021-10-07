https://www.runoob.com/w3cnote/cglibcode-generation-library-intro.html

## CGLIB(Code Generation Library) 
> CGLIB是一个功能强大，高性能的代码生成包。为没有实现接口的类提供代理，为JDK的动态代理提供了很好的补充
### 原理
CGLIB 原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。
在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
CGLIB 底层：使用字节码处理框架ASM，来转换字节码并生成新的类。
### 为什么使用
CGLIB代理主要通过对字节码的操作，为对象引入间接级别，以控制对象的访问。
JDK动态代理虽然简单易用，但是其有一个致命缺陷是，只能对接口进行代理。如果要代理的类为一个普通类、没有接口，那么Java动态代理就没法使用了。

