## Java多线程——synchronized（内建锁）机制	
### 一、synchronized对象锁相关概念
#### &emsp;&emsp;了解synchronized机制必须首先了解synchronized的作用以及synchronized锁的应用场景
#### &emsp;&emsp;1、synchronized的作用：通过同步方法/同步代码块的方式实现同步处理（加锁操作）
#### &emsp;&emsp;2、synchronized的应用场景与注意事项：
##### &emsp;&emsp;&emsp;①只有<font color=red>共享资源</font>的读写访问才需要<font color=red>同步化</font>，如果不是共享资源，没有同步的必要。（多个线程有需要共享的东西时，才需要加锁；没有需要共享的东西（每个线程都拥有），就不需要加锁）
##### &emsp;&emsp;&emsp;②多个线程只是在分别调用线程类的run()方法，其他方法或属性是共享的。
#### &emsp;&emsp;3、synchronized引入的意义：
##### &emsp;&emsp;&emsp;①非线程安全：多个线程对同一个对象中的实例变量（所有线程共有的）进行并发访问时发生，会产生“脏读”，取到的数据是被更改过的。（如果是在方法内的变量则不会出现“非线程安全”，因为每个线程都具有自己的变量）
##### &emsp;&emsp;&emsp;②线程安全：获得的实例变量的值是经过同步处理的，不会出现“脏读”
#### &emsp;&emsp;4、<font color=red>synchronized对象锁</font>的概念：
##### &emsp;&emsp;&emsp;①防止<font color=red>多个线程</font> <font color=red>同一时刻</font>执行<font color=red>同一个对象</font>的<font color=red>同步段</font>
> &emsp;&emsp;**一定要注意是：**
> > 	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;多个线程；
> > 		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;同一时刻；
> > 		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;同一对象；
> > 		&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;同步段；

##### &emsp;&emsp;&emsp;②synchronized对象锁的是括号中的对象而非代码。
##### &emsp;&emsp;&emsp;③若线程A持有了Object对象的锁，线程B不能再访问Object对象的同步方法/同步代码块，但完全可以<font color=red>异步调用非同步方法/块</font>。
##### &emsp;&emsp;&emsp;;④若一个线程执行的代码出现异常，其所持有的锁就会自动释放
### 二、synchronized机制相关实现
#### &emsp;&emsp;1、对象锁与全局锁
##### &emsp;&emsp;&emsp;①对象锁：synchronized(object)、synchronized(this)、以及普通的synchronized方法，锁的是括号中的<font color=red>对象</font>而非代码。
~~~
		public synchronized void method(){}
		synchronized（Object）{}
		synchronized（this）{}
~~~
>			对象锁中：即便这个对象的某些属性发生改变，但只要对象没有改变，
>						不同线程之间还是同步状态。
##### &emsp;&emsp;&emsp;②全局锁：使用类的静态同步方法 synchronized与static一起使用，或在同步代码块中锁当前的Class对象，此时锁的是当前使用的<font color=red>类（代码段）</font>而非对象
~~~
		public static synchronized void method(){}
		synchronized（类名称.class）{}
~~~

#### &emsp;&emsp;2、同步方法与同步代码块
##### &emsp;&emsp;&emsp;①同步代码块与同步方法概念：
##### &emsp;&emsp;&emsp;&emsp;同步代码块：在方法中使用synchronize(对象){}，表示同一时刻只有<font color=red>一个线程</font>能够进入<font color=red>同步代码块</font>，但是<font color=red>多个线程</font>可以同时进入方法。
##### &emsp;&emsp;&emsp;&emsp;同步方法：在方法声明上加synchronize，表示此时只有一个线程能够进入同步方法。
##### &emsp;&emsp;&emsp;②同步代码块分类
##### &emsp;&emsp;&emsp;&emsp;a）synchronized(this){}—该类的对象
##### &emsp;&emsp;&emsp;&emsp;b）synchronized(类名.class)-全局锁{}—类反射对象
##### &emsp;&emsp;&emsp;&emsp;c）synchronized(任意对象,obj);—任意对象都有markword字段
##### &emsp;&emsp;&emsp;③synchronized(任意对象)详解
##### &emsp;&emsp;&emsp;&emsp;a）synchronized(任意对象)最大的优点
##### &emsp;&emsp;&emsp;&emsp;&emsp;	若一个类中有很多个synchronized方法，这是虽然能实现同步，但是会受到阻塞，效率很低，若把这些synchronized方法按照功能或其他标准分类，每一部分对应一个对象（必须是同一个对象）加锁，<font color=red>不同对象的监视器之间是异步的</font>，可以大大提高效率。

