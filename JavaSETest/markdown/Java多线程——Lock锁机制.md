## Java多线程——Lock锁机制
<br/>
---

### 一、Lock锁总结
### 	&emsp;&emsp;1、Lock接口与AQS的关系
#### &emsp;&emsp;&emsp;Lock接口的最主要实现类	ReentrantLock中所有的方法实际上都是调用了其静态内部类Sync中的方法
#### &emsp;&emsp;&emsp;而Sync继承了AbstractQueuedSynchronizer(AQS-简称"同步器")
### 	&emsp;&emsp;2、<font color=red>Lock与synchronize的区别</font>
#### &emsp;&emsp;&emsp;①synchronized内建锁有的功能lock全有。而lock体系拥有 可中断的获取锁、超时获取锁、共享锁 等（这些是内建锁不具备的特性）
#### &emsp;&emsp;&emsp;②synchronized获取锁失败就是阻塞（），而lock获取锁失败就反复自旋。
#### &emsp;&emsp;&emsp;③synchronized块/方法是隐式获取释放锁，但是将锁的获取和释放固化了，简化了同步的管理，但扩展性不好，而lock体系缺拥有了锁获取与释放的可操作性。
<br/>

---
### 二、AQS同步器简介
### 	&emsp;&emsp;1、AQS（队列）同步器简介
#### &emsp;&emsp;&emsp;AQS（队列）同步器是用来构建锁以及其他同步组件的基础框架，它的实现主要是依赖一个int型的状态变量（锁的状态变量）以及通过一个FIFO队列共同构成同步队列。
### 	&emsp;&emsp;2、AQS的模板模式
#### &emsp;&emsp;&emsp;	AQS使用模板方法模式，将一些<font color=red>与状态相关</font>的核心方法开放给子类重写，而后AQS会使用子类重写的关于状态的方法进行线程的排队、阻塞以及唤醒等操作。
#### &emsp;&emsp;&emsp;	①子类必须重写AQS的 用<font color=red>protected</font>修饰的用来<font color=red>改变同步状态</font>的方法，其他方法主要是实现了排队与阻塞机制。
#### &emsp;&emsp;&emsp;②子类推荐<font color=red>使用静态内部类来继承AQS</font>，实现自己的同步语义。同步器即支持独占锁（一把锁同一时刻只能有一个线程），也支持共享锁（多个线程共享一把锁）
### &emsp;&emsp;3、锁与AQS的关系
#### &emsp;&emsp;&emsp;	Java使用锁与AQS机制隔离了使用者和实现者所关注的领域。
#### &emsp;&emsp;&emsp;	①锁lock面对使用者，定义了使用者与锁交互的接口，隐藏了实现细节。
#### &emsp;&emsp;&emsp;	②同步器AQS面向锁的实现，简化了锁的实现方式，屏蔽同步状态管理，线程排队，等待，唤醒等操作
#### &emsp;&emsp;&emsp;	③<font color=red>任何一个自定义的锁类中一定有一个抽象类继承了AQS</font>。
<br/>

---
### 三、<font color=red>AQS同步器核心</font>
### 	&emsp;&emsp;1、AQS抽象类
#### &emsp;&emsp;&emsp;	①在同步组件（锁）中，AQS是最核心的部分，<font color=red>同步组件的实现依赖AQS提供的模板方法来实现同步组件语义</font>。
> &emsp;&emsp;&emsp;	**AQS实现了对同步状态的管理，以及对阻塞线程进行排队、等待通知等等底层实现**

#### &emsp;&emsp;&emsp;	②AQS核心组成：同步队列、独占锁的获取与释放、共享锁的获取与释放、可中断锁、超时锁。这一系列功能的实现依赖于AQS提供的模板方法。
### 	&emsp;&emsp;2、同步队列详解
#### &emsp;&emsp;&emsp;	在AQS内部有一个静态内部类Node，这是同步队列中每个具体的节点的节点类。

