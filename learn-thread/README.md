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

waitandnotify包下的类是：两个线程交替打印A，B，共打印10次
## 二、lock：使用ReentrantLock
**`除了wait()和notify()协作完成线程同步之外，使用Lock也可以完成同样的目的。`**
 
ReentrantLock 与synchronized有相同的并发性和内存语义，还包含了中断锁等候和定时锁等候，意味着线程A如果先获得了对象obj的锁，那么线程B可以在等待指定时间内依然无法获取锁，那么就会自动放弃该锁。
 
但是由于synchronized是在JVM层面实现的，因此系统可以监控锁的释放与否，而ReentrantLock使用代码实现的，系统无法自动释放锁，需要在代码中finally子句中显式释放锁lock.unlock();

使用建议：
 
在并发量比较小的情况下，使用synchronized是个不错的选择，但是在并发量比较高的情况下，其性能下降很严重，此时ReentrantLock是个不错的方案。
## 三、singleton:单例模式（饿汉模式、懒汉模式）
**懒汉式单例模式：在类加载时不初始化。**  
**饿汉式单例模式：在类加载时就完成了初始化，所以类加载比较慢，但获取对象的速度快。** 
### 1.SingletonDemo1  
懒汉模式（线程不安全）
### 2.SingletonDemo2  
懒汉模式（线程安全）
### 3.SingletonDemo3  
饿汉模式
### 4.SingletonDemo4  
饿汉模式(变种)  
用静态代码块
### 5.SingletonDemo5
静态内部类(线程安全)    
这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟第三种和第四种方式不同的是（很细微的差别）：第三种和第四种方式是只要Singleton类被装载了，那么instance就会被实例化（没有达到lazy loading效果），而这种方式是Singleton类被装载了，instance不一定被初始化。因为SingletonHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载SingletonHolder类，从而实例化instance。想象一下，如果实例化instance很消耗资源，我想让他延迟加载，另外一方面，我不希望在Singleton类加载时就实例化，因为我不能确保Singleton类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化instance显然是不合适的。这个时候，这种方式相比第三和第四种方法就显得更合理 
### 6.SingletonDemo6  
枚举（这个有点看不懂）
### 7.SingletonDemo7 
懒汉模式（双重校验锁）
### 8.ThreeSingleton
控制生成3个单例  
单例模式是为了控制在运行期间，某些类的实例数目只能有一个。如果你想要控制多个，可以利用 Map 来帮助缓存多个实
## chapter2
### t1 方法内部声明一个变量，是不存在“非线程安全”问题的   
方法中的变量不存在非线程安全问题，永远都是线程安全的。这是方法内部的变量是私有的特性造成的。
### t2 实例变量非线程安全  
两个线程同时访问一个没有同步的方法，如果过两个线程同时操作义务对象中的实例变量，则有可能出现“非线程安全”问题。  
在方法名前加关键字synchronize即可。  
