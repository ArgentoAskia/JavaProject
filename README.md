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
- 图形相关：比较杂的分类，任何和绘图相关的、操作系统操作相关的都算，如字体设置、图形绘画（矩形、圆等）、图像处理、显示模式（分辨率、全屏等）、底层的图形工具类、系统任务栏操作、系统桌面操作、桌面快捷方式、鼠标图标样式等等。

## 窗口图形基本知识

> 这个小节主要是为了给没有过多窗口图形基础知识的同学说明的，如果有这方面知识的同学可以跳过！

没有做过这些窗口程序的同学可能会对窗口的出现绘制等比较困惑，实际上窗口的显示可以比喻成是画图。平常使用`Windows`系统的会点开很多窗口，这些窗口都是`windows`系统直接画出来的（就是字面意思），通过这种画图的方式，绘画显示器上的任何东西，如窗口，按钮、编辑框等组件、图像甚至是屏幕上的字，这些都是通过屏幕上的像素画出来的，你甚至可以把屏幕理解成一块画布，看着操作系统在画布上尽情挥洒笔墨。

一般操作系统会提供一些基本的底层绘图`API`，在`Java`里面也有一个和绘图有关的类，叫`Graphics`（后面期为了提供更好的绘画效果，产生了`Graphics2D`），这些类能够帮助你在窗口区域绘画任何的图形，如果你不怕折腾，甚至还可以直接使用这种绘图类来画窗口。

### 窗口激活（窗口状态）

获取焦点

### 窗口重叠与重绘

### 窗口事件



## AWT

作为`Java`中经典的图形化界面，`AWT`当之无愧。`AWT`，全称`Abstract Window Toolkit`，可以说是整个`Java`图形化体系的底层建筑，即便如`Swing`这种提供了非常丰富组件和强大功能的`Java`图形库，其事件等一些机制仍然是基于`AWT`。

`AWT`本身也提供一些组件库，但是数量非常有限，本身底层采用`C`语言编写，因此现阶段很少`Java`图形化是采用`AWT`组件的，大多数还是采用`Swing`或者更加好看的`JavaFX`。

使用`AWT`创建的图形界面应用和所在的运行平台有相同的界面风格，比如在`Windows`操作系统上，它就表现出`Windows`风格。在`UNIX`操作系统上，它就表现出`UNIX`风格。`Sun`希望采用这种方式来实现`Write Once, Run Anywhere`的目标。

### 第一个AWT的窗口

在`AWT`中，有一个类叫`Frame`（窗体），代表一个窗口，可以直接使用这个类来创建一个空白的窗口，一般的创建风格有两种：

1. 直接继承`Frame`类（推荐）：参考代码在`src/main/java/cn/argentoaskia/awt/AWTEmptyWindows1.java`
2. 创建一个类将`Frame`类对象变成私有属性：参考代码在`src/main/java/cn/argentoaskia/awt/AWTEmptyWindows2.java`

这两种创建方法优先推荐第一种，因为窗口的创建习惯来讲，第一种方法更加符合现实的习惯和设计，因为本质上你要创建窗口嘛，所以继承`Frame`让自己编写的类变成窗口（最简单的道理，你要创建的是窗口，`Frame`本身就代表窗口，那你自己写的类里面也应该能够具备`Frame`的方法吧）但这个还是要看个人习惯而言，因此确实习惯了第二种的也不是不行，实在不行的话大不了做一层聚合。

但无论哪一种创建风格，要想运行程序让窗口显示在屏幕上，就肯定要经过这些步骤：

1. 上面的类创建风格二选一
2. 设置窗口矩形大小（`bounds`），靠`JFrame`中的`setBounds()`
3. 添加关闭按钮事件处理，具体是重写`WindowListener`接口中的`windowClosing`方法
4. 设置窗口可视，也就是`Visible`属性，靠`JFrame`中的`setVisible()`方法