##### &emsp;&emsp;&emsp;&emsp;b）synchronized(任意对象)可得出以下结论
##### &emsp;&emsp;&emsp;&emsp;&emsp;	1>当多个线程同时执行synchronized(x){}同步代码块时呈同步效果。
##### &emsp;&emsp;&emsp;&emsp;&emsp;	2>当其他线程执行x对象中synchronized同步方法时呈同步效果（x对象本身也有同步方法，调用非同步方法还是异步的）
##### &emsp;&emsp;&emsp;&emsp;&emsp;	3>当其他线程执行x对象方法里面的synchronized(this)代码块时也呈同步效果
##### &emsp;&emsp;&emsp;&emsp;c）由于String存在常量池，所以一般synchronized(x)中x不用String对象
~~~
		
		eg：
					print中有以String（传入的参数）为锁的代码块
					线程1：service.print("AA")
					线程2：service.print("AA")

~~~
>&emsp;&emsp;解析：由于"AA"是匿名对象，在不同的线程中相当于new了一个新的对象，相
>	当于不同的线程拥有不同的对象，按道理这两个线程锁了两个不同的对象，这
>	两个线程之间是异步的，但由于String常量池的存在，这两个对象在内部实际
>	是一个对象，则导致线程2不能访问代码块了。
##### &emsp;&emsp;&emsp;③同步方法分类
##### &emsp;&emsp;&emsp;&emsp;a）普通方法&emsp;&emsp; //相当于 this
##### &emsp;&emsp;&emsp;&emsp;b）静态方法-全局锁&emsp;    &emsp; //相当于 类名.class
### 三、synchronized底层实现（monitor机制）	
#### &emsp;&emsp;1、同步代码块底层实现
>&emsp;&emsp;**通过操作系统的两大指令monitorenter（获取锁）和monitorexit（释放锁，解锁）来获取锁的对象的监视器（monitor）**
##### &emsp;&emsp;&emsp;①执行同步代码块前要执行monitorenter指令，执行同步代码块后要执行monitorexit指令。
##### &emsp;&emsp;&emsp;②使用synchronized实现同步，关键点就是要<font color=red>获取对象的监视器monitor对象</font>，当线程获取到monitor对象后，才可以执行同步代码块，否则就只能等待。
##### &emsp;&emsp;&emsp;③<font color=red>同一时刻</font>只有<font color=red>一个线程</font>可以获取到该对象的monitor监视器。
##### &emsp;&emsp;&emsp;④通常<font color=red>一个monitorenter</font>指令会同时对应<font color=red>多个monitorexit</font>指令。（JVM要确保所获取的锁，无论是在正常执行情况或异常执行情况都能正常解锁（即便是有异常，也得先释放锁，再报异常）
#### &emsp;&emsp;2、同步方法底层实现
##### &emsp;&emsp;&emsp;当使用synchronized标记方法时，字节码会出现访问标记(<font color=red>ACC_SYNCHRONIZED</font>)，该标记表示在进入该方法时，JVM需要进行monitorenter操作。在退出该方法时，无论是否正常返回，JVM均需要进行monitorexit操作。
#### &emsp;&emsp;3、monitor机制
>&emsp;&emsp;**可以将monitor理解为每个锁对象拥有一个<font color=red>锁计数器</font>和一个<font color=red>指向持有该锁的线程的指针</font>。**
##### &emsp;&emsp;&emsp;①当JVM执行monitorenter时，如果目标对象monitor的计数器为<font color=red>0</font>，表示此时该对象没有被其他线程所持有。此时JVM会将该锁对象的持有线程设置为当前线程，并且将monitor计数器<font color=red>+1</font>。
##### &emsp;&emsp;&emsp;②当执⾏ monitorexit 时，Java 虚拟机则需将锁对象的计数器减 1。当计数器减为 0 时，那便代表该锁已经被释放掉了，并<font color=red>唤醒所有正在等待的线程去竞争该锁</font>。
#### &emsp;&emsp;4、synchronized与monitor的关系
##### &emsp;&emsp;&emsp;<font color=red>由于synchronized获取的是当前对象的监视器monitor，类中这么多的同步方法都是属于一个对象的，所以锁的是对象。
#### &emsp;&emsp;5、synchronized锁的可重入性
##### &emsp;&emsp;&emsp;①可重入性：在目标锁对象的计数器不为0的情况下，如果锁对象的持有线程是当前线程，JVM可以将计数器再次+1（可重入锁）,否则需要等待，直到持有线程释放该锁。
##### &emsp;&emsp;&emsp;②可重入性也可以用在父子类继承中，子类完全可以通过“可重入锁”调用父类的同步方法。（父类中的非private方法子类都可以用）
##### &emsp;&emsp;&emsp;③根据synchronized锁的可重入性可以证明：在一个synchronized方法/块的内部调用本类的其他synchronized方法/块时，一定可以得到锁。（既然能进入到synchronized内部，肯定就已经拿到了当前对象的锁，当然可以访问该对象的其他同步方法）
#### &emsp;&emsp;6、monitor机制的劣势
##### &emsp;&emsp;&emsp;对象锁（monitor）机制是JDK1.6之前synchronized底层原理，又称为JDK1.6重量级锁，线程的阻塞以及唤醒均需要操作系统由用户态切换到内核态，开销非常之大，因此效率很低。
#### &emsp;&emsp;7、死锁
##### &emsp;&emsp;&emsp;①死锁的产生原因：对并发资源的加锁成“环”。
###### &emsp;&emsp;&emsp;&emsp;a）互斥：共享资源只能同时被一个线程占用。
###### &emsp;&emsp;&emsp;&emsp;b）占有且等待：某线程拿到一个共享资源X占用后，还等待着被别的线程占用了的共享资源Y。
###### &emsp;&emsp;&emsp;&emsp;c）不可抢占：线程Thread1拿到对象锁x后，其他线程无法强行抢占x锁。
###### &emsp;&emsp;&emsp;&emsp;d）循环等待：线程T1拿到了资源X的锁后，去申请Y的锁；
###### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;线程T2拿到了资源Y的锁后，去申请X的锁。
##### &emsp;&emsp;&emsp;②死锁的解决方案：“银行家”算法
### 四、synchronized（内建锁）的优化