#### &emsp;&emsp;&emsp;	①节点中的属性
>&emsp;&emsp;&emsp;&emsp;		**int waitStatus：节点状态**
>&emsp;&emsp;&emsp;&emsp;		Node prev：同步队列中的前驱节点
>&emsp;&emsp;&emsp;&emsp;		Node next：同步队列中的后继节点
>&emsp;&emsp;&emsp;&emsp;		Thread thread：当前节点包装的线程对象
>&emsp;&emsp;&emsp;&emsp;		Node nextWaiter：等待队列中下一个节点
#### &emsp;&emsp;&emsp;②节点状态值
>&emsp;&emsp;&emsp;&emsp;**<font color=green>int INITIAL = 0;</font>&emsp;&emsp;&emsp;//初始状态（锁还没有被任何线程获取）**

>&emsp;&emsp;&emsp;&emsp;**<font color=green>int CANCELLED = 1;</font>&emsp;&emsp;&emsp; //当前节点从同步队列中取消（超时获取没获取到）**

>&emsp;&emsp;&emsp;&emsp;**<font color=red>int SIGNAL = -1; </font>&emsp;&emsp;&emsp;//当前节点的后继节点处于阻塞状态（当前节点拿到了同步状态，后继节点
>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;还在同步队列中进行等待），如果当前节点释放，同步状态会通知后继
>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;节点，使后继节点继续运行。**

>&emsp;&emsp;&emsp;&emsp;**<font color=green>int CONDITON = -2;</font> &emsp;&emsp;//当前节点处于等待队列中，节点线程等待在Condition上，当其他线
>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;程对Condition调用signal()（相当于notify），该节点会从等待队列转
>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;移到同步队列中。**

>&emsp;&emsp;&emsp;&emsp;int PROPAGATE = -3; &emsp;&emsp;&emsp;//共享式同步状态会无条件的传播
>
>#### &emsp;&emsp;&emsp;	AQS同步队列采用带有头尾节点的双向链表

### 	&emsp;&emsp;3、<font color=red>独占锁的获取——acquire(int arg)
#### &emsp;&emsp;&emsp;	①独占锁获取的前提：
---
>少照片
>
>#### &emsp;&emsp;&emsp;	②<font color=red>独占锁获取的主要逻辑：
---
>少照片
#### &emsp;&emsp;&emsp;&emsp;a）调用自定义同步器实现的typeAcquire(int arg)方法，再次尝试获取锁的同步状态，该方法保证线程安全的获取同步状态。——同一时刻只能有一个线程成功获取同步状态。
##### &emsp;&emsp;&emsp;&emsp;&emsp;1> 如果同步状态获取成功，则直接退出acquire()。
##### &emsp;&emsp;&emsp;&emsp;&emsp;2> 如果同步状态获取失败，则构造同步节点（独占式Node.EXCLUSIVE）
#### &emsp;&emsp;&emsp;&emsp;b）调用addWaiter(Node node)方法，先将a)构造的同步节点尾插到同步队列
##### &emsp;&emsp;&emsp;&emsp;&emsp;1> 如果当前同步队列的尾结点不为null，则直接采用compareAndSetTail（）⽅法将该节点尾插入同步队列。
##### &emsp;&emsp;&emsp;&emsp;&emsp;2> 如果当前当前同步队列的尾结点为null（当前队列为null），或①将该节点CAS尾插入同步队列失败，则会调用enq(Node node)方法
#### &emsp;&emsp;&emsp;&emsp;b+）调用enq(Node node)方法，直到将当前节点插入到同步队列成功为止
##### &emsp;&emsp;&emsp;&emsp;&emsp;1> 若当前队列尾结点为null，初始化当前队列（调⽤compareAndSetHead(new Node()⽅法，完成链式队列的头结点的初始化）
##### &emsp;&emsp;&emsp;&emsp;&emsp;2>若CAS尾插失败，则反复自旋重试，直到成功为止
#### &emsp;&emsp;&emsp;&emsp;c）调用acquireQueued(Node node,int arg)方法获取锁的状态
##### &emsp;&emsp;&emsp;&emsp;&emsp;acquireQueued()获取锁成功条件：当前节点前驱为头结点，并且当前节点成功获得同步状态（出队的条件）
##### &emsp;&emsp;&emsp;&emsp;&emsp;acquireQueued()方法整体上是在自旋，当前驱节点不是头结点或前驱节点是头结点但当前节点未获取到同步状态，此时当前线程进入等待（阻塞）状态了，则反复自旋尝试获取同步状态。 

