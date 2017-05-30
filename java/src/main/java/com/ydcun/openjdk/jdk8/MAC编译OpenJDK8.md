# MAC编译OpenJDK8

[TOC]



## 当前环境
1. mac 10.12.5
2. 系统已经安装了JDK1.8.121

## 编译OpenJDK8大致流程
1. 下载OpenJDK8源码
2. 安装依赖软件
3. 进行编译
4. 编译中遇到的问题及解决方案

## 下载OpenJDK8源码
- 源码可以在官网上直接下载源码地址：[OpenJdk8主页](http://download.java.net/openjdk/jdk8/)，
- 本文是直接在版本控制软件中直接牵出：[OpenJDK8 Mercurial地址](http://hg.openjdk.java.net/jdk8/jdk8)
- OpenJDK使用的Mercurial版本控制软件，所以需要安装[Mercurial](https://www.mercurial-scm.org/wiki/Download)
- 安装Mercurial可视化软件[SourceTree](https://www.sourcetreeapp.com/)【可选】
- 通过SourceTree将OpenJDK8牵出到本地目录
- 命令行方式：
```
hg clone http://hg.openjdk.java.net/jdk8/jdk8 jdk8
cd jkd8
bash ./get_source.sh
```

## 安装依赖软件
- Xcode：直接在APP Store中搜索Xcode安装文件比较大
- Xcode-select：安装命令：**xcode-select –install **
- 进行x11链接
```
	sudo ln -s /usr/X11/include/X11 /usr/include/X11 
    or
    sudo ln -s /usr/local/X11/include/X11 /usr/include/X11 
```

- XQuartz：[下载地址](https://xquartz.en.softonic.com/mac)
- 安装brew：命令行安装，安装完后先进行更新，更新命令：**brew update**
```sh
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
- 安装llvm，安装命令：**brew install llvm**
- 安装freetype，安装命令：**brew freetype**
- link llvm-gcc和llvm-g++，由于xcode目录下已经没有llvm-gcc和llvm-g++程序，而build jdk的时候会用到，所以需要link过去
```
sudo ln -s /usr/bin/llvm-g++ /Applications/Xcode.app/Contents/Developer/usr/bin/llvm-g++  
sudo ln -s /usr/bin/llvm-gcc /Applications/Xcode.app/Contents/Developer/usr/bin/llvm-gcc 
```

## 进行编译
1. cd到源码根目录系jdk8
2. ./configure  如果找不freetype可以加上--with-freetype-include=/usr/local/include/freetype2 --with-freetype-lib=/usr/local/lib/
```
    ydcun-prodeMBP:jdk8 ydcun-pro$ ./configure --with-freetype-include=/usr/local/include/freetype2 --with-freetype-lib=/usr/local/lib/
    Configure source code has been updated, checking time stamps
    Running generated-configure.sh
    configure: Configuration created at Tue May 30 08:08:13 CST 2017.
    configure: configure script generated at timestamp 1389186094.
    checking for basename... /usr/bin/basename
    checking for bash... /bin/bash
    checking for cat... /bin/cat
    checking for chmod... /bin/chmod
    checking for cmp... /usr/bin/cmp
    checking for comm... /usr/bin/comm
    省略……
    ====================================================
    A new configuration has been successfully created in
    /Users/ydcun-pro/workspace/openjdk/jdk8/build/macosx-x86_64-normal-server-release
    using configure arguments '--with-freetype-include=/usr/local/include/freetype2 --with-freetype-lib=/usr/local/lib/'.

    Configuration summary:
    * Debug level:    release
    * JDK variant:    normal
    * JVM variants:   server
    * OpenJDK target: OS: macosx, CPU architecture: x86, address length: 64

    Tools summary:
    * Boot JDK:       java version "1.8.0_121" Java(TM) SE Runtime Environment (build 1.8.0_121-b13) Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)  (at /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home)
    * C Compiler:      version  (at /usr/bin/gcc)
    * C++ Compiler:    version  (at /usr/bin/g++)

    Build performance summary:
    * Cores to use:   2
    * Memory limit:   16384 MB
    * ccache status:  not installed (consider installing)
```
3. 设置环境变量：**./bash_profile**   
中文注释删掉会有乱码，修改后重新加载： **source ~/.bash_profile**
```
	# 设定语言选项，必须设置
    export LANG=C
    # Mac平台，C编译器不再是GCC，是clang
    export CC=clang
    # 跳过clang的一些严格的语法检查，不然会将N多的警告作为Error
    export COMPILER_WARNINGS_FATAL=false
    # 链接时使用的参数
    export LFLAGS='-Xlinker -lstdc++'
    # 是否使用clang
    export USE_CLANG=true
    # 使用64位数据模型
    export LP64=1
    # 告诉编译平台是64位，不然会按32位来编译
    export ARCH_DATA_MODEL=64
    # 允许自动下载依赖
    export ALLOW_DOWNLOADS=true
    # 并行编译的线程数，编译时间长，为了不影响其他工作，我选择为2
    export HOTSPOT_BUILD_JOBS=2
    export ALT_PARALLEL_COMPILE_JOBS=2
    # 是否跳过与先前版本的比较
    export SKIP_COMPARE_IMAGES=true
    # 是否使用预编译头文件，加快编译速度
    export USE_PRECOMPILED_HEADER=true
    # 是否使用增量编译
    export INCREMENTAL_BUILD=true
    # 编译内容
    export BUILD_LANGTOOLS=true
    export BUILD_JAXP=true
    export BUILD_JAXWS=true
    export BUILD_CORBA=true
    export BUILD_HOTSPOT=true
    export BUILD_JDK=true
    # 编译版本
    export SKIP_DEBUG_BUILD=true
    export SKIP_FASTDEBUG_BUILD=false
    export DEBUG_NAME=debug
    # 避开javaws和浏览器Java插件之类的部分的build
    export BUILD_DEPLOY=false
    export BUILD_INSTALL=false
```
3. make all 
	![ok_1](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/ok_1.png)
    
	![ok_2](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/ok_2.png)
    

4. 遇到的问题

- 问题1
```
问题1：configure: error: GCC compiler is required
解决方法：
jdk8/common/autoconf/generated-configure.sh文件中
注释两处代码：第20061，21640行
#as_fn_error $? "GCC compiler is required. Try setting --with-tools-dir." "$LINENO" 5
```
	![问题1_1](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/1_1.png)

	![问题1_2](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/1_2.png)


- 问题2

```	
问题2: Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:367:27: error: friend declaration specifying a default argument must be a definition
      inline friend relocInfo prefix_relocInfo(int datalen = 0);
                              ^
    /Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:462:18: error: friend declaration specifying a default argument must be the only declaration
    inline relocInfo prefix_relocInfo(int datalen) {
                     ^
    /Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:367:27: note: previous declaration is here
      inline friend relocInfo prefix_relocInfo(int datalen = 0);
                              ^
    /Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:464:59: error: 'RAW_BITS' is a protected member of 'relocInfo'
      return relocInfo(relocInfo::data_prefix_tag, relocInfo::RAW_BITS, relocInfo::datalen_tag | datalen);
                                                              ^
    /Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:272:23: note: declared protected here
      enum RawBitsToken { RAW_BITS };
                          ^
    /Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:464:10: error: calling a protected constructor of class 'relocInfo'
      return relocInfo(relocInfo::data_prefix_tag, relocInfo::RAW_BITS, relocInfo::datalen_tag | datalen);
             ^
    /Users/ydcun-pro/workspace/openjdk/jdk8/hotspot/src/share/vm/code/relocInfo.hpp:273:3: note: declared protected here
      relocInfo(relocType type, RawBitsToken ignore, int bits)
      ^
    2 warnings and 4 errors generated.
    make[8]: *** [precompiled.hpp.pch] Error 1
    make[7]: *** [the_vm] Error 2
    make[6]: *** [product] Error 2
    make[5]: *** [generic_build2] Error 2
    make[4]: *** [product] Error 2
    make[3]: *** [all_product_universal] Error 2
    make[2]: *** [universal_product] Error 2
    make[1]: *** [/Users/ydcun-pro/workspace/openjdk/jdk8/build/macosx-x86_64-normal-server-release/hotspot/_hotspot.timestamp] Error 2
    make: *** [hotspot-only] Error 2
    
   解决方法：vim hotspot/src/share/vm/code/relocInfo.hpp
    367行：
    原：inline friend relocInfo prefix_relocInfo(int datalen = 0);
    修改后：inline friend relocInfo prefix_relocInfo(int datalen);
    462行：
    原：inline relocInfo prefix_relocInfo(int datalen) {
    修改后：inline relocInfo prefix_relocInfo(int datalen = 0) {
```
![问题2_1](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/2_1.png)

![问题2_2](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/2_2.png)


- 问题3
```
问题3:
Running nasgen
Exception in thread "main" java.lang.VerifyError: class jdk.nashorn.internal.objects.ScriptFunctionImpl overrides final method setPrototype.(Ljava/lang/Object;)V
at java.lang.ClassLoader.defineClass1(Native Method)
at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
at java.net.URLClassLoader.defineClass(URLClassLoader.java:467)
at java.net.URLClassLoader.access$100(URLClassLoader.java:73)
at java.net.URLClassLoader$1.run(URLClassLoader.java:368)
at java.net.URLClassLoader$1.run(URLClassLoader.java:362)
at java.security.AccessController.doPrivileged(Native Method)
at java.net.URLClassLoader.findClass(URLClassLoader.java:361)
at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
at jdk.nashorn.internal.tools.nasgen.StringConstants.<clinit>(StringConstants.java:85)
at jdk.nashorn.internal.tools.nasgen.ScriptClassInstrumentor$2.visitMethodInsn(ScriptClassInstrumentor.java:157)
at jdk.internal.org.objectweb.asm.MethodVisitor.visitMethodInsn(MethodVisitor.java:509)
at jdk.internal.org.objectweb.asm.ClassReader.readCode(ClassReader.java:1445)
at jdk.internal.org.objectweb.asm.ClassReader.readMethod(ClassReader.java:1046)
at jdk.internal.org.objectweb.asm.ClassReader.accept(ClassReader.java:722)
at jdk.internal.org.objectweb.asm.ClassReader.accept(ClassReader.java:535)
at jdk.nashorn.internal.tools.nasgen.Main.process(Main.java:121)
at jdk.nashorn.internal.tools.nasgen.Main.processAll(Main.java:88)
at jdk.nashorn.internal.tools.nasgen.Main.main(Main.java:62)
make[1]: *** [/Users/ydcun-pro/workspace/openjdk/jdk8/build/macosx-x86_64-normal-server-release/nashorn/classes/_the.nasgen.run] Error 1
make: *** [nashorn-only] Error 2

修改：vim nashorn/make/BuildNashorn.gmk 
80行原来 -cp 修改为：-Xbootclasspath/p
如图：
``` 
![问题3_1](https://raw.githubusercontent.com/ydcun/Java/master/java/src/main/java/com/ydcun/openjdk/jdk8/image/3_1.png)
    
    
## 参考文献
> [Mac OSX 10.9 上build openjdk8和openjdk7](http://yueyemaitian.iteye.com/blog/2038304)
> [Mac OS编译OpenJDK8](http://blog.csdn.net/lizhengjava/article/details/60138890)
> [Mac os下编译openJDK 7](http://blog.csdn.net/j754379117/article/details/53695426)
> [Mac编译OpenJDK7(8)和Eclipse调试Hotspot](http://www.itnose.net/detail/6531887.html)
> [编译openjdk过程中遇到的错误](https://xiexianbin.cn/java/2017/03/15/OpenJDK-compile-error)


