#### &emsp;&emsp;1、CAS操作

##### &emsp;&emsp;&emsp;①悲观锁：线程获取锁（JDK1.6之前内建锁）是一种悲观锁策略。
###### &emsp;&emsp;&emsp;&emsp;a）假设每一次执行临界区代码（访问共享资源）都会产生冲突，所以当前线程获取到锁的同时也会阻塞其他未获取到锁的线程。
###### &emsp;&emsp;&emsp;&emsp;b）每次上厕所都有人和我抢——<font color=red>只要有一个线程在访问共享资源，都有其他的线程也在竞争锁</font>，当该线程获取到锁后，其他线程都得等待
##### &emsp;&emsp;&emsp;②乐观锁：CAS（无锁）操作，是一种乐观锁策略
###### &emsp;&emsp;&emsp;&emsp;a）假设所有线程访问共享资源时不会出现冲突，由于不会出现冲突自然就不会阻塞其他线程。因此线程就不会出现阻塞停顿的状态。(上厕所一定没人抢——可以不要锁）
###### &emsp;&emsp;&emsp;&emsp;b）若出现冲突了，无锁操作使用<font color=red>CAS(比较交换)来鉴别线程是否出现冲突</font>，出现冲突就重试当前操作，直到没有冲突（获取锁）为止。
###### &emsp;&emsp;&emsp;&emsp;c）若线程未获取到锁，该线程利用CAS不断的在重试，看在里面的那个线程结束了没，结束了就没有冲突，该线程就可以获取到锁了，此时<font color=red>该线程没有停止，会在CPU上跑，只不过跑的是无用指令</font>。（反复在重试看有无冲突，直到获取锁为止）
##### &emsp;&emsp;&emsp;③CAS的操作过程
> &emsp;&emsp;**将CAS理解为CAS(V,O,N)**
> &emsp;&emsp;&emsp;&emsp;**V：当前内存地址中实际存放的值**
> &emsp;&emsp;&emsp;&emsp;**O：期望V中存放的值（旧值）**
> &emsp;&emsp;&emsp;&emsp;**N：更新后的值（新值）**