##### &emsp;&emsp;&emsp;&emsp;&emsp;1> 获取锁成功，就调用setHead(node);设置为队列的头结点，再将之前的头结点与同步队列断开（方便GC回收）
##### &emsp;&emsp;&emsp;&emsp;&emsp;2> 获取锁失败
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;首先，调用shouldParkAfterFailedAcquire(Node prev,Node node)，使用CAS将前驱节点状态置为SIGNAL（表示需要将当前节点阻塞），若CAS失败，不断自旋直到前驱节点状态置为SIGNAL为止。（acquireQueued中死循环）
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;然后，将前驱节点状态置为SIGNAL后，调用parkAndCheckInterrupt()，该方法中会调用LockSupport.park(this);将当前线程阻塞
#### &emsp;&emsp;&emsp;	③<font color=red>独占锁获取流程图
---
>少照片

### 	&emsp;&emsp;4、<font color=red>独占锁的释放——release()
#### &emsp;&emsp;&emsp;①unlock()方法实际是在调用AQS提供的release()模板方法
#### &emsp;&emsp;&emsp;&emsp;release()方法是unlock()方法的具体实现
#### &emsp;&emsp;&emsp;&emsp;a）如果同步状态释放成功，则首先获取头结点，当头结点不为null且头结点不是初始状态时，会调用unparkSuccessor()方法
#### &emsp;&emsp;&emsp;&emsp;b）在unparkSuccessor()方法中，⾸先获取头节点的后继节点，当后继节点不为null的时候会调⽤LockSupport.unpark()方法唤醒后继节点包装的线程。
#### &emsp;&emsp;&emsp;②<font color=red>每一次锁释放后就会唤醒队列中该节点的后继节点所包装的线程。
### 	&emsp;&emsp;5、独占式锁获取与释放总结
#### &emsp;&emsp;&emsp;①线程获取锁失败，将线程调用addWaiter()封装成Node节点进行入队操作。addWaiter()中方法enq()完成对同步队列的头结点初始化以及CAS尾插失败后的重试处理。
#### &emsp;&emsp;&emsp;②入队之后排队获取锁的核心方法acquireQueued()，节点排队获取锁是一个自旋过程。<font color=red>节点获取锁的前提条件</font>，当且仅当，<font color=red>当前节点的前驱节点为头结点并且该节点成功获取同步状态（通过tryAcquire）时</font>。
#### &emsp;&emsp;&emsp;&emsp;a）若前驱节点出队，该节点作为头结点并且该节点引用的线程获取到锁。
#### &emsp;&emsp;&emsp;&emsp;b）否则不满足条件时，会不断自旋将前驱节点的状态置为SIGNAL（当前节点需要阻塞），而后调用LockSupport.park()将当前线程阻塞。
#### &emsp;&emsp;&emsp;③释放锁时会唤醒后继节点（若后继节点不为null）
### 	&emsp;&emsp;6、lock体系独有功能（加强了synchronized的功能）
#### &emsp;&emsp;&emsp;①独占锁特性
#### &emsp;&emsp;&emsp;&emsp;a）获取锁时响应中断：acquireInterruptibly()	
##### &emsp;&emsp;&emsp;&emsp;&emsp;1> 获取锁时响应中断原理与acquire()几乎一样，与之唯一的区别在于当方法parkAndCheckInterrupt()返回true时，表示线程阻塞时被中断，抛出中断异常后线程退出。
##### &emsp;&emsp;&emsp;&emsp;&emsp;2> 而acquire()中的acquireQueued()中若parkAndCheckInterrupt()返回true，只是将interrupt状态设置为true
#### &emsp;&emsp;&emsp;&emsp;b）超时等待获取锁：tryAcquireNanos()
##### &emsp;&emsp;&emsp;&emsp;&emsp;该方法在<font color=red>三种情况</font>下会返回
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;1> 在超时时间内，当前线程成功获取到锁，返回true
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;2> 当前线程在超时时间内被中断，线程抛出异常
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;3> 超时时间结束，仍未获取到锁，线程退出返回false
##### &emsp;&emsp;&emsp;&emsp;&emsp;				超时获取锁逻辑与可中断获取锁基本一致，唯一区别在于获取锁失败后，增加了一个时间处理，如果当前时间超过截止时间，线程不再等待，直接退出，返回false。否则将线程阻塞置为等待状态排队获取锁。

