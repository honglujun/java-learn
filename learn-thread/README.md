# learn-thread
# 学习Java多线程编程核心技术的代码
## 一、waitandnotify：synchronized/wait()/notify()/nitifyAll()的用法说明

使用synchronized 代码块相比方法有两点优势：
1、可以只对需要同步的使用
2、与wait()/notify()/nitifyAll()一起使用时，比较方便
 
 
wait() 与notify()/notifyAll()
 
**`这三个方法都是Object的方法，并不是线程的方法！`**  
wait():释放占有的对象锁，线程进入等待池，释放cpu,而其他正在等待的线程即可抢占此锁，获得锁的线程即可运行程序。而sleep()不同的是，线程调用此方法后，会休眠一段时间，休眠期间，会暂时释放cpu，但并不释放对象锁。也就是说，在休眠期间，其他线程依然无法进入此代码内部。休眠结束，线程重新获得cpu,执行代码。**`wait()和sleep()最大的不同在于wait()会释放对象锁，而sleep()不会!`**  
 
notify(): 该方法会唤醒因为调用对象的wait()而等待的线程，其实就是**对对象锁的唤醒，从而使得wait()的线程可以有机会获取对象锁。** 调用notify()后，并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，才会释放对象锁。JVM则会在等待的线程中调度一个线程去获得对象锁，执行代码。需要注意的是，**`wait()和notify()必须在synchronized代码块中调用。`**  
 
notifyAll()则是唤醒所有等待的线程。

waitandnotify包下的类是：两个线程交替打印A，B，共打印10次wait
## 二、lock：使用ReentrantLock
**`除了wait()和notify()协作完成线程同步之外，使用Lock也可以完成同样的目的。`**
 
ReentrantLock 与synchronized有相同的并发性和内存语义，还包含了中断锁等候和定时锁等候，意味着线程A如果先获得了对象obj的锁，那么线程B可以在等待指定时间内依然无法获取锁，那么就会自动放弃该锁。
 
但是由于synchronized是在JVM层面实现的，因此系统可以监控锁的释放与否，而ReentrantLock使用代码实现的，系统无法自动释放锁，需要在代码中finally子句中显式释放锁lock.unlock();

使用建议：
 
在并发量比较小的情况下，使用synchronized是个不错的选择，但是在并发量比较高的情况下，其性能下降很严重，此时ReentrantLock是个不错的方案。
