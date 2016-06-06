[ TOC ]
# Java
  Maven项目
  * maven3.3.3
  * tomcat8.0
  * jdk1.8
  * web2.5

##com.ydcun.java 下记录java学习总结案例

###generics 泛型使用
  1. 通过泛型减少类型强制转换
  2. 泛型是javac编译器使用的 编译器编译后会去掉泛型信息。可以通过反射来绕过语法检查
  3. 通过问号通配符接受任意类型
  4. 用Entry遍历Map
  5. 获取类方法参数的类型和泛型类型
###classloader类加载器
  1.  三个主要的类加载器：	BootStrap, 不是java类是c++写的   jre/lib/rt.jar
  					ExtClassLoader, 			   jre/lib/ext/*.jar
  					AppClassLoader				   classpath指定的所有jar或目录
  
  2.  自己写的类加载器必须继承ClassLoader  并指定父类loader
      为了防止出现多份字节码，类加载器的委托机制优先让父类加载器来加载。如果自己实现一个类加载器必须有特殊的方法
 
  3.  如果将自动编译的class文件删除就会在自定义的类加载器下面找，类加载的委托机制
  		实验步骤：1. 先将ClassLoaderAttachment.class进行加密放到resources目录下
  			        2. 将加密的文件替换原来的class文件
  			        3. 通过自定义类加载加载，发现出错，原因是父类会加载自己的class文件而class已经加密了
         			  4. 删除自动生成目录下的class文件，在运行正常加载
###proxy代理
  1. JVM　创建代理类
      1.首先通过字节码获取构造方法和方法列表
      2.通过字节码生成对象
  2. InvocationHandler 中的invoke方法有三个参数：代理对象 ，方法名，方法参数
  3. aopframework基本实现。BeanFactory.java 根据配置文件获取是否有代理的对象。在执行方法的前后添加要执行的代码
###enums枚举
  1. 先用普通的类来实现enum 要用大量的if else 在方法中
  2. 将方法定义成抽象的，在子类中重写抽象方法
  3. 定义枚举类型，很容易获取枚举的名字，排行（从0开始）,从名字到对象（valueOf）,获取枚举中所有values
  4. 枚举添加方法
    1. 方法要在枚举类型之后
    2. 构造方法不能是公有的
    3. 在构造方法中参数不能写 WeekDay3.valueOf(name);因为类都还没有初始化完不能调用只能先记住string在进行转换
