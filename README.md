# Java图形化

首先`Java`本身图形化库比较丰富的，`Java`本身有三代图形化库：

- `AWT`
- `Swing`
- `JavaFX`

其中`AWT`在`Java`中更多的是充当底层事件基础的角色，一般谈到`Java`图形化时，我们更原因称`AWT`时窗口工具包的底层机制，`Swing`则是这底层机制的上层建筑。而`JavaFX`则是`2007`年为了打败`flash`的地位，`Sun`公司的一次尝试，它拥有更加现代化的`UI`感官，组件也更为丰富。本文章主要介绍上面这三种图形化库，文章比较长，可以挑着来看。

`Java`的图形化除了官网自带的这些（~~现在JavaFX也被剥离开交给社区维护了~~），也有很多第三方非常优秀的库，如`eclipse`的`SWT`、`JGoodies`这些，这些第三方库会在额外的章节里面给大家介绍。

要想学习`Java`的图形化，大概可以从下面这几个方向来搞：

- 窗口布局：也就是所谓的`layout`，决定组件的排列和对齐拉伸方式等。
- 容器和组件：最核心的内容，窗口组件如按钮、文本框、选择框、窗口等等。
- 事件处理：最核心的内容，决定了组件的行为，如当按下按钮时出发一些操作。
- 窗口感官：决定窗口和组件显示的`UI`形式，也可以叫窗口皮肤。
- 图形相关：比较杂的分类，任何和绘图相关的都算，如字体设置、图形绘画（矩形、圆等）、图像处理、显示模式（分辨率、全屏等）、底层的图形工具类等。

## 窗口图形基本知识

> 这个小节主要是为了给没有过多窗口图形基础知识的同学说明的，如果有这方面知识的同学可以跳过！

没有做过这些窗口程序的同学可能会对窗口的出现绘制等比较困惑，实际上窗口的显示可以比喻成是画图。平常使用`Windows`系统的会点开很多窗口，这些窗口都是`windows`系统直接画出来的（就是字面意思），通过这种画图的方式，绘画显示器上的任何东西，如窗口，按钮、编辑框等组件、图像甚至是屏幕上的字，这些都是通过屏幕上的像素画出来的，你甚至可以把屏幕理解成一块画布，看着操作系统在画布上尽情挥洒笔墨。

一般操作系统会提供一些基本的底层绘图`API`，在`Java`里面也有一个和绘图有关的类，叫`Graphics`（后面期为了提供更好的绘画效果，产生了`Graphics2D`），这些类能够帮助你在窗口区域绘画任何的图形，如果你不怕折腾，甚至还可以直接使用这种绘图类来画窗口。

## AWT

作为`Java`中经典的图形化界面，`AWT`当之无愧。`AWT`，全称`Abstract Window Toolkit`，可以说是整个`Java`图形化体系的底层建筑，即便如`Swing`这种提供了非常丰富组件和强大功能的`Java`图形库，其事件等一些机制仍然是基于`AWT`。

`AWT`本身也提供一些组件库，但是数量非常有限，本身底层采用`C`语言编写，因此现阶段很少`Java`图形化是采用`AWT`组件的，大多数还是采用`Swing`或者更加好看的`JavaFX`。

使用`AWT`创建的图形界面应用和所在的运行平台有相同的界面风格，比如在`Windows`操作系统上，它就表现出`Windows`风格。在`UNIX`操作系统上，它就表现出`UNIX`风格。`Sun`希望采用这种方式来实现`Write Once, Run Anywhere`的目标。

### 第一个AWT的窗口

在`AWT`中，有一个类叫`Frame`（窗体），代表一个窗口，可以直接使用这个类来创建一个空白的窗口，一般的创建方法有两种：

1. 直接继承`Frame`类。（推荐）
2. 创建一个类将`Frame`类对象变成私有属性。



### 窗口布局



















## 图形化体系

- 容器：Frame、Panel、ScrollPanel
- 组件
- 布局
- 事件

## Container

### 窗体（Frame）

### 面板（Panel）

## Component