###### &emsp;&emsp;&emsp;&emsp;a）当V==O时：旧值与内存中的实际值相等，表明该值没有被其他线程更改过，即值O就是目前最新的值，因此可以将新值N赋值给V；
###### &emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;当V!=O时，表明该值已经被其他线程修改过了，因此O值并不是当前最新值，返回V，无法修改。
###### &emsp;&emsp;&emsp;&emsp;b）当多个线程使用CAS操作时，<font color=red>只有一个线程会成功，其余线程均失败</font>（多个线程同时进行CAS，肯定会有一个线程优先进入将V值修改为该线程的N值，其余线程再判断当前的V与O当然是不相同的，因此会失败）。失败的线程会重新尝试（自旋）或挂起线程（阻塞）
##### &emsp;&emsp;&emsp;④<font color=red>内建锁与CAS的区别
###### &emsp;&emsp;&emsp;&emsp;a）<font color=red>*内建锁*</font>在优化前（老版本）最大的问题在于：在存在线程竞争的情况下，会出现线程的阻塞以及唤醒带来的性能问题，这是一种互斥同步（阻塞同步）——<font color=red>*挂起-再唤醒（开销太大）*
###### &emsp;&emsp;&emsp;&emsp;b）<font color=red>*CAS*</font>不是将线程挂起，当CAS失败后会进行一定的尝试操作并非耗时的将线程挂起，这是一种非阻塞同步 ——<font color=red>*失败-重试*
##### &emsp;&emsp;&emsp;⑤CAS带来的问题
###### &emsp;&emsp;&emsp;&emsp;a）ABA问题
>&emsp;&emsp;⽐如⼀个旧值A变为了成B，然后再变成A，刚好在做CAS时检查发现旧值并没有变化依然为A，但是实际上的确发⽣了变化
>###### &emsp;&emsp;&emsp;&emsp;&emsp;	解决方案：添加了版本号（版本号不相同，还是不能改）
>&emsp;&emsp;老版本（A->B->A..)
>&emsp;&emsp;**新版本（1A->2B->3A）**

###### &emsp;&emsp;&emsp;&emsp;b）<font color=red>自旋（CAS）会浪费大量的CPU资源
>&emsp;&emsp;与线程阻塞相比，自旋会浪费大量的处理器资源。因为**当前线程扔处于运行状态，只不过跑的是无用指令**。
>###### &emsp;&emsp;&emsp;&emsp;&emsp;	解决方案：<font color=red>自适应自旋——重量级锁的优化（有阻塞状态）
>&emsp;&emsp;	根据以往（上一次）自旋等待时，能否获取锁所花费的时间，来**动态调整本次自旋的时间**（重复试探的循环次数）。
>>	如果在上一次自旋时获取到锁，则稍微增加下一次自旋的时长（多重试几次）
>>	否则会稍微减少下一次自旋时长（少重试几次）
>
>&emsp;&emsp;**自旋时长结束就会进入阻塞状态了**

###### &emsp;&emsp;&emsp;&emsp;c）公平性问题
###### &emsp;&emsp;&emsp;&emsp;&emsp;<font color=red>自旋的线程始终比阻塞的线程跟优先获取锁。
>&emsp;&emsp;		公平性：等待时间最长的线程优先获取锁

###### &emsp;&emsp;&emsp;&emsp;&emsp;	解决方案：内建锁无法实现公平机制，而lock体系可以实现公平锁。
#### &emsp;&emsp;2、Java1.6后全新的锁状态分析
##### &emsp;&emsp;&emsp;①Java对象头
###### &emsp;&emsp;&emsp;&emsp;a）	Java对象头Mark Word字段存放内容：对象的HashCode、分代年龄、锁标记位
###### &emsp;&emsp;&emsp;&emsp;b）32位JVM下的Mark Word![在这里插入图片描述](https://img-blog.csdnimg.cn/20190717165953755.png)
##### &emsp;&emsp;&emsp;②Java（状态）锁的分类—对应MarkWord中的标志位
###### &emsp;&emsp;&emsp;&emsp;a）	无锁：0 01
###### &emsp;&emsp;&emsp;&emsp;b）偏向锁：1 01
###### &emsp;&emsp;&emsp;&emsp;c）轻量级锁：00	
###### &emsp;&emsp;&emsp;&emsp;d）重量级锁：10	
##### &emsp;&emsp;&emsp;③根据竞争状态的激烈程度，<font color=red>锁会自动进行升级，但锁不能降级</font>（为了提高锁获取与释放的效率）
#### &emsp;&emsp;3、偏向锁
##### &emsp;&emsp;&emsp;①偏向锁的引入：大多数情况下，锁不仅不存在多线程竞争，而且总是由同一个线程多次获得。为了让线程获取锁的开销降低而引入偏向锁。

