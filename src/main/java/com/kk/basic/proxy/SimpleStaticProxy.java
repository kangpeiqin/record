package com.kk.basic.proxy;


/**
 * 静态代理
 * 代理类和实际类实现同一个接口(暴露同样的方法)，
 * 手动写代理类，代理类里面包裹实际对象
 *       调用代理对象方法            调用实际对象方法
 *  用户-----------------> 代理对象 ----------->  实际对象
 *
 *  缺点：需要为要代理的对象都手动实现一个代理类
 *
 * 代理背后一般至少有一个实际对象，代理的外部功能和实际对象一般是一样的，
 * 用户与代理打交道，不直接接触实际对象。虽然外部功能和实际对象一样，
 * 代理和实际对象一般有相同的接口
 * - 执行权限检查，代理检查权限后，再调用实际对象
 *
 * @author kpq
 * @since 1.0.0
 */
public class SimpleStaticProxy {

    static interface IService {
        public void sayHello();
    }

    static class RealService implements IService {

        @Override
        public void sayHello() {
            System.out.println("hello");
        }
    }


    /**
     * 代理类
     */
    static class Proxy implements IService {
        //实际对象
        private IService realService;

        public Proxy(IService realService) {
            this.realService = realService;
        }

        @Override
        public void sayHello() {
            System.out.println("entering");
            this.realService.sayHello();
            System.out.println("leaving");
        }
    }

    public static void main(String[] args) {
        IService realService = new RealService();
        IService proxyService = new Proxy(realService);
        proxyService.sayHello();
    }

}