上面的步骤可以参考`demo`：`AWTEmptyWindows1`和`AWTEmptyWindows2`，这里稍微解释一下为什么这样做，首先，`frame`对象在被创建出来的时候，默认的窗口矩形是`x=0，y=0，width=0，height=0`，在这个设置下，所有的窗口长度，宽度因为都是`0`，所以运行程序的时候不会窗口对象会正常创建，但不会在屏幕上显示出来（这个很好理解吧，宽度和高度都是`0`还显示个得得），不信的话你可以将两个`demo`中的`setBound`方法去掉再运行，是不是啥都没显示，但是程序照常进行呀。ヾ(≧▽≦*)o

然后是添加关闭按钮事件处理器，默认情况下，`frame`窗体右上角会有三个按钮：最大化、最小化、关闭，其中最大化、最小化是能够用的，但是关闭按钮却没反应（~~奇怪的设计，后面Swing的JFrame就没那么多屁事~~），因此需要手动指定点击关闭按钮之后的操作，这就轮到组件事件出场啦，在窗口里面，控制窗口这些最大化、最小化、关闭等事件的处理器是`windowListener`接口，但是这个接口里面很多方法，如果直接实现这个接口的话，你要实现里面的所有方法，可能会比较繁琐，因为我们指定关闭按钮的事件就够了，最大化最小化按钮关我屁事，所以可以采用`WindowAdapter`类，这个类简单实现了`windowListener`接口，你可以选择你要指定的事件来重写就`ok`。

最后是`Visible`属性，默认情况下当`frame`实例创建出来之后，也不会直接显示在屏幕上，因为默认窗口实例是不可见的，可以理解成隐藏在屏幕上了（实际上在屏幕，但是你看不到）。早期`AWT`提供了`show()`方法，来让窗口变得课时，但是这个方法在`JDK1.5`版本之后被`setVisible()`方法给代替了。

第一个`demo`中可能有很多方法如果不太熟悉的话，可以先写着，这些方法在后面组件的介绍中会跟大家说明。

### 容器&组件

非常遗憾的，因为历史原因，`AWT`并没有提供太多的客户类组件。

所有的组件大概能够分五类：

- 容器类组件：用于容纳其他组件的组件，`AWT`中这类组件有3种：
  - `Frame`：窗口
  - `Panel`：面板
  - `ScrollPane`：滚动面板
- 客户类组件：用户提供操作的组件，`AWT`中这类组件大概有9种：
  - `Button`：按钮
  - `Canvas`：画布
  - `Checkbox`：选择框
  - `Choice`：下拉列表框
  - `Label`：标签
  - `List`：列表框
  - `Scrollbar`：滑块条
  - `TextArea`：文本域
  - `TextField`：文本框
- 菜单类组件：菜单相关一类组件，`AWT`中这类组件大概6种：
  - `Menu`：基本菜单栏，内部可以容纳`MenuItem`（菜单项）
  - `MenuItem`：基本菜单项
  - `MenuBar`：菜单工具条
  - `MenuShortcut`：菜单快捷方式
  - `PopupMenu`：右键弹出式菜单
  - `CheckboxMenuItem`：选择式菜单项
- 对话框类组件：提供一些通用的对话框，`AWT`中这类组件大概有2种：
  - `Dialog`：通用对话框
  - `FileDialog`：文件选择对话框

#### 容器类

##### Frame

`AWT`中的容器，主要由`Frame`（窗口）和`Panel`（面板）组成，如果需要滚动的面板，可以采用类似于`ScrollPane`。

`Frame`代表一个窗口，在`Java`中一个简单的`Frame`展示如下，在这个窗口中，一般会被分成几大块：

![image-20230110160140921](README/image-20230110160140921.png)

一般我们的组件都是在窗口客户区上进行绘画。顶部的标题、图标和最大化最小化关闭按钮共同组成了装饰区（`frame decorations`，不知道翻译成装饰区对不对）。

窗口本身具有很多属性，可以通过相关`Setter`方法来设置，这些属性有：