#### &emsp;&emsp;&emsp;②独占（互斥）锁与共享锁的特点
#### &emsp;&emsp;&emsp;&emsp;a）共享式访问资源时，其他共享式的访问均被允许，而独占式访问被阻塞。
#### &emsp;&emsp;&emsp;&emsp;b）独占式访问资源时，同一时刻其他所有访问均被阻塞-
<br/>

---
### 四、独占式重入锁——ReentrantLock
### 	&emsp;&emsp;1、重入的实现原理
#### &emsp;&emsp;&emsp;①重入概念：
>&emsp;&emsp;&emsp;&emsp;&emsp;**表示能够对共享资源重复加锁，即当前线程再次获取锁时不会被阻塞。**
#### &emsp;&emsp;&emsp;②重入的获取
#### &emsp;&emsp;&emsp;&emsp;如果该同步状态不为0，表示此时同步状态（锁）已被线程获取，再判断持有同步状态的线程是否是当前线程，如果是，同步状态再次+1并返回true，表示持有线程重入同步块。（类似于monitor）
#### &emsp;&emsp;&emsp;③<font color=red>重入获取与锁的调用流程
#### &emsp;&emsp;&emsp;&emsp;lock()->acquire()->tryAcquire()->nonfairTryAcquire()
#### &emsp;&emsp;&emsp;&emsp;nonfairTryAcquire()可以获取当前状态，对当前状态进行判断
#### &emsp;&emsp;&emsp;&emsp;&emsp;<font color=red>a）</font>若当前状态==0（设置当前线程持有同步状态的线程）或者当前线程是持有同步状态的线程（同步状态+1），返回true，acquire()的tryAcquire()就不用继续判断了，lock也会直接将持有同步状态线程（当前线程）返回回去
#### &emsp;&emsp;&emsp;&emsp;&emsp;<font color=red>b）</font>若当前状态!=0或当前线程不是持有同步状态的线程，说明当前线程没有获取到同步状态，acquire()的tryAcquire()再次获取锁失败，继续接下来的流程即可。
#### &emsp;&emsp;&emsp;④重入的释放
#### &emsp;&emsp;&emsp;&emsp;&emsp;当且仅当同步状态减为0并且持有线程为当前线程时表示锁被正确释放，否则调用setState()将减1后的状态设置为同步状态。若非持有锁线程调用了tryRelease()方法会抛出 IllegalMonitorStateException异常
#### &emsp;&emsp;&emsp;⑤<font color=red>重入释放与锁的释放流程
#### &emsp;&emsp;&emsp;&emsp;unlock()->release()->tryRelease()
#### &emsp;&emsp;&emsp;&emsp;tryRelease()会判断当前同步状态-1后的值