##### &emsp;&emsp;&emsp;&emsp;偏向锁是锁状态中最乐观的一种锁：<font color=red>从始至终只有一个线程请求一把锁</font>。
##### &emsp;&emsp;&emsp;②偏向锁的获取
###### &emsp;&emsp;&emsp;&emsp;a）当一个线程访问某对象的同步块并成功获取到该对象的锁时，会在对象头和栈帧中的锁记录字段存储<font color=red>锁偏向的线程ID</font>，以后该线程再进入和退出该对象的其他同步块时，不需要进行CAS操作来加锁和解锁，直接进入。（同一线程再尝试获取锁<font color=red>不需要自旋过程了</font>）
###### &emsp;&emsp;&emsp;&emsp;b）当线程访问同步块失败时，使用CAS竞争锁，并将偏向锁升级为轻量级锁。
##### &emsp;&emsp;&emsp;③偏向锁的撤销（开销比较大，一般不撤销，一直让一个线程拿着）
###### &emsp;&emsp;&emsp;&emsp;a）偏向锁使用一种<font color=red>等待竞争出现才释放锁</font>的机制，所以当其他线程竞争偏向锁时，持有偏向锁的线程才会释放偏向锁，并将锁膨胀为轻量级锁（持有偏向锁的线程依然存活的时候）
###### &emsp;&emsp;&emsp;&emsp;&emsp;PS：;两个同时存活的线程，一个已经持有该偏向锁，另一个也来竞争该偏向锁，则偏向锁才会升级为轻量级锁。
###### &emsp;&emsp;&emsp;&emsp;b）如果当前偏向锁的持有线程已经终止，则将锁对象的对象头设置为无锁状态，其他线程就有机会直接拿到该偏向锁了。（原本拥有该偏向锁的线程已经终止，另一个线程可以直接获取该偏向锁）
##### &emsp;&emsp;&emsp;④偏向锁头部Epoch字段值![在这里插入图片描述](https://img-blog.csdnimg.cn/20190717171243938.png)
###### &emsp;&emsp;&emsp;&emsp;Epoch字段表示：此对象偏向锁的撤销次数
###### &emsp;&emsp;&emsp;&emsp;默认撤销<font color=red>40</font>次时，表示此对象不再适用于偏向锁，当下次（第41次撤销）线程再次获取此对象时，直接变为轻量级锁。（升级锁）
##### &emsp;&emsp;&emsp;⑤	JDK6之后，偏向锁默认开启（一个锁最初默认都是偏向锁，后来会一步步升级）

#### &emsp;&emsp;4、轻量级锁
##### &emsp;&emsp;&emsp;①<font color=red>多个线程在不同的时间段请求同一把锁</font>，也就是不存在锁竞争的情况。针对这种状况，JVM采用了轻量级锁来避免线程的阻塞与唤醒。（<font color=red>只要有竞争，立马升级为重量级锁</font>）
##### &emsp;&emsp;&emsp;②轻量级锁加锁
###### &emsp;&emsp;&emsp;&emsp;将MarkWord中的内容拷到当前线程的栈帧中用于存储锁记录的空间（Displaced Mark Word），然后尝试使用CAS将对象头中的Mark Word替换为指向锁记录的指针。

###### &emsp;&emsp;&emsp;&emsp;若成功：当前线程获得锁。
###### &emsp;&emsp;&emsp;&emsp;若失败：表示其他线程正在竞争锁，当前线程便尝试使⽤⾃旋来获取锁。
##### &emsp;&emsp;&emsp;③轻量级锁解锁
###### &emsp;&emsp;&emsp;&emsp;使用CAS操作将