- `alwaysOnTop`：是否总是显示在前端
- `autoRequestFocus`：当窗口被激活的时候是否自动获取焦点
- `Background`：背景色
- `Bounds`：bounds，相当于窗口矩形，指定x坐标、y坐标、宽度、高度
- `ComponentOrientation`：未知
- `ComponentZOrder`：窗口内组件刷新顺序
- `Cursor`：窗口内鼠标样式
- `DropTarget`：拖放目标，只有窗口设置了允许拖放的时候才有效
- `Enabled`：是否可用
- `ExtendedState`：窗口状态，如最大化、最小化等等
- `Focusable`：窗口是否可以获取焦点，设置这个值，在焦点轮切的时候可以被切换上
- `FocusableWindowState`：窗口是否可以获取焦点，这个方法会根据窗口状态来影响判断
- `FocusTraversalKeys`：焦点切换按键
- `FocusTraversalKeysEnabled`：是否开启焦点切换功能
- `FocusTraversalPolicy`：焦点切换的方式
- `FocusTraversalPolicyProvider`：是否开启设置焦点切换的方式
- `Font`：字体
- `Foreground`：前景颜色
- `IconImage | IconImages`：图标图像（可以设置多个）
- `IgnoreRepaint`：是否忽略窗口重绘
- `Layout`：窗口布局
- `Locale`：locale，多语言支持的时候需要设置这个
- `Location`：窗口位置
- `LocationByPlatform`：是否由所在的平台来决定窗口位置
- `LocationRelativeTo`：窗口的父窗口
- `MaximizedBounds`：窗口的最大边界
- `MaximumSize`：窗口的最大大小
- `MenuBar`：窗口菜单栏
- `MinimumSize`：窗口最小大小
- `ModalExclusionType`：窗口模态排除类型，一般情况下当一个窗口弹出一些信息框，那么那个窗口将会被阻塞，动不了，设置了这个属性，则窗口可能将不会被阻塞。
- `Name`：组件名称，注意区别与setTitle()，setTitle是设置标题，而这个是在相当于为窗口设置一个名字，一般用于标记窗口|组件。
- `Opacity`：窗口透明度，值在0.0f-1.0f之间，需要注意，设置透明度需要先把窗口装饰区去掉，也就是setUndecorated(true)
- `PreferredSize`：组件更加偏向的大小，一般在布局的设置中会采用这个，注意设置组件的大小不一定就会起作用，AWT会计算组件的大小以更好适应布局情况
- `Resizable`：窗口是否可拉伸
- `Shape`：窗口的形状
- `Size`：窗口大小
- `State`：窗口状态
- `Title`：窗口标题
- `Type`：窗口类型
- `Undecorated`：是否去除装饰栏
- `Visible`：可视

##### Panel

##### ScrollPane

#### 组件类

##### Button

##### Canvas

##### checkbox

##### choice

##### label

##### list

##### scrollbar

##### textarea

##### textfield

#### 对话框类













### 窗口布局

一般我们的窗口上都有很多花里呼哨的组件，像按钮、编辑框这些，常规的桌面应用就是靠这些给堆积起来的，而且一般组件量都不少，因此如何管理这些组件就成了一个问题。

`AWT`中的所有组件可以参考这个`Demo`：`src/main/java/cn/argentoaskia/awt/widgets/component/AWTComponents.java`，里面展示了所有`AWT`可用的基本组件库。

在上面的的组件`Demo`中，你可以看到大量的`setBounds()`、`setSize()`、`setPreferredSize()`等设置组件大小的方法。这些方法规定了组件的定位。但是在这个窗口被拉伸或缩小的时候（因为设置了`setResizable(false);`，所以如果想能够拉伸窗口的话，需要去掉这一行），你会发现组件不会跟着窗口的拉伸缩小大小的改变而改变。因为我们并没有规定这些组件如何被拉伸或缩小。在传统的`windows`图形化编程中，需要开发者自己去定义每一个组件的拉伸缩小，非常麻烦。在`Java`图形化中，你仍然可以使用这种方法来实现，但`Java`提供了**窗口布局管理器**的东西来处理这种组件的拉伸和缩小，大大减少了布局上的麻烦。

在`AWT`中，共有`5`种常见的布局：

- `BorderLayout`：
- `CardLayout`：
- `FlowLayout`：
- `GridLayout`：
- `GripBagLayout`：

#### BorderLayout

#### CardLayout

#### FlowLayout

#### GridLayout

#### GripBagLayout



### 事件处理

### AWT-Graphics















## Swing



## 图形化自定义

### AWT和Swing的组件体系

### 布局管理器体系

### 感官设计

### AWT事件模型