#### &emsp;&emsp;&emsp;&emsp;&emsp;<font color=red>a）</font>若为0，返回true，说明同步状态为初始状态（锁被完全释放，唤醒其他线程竞争该锁）
#### &emsp;&emsp;&emsp;&emsp;&emsp;<font color=red>b）</font>若不为0，返回flase，说明同步状态不是初始状态（锁没有被完全释放）
### 	&emsp;&emsp;2、<font color=red>可重入锁的特点
#### &emsp;&emsp;&emsp;①在线程获取锁的时候，如果已经获取锁的线程是当前线程的话则直接再次获取成功；
#### &emsp;&emsp;&emsp;②由于锁会被获取n次，那么只有锁在被释放同样的n次之后，该锁才算是完全释放成功。
### 	&emsp;&emsp;3、公平锁OR非公平锁
#### &emsp;&emsp;&emsp;①公平锁与非公平锁的概念
> &emsp;&emsp;&emsp;&emsp;&emsp;**公平锁：锁的获取顺序符合时间上的顺序，即等待时间最长的线程最先获取锁
> &emsp;&emsp;&emsp;&emsp;&emsp;非公平锁：不是公平锁**
#### &emsp;&emsp;&emsp;②公平锁与非公平锁的特点
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）公平锁：获取同步状态并且要是同步队列中的第⼀个节点（通过hasQueuedPredecessors()实现）
#### &emsp;&emsp;&emsp;&emsp;&emsp;b）非公平锁：只要获取了同步状态即成功获取锁
#### &emsp;&emsp;&emsp;③<font color=red>非公平锁（常用默认）与公平锁的对比</font>
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）公平锁保证了获取到锁的线程一定是等待时间最长的线程，保证了请求资源时间上的绝对顺序，需要频繁的进行上下文切换，性能开销较大，效率较低。
#### &emsp;&emsp;&emsp;&emsp;&emsp;b）非公平锁保证系统有更大的吞吐量（效率较高），但是会造成线程“饥饿现象”（有的线程可能永远无法获取到锁）
<br/>

---

### 五、读写锁
### 	&emsp;&emsp;1、读写锁的定义
> &emsp;&emsp;&emsp;**读写锁允许同⼀时刻被多个读线程访问，但是在写线程访问时，所有的读线程和其他的写线程都会被阻塞。**
#### &emsp;&emsp;&emsp; ①<font color=red>写线程-独占锁</font>能够获取到锁的<font color=red>前提条件</font>：没有任何读/写线程拿到锁
#### &emsp;&emsp;&emsp;	②<font color=red>读线程-共享锁</font>能够获取到锁的<font color=red>前提条件</font>：没有写线程拿到锁
#### &emsp;&emsp;&emsp;	PS：<font color=red>读锁 != 无锁</font>：当有写线程写的时候，所有读线程都必须全部停止，但如果是无锁的话，其他线程就不会停（无锁的话，不同线程之间互不干扰）。
#### &emsp;&emsp;&emsp;	③<font color=red>同步状态的低16位表示写锁获取次数，高16位表示读锁获取次数</font>。

### 	&emsp;&emsp;2、写锁-独占锁-WriteLock
#### &emsp;&emsp;&emsp;	①写锁获取逻辑
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）当读锁已被读线程获取或者写锁已被其他写线程获取，则写线程获取失败；

#### &emsp;&emsp;&emsp;&emsp;&emsp;b）否则，当前同步状态没有被任何读写线程获取，当前线程获取写锁成功并且支持重入。

#### &emsp;&emsp;&emsp;	②<font color=red>写锁注意事项
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）读写锁确保写锁的操作对读锁可见，不允许读锁在已被获取的情况下对写锁获取（正在运行的其他读线程无法感知到当前写线程的操作）
#### &emsp;&emsp;&emsp;&emsp;&emsp;b）<font color=red>只有等待其他读线程都释放了读锁，写锁才能被当前线程获取，而写锁一旦被获取，则其他读写线程的后续访问均被阻塞。</font>
#### &emsp;&emsp;&emsp;	③写锁的释放逻辑同独占式锁的释放（release）

### 	&emsp;&emsp;3、读锁-共享式锁-tryAcquireShared()
#### &emsp;&emsp;&emsp;		当写锁被其他线程获取后，读锁获取失败，其他情况均可成功。
### 	&emsp;&emsp;4、读写锁相关事项
#### &emsp;&emsp;&emsp;	①读锁和写锁是两个锁，但同一个线程是可以拥有两把锁的。
#### &emsp;&emsp;&emsp;	②若当前同步状态为S
>&emsp;&emsp;&emsp;&emsp;&emsp;**写状态获取 S=S+1&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;写状态释放 S=S-1&emsp;&emsp;&emsp;&emsp; &emsp;&emsp;&nbsp;&nbsp;低16位（直接加减）
>&emsp;&emsp;&emsp;&emsp;&emsp;读状态获取 S=S+(1<<16) &emsp;&emsp;&emsp;&emsp; &nbsp;&nbsp;读状态释放S=S-(1<<16)                   &emsp;&emsp;&emsp;&emsp;高16位（左移16位）**
>
>#### &emsp;&emsp;&emsp;	③锁降级
#### &emsp;&emsp;&emsp;&emsp;&emsp;写锁可以降级为读锁，而读锁不能升级为写锁。
#### &emsp;&emsp;&emsp; ④读写锁应用
#### &emsp;&emsp;&emsp;&emsp;&emsp;应用于“缓存”的实现（操作系统内存也是个缓存-多读少写）
<br/>

---
### 六、Condition接口
### 	&emsp;&emsp;1、Condition接口的await、signal机制
#### &emsp;&emsp;&emsp;	①<font color=red>与内建锁wait、notify的区别
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）不同底层实现
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Object类提供的wait与noify方法是与对象监视器monitor配合完成线程的等待与通知机制，属于JVM底层实现；而Condition与Lock配合完成的等待通知机制属于java语言级别，具有更高的控制与扩展性

#### &emsp;&emsp;&emsp;&emsp;&emsp;b）Condition独有特性
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;1>  支持不响应中断（<font color=red>等待队列</font>），而Object不支持

##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;2> 	支持多个等待队列（同步队列只有一个），而Object只有一个

##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;3> 支持截止时间设置，而Object不支持（注意以下两个方法的区别）
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Object.wait(long timeout)：这是在设置等待多久后停止等待
##### &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;Condition.awaitUtil(Date deadline)：设置的是一个日期，截止到这个日期时间就停止等待
#### &emsp;&emsp;&emsp;	②等待与唤醒方法
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）等待方法
> &emsp;&emsp;&emsp;**1、void await() throws InterruptedException;——同Object.wait()，直到被中断或唤醒（死等），如果在等待状态中被其他线程中断会抛出被中断异常；
> &emsp;&emsp;&emsp;2、void awaitUninterruptibly();——不响应中断，直到被唤醒（中断也不会返回）<独有>
> &emsp;&emsp;&emsp;3、boolean await(long time, TimeUnit unit) throws InterruptedException;——同Object.wait(long timeout)，多了自定义时间单位，中断、超时、被唤醒都会被返回（不会死等）
> &emsp;&emsp;&emsp;4、boolean awaitUntil(Date deadline) throws InterruptedException;——支持设置截止时间<独有>**
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）唤醒方法

> &emsp;&emsp;&emsp;&emsp;&emsp;	**1、signal()：唤醒一个等待在condition上的线程，将该线程由等待队列转移到同步队列上
> &emsp;&emsp;&emsp;&emsp;&emsp;	2、signalAll()：将所有等待在condition上的线程全部转移到同步队列中**

### 	&emsp;&emsp;2、Condition的等待队列
#### &emsp;&emsp;&emsp;	①等待队列概念
> &emsp;&emsp;&emsp;**等待队列与同步队列共享了Node节点类，等待队列是一个单向的带有头尾节点的队列**
>
> #### &emsp;&emsp;&emsp;	②await()实现原理（尾插进入等待队列）
#### &emsp;&emsp;&emsp;&emsp;&emsp;a）<font color=red>尾插入等待队列的一定是当前lock中同步队列的头结点</font>（要进入等待队列就必须得获取锁，获取的条件就是 前驱节点是头结点并且获取到同步状态）
#### &emsp;&emsp;&emsp;&emsp;&emsp;b）获取同步队列的首节点，将当前线程构造成一个新的节点（通过addConditionWaiter()方法）并尾插入等待队列，然后释放同步状态，唤醒同步队列中的后继节点。
#### &emsp;&emsp;&emsp;	②signal()实现原理
#### &emsp;&emsp;&emsp;&emsp;&emsp;将等待队列的第一个结点，插入同步队列的尾部（唤醒等待时间最长的线程）
### 	&emsp;&emsp;3、Condition机制的应用
#### &emsp;&emsp;&emsp;代替wait-notify（Object），这是操作系统中的方法，不易于控制。